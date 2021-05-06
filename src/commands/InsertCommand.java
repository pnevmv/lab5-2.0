package commands;

import data.MusicBand;
import exceptions.IncorrectInputScriptException;
import exceptions.WrongAmountOfElementsException;
import utility.BandBuilder;
import utility.CollectionManager;
import utility.Console;

import java.time.LocalDateTime;

/**
 * Command 'insert key'. Adds new element with key.
 */
public class InsertCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private BandBuilder bandBuilder;

    public InsertCommand(CollectionManager collectionManager, BandBuilder bandBuilder) {
        super("insert null {element}", "Add a new item with the given key");
        this.collectionManager = collectionManager;
        this.bandBuilder = bandBuilder;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            Integer IdAndKey = collectionManager.generateNextId();
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.addToCollection(Integer.parseInt(argument),
                    new MusicBand(IdAndKey, bandBuilder.askName(), bandBuilder.askCoordinates(),
                            LocalDateTime.now(), bandBuilder.askNumberOfParticipant(),
                            bandBuilder.askDescription(), bandBuilder.askGenre(),
                            bandBuilder.askStudio()));
            Console.println("Band added successfully!");
            return true;
        } catch (WrongAmountOfElementsException | IncorrectInputScriptException exception) {
            Console.printError("The command was entered in the wrong format!");
        }
        return false;
    }
}
