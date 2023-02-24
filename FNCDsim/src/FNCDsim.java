package FNCDsim.src;

import java.util.ArrayList;
import java.util.Objects;

/*
OOAD principal polymorphism and identity examples:

Polymorphism: the  current, departed and quit staff array lists are all examples of polymorphism as they
hold objects of type Inter, Salesperson, and Mechanic. Each of the three types, although they have inherited the same
parent class, have different methods/behaviors from each-other and thus have different forms.

Identity: the end of the sell cars method uses the unique identity of an object to remove a car from the inventory. The
API method remove for arraylists looks for an object matching the identity of the given object to remove it from the list:
sellCar=salesPerson.sellCar(customer, sellableCars)
inventory.remove(sellCar);

* */
public class FNCDsim implements Utility {

    private ArrayList<Employee> departedStaff;
    private ArrayList<Employee> currentStaff;
    private ArrayList<Vehicle> inventory;
    private ArrayList<Vehicle> soldInventory;
    private static double accountBalance;
    private static double totalSales;
    Days today = new Days();

    FNCDsim(){
          departedStaff=new ArrayList<>();
          currentStaff=new ArrayList<>();
          inventory=new ArrayList<>();
          soldInventory=new ArrayList<>();
          accountBalance=500000.0;
          totalSales=0.0;
    }

    //run through the daily tasks based on the given run time
    public void run(int runTime) {
        for (int i=0; i<3; i++) {
            currentStaff.add(new Intern());
            currentStaff.add(new Salesperson());
            currentStaff.add(new Mechanic());
        }
        for (int i =1; i<=runTime; i++){
            today.newDay();
            System.out.println("***Day number "+ today.getNumDays()+ "***");
            if(today.getToday()!=7){ //open Monday-->Saturday
                openShop();
                washCars();
                fixCars();
                sellCars();
                endOfDay();
            }else {System.out.println("Closed for Sunday...");}//closed Sunday
        }
    }

    private void openShop(){
        System.out.println("Opening ...");
        System.out.println("Starting today with a budget of $" + accountBalance);

        //hire interns as needed (3 total)
        ArrayList<Employee> interns = Employee.getStaffByType(currentStaff, "Intern");
        for(int i = interns.size(); i<3; i++){
            Intern newIntern =new Intern();
            System.out.println("Hired new intern "+ newIntern.name);
            currentStaff.add(newIntern);
        }
        //buy vehicles as needed, funds removed in addInventory method (4 of each)
        for(String type: Vehicle.getTypes()) {
            ArrayList<Vehicle> typeList= Vehicle.getVehiclesByType(inventory, type);
            if(typeList.size()<4)
                addInventory(typeList.size(), type);
        }
    }

    //add one new vehicle based on type to current inventory list
    private void addInventory(int size, String type) {
        Vehicle vehicle = null;
        //get the inventory based on type
        for(int i =0 ; i<4-size; i++) {
            if(Objects.equals(type, "Car"))
                vehicle=new Car();
            else if(Objects.equals(type, "Performance"))
                vehicle = new Performance();
            else if(Objects.equals(type, "Pickup"))
                vehicle=new Pickup();
            else if(Objects.equals(type, "Motorcycle"))
            vehicle=new Motorcycle();
            else if(Objects.equals(type, "Electric"))
                vehicle=new Electric();
            else if(Objects.equals(type, "MonsterTruck"))
                vehicle=new MonsterTruck();
            //If type given matches the current types to add then add/remove cost from funds
            if(vehicle!=null)
            {
                inventory.add(vehicle);
                getFunds(vehicle.getCost());
                System.out.println("New "+ vehicle.getType() +" added to inventory: "+ vehicle.getCondition() + " "+vehicle.getCleanliness() +" "+ vehicle.getName() + " for "+ vehicle.getCost() + " cost.");
            }
        }
    }

    private void washCars(){
        System.out.println("Washing cars...");
        ArrayList<Vehicle>cleanCars= new ArrayList<Vehicle>();
        ArrayList<Vehicle>dirtyCars= new ArrayList<Vehicle>();
        Vehicle washedCar;
        int j=0;

        for (Vehicle vehicle : inventory) { //find all clean and dirty cars in inventory that need to be washed
            if (Objects.equals(vehicle.getCleanliness(), "Clean"))
                cleanCars.add(vehicle);
            else if (Objects.equals(vehicle.getCleanliness(), "Dirty"))
                dirtyCars.add(vehicle);
        }

        ArrayList<Employee> interns = Employee.getStaffByType(currentStaff, "Intern");
        //cycle through all interns 2x first washing random dirty then clean cars
        //UPDATE: to only allow one attempt to wash a car, remove from list after attempt
        while (j<=1 && (dirtyCars.size()>0 || cleanCars.size()>0)){
            for(Employee intern:interns){
                Intern washer = (Intern) intern;
                if(dirtyCars.size()>0) {
                    washedCar = dirtyCars.get(Utility.findValue(0, dirtyCars.size() - 1));
                    washer.internWashCar(washedCar);
                    dirtyCars.remove(washedCar);
                }
                else if(cleanCars.size()>0){
                    washedCar= cleanCars.get(Utility.findValue(0, cleanCars.size()-1));
                    washer.internWashCar(washedCar);
                    cleanCars.remove(washedCar);
                }
            }
            j++;
        }

    }

