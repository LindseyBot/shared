package net.notfab.lindsey.shared.entities.items;

import lombok.Data;
import net.notfab.lindsey.shared.enums.ItemType;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Inventory")
public class ItemReference {

    @Id
    private Long id;
    private Long owner;
    private Long itemId;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    private Long count;

}
