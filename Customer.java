import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Customer {
    // Private fields of the Customer class
    private String name;
    private Date dateOfBirth;
    private long income;

    // Static field used to match the command name from the user's input to this class
    static final String inputTag = "CUSTOMER";

    // Constructor that takes the information provided from the tags and assigns that to
    // the fields of the class
    Customer(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        // Assigns the fields with the necessary information taken from the tags
        name = tags.get("NAME").get(0).getValue();
        dateOfBirth = Utils.convertDate(tags.get("DATE_OF_BIRTH").get(0).getValue());
        income = Long.parseLong(tags.get("INCOME").get(0).getValue());
    }

    // Returns the name of the customer
    public String getName() {
        return name;
    }

    // Returns the customer's date of birth
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    // Returns the customer's income
    public long getIncome() {
        return income;
    }

    // Returns the input tag
    public static String getInputTag() {
        return inputTag;
    }
}
