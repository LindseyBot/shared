package net.notfab.lindsey.shared.entities.playlist;

import lombok.Data;
import net.notfab.lindsey.shared.enums.PlayListGenre;
import net.notfab.lindsey.shared.enums.PlayListSecurity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "playlists")
public class PlayList {

    @Id
    private long id;

    private long owner;
    private String name;
    private boolean shuffle;
    private String logoUrl;

    @Enumerated(EnumType.STRING)
    private PlayListSecurity security = PlayListSecurity.PUBLIC;

    @Enumerated(EnumType.STRING)
    private PlayListGenre genre = PlayListGenre.MIXED;

}
