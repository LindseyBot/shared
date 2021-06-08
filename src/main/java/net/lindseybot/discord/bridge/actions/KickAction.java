package net.lindseybot.discord.bridge.actions;

import lombok.Data;
import net.lindseybot.discord.bridge.Action;

@Data
public class KickAction implements Action {

    private long targetId;
    private String reason;

}
