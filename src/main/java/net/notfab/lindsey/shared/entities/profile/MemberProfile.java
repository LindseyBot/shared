package net.notfab.lindsey.shared.entities.profile;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "MemberProfiles", indexes = {
        @Index(name = "user_guild", columnList = "user,guild")
})
public class MemberProfile {

    @Id
    private long id;

    private long user;
    private long guild;

    private long lastSeen;

}
