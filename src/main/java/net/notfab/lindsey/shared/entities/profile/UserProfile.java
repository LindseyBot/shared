package net.notfab.lindsey.shared.entities.profile;

import lombok.Data;
import net.notfab.lindsey.shared.entities.profile.user.Customization;
import net.notfab.lindsey.shared.enums.Flags;
import net.notfab.lindsey.shared.enums.Language;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Document(collection = "UserProfiles")
public class UserProfile {

    @Id
    private String id;

    private String name;
    private long lastSeen;

    @Enumerated(EnumType.STRING)
    private Language language = Language.en_US;

    @Enumerated(EnumType.STRING)
    private Flags country;

    private long cookies = 0;

    private long cookieStreak = 0;
    private long lastDailyCookies = 0;

    private Customization customization;

}
