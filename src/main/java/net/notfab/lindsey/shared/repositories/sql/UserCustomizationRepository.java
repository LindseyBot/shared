package net.notfab.lindsey.shared.repositories.sql;

import net.notfab.lindsey.shared.entities.profile.user.UserCustomization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCustomizationRepository extends JpaRepository<UserCustomization, Long> {
}
