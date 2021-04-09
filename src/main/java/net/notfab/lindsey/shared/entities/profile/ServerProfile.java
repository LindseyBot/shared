package net.notfab.lindsey.shared.entities.profile;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.notfab.lindsey.shared.enums.Language;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "server_settings")
public class ServerProfile {

    @Id
    private long guild;

    private String prefix;
    private long lastSeen;

    @Enumerated(EnumType.STRING)
    private Language language = Language.en_US;

    @Transient
    private Set<String> ignoredChannels;

    public ServerProfile(long guild) {
        this.guild = guild;
    }

}
