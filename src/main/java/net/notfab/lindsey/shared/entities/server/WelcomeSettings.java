package net.notfab.lindsey.shared.entities.server;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class WelcomeSettings {

    @Id
    private long id;

    private boolean enabled = false;
    private boolean dms = false;

    private Long channelId;
    private String message;

}
