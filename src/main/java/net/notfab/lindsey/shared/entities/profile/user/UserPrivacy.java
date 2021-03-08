package net.notfab.lindsey.shared.entities.profile.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "UserProfiles_Privacy")
public class UserPrivacy {

    @Id
    private long user;

    private boolean anonymousInLeaderboards = false;

    public UserPrivacy(long user) {
        this.user = user;
    }

}
