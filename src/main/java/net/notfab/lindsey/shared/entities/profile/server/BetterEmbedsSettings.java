package net.notfab.lindsey.shared.entities.profile.server;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "server_settings_embeds")
public class BetterEmbedsSettings {

    @Id
    private long guild;

    private boolean apoiase = true;
    private boolean catarse = true;
    private boolean kitsu = true;
    private boolean myAnimeList = true;
    private boolean picarto = true;

    public BetterEmbedsSettings(long guild) {
        this.guild = guild;
    }

}
