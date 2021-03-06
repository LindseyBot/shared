package net.notfab.lindsey.shared.entities.profile.server;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "server_settings_antiad")
public class AntiAd {

    @Id
    private long guild;

    private boolean enabled = false;
    private int strikes = 3;

    public AntiAd(long guild) {
        this.guild = guild;
    }

}
