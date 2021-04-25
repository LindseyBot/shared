package net.lindseybot.commands;

import net.lindseybot.commands.request.CommandOption;
import net.lindseybot.commands.request.OptionBuilder;
import net.lindseybot.enums.OptionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandBuilder {

    private final String name;
    private final String description;
    private final CommandBuilder parent;
    private final List<String> aliases;

    private final List<CommandOption> options = new ArrayList<>();
    private final List<Command> commands = new ArrayList<>();

    private boolean nsfw = false;
    private boolean adminOnly = false;
    private boolean developerOnly = false;

    // Subcommand
    public CommandBuilder(String name, String description, CommandBuilder parent) {
        this.name = name;
        this.description = description;
        this.parent = parent;
        this.aliases = new ArrayList<>();
    }

    // New command
    public CommandBuilder(String name, String description, String... aliases) {
        this.name = name;
        this.description = description;
        this.parent = null;
        this.aliases = Arrays.asList(aliases);
    }

    public OptionBuilder option(OptionType type, String name, String description) {
        return new OptionBuilder(type, name, description, this);
    }

    public CommandBuilder subCommand(String name, String description) {
        return new CommandBuilder(name, description, this);
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

    public void addGroup(Command cmd) {
        this.commands.add(cmd);
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

    public Command build() {
        return new Command(this.name, this.description, this.nsfw, this.adminOnly, this.developerOnly, this.aliases, this.options, this.commands);
    }

}
