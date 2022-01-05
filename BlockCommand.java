import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

class BlockCommand extends Command {
    // The private fields of the BlockCommand class
    private String blockType;
    private HashMap<String, ArrayList<Tag>> tags = new HashMap<>();

    // Constructor that sets the blockType field to the String that is passed to the constructor
    BlockCommand(String blockType) {
        this.blockType = blockType;
    }


    // addTag adds the given tag to the HashMap if the tag is not already previously
    // in the HashMap
    void addTag(Tag tag) {
        // If statement to see if HashMap already contains the tag
        if (!tags.containsKey(tag.getName())) {
            // If tag is not in HashMap then it is added to the HashMap
            tags.put(tag.getName(), new ArrayList<Tag> ());
        }
        // Adds the tag to its corresponding key in the HashMap
        tags.get(tag.getName()).add(tag);
    }

    // Returns the blockType field
    String getBlockType() {
        return blockType;
    }

    // The run method inserts the given information into the database based on the blockType
    @Override
    void run(Database database) throws ParseException {
        // If blockType is "CUSTOMER" then customer information is added to the database
        if (blockType.equals(Customer.inputTag)) {
            database.insertCustomer(new Customer(tags));
        }
        // If blockType is "HOME" then home information is added to the database
        if (blockType.equals(Home.inputTag)) {
            database.insertHome(new Home(tags));
        }
        // If blockType is "CAR" then car information is added to the database
        if (blockType.equals(Car.inputTag)) {
            database.insertCar(new Car(tags));
        }
        // If blockType is "CLAIM" then claim information is added to the database
        if (blockType.equals(Claim.inputTag)) {
            Claim claim = new Claim(tags);
            database.insertClaim(claim);
            // If the claim is able to be processed then the claim is set as successful and
            // a statement is displayed to the user
            if (processClaim(claim, database)) {
                claim.setSuccessful(true);
                System.out.println("Claim on " + Utils.formattedDate(claim.getDate())
                        + " for contract " + claim.getContractName() + " was successful.");
            }
            // If the claim is not able to be processed then the claim is set as not successful
            // and a statement is displayed to the user
            else {
                claim.setSuccessful(false);
                System.out.println("Claim on " + Utils.formattedDate(claim.getDate())
                        + " for contract " + claim.getContractName() + " was not successful.");
            }
        }
        // If blockType is "CONTRACT" then contract information is added to the database
        if (blockType.equals(Contract.inputTag)) {
            database.insertContract(new Contract(tags));
        }
        // If blockType is "HOME_PLAN" then home plan information is added to the database
        if (blockType.equals(HomePlan.inputTag)) {
            database.insertPlan(new HomePlan(tags));
        }
        // If blockType is "CAR_PLAN" then car plan information is added to the database
        if (blockType.equals(CarPlan.inputTag)) {
            database.insertPlan(new CarPlan(tags));
        }
    }

    // processClaim determines if the user claim is successful or not based on the requirements
    // of the contract and plan
    private boolean processClaim(Claim claim, Database database) {
        Contract contract = database.getContract(claim.getContractName());
        Customer customer = database.getCustomer(contract.getCustomerName());
        Plan plan = database.getPlan(contract.getPlanName());
        Insurable insurable = plan.getInsuredItem(customer, database);

        // If the claimed amount is higher than covered by the plan.
        if (plan.getMaxCoveragePerClaim() < claim.getAmount())
            return false;

        // If the claim date is not in the contract period.
        if (claim.getDate().after(contract.getEndDate()) ||
                claim.getDate().before(contract.getStartDate()))
            return false;

        // If the customer was not eligible.
        if (!plan.isEligible(customer, claim.getDate()))
            return false;

        return plan.isEligible(insurable, claim.getDate());
    }
}
