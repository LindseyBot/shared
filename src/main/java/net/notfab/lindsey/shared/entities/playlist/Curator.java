package net.notfab.lindsey.shared.entities.playlist;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "playlists_curators")
public class Curator {

    @Id
    private long id;

    private long userId;
    private String name;

    @ManyToOne
    private PlayList playList;

}
