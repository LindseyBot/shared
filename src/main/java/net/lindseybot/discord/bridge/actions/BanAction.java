package net.lindseybot.discord.bridge.actions;

import lombok.Data;
import net.lindseybot.discord.bridge.Action;

@Data
public class BanAction implements Action {

    private long targetId;
    private String reason;
    private int delDays;

}
