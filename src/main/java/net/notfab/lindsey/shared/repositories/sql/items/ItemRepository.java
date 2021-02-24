package net.notfab.lindsey.shared.repositories.sql.items;

import net.notfab.lindsey.shared.entities.items.Item;
import net.notfab.lindsey.shared.enums.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByMarketable(boolean marketable);

    List<Item> findAllByInStore(boolean inStore);

    List<Item> findAllByType(ItemType type);

}
