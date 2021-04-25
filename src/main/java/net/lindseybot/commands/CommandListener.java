package net.lindseybot.commands;

/**
 * Used for grouping all command listeners.
 */
public interface CommandListener {

    /**
     * @return Full command description, with all arguments and subcommands.
     */
    Command describe();

}
