package net.notfab.lindsey.shared.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ReactionRoles")
public class ReactionRole {

    @Id
    private String id;
    private String name;

    private long roleId;
    private long guildId;
    private long messageId;
    private long channelId;

    private String reaction;

}
