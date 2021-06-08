package net.lindseybot.discord.bridge.actions;

import lombok.Data;
import net.lindseybot.discord.bridge.Action;

@Data
public class WaitAction implements Action {

    private long time;

}
