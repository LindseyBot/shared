package net.notfab.lindsey.shared.repositories.sql;

import net.notfab.lindsey.shared.entities.playlist.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {

    List<PlayList> findAllByOwner(long owner);

    Optional<PlayList> findByNameLikeAndOwner(String name, long owner);

    Optional<PlayList> findTopByNameLike(String name);

    long countByOwner(long owner);

}
