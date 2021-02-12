package net.notfab.lindsey.shared.entities.profile.server;

import lombok.Data;

@Data
public class AntiAd {

    private boolean enabled = false;

    private boolean ban = false;
    private int strikes = 3;

}
