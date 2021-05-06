package commands;

import exceptions.BandCanNotFoundException;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'remove_key'. Removes the element by key.
 */
public class RemoveCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveCommand(CollectionManager collectionManager) {
        super("remove_key null", "Remove an item from the collection by its key");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String arg) {
        try {
            if (arg.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Integer key = Integer.parseInt(arg);
            if (collectionManager.getByKey(key) == null) throw new BandCanNotFoundException();
            collectionManager.removeFromCollection(key);
            Console.println("Band successfully deleted!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Executing: '" + getName() + "'. Key not entered!");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("The collection is empty!");
        } catch (NumberFormatException exception) {
            Console.printError("The key must be represented by a number!");
        } catch (BandCanNotFoundException exception) {
            Console.printError("The group with the given key was not found!");
        }
        return false;
    }
}
