package net.lindseybot.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.lindseybot.models.RedisConsumer;
import net.notfab.eventti.Event;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class MessagingService {

    private final JedisPool pool;
    private final EventService eventService;
    private final ObjectMapper objectMapper;
    private final ExecutorService service;
    private final Map<String, AtomicBoolean> stopMap = new HashMap<>();

    public MessagingService(JedisPool pool, EventService eventService) {
        this.pool = pool;
        this.eventService = eventService;
        this.objectMapper = new ObjectMapper();
        this.service = Executors.newCachedThreadPool();
    }

    /**
     * Sends an event to a network channel.
     *
     * @param channel Channel.
     * @param event   Event.
     */
    public void broadcast(String channel, Event event) {
        try (Jedis jedis = this.pool.getResource()) {
            String data = this.objectMapper.writeValueAsString(event);
            jedis.publish(channel, data);
        } catch (JedisException | JsonProcessingException ex) {
            log.error("Failed to broadcast event", ex);
        }
    }

    /**
     * Adds a listener on a specific pub-sub channel.
     *
     * @param channel Channel name.
     */
    public void subscribe(String channel) {
        this.service.submit(() -> {
            try (Jedis jedis = this.pool.getResource()) {
                jedis.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message) {
                        eventService.onMessage(message);
                    }
                }, channel);
            } catch (Exception ex) {
                log.error("Error during channel subscription", ex);
            }
        });
    }

    /**
     * Adds a message to a queue for processing.
     *
     * @param queueName Name of the queue.
     * @param object    Message to publish.
     */
    public void enqueue(String queueName, Object object) {
        try (Jedis jedis = this.pool.getResource()) {
            String data = this.objectMapper.writeValueAsString(object);
            jedis.rpush(queueName, data);
        } catch (JedisException | JsonProcessingException ex) {
            log.error("Failed to publish event to queue", ex);
        }
    }

    /**
     * Subscribes to a queue and starts consuming.
     *
     * @param queue    Queue name.
     * @param consumer Consumer.
     */
    public void addConsumer(String queue, RedisConsumer<?> consumer) {
        this.service.submit(() -> {
            try (Jedis redis = this.pool.getResource()) {
                AtomicBoolean running = new AtomicBoolean(true);
                this.stopMap.put(queue + ":" + consumer.getClass().getSimpleName(), running);
                do {
                    List<String> list = redis.blpop(15, queue);
                    if (list == null || list.isEmpty()) {
                        continue;
                    }
                    for (String data : list) {
                        if (data == null || data.isBlank() || data.equals(queue)) {
                            continue;
                        }
                        try {
                            consumer.onRedisMessage(data, this.objectMapper);
                        } catch (JsonProcessingException ex) {
                            log.error("Failed to deserialize message on channel " + queue, ex);
                        } catch (Exception ex) {
                            log.error("Error during queue execution", ex);
                        }
                    }
                } while (running.get());
            } catch (JedisException ex) {
                log.error("Failed to connect to redis", ex);
            }
        });
    }

    /**
     * Removes a consumer, unregistering the redis listener.
     *
     * @param queue    Queue name.
     * @param consumer Consumer.
     */
    public void remConsumer(String queue, RedisConsumer<?> consumer) {
        String keyName = queue + ":" + consumer.getClass().getSimpleName();
        AtomicBoolean atom = this.stopMap.get(keyName);
        if (atom == null) {
            return;
        }
        atom.set(false);
        this.stopMap.remove(keyName);
    }

}
