package utility;

import java.util.NoSuchElementException;
import java.util.Scanner;
import data.*;
import exceptions.DeclaredLimitException;
import exceptions.IncorrectInputScriptException;
import exceptions.MustBeNotEmptyException;
import run.App;

/**
 * Asks a user a band's value.
 */
public class BandBuilder {
    private final int MIN_NUMBER_OF_MEMBERS = 1;

    private Scanner userScanner;
    private boolean fileMode;

    public BandBuilder(Scanner scan) {
        this.userScanner = scan;
        fileMode = false;
    }

    /**
     * Sets a scanner to scan user input.
     * @param scanner Scanner to set.
     */
    public void setUserScanner(Scanner scanner) {
        this.userScanner = scanner;
    }

    /**
     * @return Scanner, which uses for user input.
     */
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Sets band builder mode to 'File Mode'.
     */
    public void setFileMode() {
        fileMode = true;
    }

    /**
     * Sets band builder mode to 'User Mode'.
     */
    public void setUserMode() {
        fileMode = false;
    }

    /**
     * Asks a user the marine's name.
     * @return Band's name.
     * @throws IncorrectInputScriptException If script is running and something goes wrong.
     */
    public String askName() throws IncorrectInputScriptException {
        String name;
        while (true) {
            try {
                Console.println("Enter the name:");
                Console.print(App.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exc) {
                Console.printError("Error! Name not recognized!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (MustBeNotEmptyException exc) {
                Console.printError("Error! The name cannot be empty!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (IllegalStateException exc) {
                Console.printError("Unexpected error!");
            }
        }
        return name;
    }

    /**
     * Asks a user the marine's X coordinate.
     * @return Band's X coordinate.
     * @throws IncorrectInputScriptException If script is running and something goes wrong.
     */
    public Double askX() throws IncorrectInputScriptException {
        String strX;
        Double x;
        while (true) {
            try {
                Console.println("Enter the X coordinate:");
                Console.print(App.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Double.parseDouble(strX);
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("X coordinate not recognized!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("The X coordinate must be represented by a number!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return x;
    }

    /**
     * Asks a user the marine's X coordinate.
     * @return Band's Y coordinate.
     * @throws IncorrectInputScriptException If script is running and something goes wrong.
     */
    public long askY() throws IncorrectInputScriptException {
        String strY;
        long y;
        while (true) {
            try {
                Console.println("Enter Y coordinate:");
                Console.print(App.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println(strY);
                y = Long.valueOf(strY);
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Y coordinate not recognized!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("The Y coordinate must be an integer!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return y;
    }

    /**
     * Asks a user the marine's X coordinate.
     * @return Band's X,Y coordinates.
     * @throws IncorrectInputScriptException If script is running and something goes wrong.
     */
    public Coordinates askCoordinates() throws IncorrectInputScriptException {
        Double x;
        long y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }

    /**
     * Asks a user the marine's X coordinate.
     * @return Band's number of participants.
     * @throws IncorrectInputScriptException If script is running and something goes wrong.
     */
    public Long askNumberOfParticipant() throws IncorrectInputScriptException {
        Long number;
        while (true) {
            try {
                Console.println("Enter the number of participants(> 0):");
                Console.print(App.PS2);
                number = Long.valueOf(userScanner.nextLine().trim());
                if (number < MIN_NUMBER_OF_MEMBERS) throw new DeclaredLimitException();
                if (fileMode) Console.println(number);
                break;
            } catch (NumberFormatException | NoSuchElementException | DeclaredLimitException exception) {
                Console.printError("Quantity must be a natural number!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return number;
    }

    /**
     * Asks a user the marine's X coordinate.
     * @return Band's description.
     * @throws IncorrectInputScriptException If script is running and something goes wrong.
     */
    public String askDescription() throws IncorrectInputScriptException {
        String desc;
        while (true) {
            try {
                Console.println("Enter a description:");
                Console.print(App.PS2);
                desc = userScanner.nextLine().trim();
                if (desc.equals("")) throw new NoSuchElementException();
                if (fileMode) Console.println(desc);
                break;
            } catch (NoSuchElementException | IllegalStateException exception) {
                Console.printError("Description cannot be empty!");
                if (fileMode) throw new NullPointerException();
            } catch (NullPointerException exception) {
                Console.printError("Invalid value entered!");
                System.exit(0);
            }
        }
        return desc;
    }

    /**
     * Asks a user the marine's X coordinate.
     * @return Band's studio.
     */
    public Studio askStudio(){
        String address;
        while (true) {
            try {
                Console.println("Enter studio address:");
                Console.print(App.PS2);
                address = userScanner.nextLine().trim();
                if (address.equals("")) return null;
                if (fileMode) Console.println(address);
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Value not recognized!");
                System.exit(0);
            }
        }
        return new Studio(address);
    }

    /**
     * Asks a user the marine's X coordinate.
     * @return Band's music genre.
     * @throws IncorrectInputScriptException If script is running and something goes wrong.
     */
    public MusicGenre askGenre() throws IncorrectInputScriptException {
        MusicGenre genre;
        while (true) {
            try {
                Console.println("Enter music genre" + "(possible values: " + MusicGenre.nameList() + "):");
                Console.print(App.PS2);
                String genre1 = userScanner.nextLine().trim();
                if (genre1.equals("SOUL") || genre1.equals("POST_ROCK") || genre1.equals("PUNK_ROCK") || genre1.equals("PROGRESSIVE_ROCK"))
                    genre = MusicGenre.valueOf(genre1);
                else throw new IllegalStateException();
                if (fileMode) Console.println(genre1);
                break;
            } catch (NoSuchElementException | IllegalStateException exception) {
                Console.printError("Value not recognized!");
                if (fileMode) throw new NullPointerException();
            } catch (NullPointerException exception) {
                Console.printError("Invalid value entered!");
                System.exit(0);
            }
        }
        return genre;
    }

    /**
     * Asks a user a question.
     * @return Answer (true/false).
     * @param question A question.
     * @throws IncorrectInputScriptException If script is running and something goes wrong.
     */
    public boolean askQuestion(String question) throws IncorrectInputScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                Console.print(App.PS2);
                answer = userScanner.nextLine().trim();
                if (fileMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new DeclaredLimitException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("The answer is not recognized!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (DeclaredLimitException exception) {
                Console.printError("The answer must be in signs '+' or '-'!");
                if (fileMode) throw new IncorrectInputScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return answer.equals("+");
    }

    @Override
    public String toString() {
        return "BandBuilder (helper class for prompting the user)";
    }
}
