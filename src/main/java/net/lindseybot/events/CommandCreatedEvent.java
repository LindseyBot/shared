package net.lindseybot.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.notfab.eventti.Event;
import net.lindseybot.commands.Command;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommandCreatedEvent extends Event {

    private Command command;

}
