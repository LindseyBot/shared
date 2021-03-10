package net.notfab.lindsey.shared.repositories.sql;

import net.notfab.lindsey.shared.entities.profile.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {

    @Query("UPDATE MemberProfile m SET m.lastSeen = ?1 WHERE m.user = ?2 AND m.guild = ?3")
    void updateLastSeen(long seen, long user, long guild);

    Optional<MemberProfile> findByUserAndGuild(long user, long guild);

}
