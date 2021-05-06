package run;

import utility.*;
import commands.*;

import java.util.Scanner;

/**
 * Main application class. Creates all instances and runs the program.
 * @author Smirnov Danil
 */
public class App{
    public static final String PS1 = ">>> ";
    public static final String PS2 = "$>> ";

    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {
        BandBuilder bandBuilder = new BandBuilder(userScanner);
        FileManager fileManager = new FileManager(args[0]);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        CommandManager commandManager = new CommandManager(
                new HelpCommand(),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new InsertCommand(collectionManager, bandBuilder),
                new UpdateCommand(collectionManager, bandBuilder),
                new RemoveCommand(collectionManager),
                new ClearCommand(collectionManager),
                new SaveCommand(collectionManager),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new RemoveLowerCommand(collectionManager, bandBuilder),
                new ReplaceIfGreaterCommand(collectionManager, bandBuilder),
                new RemoveLowerKeyCommand(collectionManager),
                new AverageOfNumberParticipantsCommand(collectionManager),
                new FilterCommand(collectionManager),
                new FieldsOfDescriptionsCommand(collectionManager));
        Console console = new Console(commandManager, userScanner, bandBuilder);
        console.interactiveMode();
    }
    }
}