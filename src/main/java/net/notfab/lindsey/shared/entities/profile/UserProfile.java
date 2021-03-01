package net.notfab.lindsey.shared.entities.profile;

import lombok.Data;
import net.notfab.lindsey.shared.enums.Flags;
import net.notfab.lindsey.shared.enums.Language;

import javax.persistence.*;

@Data
@Entity
@Table(name = "UserProfiles")
public class UserProfile {

    @Id
    private long user;

    private String name;
    private long lastSeen;

    @Enumerated(EnumType.STRING)
    private Language language = Language.en_US;

    @Enumerated(EnumType.STRING)
    private Flags country;

    private long cookies = 0;

    private long cookieStreak = 0;
    private long lastDailyCookies = 0;

}
