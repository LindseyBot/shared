package net.lindseybot.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.lindseybot.commands.request.CommandOption;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Command {

    private String name;
    private String description;
    private boolean nsfw;
    private boolean adminOnly;
    private boolean developerOnly;
    private List<String> aliases;

    private List<CommandOption> options;
    private List<Command> commands;

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Command)) {
            return false;
        }
        return ((Command) object).getName().equals(name);
    }

}
