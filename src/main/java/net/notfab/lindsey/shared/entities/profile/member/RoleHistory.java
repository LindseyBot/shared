package net.notfab.lindsey.shared.entities.profile.member;

import lombok.Data;
import net.notfab.lindsey.shared.converters.SetStringConverter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "RoleHistory", indexes = {
        @Index(name = "user_guild", columnList = "user,guild")
})
public class RoleHistory {

    @Id
    private long id;

    private long user;
    private long guild;

    private long lastUpdated;

    @Convert(converter = SetStringConverter.class)
    private Set<String> roles = new HashSet<>();

}
