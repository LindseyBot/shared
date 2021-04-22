package net.notfab.lindsey.shared.entities.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.notfab.eventti.Event;
import net.notfab.lindsey.shared.entities.commands.ExternalCommand;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommandCreatedEvent extends Event {

    private ExternalCommand command;

}
