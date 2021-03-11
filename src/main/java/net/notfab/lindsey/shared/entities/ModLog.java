package net.notfab.lindsey.shared.entities;

import lombok.Data;
import net.notfab.lindsey.shared.enums.ModLogType;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ModLog", indexes = {
        @Index(name = "idx_guild", columnList = "guild"),
        @Index(name = "idx_admin", columnList = "admin")
})
public class ModLog {

    @Id
    private long id;

    private long guild;
    private long admin;

    private Long targetId;
    private String targetName;

    @Enumerated(EnumType.STRING)
    private ModLogType type;

    private Long duration;
    private String reason;

}
