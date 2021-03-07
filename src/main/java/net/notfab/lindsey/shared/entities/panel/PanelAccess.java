package net.notfab.lindsey.shared.entities.panel;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
public class PanelAccess {

    @Id
    private long id;

    private long user;
    private long guild;

    @Enumerated(EnumType.STRING)
    private AccessLevel level;

    private String username;

}
