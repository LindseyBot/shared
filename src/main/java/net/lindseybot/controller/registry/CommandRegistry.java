package net.lindseybot.controller.registry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import net.lindseybot.controller.CommandMeta;
import net.lindseybot.discord.bridge.InteractionResponse;
import net.lindseybot.events.CommandMetaEvent;
import net.lindseybot.framework.CommandRequest;
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
public class CommandRegistry implements Listener, Interceptor, RedisConsumer<CommandRequest> {

    private final ControllerProperties properties;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;
    private final Map<String, CommandMeta> commands = new HashMap<>();
    private final Set<String> selfCommands = new HashSet<>();
    private final Map<String, Function<CommandRequest, InteractionResponse>> listeners = new HashMap<>();

    private final MessagingService messaging;

    public CommandRegistry(ControllerProperties properties, EventService events, MessagingService messaging) {
        this.properties = properties;
        this.messaging = messaging;
        this.okHttpClient = new OkHttpClient.Builder().addInterceptor(this).build();
        this.objectMapper = new ObjectMapper();
        events.addListener(this);
        this.load();
    }

    @EventHandler
    public void onEvent(CommandMetaEvent event) {
        if (event.isCreate()) {
            this.commands.put(event.getModel().getName(), event.getModel());
            log.info("Registered 1 command from event.");
        } else {
            this.commands.remove(event.getModel().getName());
            log.info("Removed 1 command from event.");
        }
    }

    /**
     * Loads all commands from the main registry.
     */
    public void load() {
        try {
            Request request = new Request.Builder()
                    .url(this.properties.getUrl() + "/commands")
                    .get().build();
            Response response = this.okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Invalid response code " + response.code());
            }
            try (ResponseBody body = response.body()) {
                if (body == null) {
                    throw new IOException("Missing body");
                }
                CommandMeta[] commandList = objectMapper.readValue(body.bytes(), CommandMeta[].class);
                for (CommandMeta meta : commandList) {
                    CommandMetaEvent event = new CommandMetaEvent();
                    event.setModel(meta);
                    event.setCreate(true);
                    this.onEvent(event);
                }
                log.info("Loaded " + commandList.length + " commands.");
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load commands", ex);
        }
    }

    /**
     * Registers a command to the registry.
     *
     * @param meta     Command metadata.
     * @param path     Command path.
     * @param listener The listener that will receive the requests.
     */
    public void register(CommandMeta meta, String path, Function<CommandRequest, InteractionResponse> listener) {
        if (!this.commands.containsKey(meta.getName())) {
            try {
                RequestBody body = RequestBody
                        .create(MediaType.parse("application/json"), this.objectMapper.writeValueAsString(meta));
                Request request = new Request.Builder()
                        .url(this.properties.getUrl() + "/commands")
                        .post(body).build();
                Response response = this.okHttpClient.newCall(request).execute();
                if (!response.isSuccessful()) {
                    throw new IOException("Invalid response code " + response.code());
                }
            } catch (IOException ex) {
                throw new IllegalStateException("Failed to create command", ex);
            }
            this.messaging.addConsumer("Lindsey:Commands:" + meta.getName(), this);
        }
        this.selfCommands.add(meta.getName());
        if (path == null || path.isBlank()) {
            this.listeners.put(meta.getName(), listener);
        } else {
            this.listeners.put(meta.getName() + "." + path, listener);
        }
    }

    /**
     * Unregisters a command from the registry.
     *
     * @param name Command name.
     */
    public void unregister(String name) {
        CommandMeta meta = this.get(name);
        if (meta == null) {
            throw new IllegalArgumentException("Unknown command " + name);
        }
        try {
            Request request = new Request.Builder()
                    .url(this.properties.getUrl() + "/commands/" + name)
                    .delete().build();
            Response response = this.okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Invalid response code " + response.code());
            }
            this.selfCommands.remove(meta.getName());
            this.messaging.remConsumer("Lindsey:Commands:" + meta.getName(), this);
            this.listeners.keySet().stream()
                    .filter(key -> key.startsWith(meta.getName() + ".") || key.equalsIgnoreCase(meta.getName()))
                    .forEach(this.listeners::remove);
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to delete command", ex);
        }
    }

    /**
     * Unregisters all commands.
     */
    @PreDestroy
    public void shutdown() {
        this.selfCommands.forEach(this::unregister);
    }

    /**
     * Checks if a command exists by a given name.
     *
     * @param commandName Name or alias of the command.
     * @return True if a command exists.
     */
    public boolean exists(String commandName) {
        return this.get(commandName) != null;
    }

    /**
     * Used for getting a list of all commands currently loaded, may be empty if load was not called.
     *
     * @return List of commands.
     */
    public List<CommandMeta> getAll() {
        return new ArrayList<>(this.commands.values());
    }

    /**
     * Returns a command's metadata.
     *
     * @param name Name or alias of the command.
     * @return Metadata.
     */
    public CommandMeta get(String name) {
        CommandMeta meta = this.commands.get(name);
        if (meta == null) {
            meta = this.commands.values().stream()
                    .filter(cmd -> cmd.getAliases().contains(name)).findFirst()
                    .orElse(null);
        }
        return meta;
    }

    @Override
    public Class<CommandRequest> getTClass() {
        return CommandRequest.class;
    }

    @Override
    public void onMessage(CommandRequest message) {
        Function<CommandRequest, InteractionResponse> fn = this.listeners.get(message.getPath());
        if (fn == null) {
            log.warn("Received a command request with unknown path " + message.getPath());
            return;
        }
        try {
            this.messaging.enqueue("Lindsey:Interactions", fn.apply(message));
        } catch (Exception ex) {
            log.error("Error during command execution", ex);
        }
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + this.properties.getToken())
                .addHeader("X-Worker-Id", this.properties.getId())
                .build();
        return chain.proceed(request);
    }

}
