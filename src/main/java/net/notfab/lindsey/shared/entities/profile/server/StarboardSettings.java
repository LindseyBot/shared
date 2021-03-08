package net.notfab.lindsey.shared.entities.profile.server;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "server_settings_starboard")
public class StarboardSettings {

    @Id
    private long guild;

    private boolean enabled = false;
    private int minStars = 3;
    private Long channel;

    public StarboardSettings(long guild) {
        this.guild = guild;
    }

}
