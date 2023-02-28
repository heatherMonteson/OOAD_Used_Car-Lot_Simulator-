package FNCDsim.src;
/*OOAD Principal
Identity: the end of the sell cars method uses the unique identity of an object to remove a car from the inventory. The
API method remove for arraylists looks for an object matching the identity of the given object to remove it from the list:
sellCar=salesPerson.sellCar(customer, sellableCars)
inventory.remove(sellCar);
*/

//Daily activity carried out at the FNCD broken up by type
//Accessing and updating static variables from the simulation class
//NOTE: only make monetary updates through the getter and setters of the FNCD simulation class
import java.util.ArrayList;
import java.util.Objects;

public abstract class DailyActivity {

}

//////////////////////////////////////////////////////////////////////
//accesses/update staff and inventory list arrays directly from FNCDsim
//announcements: adding to inventory, hiring interns, opening budget
abstract class OpenShop extends DailyActivity{

    public static void openShop(){
        FNCDsim.broker.out("Opening ...");
        FNCDsim.broker.out(Enums.EventType.Opening, "Starting today with a budget of $" + FNCDsim.getBalance(), FNCDsim.getBalance());

        //Add employees as needed by type
        for(String type: Employee.getStaffTypes()) {
            ArrayList<Employee> typeList= Employee.getStaffByType(FNCDsim.currentStaff, type);
            if(typeList.size()<3)
                addEmployee(typeList.size(), type);
        }

        //buy vehicles as needed, funds removed in addInventory method (4 of each)
        for(String type: Vehicle.getTypes()) {
            ArrayList<Vehicle> typeList= Vehicle.getVehiclesByType(FNCDsim.inventory, type);
            if(typeList.size()<4)
                addInventory(typeList.size(), type);
        }
    }

    private static void addEmployee(int size, String type) {
        Employee employee = null;
        //get the inventory based on type
        for(int i =0 ; i<3-size; i++) {
            if(Objects.equals(type, "Intern"))
                employee=new Intern();
            else if(Objects.equals(type, "Sales"))
                employee=new Salesperson();
            else if (Objects.equals(type, "Mechanic"))
                employee=new Mechanic();
            else if (Objects.equals(type, "Driver"))
                employee=new Driver();

            if(employee!=null)
            {
                FNCDsim.broker.out(Enums.EventType.Hiring,"Hired new "+ type + " " +employee.getName());
                FNCDsim.currentStaff.add(employee);
            }
            else
                FNCDsim.broker.errorOut("type "+ type + " employees not added");
        }

    }

    //add one new vehicle based on type to current inventory list
    private static void addInventory(int size, String type) {
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
                FNCDsim.inventory.add(vehicle);
                FNCDsim.getFunds(vehicle.getCost());
                FNCDsim.broker.out(Enums.EventType.Buying,"New "+ vehicle.getType() +" added to inventory: "+ vehicle.getCondition() + " "+vehicle.getCleanliness() +" "+ vehicle.getName() + " for "+ vehicle.getCost() + " cost.",  vehicle.getCost()  );
            }
            else
                FNCDsim.broker.errorOut("type "+ type + " cars not added");
        }
    }
}

