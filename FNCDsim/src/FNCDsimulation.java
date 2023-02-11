import java.util.ArrayList;

public class FNCDsimulation implements ValueFromRange{

    FNCDsimulation(){}

    public void run(int runTime){
        Days today = new Days();
        Bank bankAccount = new Bank(500000, 250000.0);
        Intern intern = new Intern();
        Intern intern2 = new Intern();
        Intern intern3 = new Intern();
        Salesperson salesPerson = new Salesperson();
        Salesperson salesPerson2 = new Salesperson();
        Salesperson salesPerson3 = new Salesperson();
        Mechanic mechanic = new Mechanic();
        Mechanic mechanic1  = new Mechanic();
        Mechanic mechanic2  = new Mechanic();

        Performance perCar = new Performance();
        Performance perCar2 = new Performance();
        Performance perCar3 = new Performance();
        Performance perCar4 = new Performance();
        Car basicCar = new Car();
        Car basicCar2 = new Car();
        Car basicCar3 = new Car();
        Car basicCar4 = new Car();
        Pickup pickupTruck = new Pickup();
        Pickup pickupTruck2 = new Pickup();
        Pickup pickupTruck3 = new Pickup();
        Pickup pickupTruck4 = new Pickup();

        for (int i =1; i<=runTime; i++){
            today.newDay();
            if(today.getToday()!=7){ //open Monday-->Saturday
                openShop();
                washCars(intern);
                fixCars(mechanic);
                sellCars(salesPerson, today.getToday());
                endOfDay(salesPerson, mechanic, intern);
            }else {System.out.println("Closed for Sunday...");}//closed Sunday
        }
        SimulationTableOutput.finalOutput();
    }

    //Handel all opening activities
    private void openShop(){
        System.out.println("Opening ...");
        System.out.println("Starting today with a budget of $" + Bank.getBalance());
        //check 3 interns
        ArrayList<Employee> interns = Employee.getInterns();
        if(interns.size()<3)
            Intern.hireInterns(3-interns.size());

        ArrayList<Vehicle> pickups = Vehicle.getPickups();
        ArrayList<Vehicle> cars = Vehicle.getCars();
        ArrayList<Vehicle> performance = Vehicle.getPerformanceCars();

        if(pickups.size()<4)//check 4 of each car in stock
            Pickup.addPickups(4-pickups.size());
        if(cars.size()<4)
            Car.addCars(4-cars.size());
        if(performance.size()<4)
            Performance.addPerformance(4-performance.size());
    }

    //Handel all washing
    private void washCars(Intern internAccess){
        System.out.println("Washing cars...");
        internAccess.washCars();
    }

    //handel all fixing
    private void fixCars(Mechanic mechanicAccess){
        System.out.println("Fixing cars ...");
        mechanicAccess.fixCars();
    }

    //handel all selling
    private void sellCars(Salesperson salesAccess, int day){
        System.out.println("Selling cars...");
        if(day==6 || day == 5)//friday and satuday 2--> 8 customers show up
            salesAccess.sellCars(findValue(2, 8));
        else if(day<5 && day>0) //all other days 0-->5 show up
            salesAccess.sellCars(findValue(0, 5));
    }


    //handel EOD activities
    private void endOfDay(Salesperson salesAccess, Mechanic mechanicAccess, Intern internAccess){
        ArrayList<Employee> allEmployees = Employee.getCurrentEmployees();
        for (Employee employee: allEmployees){
            employee.addDayWorked();
            employee.payDailyRate();
        }
        salesAccess.salesQuit();
        internAccess.internQuit();
        mechanicAccess.mechanicQuit();
        if(Salesperson.getSalePeople().size()<3)
            internAccess.promoteInternToSales();
        if(Mechanic.getMechanics().size()<3)
            internAccess.promoteInternToMechanic();
        Vehicle.resetDailySales();
        SimulationTableOutput.endOfDayOutput();
        System.out.println("Closing...");
    }
}
