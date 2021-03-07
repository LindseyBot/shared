package net.notfab.lindsey.shared.repositories.sql;

import net.notfab.lindsey.shared.entities.panel.PanelAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PanelAccessRepository extends JpaRepository<PanelAccess, Long> {

    List<PanelAccess> findAllByUser(long user);

    Optional<PanelAccess> findByUserAndGuild(long user, long guild);

    List<PanelAccess> findAllByGuild(long guild);

    @Transactional
    void deleteByUserAndGuild(long user, long guild);

}
