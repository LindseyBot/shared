package net.lindseybot.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.lindseybot.controller.ButtonMeta;
import net.notfab.eventti.Event;

@Data
@EqualsAndHashCode(callSuper = true)
public class ButtonMetaEvent extends Event {

    private ButtonMeta model;
    private boolean create;

}
