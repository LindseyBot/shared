package net.notfab.lindsey.shared.entities.profile.user;

import lombok.Data;
import net.notfab.lindsey.shared.converters.LongListJsonConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "UserProfiles_Customization")
public class UserCustomization {

    @Id
    private long user;

    private Long background;

    @Convert(converter = LongListJsonConverter.class)
    private List<Long> badges;

}
