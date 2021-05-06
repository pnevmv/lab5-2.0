package commands;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'save'. Saves the collection to a file.
 */
public class SaveCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save", "Save collection to file");
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
            collectionManager.saveCollection();
            System.out.println("Collection saving finished");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Executing the: '" + getName() + "'");
        }
        return false;
    }
}
