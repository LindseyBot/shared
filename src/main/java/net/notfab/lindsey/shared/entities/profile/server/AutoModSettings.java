package net.notfab.lindsey.shared.entities.profile.server;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "server_settings_automod")
public class AutoModSettings {

    @Id
    private long guild;

    private boolean muteEnabled = false;
    private boolean banEnabled = true;

    public AutoModSettings(long guild) {
        this.guild = guild;
    }

}
