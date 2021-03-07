package net.notfab.lindsey.shared.rpc;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class FMember extends FUser implements Serializable {

    static final long serialVersionUID = 1L;

    private long guildId;
    private String guildName;

}
