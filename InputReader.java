import java.util.ArrayList;
import java.util.Scanner;

class InputReader {
    // Private fields of the InputReader class
    private Scanner keyboard;
    private static InputReader instance = null;
    private int lineNumber = 0;

    // InputReader function that initializes the Scanner object
    private InputReader() {
        keyboard = new Scanner(System.in);
    }

    // Returns the instance of the InputReader if it already exists and creates the instance
    // if it doesn't exist and then returns it
    static InputReader getInstance() {
        // If instance is null it is set as a new instance of the InputReader class
        if (instance == null) {
            instance = new InputReader();
        }
        return instance;
    }

    // This function goes through all the input and based on what the line starts with it the code
    // is sent to another function that executes the line. The instance of the object that is
    // returned is then stored in an ArrayList of the Command object
    ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        String line = "";
        lineNumber = 0;

        // Try block to catch BadCommandException
        try {
            // Loops as long the loop has a next line
            while (keyboard.hasNext()) {
                lineNumber++;
                line = keyboard.nextLine();
                // If the line starts with the String "PRINT " the line is sent to
                // makePrintCommand and adds that to the commands ArrayList
                if (line.startsWith("PRINT ")) {
                    commands.add(makePrintCommand(line));
                }
                // If the line starts with the String "BEGIN_" the line is sent to
                // makeBlockCommand and adds that to the commands ArrayList
                else if (line.startsWith("BEGIN_")) {
                    commands.add(makeBlockCommand(line));
                }
                // If the line starts with the String "FINISH" the loop is broken
                else if (line.equals("FINISH")) {
                    break;
                }
                // If line is not the empty String then the line is outputted and
                // a BadCommandException is thrown
                else if (!line.equals("")) {
                    System.out.println(line);
                    throw new BadCommandException("Invalid command.");
                }
            }
        } catch (BadCommandException e) {
            // If there is a BadCommandException then a statement is outputted with the line
            // indicating where the exception was thrown
            throw new BadCommandException("Line " + lineNumber + " : " + e.getMessage());
        }
        return commands;
    }

    // The makeBlockCommand function first parses the first line to determine the type of command
    // and then it goes through each line of the command block which is tokenized and then added
    // as tags to the instance of the BlockCommand object
    private Command makeBlockCommand(String line) {
        // Removes "BEGIN_" from the current line to get the command type;
        BlockCommand command = new BlockCommand(line.substring(6));

        while (keyboard.hasNext()) {
            lineNumber ++;
            line = keyboard.nextLine();
            // If the line ends with "END_" and with the block type then the command is returned
            if (line.equals("END_" + command.getBlockType())) {
                return command;
            }
            // If the line is the empty String then it is skipped over
            else if (line.equals("")) {
            }
            // Otherwise the line is split into tokens
            else {
                String [] tokens = line.split(" ", 3);
                // If the token length is not three or the length of the second token is not one
                // then a BadCommandException is thrown
                if (tokens.length != 3 || tokens[1].length() != 1)
                    throw new BadCommandException("Invalid tag.");
                // Tokens are added to the tag of the command
                command.addTag(new Tag(tokens));
            }
        }
        return command;
    }

    // The makePrintCommand function splits the String that was passed to it and splits it into
    // tokens. If the number of tokens is not equal to 4 then a BadCommandException is thrown.
    // Otherwise it returns an instance of the PrintCommand that holds the tokens
    private Command makePrintCommand(String line) {
        // The line is split into tokens
        String[] tokens = line.split(" ", 5);
        // If there are more than 4 tokens then a BadCommandException is thrown
        if (tokens.length > 4) {
            throw new BadCommandException("Invalid print command; too many tokens.");
        }
        // If there are less than 4 tokens then a BadCommandException is thrown
        else if (tokens.length < 4) {
                throw new BadCommandException("Invalid print command; too few tokens.");
        }
        return new PrintCommand(tokens);
    }
}