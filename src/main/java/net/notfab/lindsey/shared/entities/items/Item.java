package net.notfab.lindsey.shared.entities.items;

import lombok.Data;

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

    private Boolean inStore;
    private Boolean marketable;

    private Integer storePrice;

}
