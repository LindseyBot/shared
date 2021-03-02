package net.notfab.lindsey.shared.entities.profile;

import lombok.Data;
import net.notfab.lindsey.shared.entities.profile.member.RoleHistory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "MemberProfiles")
public class MemberProfile {

    @Id
    private String id;
    private long guildId;
    private long userId;

    private long lastSeen;

    private RoleHistory roleHistory = new RoleHistory();

}
