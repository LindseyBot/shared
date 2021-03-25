package net.notfab.lindsey.shared.repositories.sql;

import net.notfab.lindsey.shared.entities.playlist.PlayListVote;
import net.notfab.lindsey.shared.entities.playlist.PlayListSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayListVoteRepository extends JpaRepository<PlayListVote, Long> {

    @Query(nativeQuery = true, value = "SELECT vote.playlist as id, pl.name as name, pl.logo_url as logo, pl.genre as genre, count(vote.id) as votes" +
            " FROM playlists_votes vote" +
            " JOIN playlists pl ON pl.id = vote.playlist" +
            " WHERE vote.playlist > ?1 AND vote.upvote = 1 AND pl.security = 'PUBLIC'" +
            " GROUP BY playlist ORDER BY votes DESC LIMIT ?2")
    List<PlayListSummary> findMostVoted(long last, int limit);

    Optional<PlayListVote> findByUserAndPlaylist(long user, long playlist);

    long countByPlaylistAndUpvote(long playlist, boolean isUpvote);

}
