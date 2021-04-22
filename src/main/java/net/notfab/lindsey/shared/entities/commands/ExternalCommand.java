package net.notfab.lindsey.shared.entities.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExternalCommand {

    private String name;
    private String description;
    private boolean nsfw;
    private boolean adminOnly;
    private boolean developerOnly;
    private List<String> aliases;

    private List<CommandOption> options;
    private List<ExternalCommand> commands;

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ExternalCommand)) {
            return false;
        }
        return ((ExternalCommand) object).getName().equals(name);
    }

}
