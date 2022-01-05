import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class CarPlan extends Plan {
    // The fields of the CarPlan class
    static final String inputTag = "CAR_PLAN";
    RangeCriterion mileageCriterion = new RangeCriterion();

    // Constructor that takes the information provided from the tags and assigns that to the
    // fields of the class. Also checks if "CAR.MILEAGE" is a tag and if it is the car
    // mileage is added to the instance of the RangeCriterion class
    CarPlan(HashMap<String, ArrayList<Tag>> tags) {
        // Sends tags to the super class to initialize the constructor of the Plan class
        super(tags);

        // If the tags contain "CAR.MILEAGE" then those tags are added to the instance of the
        // RangeCriterion class
        if (tags.get("CAR.MILEAGE") != null) {
            for (Tag tag : tags.get("CAR.MILEAGE")) {
                mileageCriterion.addCriterion(tag);
            }
        }
    }

    // Determines if the car is insurable and if its mileage is in range it returns true.
    // Otherwise it returns false
    @Override
    boolean isEligible(Insurable insurable, Date date) {
        // Checks if car is insurable
        if (!(insurable instanceof Car))
            return false;
        Car car = (Car) insurable;
        // Checks if the car mileage is in the range
        return mileageCriterion.isInRange(car.getMileage());
    }

    // Returns the car along with all its information from the database
    @Override
    Insurable getInsuredItem(Customer customer, Database database) {
        return database.getCar(customer.getName());
    }
}
