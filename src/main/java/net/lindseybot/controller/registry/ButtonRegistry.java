package net.lindseybot.controller.registry;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.lindseybot.controller.ButtonMeta;
import net.lindseybot.events.ButtonMetaEvent;
import net.lindseybot.properties.ControllerProperties;
import net.lindseybot.services.EventService;
import net.notfab.eventti.EventHandler;
import net.notfab.eventti.Listener;
import okhttp3.*;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.*;

@Slf4j
public class ButtonRegistry implements Listener {

    private final ControllerProperties properties;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;
    private final Map<String, ButtonMeta> buttons = new HashMap<>();
    private final Set<String> selfButtons = new HashSet<>();

    public ButtonRegistry(ControllerProperties properties, EventService events) {
        this.properties = properties;
        this.okHttpClient = new OkHttpClient();
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
        Request request = new Request.Builder()
                .url(this.properties.getUrl() + "/buttons")
                .addHeader("Authorization", "Bearer " + this.properties.getToken())
                .addHeader("X-Worker-Id", this.properties.getId())
                .get().build();
        try {
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
    public void register(ButtonMeta meta) {
        try {
            RequestBody body = RequestBody
                    .create(MediaType.parse("application/json"), this.objectMapper.writeValueAsString(meta));
            Request request = new Request.Builder()
                    .url(this.properties.getUrl() + "/buttons")
                    .addHeader("Authorization", "Bearer " + this.properties.getToken())
                    .addHeader("X-Worker-Id", this.properties.getId())
                    .post(body).build();
            Response response = this.okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Invalid response code " + response.code());
            }
            this.selfButtons.add(meta.getMethod());
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
                    .addHeader("Authorization", "Bearer " + this.properties.getToken())
                    .addHeader("X-Worker-Id", this.properties.getId())
                    .delete().build();
            Response response = this.okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Invalid response code " + response.code());
            }
            this.selfButtons.remove(name);
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to delete button", ex);
        }
    }

    /**
     * Unregisters all buttons.
     */
    @PreDestroy
    public void shutdown() {
        this.selfButtons.forEach(this::unregister);
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

}
