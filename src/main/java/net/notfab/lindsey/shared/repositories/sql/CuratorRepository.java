package net.notfab.lindsey.shared.repositories.sql;

import net.notfab.lindsey.shared.entities.playlist.Curator;
import net.notfab.lindsey.shared.entities.playlist.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuratorRepository extends JpaRepository<Curator, Long> {

    List<Curator> findAllByPlayList(PlayList playList);

    Optional<Curator> findByPlayListAndUserId(PlayList playList, long userId);

    long countByPlayList(PlayList playList);

}
