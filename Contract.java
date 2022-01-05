import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Contract {
    // Private fields of the Contract class
    private String contractName;
    private String customerName;
    private String planName;
    private Date startDate;
    private Date endDate;
    private int discountPercentage;

    // Static field used to match the command name from the user's input to this class
    static final String inputTag = "CONTRACT";

    // Constructor that takes the information provided from the tags and assigns that to
    // the fields of the class
    Contract(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        // Assigns the fields with the necessary information taken from the tags
        contractName = tags.get("CONTRACT_NAME").get(0).getValue();
        customerName = tags.get("CUSTOMER_NAME").get(0).getValue();
        planName = tags.get("PLAN_NAME").get(0).getValue();
        startDate = Utils.convertDate(tags.get("START_DATE").get(0).getValue());
        endDate = Utils.convertDate(tags.get("END_DATE").get(0).getValue());
        discountPercentage = Integer.parseInt(tags.get("DISCOUNT_PERCENTAGE").get(0).getValue());
    }

    // Returns the name of the customer
    public String getCustomerName() {
        return customerName;
    }

    // Returns the name of the plan
    public String getPlanName() {
        return planName;
    }

    // Returns the start date of the plan
    public Date getStartDate() {
        return startDate;
    }

    // Returns the end date of the plan
    public Date getEndDate() {
        return endDate;
    }

    // Returns the discount percentage of the plan
    public int getDiscountPercentage() {
        return discountPercentage;
    }

    // Returns the input tag
    public static String getInputTag() {
        return inputTag;
    }

    // Returns the name of the contract
    public String getContractName() {
        return contractName;
    }
}
