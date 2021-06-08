package net.lindseybot.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.lindseybot.enums.CommandType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommandMeta {

    private String name;
    private String description;
    private boolean nsfw;
    private boolean adminOnly;
    private boolean developerOnly;
    private CommandType type;
    private List<String> aliases;

    private List<CommandOption> options;
    private List<CommandMeta> subcommands;

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CommandMeta)) {
            return false;
        }
        return ((CommandMeta) object).getName().equals(name);
    }

}
