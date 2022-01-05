import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Claim {
    // Private fields of the Claim class
    private String contractName;
    private long amount;
    private Date date;
    private boolean successful;

    // Static field used to match the command name from the user's input to this class
    static final String inputTag = "CLAIM";

    // Constructor that takes the information provided from the tags and assigns that to
    // the fields of the class
    Claim(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        // Assigns the fields with the necessary information taken from the tags
        contractName = tags.get("CONTRACT_NAME").get(0).getValue();
        date = Utils.convertDate(tags.get("DATE").get(0).getValue());
        amount = Long.parseLong(tags.get("AMOUNT").get(0).getValue());
    }

    // Returns the name of the contract
    public String getContractName() {
        return contractName;
    }

    // Returns the amount fo the contract
    public long getAmount() {
        return amount;
    }

    // Returns the date the claim was made
    public Date getDate() {
        return date;
    }

    // Returns whether the claim was successful
    public boolean wasSuccessful() {
        return successful;
    }

    // Sets the successful field based on the boolean value sent to it
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
