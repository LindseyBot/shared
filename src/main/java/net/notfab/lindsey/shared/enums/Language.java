package net.notfab.lindsey.shared.enums;

import lombok.Getter;

public enum Language {

    en_US("English (US)");

    @Getter
    private final String name;

    Language(String name) {
        this.name = name;
    }

}
