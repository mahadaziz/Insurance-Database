import java.text.ParseException;

class PrintCommand extends Command {
    // Private fields of the PrintCommand class
    private String entityType;
    private String queryType;
    private String queryValue;

    // Constructor that assigns the each token from the array of Strings to a field of the class
    PrintCommand(String[] tokens) {
        // Sends tags to the super class to initialize the constructor of the Command class
        super();
        // Assigns the fields with the necessary information taken from the tokens
        entityType = tokens[1];
        queryType = tokens[2];
        queryValue = tokens[3];
    }

    // This function determines the type of output the user is trying to receive and based on
    // the entityType it calls the function that will handle the user's expected output
    @Override
    void run(Database database) {
        // Calls runPrintCustomer if entityType is "CUSTOMER"
        if (entityType.equals("CUSTOMER"))
            runPrintCustomer(database);
        // Calls runPrintPlan if entityType is "PLAN"
        else if (entityType.equals("PLAN"))
            runPrintPlan(database);
        // Throws BadCommandException if the entityType is neither "CUSTOMER" or "PLAN"
        else {
            throw new BadCommandException("Bad print command.");
        }
    }

    // This function will display the output to the user based on what information they seek
    // regarding the customers
    private void runPrintCustomer(Database database) {
        // If the queryType is equal to "TOTAL_CLAIMED" then the user is displayed information
        // by calling the function from database that returns the amount claimed by a customer
        if (queryType.equals("TOTAL_CLAIMED")) {
            System.out.println("Total amount claimed by " +
                    database.getCustomer(queryValue).getName() +
                    " is " + database.totalClaimAmountByCustomer(queryValue));
        }
        // If the queryType is equal to "TOTAL_RECEIVED" then the user is displayed information
        // by calling the function from database that returns the amount received by a customer
        else if (queryType.equals("TOTAL_RECEIVED")) {
            System.out.println("Total amount received by " +
                    database.getCustomer(queryValue).getName() +
                    " is " + database.totalReceivedAmountByCustomer(queryValue));
        }
        // Throws BadCommandException if the queryType is neither "TOTAL_CLAIMED" or
        // "TOTAL_RECEIVED"
        else {
            throw new BadCommandException("Invalid PRINT CUSTOMER command.");
        }
    }

    // This function will display the output to the user based on what information they seek
    // regarding the plans
    //TODO
    private void runPrintPlan(Database database) {
        // If the queryType is equal to "NUM_CUSTOMERS" then the user is displayed information
        // by calling the function from database that returns the number of customers for a
        // given plan
        if (queryType.equals("NUM_CUSTOMERS")) {
            System.out.println("Number of customers under " + queryValue +
                    " is " + database.totalNumberOfCustomersForAPlan(queryValue));
        }
        // If the queryType is equal to "TOTAL_PAYED_TO_CUSTOMERS" then the user is
        // displayed information by calling the function from database that returns
        // the number of customers for a given plan
        else if (queryType.equals("TOTAL_PAYED_TO_CUSTOMERS")) {
            System.out.println("Total amount payed under " + queryValue +
                    " is " + database.totalPayedToCustomersForAPlan(queryValue));
        }
        // Throws BadCommandException if the queryType is neither "NUM_CUSTOMERS" or
        // "TOTAL_PAYED_TO_CUSTOMERS"
        else {
            throw new BadCommandException("Invalid PRINT PLAN command.");
        }
    }
}
