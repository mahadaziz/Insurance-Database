import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // Try statement to catch a parse exception or a bad command exception
        try {
            // Creates an instance of the inputReader class that will be used to take
            // user input
            InputReader inputReader = InputReader.getInstance();
            // An arraylist of the Commands object is created to store the commands from
            // the user's input
            ArrayList<Command> commands = inputReader.getCommands();
            // The iterator object is then used to iterate through the commands stored
            // in the ArrayList
            Iterator<Command> currentCommand = commands.iterator();

            // Creates a CommandHandler object that will hold the database
            CommandHandler commandHandler = new CommandHandler(new Database());

            // Iterate through the commands and adds the information to the database
            while (currentCommand.hasNext()) {
                commandHandler.run(currentCommand.next());
            }
        } catch (ParseException e) {
            // If a ParseException occurs then the exception is displayed to the user
            System.out.println(e.getMessage());
        } catch (BadCommandException e) {
            // If a BadCommandException occurs then the exception is displayed to the user
            System.out.println(e.getMessage());
        }
    }
}
