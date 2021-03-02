package net.notfab.lindsey.shared.repositories.sql;

import net.notfab.lindsey.shared.entities.profile.member.Strike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StrikeRepository extends JpaRepository<Strike, Long> {

    Page<Strike> findAllByUser(long user, Pageable pageable);

    List<Strike> findAllByUserAndGuild(long user, long guild);

    @Query("SELECT sum(s.count) from Strike s WHERE s.user  = ?1 and s.guild = ?2")
    int sumByUserAndGuild(long user, long guild);

    Optional<Strike> findTopByUserAndGuildOrderByIdDesc(long user, long guild);

}
