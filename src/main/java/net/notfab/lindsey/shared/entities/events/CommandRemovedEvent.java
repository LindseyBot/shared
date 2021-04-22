package net.notfab.lindsey.shared.entities.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.notfab.eventti.Event;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommandRemovedEvent extends Event {

    private List<String> commandNames;

}
