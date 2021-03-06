package net.lindseybot.controller.builder;

import lombok.Data;
import net.lindseybot.controller.CommandOption;
import net.lindseybot.enums.OptionType;

import java.util.List;

@Data
public class OptionBuilder {

    private final OptionType type;
    private final String name;
    private final String description;
    private final CommandBuilder parent;

    public OptionBuilder(OptionType type, String name, String description, CommandBuilder parent) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.parent = parent;
    }

    private boolean required = false;
    private String pattern;
    private boolean list = false;
    private List<String> enumEntries;
    private double minimum;
    private double maximum;

    public OptionBuilder required() {
        this.required = true;
        return this;
    }

    public OptionBuilder list() {
        this.list = true;
        return this;
    }

    public OptionBuilder regex(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public OptionBuilder enumEntries(String... entries) {
        this.enumEntries = List.of(entries);
        return this;
    }

    public OptionBuilder minMax(double minimum, double maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
        return this;
    }

    public CommandBuilder build() {
        CommandOption option = new CommandOption(this.type, this.name, this.description);
        option.setRequired(this.required);
        option.setPattern(this.pattern);
        option.setList(this.list);
        option.setEnumEntries(this.enumEntries);
        option.setMinimum(this.minimum);
        option.setMaximum(this.maximum);
        this.parent.addOption(option);
        return this.parent;
    }

}
