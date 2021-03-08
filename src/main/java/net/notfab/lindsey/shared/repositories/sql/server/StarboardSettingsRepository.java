package net.notfab.lindsey.shared.repositories.sql.server;

import net.notfab.lindsey.shared.entities.profile.server.StarboardSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarboardSettingsRepository extends JpaRepository<StarboardSettings, Long> {
}
