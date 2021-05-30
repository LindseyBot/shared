package net.lindseybot.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.notfab.eventti.Event;
import net.notfab.lindsey.shared.rpc.FMember;

@Data
@EqualsAndHashCode(callSuper = true)
public class ButtonClickEvent extends Event {

    private String id;
    private long messageId;
    private long channelId;
    private String token;
    private FMember member;

}
