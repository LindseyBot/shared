package net.lindseybot.controller.builder;

import net.lindseybot.controller.CommandMeta;
import net.lindseybot.controller.CommandOption;
import net.lindseybot.enums.CommandType;
import net.lindseybot.enums.OptionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandBuilder {

    private final String name;
    private final String description;
    private final CommandBuilder parent;
    private final List<String> aliases = new ArrayList<>();

    private final List<CommandOption> options = new ArrayList<>();
    private final List<CommandMeta> commandMetas = new ArrayList<>();

    private boolean nsfw = false;
    private boolean adminOnly = false;
    private boolean developerOnly = false;
    private CommandType type = CommandType.NORMAL;

    // Subcommand
    private CommandBuilder(String name, String description, CommandBuilder parent) {
        this.name = name;
        this.description = description;
        this.parent = parent;
    }

    // New command
    public CommandBuilder(String name, String description) {
        this.name = name;
        this.description = description;
        this.parent = null;
    }

    public OptionBuilder option(OptionType type, String name, String description) {
        return new OptionBuilder(type, name, description, this);
    }

    public CommandBuilder subCommand(String name, String description) {
        return new CommandBuilder(name, description, this);
    }

    public CommandBuilder type(CommandType type) {
        this.type = type;
        return this;
    }

    public CommandBuilder aliases(String... aliases) {
        this.aliases.addAll(Arrays.asList(aliases));
        return this;
    }

    public CommandBuilder nsfw() {
        this.nsfw = true;
        return this;
    }

    public CommandBuilder admin() {
        this.adminOnly = true;
        return this;
    }

    public CommandBuilder developer() {
        this.developerOnly = true;
        return this;
    }

    // -- Internal

    public void addGroup(CommandMeta cmd) {
        this.commandMetas.add(cmd);
    }

    public void addOption(CommandOption option) {
        this.options.add(option);
    }

    public CommandBuilder next() {
        if (this.parent == null) {
            throw new IllegalStateException("Not on a subcommand, cannot go back");
        }
        this.parent.addGroup(this.build());
        return this.parent;
    }

    public CommandMeta build() {
        return new CommandMeta(this.name, this.description, this.nsfw, this.adminOnly, this.developerOnly, this.type,
                this.aliases, this.options, this.commandMetas);
    }

}
