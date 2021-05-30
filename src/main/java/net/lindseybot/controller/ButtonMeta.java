package net.lindseybot.controller;

import lombok.Data;

@Data
public class ButtonMeta {

    private String method;
    private boolean ephemeral;
    private boolean editsMessage;

}
