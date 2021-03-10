package net.notfab.lindsey.shared.repositories.sql;

import net.notfab.lindsey.shared.entities.profile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @Query("UPDATE UserProfile u SET u.lastSeen = ?1 WHERE u.user = ?2")
    void updateLastSeen(long seen, long user);

    @Query("UPDATE UserProfile u SET u.name = ?1 WHERE u.user = ?2")
    void updateName(String name, long user);

}
