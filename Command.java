import java.text.ParseException;

abstract class Command {
    // Run function that takes the database and throws a ParseException if it is not passed
    abstract void run(Database database) throws ParseException;
}
