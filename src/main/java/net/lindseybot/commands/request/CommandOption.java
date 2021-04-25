package net.lindseybot.commands.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.lindseybot.enums.OptionType;

import java.util.List;

@Data
@NoArgsConstructor
public class CommandOption {

    private OptionType type;
    private String name;
    private String description;

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
