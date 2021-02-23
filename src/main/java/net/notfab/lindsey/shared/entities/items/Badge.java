package net.notfab.lindsey.shared.entities.items;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Items_Badges")
public class Badge {

    @Id
    private Long id;
    private String name;
    private String description;
    private String assetUrl;

}
