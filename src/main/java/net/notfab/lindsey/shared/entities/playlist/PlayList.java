package net.notfab.lindsey.shared.entities.playlist;

import lombok.Data;
import net.notfab.lindsey.shared.enums.PlayListSecurity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

@Data
@Document("PlayLists")
public class PlayList {

    @Id
    private String id;

    private long owner;
    private String name;
    private boolean shuffle;
    private String logoUrl;

    @Enumerated(EnumType.STRING)
    private PlayListSecurity security = PlayListSecurity.PUBLIC;

    private List<Song> songs = new ArrayList<>();
    private List<Curator> curators = new ArrayList<>();

}
