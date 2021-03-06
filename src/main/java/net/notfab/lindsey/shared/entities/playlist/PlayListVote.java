package net.notfab.lindsey.shared.entities.playlist;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "playlists_votes")
public class PlayListVote {

    @Id
    private long id;

    private long user;
    private long playlist;
    private boolean upvote;

}
