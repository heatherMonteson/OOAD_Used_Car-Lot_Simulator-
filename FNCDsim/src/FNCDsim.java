package FNCDsim.src;

import java.util.ArrayList;

/*
OOAD principal polymorphism and identity examples:

Polymorphism: the  current, departed and quit staff array lists are all examples of polymorphism as they
hold objects of type Inter, Salesperson, and Mechanic. Each of the three types, although they have inherited the same
parent class, have different methods/behaviors from each-other and thus have different forms.

* */

//Running daily tasks through classes for daily tasks. Tasks access the inventory and staff arrays
//employees and tasks access/adjust account methods, see comments on each class for specifics
//Currently running with 2 FNCDs
public class FNCDsim implements Utility {

    public static ArrayList<Employee> departedStaff;
    private static ArrayList<Employee> northStaff;
    private static ArrayList<Employee> southStaff;
    private static ArrayList<Vehicle> northInventory;
    private static ArrayList<Vehicle> southInventory;
    public static ArrayList<Vehicle> soldInventory;
    private static double accountBalanceNorth;
    private static double accountBalanceSouth;
    private static double totalSales;
    private static double accountBalance;
    Days today = new Days();
    public static InformationBroker broker;
    static Enums.FNCD_location currentDealership;

    FNCDsim(){
          departedStaff=new ArrayList<>();
          soldInventory=new ArrayList<Vehicle>();
          northStaff=new ArrayList<Employee>();
          southStaff=new ArrayList<>();
          northInventory=new ArrayList<Vehicle>();
          southInventory=new ArrayList<Vehicle>();
          accountBalanceSouth=500000.0;
          accountBalanceNorth=500000.0;
          accountBalance=500000.0;
          totalSales=0.0;
          broker=new InformationBroker();
          switchDealerships();//just set dealership
    }

    //run through the daily tasks based on the given run time
    public void run(int runTime) {

        //run through the simulation based on the number of days set to run
        Logger.getLogger();
        Tracker.getTracker();
        for (int i =1; i<=runTime; i++){
            today.newDay();
            broker.out(Enums.EventType.NewDay, today.getNumDays());

            for(int j=1; j<=2; j++){
                switchDealerships();
                OpenShop.openShop();
            }
            for(int j=1; j<=2; j++){
                switchDealerships();
                WashCars.washCars();
            }
            for(int j=1; j<=2; j++){
                switchDealerships();
                FixCars.fixCars();
            }
            for(int j=1; j<=2; j++){
                switchDealerships();
                SellCars.sellCars(today.getToday());
            }
            if(today.getToday()==7 || today.getToday()==3) {
                for(int j=1; j<=2; j++){
                    switchDealerships();
                    Race.race();//race on wed. and sun.
                }
            }
            for(int j=1; j<=2; j++){
                switchDealerships();
                EndOfDay.endOfDay();
            }

            if (i == 31){
                //add CLIsim
                CLSim.CLSimRun(i);
            }
            
            Tracker.getTracker().showTracker();
        }
    }

    public void switchDealerships(){
        if(currentDealership== Enums.FNCD_location.FNCD_South) {
            currentDealership = Enums.FNCD_location.FNCD_North;
            //switch accounts
            accountBalanceSouth= accountBalance;
            accountBalance=accountBalanceNorth;
        }
        else {
            currentDealership = Enums.FNCD_location.FNCD_South;
            accountBalanceNorth=accountBalance;
            accountBalance=accountBalanceSouth;
        }
    }

    public static ArrayList<Vehicle> inventory(){
        if(currentDealership== Enums.FNCD_location.FNCD_South)
            return southInventory;
        else
            return northInventory;
    }

    public static ArrayList<Employee> currentStaff(){
        if(currentDealership== Enums.FNCD_location.FNCD_South)
            return southStaff;
        else
            return northStaff;
    }

    public static ArrayList<Employee> getNorthstaff(){
        return northStaff;
    }

    public static ArrayList<Employee> getSouthstaff(){
        return southStaff;
    }

    public static void addStaff(Employee employee){
        if(currentDealership== Enums.FNCD_location.FNCD_South)
            southStaff.add(employee);
        else
            northStaff.add(employee);
    }

    public static void addVehicle(Vehicle vehicle){
        if(currentDealership== Enums.FNCD_location.FNCD_South)
            southInventory.add(vehicle);
        else
            northInventory.add(vehicle);
    }

    public static void removeStaff(Employee employee){
        departedStaff.add(employee);
        if(currentDealership== Enums.FNCD_location.FNCD_South)
            southStaff.remove(employee);
        else
            northStaff.remove(employee);
    }

    //Only use for promoting interns, does not add to departed list
    public static void removeIntern(Employee employee){
        if(currentDealership== Enums.FNCD_location.FNCD_South)
            southStaff.remove(employee);
        else
            northStaff.remove(employee);
    }

    public static void removeVehicle(Vehicle vehicle){
        soldInventory.add(vehicle);
        if(currentDealership== Enums.FNCD_location.FNCD_South)
            southInventory.remove(vehicle);
        else
            northInventory.remove(vehicle);
    }
    public static Enums.FNCD_location getCurrentDealership() {
        return currentDealership;
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
        accountBalance=Utility.format(accountBalance+250000.0);
        broker.out(Enums.EventType.Emergency,"Emergency funds of $"+250000.0+" added to the FNCD budget", 250000.0 );
    }
    public static void addSales(double sales){
        totalSales= Utility.format(sales+totalSales);
        accountBalance=Utility.format(accountBalance+sales);
    }
    public static double getTotalSales(){
        return totalSales;
    }
    public static double getBalance(){
        return Utility.format(accountBalance);
    }

}