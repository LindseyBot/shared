package net.lindseybot.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface RedisConsumer<T> {

    Class<T> getTClass();

    void onMessage(T message);

    default void onRedisMessage(String data, ObjectMapper objectMapper) throws JsonProcessingException {
        T mapped = objectMapper.readValue(data, this.getTClass());
        this.onMessage(mapped);
    }

}
