import java.util.ArrayList;
import java.util.HashMap;

class Insurable {
    // The protected fields of the Insurable class
    protected String ownerName;
    protected long value;

    // Constructor that takes the information provided from the tags and assigns that to
    // the fields of the class
    Insurable(HashMap<String, ArrayList<Tag>> tags) {
        // Assigns the fields with the necessary information taken from the tags
        ownerName = tags.get("OWNER_NAME").get(0).getValue();
        value = Long.parseLong(tags.get("VALUE").get(0).getValue());
    }

    // Returns the owner's name
    public String getOwnerName() {
        return ownerName;
    }

    // Returns the value
    public long getValue() {
        return value;
    }
}
