package commands;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;
/**
 * Command 'clear'. Cleans the collection.
 */
public class ClearCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear","Clear collection");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.clearCollection();
            Console.println("Collection cleared!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Executing the: '" + getName() + "'");
        }
        return false;
    }
}
