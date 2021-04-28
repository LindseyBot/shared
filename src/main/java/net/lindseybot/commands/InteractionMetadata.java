package net.lindseybot.commands;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("Lindsey:Interactions")
public class InteractionMetadata {

    @Id
    private String id;

    private Long messageId;
    private Long guildId;
    private Long channelId;

}
