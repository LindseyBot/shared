package net.notfab.lindsey.shared.entities.music;

import lombok.Data;
import net.notfab.lindsey.shared.enums.SongSource;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Tracks", indexes = {
        @Index(name = "code_source", columnList = "code,source")
})
public class Track {

    @Id
    private String code;

    private String title;
    private String author;

    private long duration;
    private boolean stream;
    private boolean cached;

    @Enumerated(EnumType.ORDINAL)
    private SongSource source;

    @Transient
    private int position;

}
