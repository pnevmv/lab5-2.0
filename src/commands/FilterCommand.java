package commands;

import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'filter_starts_with description'. Finds element with description start.
 */
public class FilterCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public FilterCommand(CollectionManager collectionManager) {
        super("filter_starts_with description", "Display elements whose description field value begins with a given substring");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            String filteredInfo = collectionManager.descriptionFilter(argument);
            if (!filteredInfo.isEmpty()) {
                Console.println(filteredInfo);
                return true;
            } else Console.println("There are no groups in the collection with a suitable description!");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Executing the: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("Collection is empty!");
        } catch (IllegalArgumentException exception) {
            Console.printError("Description isn't in collection!");
        }
        return false;
    }
}
