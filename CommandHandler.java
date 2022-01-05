import java.text.ParseException;

class CommandHandler {
    // The fields of the CommandHandler class
    Database database;

    // Constructor that provides the database field access to the database already being used
    CommandHandler(Database database) {
        this.database = database;
    }

    // Run function that sends the database to the run function in the Command class
    void run(Command command) throws ParseException {
        command.run(database);
    }
}
