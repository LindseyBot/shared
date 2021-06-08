package net.lindseybot.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.lindseybot.controller.CommandMeta;
import net.notfab.eventti.Event;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommandMetaEvent extends Event {

    private CommandMeta model;
    private boolean create;

}
