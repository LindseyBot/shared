package net.notfab.lindsey.shared.entities.items;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Items_Badges")
@EqualsAndHashCode(callSuper = true)
public class Badge extends Item {

    private String assetUrl;

}
