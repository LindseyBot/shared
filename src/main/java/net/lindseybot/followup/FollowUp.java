package net.lindseybot.followup;

import lombok.Data;

@Data
public class FollowUp {

    private long id;

    private Long guildId;
    private Long channelId;
    private Long messageId;

}
