import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Car extends Insurable {

    // The private fields of the Car class
    private String make;
    private String model;
    private Date purchaseDate;
    private long mileage;

    // Static field used to match the command name from the user's input to this class
    static final String inputTag = "CAR";

    // Constructor that takes the information provided from the tags and assigns that to
    // the fields of the class
    Car(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        // Sends tags to the super class to initialize the constructor of the Insurable class
        super(tags);
        // Assigns the fields with the necessary information taken from the tags
        make = tags.get("MAKE").get(0).getValue();
        model = tags.get("MODEL").get(0).getValue();
        purchaseDate = Utils.convertDate(tags.get("PURCHASE_DATE").get(0).getValue());
        mileage = Long.parseLong(tags.get("MILEAGE").get(0).getValue());
    }

    // Returns the owner's name
    public String getOwnerName() {
        return ownerName;
    }

    // Returns the make of the car
    public String getMake() {
        return make;
    }

    // Returns the model of the car
    public String getModel() {
        return model;
    }

    // Returns the date the car was purchased
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    // Returns the current mileage on the car
    public long getMileage() {
        return mileage;
    }

    // Returns the input tag
    public static String getInputTag() {
        return inputTag;
    }
}

