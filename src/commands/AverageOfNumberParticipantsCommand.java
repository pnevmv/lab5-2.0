package commands;

import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;
/**
 * Command 'AverageOfNumberOfParticipants'. Return average of number of participants in whole collection.
 */
public class AverageOfNumberParticipantsCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public AverageOfNumberParticipantsCommand(CollectionManager collectionManager) {
        super("average_of_number_of_participant", "Print the average of the numberOfParticipants field for all elements in the collection");
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
            double avNum = collectionManager.getAverageOfNumberOfParticipants();
            if (avNum == 0) throw new CollectionIsEmptyException();
            Console.println("Average of number of participants: " + avNum);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Executing the: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("Collection is empty!");
        }
        return false;
    }
}
