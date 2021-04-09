package net.notfab.lindsey.shared.entities.profile.server;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.notfab.lindsey.shared.enums.PlayListMode;

import javax.persistence.*;

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

    @Enumerated(EnumType.ORDINAL)
    private PlayListMode mode = PlayListMode.QUEUE;

    public MusicSettings(long guild) {
        this.guild = guild;
    }

}
