package net.notfab.lindsey.shared.entities.profile.server;

import lombok.Data;

@Data
public class WelcomeSettings {

    private boolean enabled = false;
    private boolean dms = false;

    private Long channelId;
    private String message;

}
