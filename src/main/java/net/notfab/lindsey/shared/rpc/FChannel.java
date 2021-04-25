package net.notfab.lindsey.shared.rpc;

import lombok.Data;

import java.io.Serializable;

@Data
public class FChannel implements Serializable {

    static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private FChannelType type;
    int position;
    private boolean nsfw;

}
