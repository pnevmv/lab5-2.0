package commands;

import exceptions.CollectionIsEmptyException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'print_fields_ascending_description'. Prints all descriptions.
 */
public class FieldsOfDescriptionsCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public FieldsOfDescriptionsCommand(CollectionManager collectionManager) {
        super("print_field_ascending_description", "Print the values of the description field of all elements in ascending order");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            String filteredInfo = collectionManager.getAllDescriptions();
            if (!filteredInfo.isEmpty()) {
                Console.println(filteredInfo);
                return true;
            } else Console.println("There are no bands in the collection with such a description.!");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("Collection is empty!");
        } catch (IllegalArgumentException exception) {
            Console.printError("Description isn't in collection!");
        }
        return false;
    }
}
