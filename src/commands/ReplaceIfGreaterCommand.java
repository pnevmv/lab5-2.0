package commands;

import data.MusicBand;
import exceptions.BandCanNotFoundException;
import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputScriptException;
import exceptions.WrongAmountOfElementsException;
import utility.BandBuilder;
import utility.CollectionManager;
import utility.Console;

import java.time.LocalDateTime;

/**
 * Command 'replace_if_greater'. Replace element greater than user entered by key.
 */
public class ReplaceIfGreaterCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private BandBuilder bandBuilder;

    public ReplaceIfGreaterCommand(CollectionManager collectionManager, BandBuilder bandBuilder) {
        super("replace_if_greater null {element}", "Replace value by key if new value is greater than old");
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
            if (arg.isEmpty()) throw new WrongAmountOfElementsException();
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
            if (collectionManager.getByKey(Integer.parseInt(arg)) == null) throw new BandCanNotFoundException();
            collectionManager.replaceIfGreater(Integer.parseInt(arg), bandToCompare);
            Console.println("Band successfully replaced!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Executing: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("Collection is empty!");
        } catch (BandCanNotFoundException exception) {
            Console.printError("There is no band with such a key");
        } catch (IncorrectInputScriptException exception) {}
        return false;
    }
}
