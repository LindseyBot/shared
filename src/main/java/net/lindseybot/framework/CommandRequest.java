package net.lindseybot.framework;

import lombok.Data;
import net.lindseybot.discord.bridge.InteractionData;
import net.notfab.lindsey.shared.rpc.FChannel;
import net.notfab.lindsey.shared.rpc.FGuild;
import net.notfab.lindsey.shared.rpc.FMember;

@Data
public class CommandRequest {

    private String path;
    private InteractionData interaction;

    private FGuild guild;
    private FMember member;
    private FChannel channel;

    private CommandOpts options = new CommandOpts();

}
