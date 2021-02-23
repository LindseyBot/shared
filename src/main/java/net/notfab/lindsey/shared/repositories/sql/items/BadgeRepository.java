package net.notfab.lindsey.shared.repositories.sql.items;

import net.notfab.lindsey.shared.entities.items.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
