package net.notfab.lindsey.shared.entities.profile.member;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Strikes")
public class Strike {

    @Id
    private Long id;

    private Long user;
    private Long guild;
    private Long admin;

    private Integer count;
    private String reason;

}
