package net.notfab.lindsey.shared.entities.items;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Items_Background")
public class Background {

    @Id
    private Long id;
    private String name;
    private String description;
    private String fontColor;
    private String assetUrl;

}
