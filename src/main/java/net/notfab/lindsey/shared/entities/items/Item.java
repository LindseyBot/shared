package net.notfab.lindsey.shared.entities.items;

import lombok.Data;
import net.notfab.lindsey.shared.enums.ItemType;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Items")
@Inheritance(strategy = InheritanceType.JOINED)
public class Item {

    @Id
    private Long id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    private Boolean inStore;
    private Boolean marketable;

    private Integer storePrice;

}
