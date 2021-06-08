package net.lindseybot.framework;

import lombok.Data;
import net.lindseybot.discord.bridge.InteractionData;
import net.notfab.lindsey.shared.rpc.FMember;

@Data
public class ButtonRequest {

    private InteractionData interaction;
    private String id;
    private String data;
    private FMember member;

}
