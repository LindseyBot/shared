package net.notfab.lindsey.shared.entities.items;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Items_Background")
@EqualsAndHashCode(callSuper = true)
public class Background extends Item {

    private String fontColor;
    private String assetUrl;

}
