package net.notfab.lindsey.shared.repositories.sql;

import net.notfab.lindsey.shared.entities.Notification;
import net.notfab.lindsey.shared.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByStatusAndTarget(NotificationStatus status, Long target);

    List<Notification> findAllByTarget(Long target);

}
