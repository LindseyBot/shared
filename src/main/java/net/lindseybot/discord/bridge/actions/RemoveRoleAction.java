package net.lindseybot.discord.bridge.actions;

import lombok.Data;
import net.lindseybot.discord.bridge.Action;

@Data
public class RemoveRoleAction implements Action {

    private long roleId;
    private long userId;

}