///////////////////////////////////////////////////////////////////////
//accesses staff and inventory list arrays directly from FNCDsimulation
abstract class WashCars extends DailyActivity{
    public static void washCars(){
        if(FNCDsim.inventory.size()==0){
            FNCDsim.broker.out("No cars to wash" );
            return;
        }
        FNCDsim.broker.out("Washing cars..." );
        ArrayList<Vehicle>cleanCars= new ArrayList<Vehicle>();
        ArrayList<Vehicle>dirtyCars= new ArrayList<Vehicle>();
        Vehicle washedCar;
        int j=0;

        for (Vehicle vehicle : FNCDsim.inventory) { //find all clean and dirty cars in inventory that need to be washed
            if (Objects.equals(vehicle.getCleanliness(), "Clean"))
                cleanCars.add(vehicle);
            else if (Objects.equals(vehicle.getCleanliness(), "Dirty"))
                dirtyCars.add(vehicle);
        }

        ArrayList<Employee> interns = Employee.getStaffByType(FNCDsim.currentStaff, "Intern");
        //cycle through all interns 2x first washing random dirty then clean cars
        //UPDATED from 2.2: only allow one attempt to wash a car, remove from list after attempt
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

}

//////////////////////////////////////////////
//accesses staff and inventory list arrays directly from FNCDsimulation
abstract class FixCars extends DailyActivity{
    public static void fixCars(){
        if(FNCDsim.inventory.size()==0){
            FNCDsim.broker.out("No cars to fix");
            return;
        }
        FNCDsim.broker.out("Fixing cars ...");
        ArrayList<Vehicle>carsToFix= new ArrayList<Vehicle>();
        ArrayList<Employee> mechanics = Employee.getStaffByType(FNCDsim.currentStaff, "Mechanic");
        int j=0;

        //select only broken and used cars for them to fix.
        for(Vehicle vehicle: FNCDsim.inventory){
            if(Objects.equals(vehicle.getCondition(), "Broken") || Objects.equals(vehicle.getCondition(), "Used"))
                carsToFix.add(vehicle);
        }

        //loop though mechanics 2x to have them fix cars
        //UPDATE from 2.2: only allowing one fix attempt on a car, removing from list of repairable cars after attempt
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

}

//////////////////////////////////////////////
//accesses staff and accesses/update inventory and sold inventory list arrays directly from FNCDsimulation
//announcements: number of customers shopping that day
abstract class SellCars extends DailyActivity{

    public static void sellCars(int today){
        if(FNCDsim.inventory.size()==0){
            FNCDsim.broker.out("No cars to sell");
            return;
        }
        FNCDsim.broker.out("Selling cars...");
        Customer customers= new Customer();
        ArrayList <Vehicle> sellableCars = new ArrayList<Vehicle>();
        ArrayList<Employee> salesPeople = Employee.getStaffByType(FNCDsim.currentStaff, "Sales");
        Vehicle sellCar;
        int numCustomers=0;

        //create num customers based on day of the week and access with array
        if(today==6 || today== 5)//friday and satuday 2--> 8 customers show up
            numCustomers = Utility.findValue(2, 8);
        else //all other days 0-->5 show up
            numCustomers =Utility.findValue(0, 5);

        customers = new Customer(numCustomers);

        ArrayList<Customer> allCustomers = customers.getCustomers();
        FNCDsim.broker.out("There are "+ allCustomers.size()+" customers looking to purchase vehicles today");
        if(allCustomers.size()==0)
            return;
        //Find vehicles to sell don't sell broken cars
        for(Vehicle car: FNCDsim.inventory){
            if(!Objects.equals(car.getCondition(), "Broken"))
                sellableCars.add(car);
        }

        //loop through customers and pair them with a sales person
        for(Customer customer: allCustomers){
            //determine if sales person makes a sale, remove from inventory if so and update all other info
            Salesperson salesPerson = (Salesperson) salesPeople.get(Utility.findValue(0, salesPeople.size()-1));
            sellCar=salesPerson.sellCar(customer, sellableCars);

            if(sellCar!=null){
                FNCDsim.soldInventory.add(sellCar);
                sellableCars.remove(sellCar);
                FNCDsim.inventory.remove(sellCar);
            }
        }
    }
}

//////////////////////////////////////////////
//TODO: finish racer method, need to actually race, pair a driver with the cars,
// add bonus, change state of the cars, see if driver is injured/remove from current staff and add to departed etc.
abstract class RaceCars extends DailyActivity{
    public static void race() {
        FNCDsim.broker.out("Racing...");
        String[] raceTypes = RaceCar.getRaceTypes();
        String type = raceTypes[Utility.findValue(0, raceTypes.length-1)];
        ArrayList<Vehicle> raceCars= RaceCar.getRaceCarsByType(FNCDsim.inventory, type);
        if(raceCars.size()==0)
            FNCDsim.broker.out(Enums.EventType.Racing, "The FNCD is unable to participate in races today as there are no " +type + "s");
        else{
            FNCDsim.broker.out(Enums.EventType.Racing, "There are "+ raceCars.size()+ " cars racing today.");
            Employee.getStaffByType(FNCDsim.currentStaff, "Driver");
        }
    }
}

//////////////////////////////////////////////
//accesses/update staff and inventory and sold inventory list arrays directly from FNCDsimulation
//uses funds from simulation to pay employees
//announcements: quitting, promotions,
abstract class EndOfDay extends DailyActivity{

