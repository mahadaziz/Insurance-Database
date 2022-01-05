import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

abstract class Plan {
    // The fields of the Plan class
    String name;
    long premium;
    long maxCoveragePerClaim;
    long deductible;
    RangeCriterion customerAgeCriterion = new RangeCriterion();
    RangeCriterion customerIncomeCriterion = new RangeCriterion();

    // Constructor that takes the information provided from the tags and assigns that to
    // the fields of the class. Also checks if "CUSTOMER.AGE" is a tag and if it is the age i
    // is added to the instance of the RangeCriterion class. Also checks if "CUSTOMER.INCOME"
    // is a tag and if it is the customer income is added to the instance of the
    // RangeCriterion class.
    Plan(HashMap<String, ArrayList<Tag>> tags) {
        // Assigns the fields with the necessary information taken from the tags
        name = tags.get("NAME").get(0).getValue();
        premium = Integer.parseInt(tags.get("PREMIUM").get(0).getValue());
        maxCoveragePerClaim =
                Integer.parseInt(tags.get("MAX_COVERAGE_PER_CLAIM").get(0).getValue());
        deductible = Integer.parseInt(tags.get("DEDUCTIBLE").get(0).getValue());

        // If the tags contain "CUSTOMER.AGE" then those tags are added to the instance of the
        // RangeCriterion class
        if (tags.get("CUSTOMER.AGE") != null) {
            // Iterates through all the tags that have "CUSTOMER.AGE"
            for (Tag tag: tags.get("CUSTOMER.AGE")) {
                customerAgeCriterion.addCriterion(tag);
            }
        }
        // If the tags contain "CUSTOMER.INCOME" then those tags are added to the instance of the
        // RangeCriterion class
        if (tags.get("CUSTOMER.INCOME") != null) {
            // Iterates through all the tags that have "CUSTOMER.INCOME"
            for (Tag tag: tags.get("CUSTOMER.INCOME")) {
                customerIncomeCriterion.addCriterion(tag);
            }
        }

    }

    // isEligible is an abstract function that takes an Insurable and Date object
    abstract boolean isEligible(Insurable insurable, Date date);

    // getInsuredItem is an abstract function that takes a Customer and Database object
    abstract Insurable getInsuredItem(Customer customer, Database database);

    boolean isEligible(Customer customer, Date currentDate) {
        // Extracting the approximate age of the customer (just based on the calendar years)
        LocalDate localCurrentDate =
                currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBirthDate =
                customer.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long age = localCurrentDate.getYear() - localBirthDate.getYear();
        // Checking if the age is in the range.
        if (!customerAgeCriterion.isInRange(age))
            return false;
        // Checking if the income is in the range.
        return customerIncomeCriterion.isInRange(customer.getIncome());
    }

    // Returns the name of the plan
    String getName() {
        return name;
    }

    // Returns the amount of premium
    long getPremium() {
        return premium;
    }

    // Returns the max coverage the customer can get per claim
    long getMaxCoveragePerClaim() {
        return maxCoveragePerClaim;
    }

    // Returns the deductible amount
    long getDeductible() {
        return deductible;
    }
}
