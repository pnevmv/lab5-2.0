package commands;

import data.Coordinates;
import data.MusicBand;
import data.MusicGenre;
import data.Studio;
import exceptions.BandCanNotFoundException;
import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputScriptException;
import exceptions.WrongAmountOfElementsException;
import utility.BandBuilder;
import utility.CollectionManager;
import utility.Console;

import java.time.LocalDateTime;

/**
 * Command 'update'. Updates the information about selected marine.
 */
public class UpdateCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private BandBuilder bandBuilder;

    public UpdateCommand(CollectionManager collectionManager, BandBuilder bandBuilder) {
        super("update id {element}", "Update the value of the collection item whose id is equal to the given");
        this.bandBuilder = bandBuilder;
        this.collectionManager = collectionManager;
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

            Integer id = Integer.parseInt(arg);
            MusicBand oldBand = collectionManager.getById(id);
            if (oldBand == null) throw new BandCanNotFoundException();

            String oldName = oldBand.getName();
            Coordinates oldCoordinates = oldBand.getCoordinates();
            LocalDateTime oldTime = oldBand.getCreationDate();
            Long oldNumber = oldBand.getNumberOfParticipants();
            String oldDescription = oldBand.getDescription();
            MusicGenre oldGenre = oldBand.getGenre();
            Studio oldStudio = oldBand.getStudio();

            String name;
            Coordinates coordinates;
            Long number;
            String description;
            MusicGenre genre;
            Studio studio;

            if (bandBuilder.askQuestion("Do you want to change the name of the band?")) name = bandBuilder.askName();
                    else name = oldName;
            if (bandBuilder.askQuestion("Do want to change bands coordinates?")) coordinates = bandBuilder.askCoordinates();
            else coordinates = oldCoordinates;
            if (bandBuilder.askQuestion("Do you want to change the number of participants??")) number = bandBuilder.askNumberOfParticipant();
            else number = oldNumber;
            if (bandBuilder.askQuestion("Do you want to change bands description?")) description = bandBuilder.askDescription();
            else description = oldDescription;
            if (bandBuilder.askQuestion("Do you want to change bands genre?")) genre = bandBuilder.askGenre();
            else genre = oldGenre;
            if (bandBuilder.askQuestion("Do you want to change studio of band?")) studio = bandBuilder.askStudio();
            else studio = oldStudio;

            collectionManager.addToCollection(collectionManager.generateNextId(),
                    new MusicBand(id, name, coordinates, oldTime, number, description, genre, studio));
            collectionManager.removeById(id);
            Console.println("Group updated successfully!");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Executing: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("Collection is empty!");
        } catch (NumberFormatException exception) {
            Console.printError("ID must be represented by a number!");
        } catch (BandCanNotFoundException exception) {
            Console.printError("There is no band with this ID in the collection!");
        } catch (IncorrectInputScriptException exception) {}
        return false;
    }
}
