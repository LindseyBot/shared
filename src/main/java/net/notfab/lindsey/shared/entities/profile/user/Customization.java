package net.notfab.lindsey.shared.entities.profile.user;

import lombok.Data;

import java.util.List;

@Data
public class Customization {

    private Long background;
    private List<Long> badges;

}
