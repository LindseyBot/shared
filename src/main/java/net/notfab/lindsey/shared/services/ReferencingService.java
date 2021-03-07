package net.notfab.lindsey.shared.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.notfab.lindsey.shared.rpc.FMember;
import net.notfab.lindsey.shared.rpc.FUser;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ReferencingService {

    private final ObjectMapper objectMapper;
    private final StringRedisTemplate redis;

    public ReferencingService(ObjectMapper objectMapper, StringRedisTemplate redis) {
        this.objectMapper = objectMapper;
        this.redis = redis;
    }

    private String createToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public FUser getUser(String ticket) {
        String str = redis.opsForValue().get("Reference:" + ticket);
        if (str == null) {
            return null;
        }
        try {
            return this.objectMapper.readValue(str, FUser.class);
        } catch (JsonProcessingException ex) {
            log.error("Failed to parse user reference", ex);
            return null;
        }
    }

    public FMember getMember(String ticket) {
        String str = redis.opsForValue().get("Reference:" + ticket);
        if (str == null) {
            return null;
        }
        try {
            return this.objectMapper.readValue(str, FMember.class);
        } catch (JsonProcessingException ex) {
            log.error("Failed to parse member reference", ex);
            return null;
        }
    }

    public String create(FUser user) {
        String ticket = this.createToken();
        String str;
        try {
            str = this.objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize user reference");
            return null;
        }
        redis.opsForValue().set("Reference:" + ticket, str, 5, TimeUnit.MINUTES);
        return ticket;
    }

    public String create(FMember member) {
        String ticket = this.createToken();
        String str;
        try {
            str = this.objectMapper.writeValueAsString(member);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize member reference");
            return null;
        }
        redis.opsForValue().set("Reference:" + ticket, str, 5, TimeUnit.MINUTES);
        return ticket;
    }

}
