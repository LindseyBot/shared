package net.notfab.lindsey.shared.rpc;

import lombok.Data;

import java.io.Serializable;

@Data
public class DVoiceChannel implements Serializable {

    static final long serialVersionUID = 1L;

    private long id;
    private String name;
    int position;

}
