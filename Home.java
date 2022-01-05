import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Home extends Insurable {
    // Private fields of the Insurable class
    private String postalCode;
    private Date buildDate;

    // Static field used to match the command name from the user's input to this class
    static final String inputTag = "HOME";

    // Constructor that takes the information provided from the tags and assigns that to
    // the fields of the class
    Home(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        // Sends tags to the super class to initialize the constructor of the Insurable class
        super(tags);
        // Assigns the fields with the necessary information taken from the tags
        postalCode = tags.get("POSTAL_CODE").get(0).getValue();
        buildDate = Utils.convertDate(tags.get("BUILD_DATE").get(0).getValue());
    }

    // Returns the postal code of the home
    public String getPostalCode() {
        return postalCode;
    }

    // Returns the build date of the home
    public Date getBuildDate() {
        return buildDate;
    }

    // Returns the input tag
    public static String getInputTag() {
        return inputTag;
    }
}