    //handel all fixing
    private void fixCars(){
        System.out.println("Fixing cars ...");
        ArrayList<Vehicle>carsToFix= new ArrayList<Vehicle>();
        ArrayList<Employee> mechanics = Employee.getStaffByType(currentStaff, "Mechanic");
        int j=0;
        Vehicle fixedCar;

        //select only broken and used cars for them to fix.
        for(Vehicle vehicle: inventory){
            if(Objects.equals(vehicle.getCondition(), "Broken") || Objects.equals(vehicle.getCondition(), "Used"))
                carsToFix.add(vehicle);
        }

        //loop though mechanics 2x to have them fix cars
        //UPDATE: only allowing one fix attempt on a car, removing from list of repairable cars after attempt
        while (j<=1 && (carsToFix.size()>0)) {
            for(Employee mech: mechanics){
                Mechanic mechanic = (Mechanic) mech;
                if(carsToFix.size()>0){
                    Vehicle car = carsToFix.get(Utility.findValue(0, carsToFix.size()-1));
                    mechanic.fixCar(car);
                    carsToFix.remove(car);
                }
            }
            j++;
        }
    }

    private void sellCars(){
        System.out.println("Selling cars...");
        Customer customers= new Customer();
        ArrayList <Vehicle> sellableCars = new ArrayList<Vehicle>();
        ArrayList<Employee> salesPeople = Employee.getStaffByType(currentStaff, "Sales");
        Vehicle sellCar;
        int numCustomers=0;

        //create num customers based on day of the week and access with array
        if(today.getToday()==6 || today.getToday() == 5)//friday and satuday 2--> 8 customers show up
            numCustomers = Utility.findValue(2, 8);
        else //all other days 0-->5 show up
            numCustomers =Utility.findValue(0, 5);

        customers = new Customer(numCustomers);

        ArrayList<Customer> allCustomers = customers.getCustomers();
        System.out.println("There are "+ allCustomers.size()+" customers looking to purchase vehicles today");
        if(allCustomers.size()==0)
            return;
        //Find vehicles to sell don't sell broken cars
        for(Vehicle car: inventory){
            if(!Objects.equals(car.getCondition(), "Broken"))
                sellableCars.add(car);
        }

        //loop through customers and pair them with a sales person
        for(Customer customer: allCustomers){
            //determine if sales person makes a sale, remove from inventory if so and update all other info
            Salesperson salesPerson = (Salesperson) salesPeople.get(Utility.findValue(0, salesPeople.size()-1));
            sellCar=salesPerson.sellCar(customer, sellableCars);

            if(sellCar!=null){
                soldInventory.add(sellCar);
                sellableCars.remove(sellCar);
                inventory.remove(sellCar);
            }
        }
    }

    private void endOfDay(){
        System.out.println("End of day...");
        //update everyone's pay and days worked
        for(Employee employee:currentStaff ){
            employee.addDayWorked();
            employee.payDailyRate();
            getFunds(employee.getSalary());
        }
        Employee promoted = null;
        staffQuitByType("Intern");//intern quits, just gets removed from list
        if(staffQuitByType("Sales"))//if sales quits, promote intern
            promoted=promoteInternTo("Sales");

        if(staffQuitByType("Mechanic"))//if mechanic quits, promote intern
            promoted=promoteInternTo("Mechanic");

        if(promoted!=null)//announce promotion
            System.out.println("Intern " + promoted.getName()+ " was promoted to "+promoted.getType() +" has a new daily salary of $"+promoted.getSalary());

        SimulationTableOutput.endOfDayOutput(currentStaff, departedStaff.size(), inventory, soldInventory.size(), totalSales, accountBalance);
    }

    private Employee promoteInternTo(String newPosition){
        ArrayList<Employee> staffByType = Employee.getStaffByType(currentStaff, "Intern");
        Employee intern =staffByType.get(Utility.findValue(0, staffByType.size()-1));
        Employee newEmployee = null;
        if(newPosition=="Sales")
            newEmployee= new Salesperson(intern.getName(), intern.getTotalBonusPay(), intern.getDaysWorked(), intern.getIncomeToDate());
        else if(newPosition=="Mechanic"){
            newEmployee=new Mechanic(intern.getName(), intern.getTotalBonusPay(), intern.getDaysWorked(), intern.getIncomeToDate());
        }
        if(newEmployee!=null){
            currentStaff.remove(intern);
            currentStaff.add(newEmployee);
        }
        return newEmployee;
    }

    private boolean staffQuitByType(String type){
        //if employee quites remove from current staff and add to departed
        if(Employee.employeeQuit()){
            ArrayList<Employee> staffByType = Employee.getStaffByType(currentStaff, type);
            int randEmp=Utility.findValue(0, staffByType.size()-1);
            departedStaff.add(staffByType.get(randEmp));
            System.out.println(type+" staff " + staffByType.get(randEmp).getName() + " has quit");
            currentStaff.remove(staffByType.get(randEmp));
            return true;
        }
        return false;
    }

    ///////////////////////////////////////////////////
    //      ALL FINANCIAL METHODS FOR THE FNCD       //
    public static double getFunds(double funds) {
        if(Utility.format(accountBalance- funds)<0)
            addEmergencyFunds();
        accountBalance = Utility.format(accountBalance- funds);
        return Utility.format(funds);
    }
    private static void addEmergencyFunds(){
        accountBalance=accountBalance+250000.0;
        System.out.println("Emergency funds added to the FNCD budget");
    }
    public static void addSales(double sales){
       totalSales= Utility.format(sales+totalSales);
       accountBalance=Utility.format(accountBalance+sales);
    }

}




