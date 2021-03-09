package net.notfab.lindsey.shared.rpc;

import lombok.Data;

import java.io.Serializable;

@Data
public class FRole implements Serializable {

    static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private int position;

}
