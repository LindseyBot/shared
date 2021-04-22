package net.notfab.lindsey.shared.enums;

import lombok.Getter;

public enum RabbitExchange {

    WORKERS("cluster.workers"),
    GATEWAYS("cluster.gateways"),
    CONTROLLERS("cluster.controllers"),
    COMMANDS("commands");

    @Getter
    private final String name;

    RabbitExchange(String name) {
        this.name = name;
    }

}
