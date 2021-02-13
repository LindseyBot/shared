package net.notfab.lindsey.shared.repositories.sql;

import net.notfab.lindsey.shared.entities.server.WelcomeSettings;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WelcomeSettingsRepository extends PagingAndSortingRepository<WelcomeSettings, Long> {
}
