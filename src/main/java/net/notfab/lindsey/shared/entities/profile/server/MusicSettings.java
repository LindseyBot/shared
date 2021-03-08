package net.notfab.lindsey.shared.entities.profile.server;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "server_settings_music")
public class MusicSettings {

    @Id
    private long guild;

    private boolean logTracks = false;
    private Long logChannel;

    private Long activePlayList;
    private int position;

    public MusicSettings(long guild) {
        this.guild = guild;
    }

}
