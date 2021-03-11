package net.notfab.lindsey.shared.repositories.sql;

import net.notfab.lindsey.shared.entities.ModLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModLogRepository extends JpaRepository<ModLog, Long> {

    Page<ModLog> findAllByGuildOrderByIdDesc(long guild, Pageable pageable);

}