    public static void endOfDay(){
        FNCDsim.broker.out("End of day...");

        //update everyone's pay and days worked
        //remove daily pay from the budget
        for(Employee employee:FNCDsim.currentStaff ){
            employee.addDayWorked();
            FNCDsim.getFunds(employee.getSalary());
            FNCDsim.broker.out(Enums.EventType.PaySalary, employee.getSalary());
            employee.payDailyRate();
        }

        //All quitting activity during the end of day
        staffQuitByType("Intern");//intern quits, just gets removed from list
        Employee promoted = null;
        if(staffQuitByType("Sales")){//if sales quits, promote intern
            promoted=promoteInternTo("Sales");

            if(promoted!=null)//announce promotion
                FNCDsim.broker.out(Enums.EventType.Promoting,"Intern " + promoted.getName()+ " was promoted to "+promoted.getType() +" has a new daily salary of $"+promoted.getSalary());
        }
        if(staffQuitByType("Mechanic")) {//if mechanic quits, promote intern
            promoted = promoteInternTo("Mechanic");
            if (promoted != null)//announce promotion
                FNCDsim.broker.out(Enums.EventType.Promoting, "Intern " + promoted.getName() + " was promoted to " + promoted.getType() + " has a new daily salary of $" + promoted.getSalary());
        }
        SimulationTableOutput.endOfDayOutput(FNCDsim.currentStaff, FNCDsim.departedStaff.size(), FNCDsim.inventory, FNCDsim.soldInventory.size(), FNCDsim.getTotalSales(), FNCDsim.getBalance());
    }

    //Selects a random intern to promote based on type of promotion
    private static Employee promoteInternTo(String newPosition){
        ArrayList<Employee> staffByType = Employee.getStaffByType(FNCDsim.currentStaff, "Intern");
        Intern intern = (Intern) staffByType.get(Utility.findValue(0, staffByType.size()-1));
        Employee newEmployee = null;

        if(Objects.equals(newPosition, "Sales"))
            newEmployee= intern.promoteInternToSales();
        else if(Objects.equals(newPosition, "Mechanic"))
            newEmployee= intern.promoteInternToMechanic();

        if(newEmployee!=null){
            FNCDsim.currentStaff.remove(intern);
            FNCDsim.currentStaff.add(newEmployee);
        }
        else
            FNCDsim.broker.errorOut("Error promoting intern");
        return newEmployee;
    }

    private static boolean staffQuitByType(String type){
        //if employee quites remove from current staff and add to departed
        if(Employee.employeeQuit()){
            try {
                ArrayList<Employee> staffByType = Employee.getStaffByType(FNCDsim.currentStaff, type);
                int randEmp=Utility.findValue(0, staffByType.size()-1);
                FNCDsim.departedStaff.add(staffByType.get(randEmp));
                FNCDsim.broker.out(Enums.EventType.Quitting, type+" staff " + staffByType.get(randEmp).getName() + " has quit");
                FNCDsim.currentStaff.remove(staffByType.get(randEmp));
                return true;
            }
            catch (Exception e){
                FNCDsim.broker.errorOut("Error with staff quit");
            }

        }
        return false;
    }

}