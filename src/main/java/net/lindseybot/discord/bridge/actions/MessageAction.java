package net.lindseybot.discord.bridge.actions;

import lombok.Data;
import net.lindseybot.discord.Message;
import net.lindseybot.discord.bridge.Action;

@Data
public class MessageAction implements Action {

    private Message message;
    private long channelId;
    private boolean edit;
    private long targetId;

}
