package commands;

import exceptions.CollectionIsEmptyException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'remove_lower_key'. Removes all element if it's key is lower'.
 */
public class RemoveLowerKeyCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public RemoveLowerKeyCommand(CollectionManager collectionManager) {
        super("remove_lower_key null", "Remove from the collection all elements whose key is less than the specified one");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String arg) {
        try {
            if (arg.isEmpty()) throw new IllegalArgumentException();

            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            int key = Integer.parseInt(arg);
            collectionManager.removeLowerKey(key);
            Console.println("Bands successfully deleted!");
            return true;
        } catch (CollectionIsEmptyException exception) {
            Console.printError("Collection is empty!");
        } catch (IllegalArgumentException exc) {
            Console.printError("Invalid command argument!");
        }
        return false;
    }
}
