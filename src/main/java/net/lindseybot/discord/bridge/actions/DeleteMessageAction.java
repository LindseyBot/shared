package net.lindseybot.discord.bridge.actions;

import lombok.Data;
import net.lindseybot.discord.bridge.Action;

@Data
public class DeleteMessageAction implements Action {

    private long channelId;
    private long messageId;

}
