package net.notfab.lindsey.shared.repositories.sql.server;

import net.notfab.lindsey.shared.entities.profile.server.MusicSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicSettingsRepository extends JpaRepository<MusicSettings, Long> {
}
