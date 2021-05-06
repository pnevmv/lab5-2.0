package commands;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

import java.time.LocalDateTime;

/**
 * Command 'info'. Prints information about the collection.
 */
public class InfoCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "Displaying information about a collection");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            LocalDateTime lastInitTime = collectionManager.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "no initialization has occurred in this session yet" :
                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();
            LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
            String lastSaveTimeString;
            if (lastSaveTime == null) lastSaveTimeString = "saving has not yet occurred in this session";
            else lastSaveTimeString = lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();
            Console.println("Collection information:\n Type:" + collectionManager.collectionType()
                    + "\n Amount of elements: " + collectionManager.collectionSize()
                    + "\n Last initialization date: " + lastInitTimeString
                    + "\n Last save date: " + lastSaveTimeString);
            return true;
        } catch (WrongAmountOfElementsException exc) {
            Console.println("Executing: '" + getName() + "'");
        }
        return false;
    }
}
