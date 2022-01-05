import java.util.ArrayList;

class Database {
    // The private fields of the Database class
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Home> homes = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Plan> plans = new ArrayList<>();
    private ArrayList<Contract> contracts = new ArrayList<>();
    private ArrayList<Claim> claims = new ArrayList<>();

    // Adds an instance of the Home class to the Home ArrayList
    void insertHome(Home home) {
        homes.add(home);
    }

    // Adds an instance of the Car class to the Car ArrayList
    void insertCar(Car car) {
        cars.add(car);
    }

    // Adds an instance of the Customer class to the Customer ArrayList
    void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    // Adds an instance of the Plan class to the Plan ArrayList
    void insertPlan(Plan plan) {
        plans.add(plan);
    }

    // Adds an instance of the Claim class to the Claim ArrayList
    void insertClaim(Claim claim) {
        claims.add(claim);
    }

    // Adds an instance of the Contract class to the Contract ArrayList
    void insertContract(Contract contract) {
        contracts.add(contract);
    }

    // Returns the plan if the name of the plan exists in the Plan ArrayList
    Plan getPlan(String name) {
        // Iterates through each plan in the Plan ArrayList
        for (Plan plan : plans) {
            // Checks if the name of the plan in the ArrayList is the same as the String that
            // was sent to this function
            if (plan.name.equals(name))
                return plan;
        }
        return null;
    }

    // Returns the customer if the name of the customer exists in the Customer Arraylist
    Customer getCustomer(String name) {
        // Iterates through each customer in the Customer ArrayList
        for (Customer customer : customers) {
            // Checks if the name of the customer in the ArrayList is the same as the String that
            // was sent to this function
            if (customer.getName().equals(name))
                return customer;
        }
        return null;
    }

    // Returns the contract if the name of the contract exists in the Contract ArrayList
    Contract getContract(String name) {
        // Iterates through each contract in the Contract ArrayList
        for (Contract contract : contracts) {
            // Checks if the name of the contract in the ArrayList is the same as the String that
            // was sent to this function
            if (contract.getContractName().equals(name))
                return contract;
        }
        return null;
    }

    /**
     * There is at most one home owned by each person.
     */
    // Returns the home if the owner's name is assigned to a home in the Home ArrayList
    Home getHome(String ownnerName) {
        // Iterates through each home in the Home ArrayList
        for (Home home : homes) {
            // Checks if the name of the owner of the home in the ArrayList is the same as
            // the String that was sent to this function
            if (home.getOwnerName().equals(ownnerName))
                return home;
        }
        return null;
    }

    /**
     * There is at most one car owned by each person.
     */
    // Returns the car if the owner's name is assigned to a car in the Car ArrayList
    Car getCar(String ownnerName) {
        // Iterates through each car in the Car ArrayList
        for (Car car : cars) {
            // Checks if the name of the owner of the car in the ArrayList is the same as
            // the String that was sent to this function
            if (car.getOwnerName().equals(ownnerName))
                return car;
        }
        return null;
    }

    // Returns the total amount a customer has claimed through all of the contracts that they
    // are in
    long totalClaimAmountByCustomer(String customerName) {
        long totalClaimed = 0;
        // Iterates through each claim in the Claim ArrayList
        for (Claim claim : claims) {
            // If the customer's name is under the contract that has had a claim made then claim
            // amount is added to the total
            if (getContract(claim.getContractName()).getCustomerName().equals(customerName))
                totalClaimed += claim.getAmount();
        }
        return totalClaimed;
    }

    // Returns the total amount received by a customer through all the claims they have made
    long totalReceivedAmountByCustomer(String customerName) {
        long totalReceived = 0;
        // Iterates through each claim in the Claim ArrayList
        for (Claim claim : claims) {
            Contract contract = getContract(claim.getContractName());
            // If the customer name is present in the contracts that had a claim made
            if (contract.getCustomerName().equals(customerName)) {
                // If the claim is successful then deductible is taken from the deductible
                // amount from the plan
                if (claim.wasSuccessful()) {
                    long deductible = getPlan(contract.getPlanName()).getDeductible();
                    // The claim amount minus deductible is added to the total if it is more
                    // than 0
                    totalReceived += Math.max(0, claim.getAmount() - deductible);
                }
            }
        }
        return totalReceived;
    }

    // Returns the number of customers that are under contract within a given plan
    long totalNumberOfCustomersForAPlan(String planName) {
        long numCustomers = 0;
        // Iterates through each contract in the Contract ArrayList
        for (Contract contract : contracts) {
            // If the plan currently has someone under contract then the number of customers
            // that have that plan is incremented
            if (planName.equals(contract.getPlanName())) {
                numCustomers ++;
            }
        }
        return numCustomers;
    }

    // Returns the total amount payed to customers under a given plan if their claim was
    // successful
    long totalPayedToCustomersForAPlan(String planName) {
        long amountPayed = 0;
        // Iterates through each claim in the Claim ArrayList
        for (Claim claim : claims) {
            Contract contract = getContract(claim.getContractName());
            // If the plan name is present in the contracts that had a claim made
            if (contract.getPlanName().equals(planName)) {
                // If the claim is successful then deductible is taken from the deductible
                // amount from the plan
                if (claim.wasSuccessful()) {
                    long deductible = getPlan(contract.getPlanName()).getDeductible();
                    // The claim amount minus deductible is added to the total if it is more
                    // than 0
                    amountPayed += Math.max(0, claim.getAmount() - deductible);
                }
            }
        }
        return amountPayed;
    }
}
