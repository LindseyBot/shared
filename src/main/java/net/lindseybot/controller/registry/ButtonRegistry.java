package net.lindseybot.controller.registry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import net.lindseybot.controller.ButtonMeta;
import net.lindseybot.discord.bridge.InteractionResponse;
import net.lindseybot.events.ButtonMetaEvent;
import net.lindseybot.framework.ButtonRequest;
import net.lindseybot.models.RedisConsumer;
import net.lindseybot.properties.ControllerProperties;
import net.lindseybot.services.EventService;
import net.lindseybot.services.MessagingService;
import net.notfab.eventti.EventHandler;
import net.notfab.eventti.Listener;
import okhttp3.*;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

@Slf4j
public class ButtonRegistry implements Listener, Interceptor, RedisConsumer<ButtonRequest> {

    private final ControllerProperties properties;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;
    private final Map<String, ButtonMeta> buttons = new HashMap<>();
    private final Set<String> selfButtons = new HashSet<>();
    private final Map<String, Function<ButtonRequest, InteractionResponse>> listeners = new HashMap<>();

    private final MessagingService messaging;

    public ButtonRegistry(ControllerProperties properties, EventService events, MessagingService messaging) {
        this.properties = properties;
        this.messaging = messaging;
        this.okHttpClient = new OkHttpClient.Builder().addInterceptor(this).build();
        this.objectMapper = new ObjectMapper();
        events.addListener(this);
        this.load();
    }

    @EventHandler
    public void onEvent(ButtonMetaEvent event) {
        if (event.isCreate()) {
            this.buttons.put(event.getModel().getMethod(), event.getModel());
            log.info("Registered 1 button from event.");
        } else {
            this.buttons.remove(event.getModel().getMethod());
            log.info("Removed 1 button from event.");
        }
    }

    /**
     * Loads all buttons from the main registry.
     */
    public void load() {
        try {
            Request request = new Request.Builder()
                    .url(this.properties.getUrl() + "/buttons")
                    .get().build();
            Response response = this.okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Invalid response code " + response.code());
            }
            try (ResponseBody body = response.body()) {
                if (body == null) {
                    throw new IOException("Missing body");
                }
                ButtonMeta[] buttonList = objectMapper.readValue(body.bytes(), ButtonMeta[].class);
                for (ButtonMeta meta : buttonList) {
                    ButtonMetaEvent event = new ButtonMetaEvent();
                    event.setModel(meta);
                    event.setCreate(true);
                    this.onEvent(event);
                }
                log.info("Loaded " + buttonList.length + " buttons.");
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load buttons", ex);
        }
    }

    /**
     * Registers a button to the registry.
     *
     * @param meta Button metadata.
     */
    public void register(ButtonMeta meta, Function<ButtonRequest, InteractionResponse> listener) {
        try {
            RequestBody body = RequestBody
                    .create(MediaType.parse("application/json"), this.objectMapper.writeValueAsString(meta));
            Request request = new Request.Builder()
                    .url(this.properties.getUrl() + "/buttons")
                    .post(body).build();
            Response response = this.okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Invalid response code " + response.code());
            }
            this.selfButtons.add(meta.getMethod());
            this.listeners.put(meta.getMethod(), listener);
            this.messaging.addConsumer("Lindsey:Buttons:" + meta.getMethod(), this);
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to create button", ex);
        }
    }

    /**
     * Unregisters self from the button registry.
     *
     * @param name Button's method.
     */
    public void unregister(String name) {
        try {
            Request request = new Request.Builder()
                    .url(this.properties.getUrl() + "/buttons/" + name)
                    .delete().build();
            Response response = this.okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Invalid response code " + response.code());
            }
            this.selfButtons.remove(name);
            this.listeners.remove(name);
            this.messaging.remConsumer("Lindsey:Buttons:" + name, this);
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to delete button", ex);
        }
    }

    /**
     * Unregisters all buttons.
     */
    @PreDestroy
    public void shutdown() {
        new ArrayList<>(this.selfButtons).forEach(this::unregister);
    }

    /**
     * Checks if a button exists by a given name.
     *
     * @param methodName Name of the button.
     * @return True if a button exists.
     */
    public boolean exists(String methodName) {
        return this.buttons.containsKey(methodName);
    }

    /**
     * Used for getting a list of all buttons currently loaded, may be empty if load was not called.
     *
     * @return List of buttons.
     */
    public List<ButtonMeta> getAll() {
        return new ArrayList<>(this.buttons.values());
    }

    /**
     * Returns a button's metadata.
     *
     * @param name Name of the button.
     * @return Metadata.
     */
    public ButtonMeta get(String name) {
        return this.buttons.get(name);
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + this.properties.getToken())
                .addHeader("X-Worker-Id", this.properties.getId())
                .build();
        return chain.proceed(request);
    }

    @Override
    public Class<ButtonRequest> getTClass() {
        return ButtonRequest.class;
    }

    @Override
    public void onMessage(ButtonRequest message) {
        Function<ButtonRequest, InteractionResponse> fn = this.listeners.get(message.getId());
        if (fn == null) {
            log.warn("Received a button request with unknown id " + message.getId());
            return;
        }
        try {
            this.messaging.enqueue("Lindsey:Interactions", fn.apply(message));
        } catch (Exception ex) {
            log.error("Failed to process button click", ex);
        }
    }

}
