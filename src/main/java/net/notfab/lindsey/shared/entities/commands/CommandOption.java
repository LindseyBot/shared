package net.notfab.lindsey.shared.entities.commands;

import lombok.Data;

import java.util.List;

@Data
public class CommandOption {

    private final OptionType type;
    private final String name;
    private final String description;

    public CommandOption(OptionType type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    private boolean required;
    private String pattern;
    private boolean list;
    private List<String> enumEntries;
    private double minimum;
    private double maximum;

}
