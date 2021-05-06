package utility;

import commands.Command;
import commands.ExecuteScriptCommand;
import commands.HelpCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Operates the commands.
 */
public class CommandManager {
    private List<Command> commands = new ArrayList<>();

    public CommandManager(Command... commands1) {
        for (int i = 0; i < commands1.length; i++) {
            commands.add(commands1[i]);
        }
    }

    /**
     * Prints that command is not found.
     * @param command Command, which is not found.
     * @return Command exit status.
     */
    public boolean noSuchCommand(String command) {
        Console.println("Command '" + command + "' not found. Type 'help' for help.");
        return false;
    }

    /**
     * Prints info about the all commands.
     * @param argument It's argument.
     * @return Command exit status.
     */
    public boolean getAllCommands(String argument) {
        HelpCommand helpCommand = new HelpCommand();
        if (helpCommand.execute(argument)) {
            for (Command command: commands) {
                Console.printable(command.getName(), command.getDescription());
            }
            return true;
        }
        else return false;
    }

    /**
     * Executes needed command.
     * @param argument It's argument.
     * @return Command exit status.
     */
    public boolean executeScript(String argument) {
        ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand();
        return executeScriptCommand.execute(argument);
    }

    public List<Command> getCommands() {
        return this.commands;
    }

    @Override
    public String toString() {
        return "CommandManager (helper class for working with commands)";
    }
}