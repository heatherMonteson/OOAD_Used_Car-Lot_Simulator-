import java.util.ArrayList;
import java.util.Objects;

public class FNCDsim implements ValueFromRange{

    private ArrayList<Employee> quitToday = new ArrayList<Employee>();
    private ArrayList<Employee> departedStaff = new ArrayList<Employee>();
    private ArrayList<Employee> currentStaff = new ArrayList<Employee>();
    private ArrayList<Intern> interns = new ArrayList<Intern>();
    private ArrayList<Salesperson> salesPeople= new ArrayList<>();
    private ArrayList<Mechanic> mechanics = new ArrayList<>();


    private ArrayList<Vehicle> inventory = new ArrayList<Vehicle>();
    private ArrayList<Vehicle> soldInventory = new ArrayList<Vehicle>();
    private ArrayList<Vehicle> soldToday = new ArrayList<Vehicle>();

    Days today = new Days();

    public void run(int runTime) {
        Bank bankAccount = new Bank(500000, 250000.0);

        for (int i=0; i<3; i++) {
            interns.add(new Intern());
            salesPeople.add(new Salesperson());
            mechanics.add(new Mechanic());
        }
        for (int i=0; i<4; i++){
            inventory.add(new Pickup());
            inventory.add(new Performance());
            inventory.add(new Car());
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
        System.out.println("Starting today with a budget of $" + Bank.getBalance());

        while(interns.size()<3) {
            Intern newIntern =new Intern();
            System.out.println("Hired new intern "+ newIntern.name);
            interns.add(newIntern);
        }

        int numCars =0;
        int numPer=0;
        int numPickup=0;
        for (Vehicle car: inventory){
            if(car.getType()=="Car")
                numCars++;
            if(car.getType()=="Performance")
                numPer++;
            if(car.getType()=="Pickup")
                numPickup++;
        }
        for(int i =1 ; i<=4-numCars; i++) {
            Vehicle newCar= new Car();
            inventory.add(newCar);
            Bank.getFunds(newCar.getCost());
            System.out.println("New basic car added to inventory: "+ newCar.getCondition() + " "+newCar.getCleanliness() +" "+ newCar.getName() + " for "+ newCar.getCost() + " cost.");
        }
        for(int i =1 ; i<=4-numPer; i++) {
            Vehicle newCar= new Performance();
            inventory.add(newCar);
            Bank.getFunds(newCar.getCost());
            System.out.println("New performance car added to inventory: "+ newCar.getCondition() + " "+newCar.getCleanliness() +" "+ newCar.getName() + " for "+ newCar.getCost() + " cost.");

        }
        for(int i =1 ; i<=4-numPickup; i++) {
            Vehicle newCar= new Pickup();
            inventory.add(newCar);
            Bank.getFunds(newCar.getCost());
            System.out.println("New pickup added to inventory: "+ newCar.getCondition() + " "+newCar.getCleanliness() +" "+ newCar.getName() + " for "+ newCar.getCost() + " cost.");
        }
    }

    //Handel all washing
    private void washCars(){
        System.out.println("Washing cars...");
        ArrayList<Vehicle>cleanCars= new ArrayList<Vehicle>();
        ArrayList<Vehicle>dirtyCars= new ArrayList<Vehicle>();

        int j=0;
        boolean flag =false;

        for (Vehicle vehicle : inventory) { //find all clean and dirty cars in inventory that need to be washed
            if (Objects.equals(vehicle.getCleanliness(), "Clean"))
                cleanCars.add(vehicle);
            else if (Objects.equals(vehicle.getCleanliness(), "Dirty"))
                dirtyCars.add(vehicle);
        }

        while(j<=1 && (dirtyCars.size()>0 || cleanCars.size()>0)){
            Vehicle car = null;
            for (Intern intern: interns){

                if(dirtyCars.size()>0){//wash dirty cars first
                    car = dirtyCars.get(findValue(0, dirtyCars.size()-1));
                    flag=intern.washCar(car);
                    if(flag){
                        dirtyCars.remove(car);
                        if(car.getCleanliness()=="Clean")
                            cleanCars.add(car);
                    }
                }
                else if(cleanCars.size()>0){//wash clean cars once no more dirty
                    car = cleanCars.get(findValue(0, cleanCars.size()-1));
                    flag=intern.washCar(car);
                    if(flag==true){
                        cleanCars.remove(car);
                        if(car.getCleanliness()=="Dirty")
                            dirtyCars.add(car);
                    }
                }
                if(car !=null && flag) {
                    if(car.getCleanliness()=="Sparkly"){
                        intern.payBonus(Bank.getFunds(car.getCleaningBonus()));
                        System.out.println("Intern "+ intern.getName() + " cleaned " + car.getName() + " and made it " + car.getCleanliness() +" (earned a $"+ car.getCleaningBonus()+")");
                    }
                    else
                        System.out.println("Intern "+ intern.getName() + " cleaned " + car.getName() + " and made it " + car.getCleanliness());
                }
                car =null;
                flag=false;
            }
        j++;
        }
    }
    //handel all fixing
    private void fixCars(){
        System.out.println("Fixing cars ...");
        ArrayList<Vehicle>carsToFix= new ArrayList<Vehicle>();

        int j=0;
        boolean flag =false;

        for(Vehicle vehicle: inventory){
            if(Objects.equals(vehicle.getCondition(), "Broken") || Objects.equals(vehicle.getCondition(), "Used"))
                carsToFix.add(vehicle);
        }
        while (j<=1 && (carsToFix.size()>0)) {
            for(Mechanic mechanic: mechanics){
                if(carsToFix.size()>0){
                    Vehicle car = carsToFix.get(findValue(0, carsToFix.size()-1));
                    flag= mechanic.fixCar(car);
                    if(flag){
                        System.out.println("Mechanic "+ mechanic.getName() + " fixed the " + mechanic.getName() + " and made it like " + car.getCondition()+ "(earned a $"+car.getFixBonus()+" bonus)");
                        mechanic.payBonus(Bank.getFunds(car.getFixBonus()));
                    }
                }
            }
            j++;
        }
    }

    //handel all selling
    private void sellCars(){
        System.out.println("Selling cars...");
        Customer customers= new Customer();
        ArrayList <Vehicle> sellableCars = new ArrayList<Vehicle>();
        Vehicle sellCar = null;

        //create num customers based on day of the week and access with array
        if(today.getToday()==6 || today.getToday() == 5){//friday and satuday 2--> 8 customers show up
            int numCustomers = findValue(2, 8);
            customers = new Customer(numCustomers);
        }
        else if(today.getToday()<5 && today.getToday()>0) {//all other days 0-->5 show up
            int numCustomers = findValue(2, 8);
            customers = new Customer(numCustomers);
        }
        ArrayList<Customer> allCustomers = customers.getCustomers();


        for(Vehicle car: inventory){
            if(car.getCondition()!="Broken")
                sellableCars.add(car);
        }

        for(Customer customer: allCustomers){
            //determine if sales person makes a sale, remove from inventory if so and update all other info
            Salesperson salesPerson = salesPeople.get(findValue(0, salesPeople.size()-1));
            sellCar=salesPerson.sellCar(customer, sellableCars);

            if(sellCar!=null){
                soldToday.add(sellCar);
                soldInventory.add(sellCar);
                Bank.addToBalance(sellCar.getSalePrice());
                Bank.addSaleToBalance(sellCar.getSalePrice());
                salesPerson.payBonus(Bank.getFunds(sellCar.getSaleBonus()));
                System.out.println("Salesperson "+ salesPerson.getName()+" sold the "+ sellCar.getName()+ " "+sellCar.getType() +" for $"+ sellCar.getSalePrice() +" ( earned a $"+ sellCar.getSaleBonus()+" bonus)");
                sellableCars.remove(sellCar);
                for(Vehicle car : inventory){
                    if (car.equals(sellCar)){
                        inventory.remove(car);
                        break;
                    }
                }
            }
        }
    }

    private void endOfDay(){
        System.out.println("End of day...");
        int randIntern = findValue(0, interns.size()-1);
        int randSales= findValue(0, salesPeople.size()-1);
        int randMechanic = findValue(0, mechanics.size()-1);
        for(Intern intern: interns ){
            intern.addDayWorked();
            intern.payDailyRate();
        }
        for(Salesperson sales: salesPeople ){
            sales.addDayWorked();
            sales.payDailyRate();
        }
        for(Mechanic mechanic: mechanics ){
            mechanic.addDayWorked();
            mechanic.payDailyRate();
        }

        if(interns.get(randIntern).employeeQuit()){
            departedStaff.add(interns.get(randIntern));
            System.out.println("Intern " + interns.get(randIntern).getName() + " has quit");
            quitToday.add(interns.get(randIntern));
            interns.remove(interns.get(randIntern));
        }
        if(salesPeople.get(randSales).employeeQuit()){

            departedStaff.add(salesPeople.get(randSales));
            System.out.println("Salesperson " + salesPeople.get(randSales).getName() + " has quit");
            quitToday.add(salesPeople.get(randSales));
            salesPeople.remove(salesPeople.get(randSales));

            randIntern = findValue(0, interns.size()-1);
            Salesperson newSales = interns.get(randIntern).promoteInternToSales(interns.get(randIntern));
            salesPeople.add(newSales);
            interns.remove(interns.get(randIntern));
            System.out.println("Intern "+ newSales.getName() + " has been promoted to sales");
        }
        if(mechanics.get(randMechanic).employeeQuit()){
            //mechanic quit
            departedStaff.add(mechanics.get(randMechanic));
            System.out.println("Mechanic " + mechanics.get(randMechanic).getName() + " has quit");
            quitToday.add(mechanics.get(randMechanic));
            mechanics.remove(mechanics.get(randMechanic));

            //promote intern to mechanic
            randIntern = findValue(0, interns.size()-1);
            Mechanic newMech = interns.get(randIntern).promoteInternToMechanic(interns.get(randIntern));
            mechanics.add(newMech);
            interns.remove(interns.get(randIntern));
            System.out.println("Intern "+ newMech.getName() + " has been promoted to mechanic");
        }

        currentStaff.addAll(interns);
        currentStaff.addAll(salesPeople);
        currentStaff.addAll(mechanics);
        SimulationTableOutput.endOfDayOutput(currentStaff, quitToday, inventory, soldToday);
        quitToday=new ArrayList<Employee>();
        currentStaff= new ArrayList<Employee>();
        soldToday=new ArrayList<Vehicle>();
        Bank.resetDailySales();
    }

}




