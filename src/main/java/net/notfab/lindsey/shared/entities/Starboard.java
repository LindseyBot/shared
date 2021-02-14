package net.notfab.lindsey.shared.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Starboard")
public class Starboard {

    @Id
    private long id;

    private long guildId;
    private long channelId;
    private Long starboardMessageId;
    private int stars;

}
