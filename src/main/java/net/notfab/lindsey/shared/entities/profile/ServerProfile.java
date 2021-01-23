package net.notfab.lindsey.shared.entities.profile;

import lombok.Data;
import net.notfab.lindsey.shared.entities.playlist.PlayList;
import net.notfab.lindsey.shared.entities.playlist.PlayListCursor;
import net.notfab.lindsey.shared.enums.Language;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "ServerProfiles")
public class ServerProfile {

    @Id
    private String id;

    private String prefix;
    private PlayListCursor cursor;
    private Language language;

    @DBRef
    private PlayList activePlayList;

    private boolean keepRolesEnabled;
    private Long starboardChannelId;
    private Long musicChannelId;

}
