package net.notfab.lindsey.shared.repositories.sql.server;

import net.notfab.lindsey.shared.entities.profile.server.AntiAd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntiAdRepository extends JpaRepository<AntiAd, Long> {
}
