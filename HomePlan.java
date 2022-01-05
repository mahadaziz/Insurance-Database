import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class HomePlan extends Plan {
    // The fields of the HomePlan class
    static final String inputTag = "HOME_PLAN";
    private RangeCriterion homeValueCriterion = new RangeCriterion();
    private RangeCriterion homeAgeCriterion = new RangeCriterion();

    // Constructor that takes the information provided from the tags and assigns that to the
    // fields of the class. Also checks if "HOME.VALUE" is a tag and if it is the home value
    // is added to the instance of the RangeCriterion class. Also checks if "HOME.AGE" is a tag
    // and if it is the home value is added to the instance of the RangeCriterion class.
    HomePlan(HashMap<String, ArrayList<Tag>> tags) {
        // Sends tags to the super class to initialize the constructor of the Plan class
        super(tags);

        // If the tags contain "HOME.VALUE" then those tags are added to the instance of the
        // RangeCriterion class
        if (tags.get("HOME.VALUE") != null) {
            // Iterates through all the tags that have "HOME.VALUE"
            for (Tag tag : tags.get("HOME.VALUE")) {
                homeValueCriterion.addCriterion(tag);
            }
        }
        // If the tags contain "HOME.AGE" then those tags are added to the instance of the
        // RangeCriterion class
        if (tags.get("HOME.AGE") != null) {
            // Iterates through all the tags that have "HOME.AGE"
            for (Tag tag : tags.get("HOME.AGE")) {
                homeAgeCriterion.addCriterion(tag);
            }
        }
    }

    // Determines if the home is insurable and if its value is not in range it returns false.
    // Otherwise it determines the approximate age of the house and returns if its age is in
    // range
    @Override
    boolean isEligible(Insurable insurable, Date date) {
        // Checks if home is insurable
        if (!(insurable instanceof Home))
            return false;
        Home home = (Home) insurable;
        // Checks if home value is within the range and meets the home criteria
        if (!homeValueCriterion.isInRange(home.getValue()))
            return false;

        // Extracting the approximate age of the home (calendar years)
        LocalDate localCurrentDate =
                date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBuiltDate =
                home.getBuildDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long age = localCurrentDate.getYear() - localBuiltDate.getYear();;
        // Checking if the age is in the range.
        return homeAgeCriterion.isInRange(age);
    }

    // Returns the home along with all its information
    @Override
    Insurable getInsuredItem(Customer customer, Database database) {
        return database.getHome(customer.getName());
    }

}
