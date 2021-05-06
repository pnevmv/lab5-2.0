package utility;

import commands.Command;
import exceptions.ScriptRecursionException;
import run.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Operates command input.
 */
public class Console {
    private CommandManager commandManager;
    private Scanner scanner;
    private BandBuilder bandBuilder;
    private List<String> scriptStack = new ArrayList<>();

    public Console(CommandManager commandManager, Scanner scanner, BandBuilder bandBuilder) {
        this.commandManager = commandManager;
        this.scanner = scanner;
        this.bandBuilder = bandBuilder;
    }

    /**
     * Mode for catching commands from user input.
     */
    public void interactiveMode() {
        String[] userCommand;
        int commandStatus;
        try {
            do {
                Console.print(App.PS1);
                userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        } catch (NoSuchElementException exception) {
            Console.printError("No user input detected!");
        } catch (IllegalStateException exception) {
            Console.printError("Unexpected error!");
        }
    }

    /**
     * Mode for catching commands from a script.
     * @param argument Its argument.
     * @return Exit code.
     */
    public int scriptMode(String argument) {
        String[] userCommand;
        int commandStatus;
        scriptStack.add(argument);
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = bandBuilder.getUserScanner();
            bandBuilder.setUserScanner(scriptScanner);
            bandBuilder.setFileMode();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                Console.println(App.PS1 + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            bandBuilder.setUserScanner(tmpScanner);
            bandBuilder.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                Console.println("Check the script for the correctness of the entered data!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            Console.printError("Script file not found!");
        } catch (NoSuchElementException exception) {
            Console.printError("The script file is empty!");
        } catch (ScriptRecursionException exception) {
            Console.printError("Scripts cannot be called recursively!");
        } catch (IllegalStateException exception) {
            Console.printError("Unexpected error!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size()-1);
        }
        return 1;
    }

    /**
     * Launch the command.
     * @param userCommand Command to launch.
     * @return Exit code.
     */

    private int launchCommand(String[] userCommand) {
        List<Command> list = commandManager.getCommands();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(userCommand[0]) && !(list.get(i).getName().equals("help"))) {
                list.get(i).execute(userCommand[1]);
                return 1;
            }
        }
        if (userCommand[0].equals("execute_script")) {
            if (!commandManager.executeScript(userCommand[1])) return 1;
            else return scriptMode(userCommand[1]);
        }
        if (userCommand[0].equals("help")) {
            commandManager.getAllCommands(userCommand[1]);
            return 1;
        }
        commandManager.noSuchCommand(userCommand[0]);
        return 0;
    }

    /**
     * Prints toOut.toString() to Console
     * @param toOut Object to print
     */
    public static void print(Object toOut) {
        System.out.print(toOut);
    }

    /**
     * Prints toOut.toString() + \n to Console
     * @param toOut Object to print
     */
    public static void println(Object toOut) {
        System.out.println(toOut);
    }

    /**
     * Prints formatted 2-element table to Console
     * @param element1 Left element of the row.
     * @param element2 Right element of the row.
     */
    public static void printable(Object element1, Object element2) {
        System.out.printf("%-37s%-1s%n", element1, element2);
    }

    /**
     * Prints error: toOut.toString() to Console
     * @param toOut Error to print
     */
    public static void printError(Object toOut) {
        System.out.println("error: " + toOut);
    }

    @Override
    public String toString() {
        return "Console - class for handling command input";
    }
}
