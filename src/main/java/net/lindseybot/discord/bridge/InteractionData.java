package net.lindseybot.discord.bridge;

import lombok.Data;
import net.lindseybot.enums.InteractionType;

@Data
public class InteractionData {

    private long guildId;
    private long channelId;
    private String token;
    private InteractionType type;

    private Long messageId;
    private Long userId;

}
