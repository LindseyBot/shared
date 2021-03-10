package net.notfab.lindsey.shared.repositories.sql;

import net.notfab.lindsey.shared.entities.profile.ServerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServerProfileRepository extends JpaRepository<ServerProfile, Long> {

    @Query("UPDATE ServerProfile s SET s.lastSeen = ?1 WHERE s.guild = ?2")
    void updateLastSeen(long seen, long guild);

}
