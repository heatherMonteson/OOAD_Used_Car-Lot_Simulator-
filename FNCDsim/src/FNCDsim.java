package FNCDsim.src;

import java.util.ArrayList;
import java.util.Objects;

/*
OOAD principal polymorphism and identity examples:

Polymorphism: the  current, departed and quit staff array lists are all examples of polymorphism as they
hold objects of type Inter, Salesperson, and Mechanic. Each of the three types, although they have inherited the same
parent class, have different methods/behaviors from each-other and thus have different forms.

* */

//Running daily tasks through classes for daily tasks. Tasks access the inventory and staff arrays
//employees and tasks access/adjust account methods, see comments on each class for specifics

public class FNCDsim implements Utility {

    public static ArrayList<Employee> departedStaff;
    public static ArrayList<Employee> currentStaff;
    public static ArrayList<Vehicle> inventory;
    public static ArrayList<Vehicle> soldInventory;
    private static double accountBalance;
    private static double totalSales;
    Days today = new Days();

    //send all output through the broker FNCDsim.broker.out(<args>) from outside of simulation class
    //See InformationBroker for overloaded method information formats. Using Enums as tags for specifying data
    //errors sent to FNCDsim.broker.errorOut(<args>)
    public static InformationBroker broker;

    FNCDsim(){
          departedStaff=new ArrayList<>();
          currentStaff=new ArrayList<>();
          inventory=new ArrayList<>();
          soldInventory=new ArrayList<>();
          accountBalance=500000.0;
          totalSales=0.0;
          broker=new InformationBroker();
    }
    //run through the daily tasks based on the given run time
    public void run(int runTime) {

        Tracker tracker = new Tracker(broker);
        Logger logger;

        //run through the simulation based on the number of days set to run
        for (int i =1; i<=runTime; i++){
            logger=new Logger(broker);
            today.newDay();
            broker.out(Enums.EventType.NewDay, today.getNumDays());
            OpenShop.openShop();
            WashCars.washCars();
            FixCars.fixCars();
            SellCars.sellCars(today.getToday());
            if(today.getToday()==7 || today.getToday()==3)
                Race.race();//race on wed. and sun.
            EndOfDay.endOfDay();
            broker.removeObserver(logger);
            tracker.showTracker();
        }
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
