package net.notfab.lindsey.shared.repositories.sql.items;

import net.notfab.lindsey.shared.entities.items.ItemReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<ItemReference, Long> {

    Optional<ItemReference> findByOwnerAndItem_Id(long owner, long itemId);

}
