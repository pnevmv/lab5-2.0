package commands;

import exceptions.WrongAmountOfElementsException;
import utility.Console;

/**
 * Command 'exit'. Checks for wrong arguments then finishing app.
 */
public class ExitCommand extends AbstractCommand{

    public ExitCommand() {
        super("exit", "Exit without saving");
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new WrongAmountOfElementsException();
            Console.println("Closing the application ...");
            System.exit(0);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Executing the: '" + getName() + "'");
        }
        return false;
    }
}
