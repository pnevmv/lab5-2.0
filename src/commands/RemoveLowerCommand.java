package commands;

import data.MusicBand;
import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputScriptException;
import utility.BandBuilder;
import utility.CollectionManager;
import utility.Console;

import java.time.LocalDateTime;

/**
 * Command 'remove_lower'. Removes all elements it's lower.
 */
public class RemoveLowerCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private BandBuilder bandBuilder;

    public RemoveLowerCommand(CollectionManager collectionManager, BandBuilder bandBuilder) {
        super("remove_lower {element}", "Remove all items from the collection that are less than the specified one");
        this.collectionManager = collectionManager;
        this.bandBuilder = bandBuilder;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new IncorrectInputScriptException();

            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            MusicBand bandToCompare = new MusicBand(
                    collectionManager.generateNextId(),
                    bandBuilder.askName(),
                    bandBuilder.askCoordinates(),
                    LocalDateTime.now(),
                    bandBuilder.askNumberOfParticipant(),
                    bandBuilder.askDescription(),
                    bandBuilder.askGenre(),
                    bandBuilder.askStudio()
            );
            collectionManager.removeLower(bandToCompare);
            Console.println("Groups successfully deleted!");
            return true;
        } catch (CollectionIsEmptyException exception) {
            Console.printError("The collection is empty!");
        } catch (IncorrectInputScriptException exception) {}
        return false;
    }
}
