package net.lindseybot.commands.request;

import lombok.Data;
import net.notfab.lindsey.shared.rpc.FChannel;
import net.notfab.lindsey.shared.rpc.FGuild;
import net.notfab.lindsey.shared.rpc.FMember;

import java.util.HashMap;
import java.util.Map;

@Data
public class CommandRequest {

    private String id;
    private String commandName;
    private String commandPath;

    private FGuild guild;
    private FMember member;
    private FChannel channel;

    private Map<String, Object> options = new HashMap<>();

}
