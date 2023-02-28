package FNCDsim.src;


import java.util.HashMap;

//OBSERVER: accepting information from the publisher (informationBroker) to increment the total sales and total
//amount all employees have made. Storing in a hash map FNCD-->amount made by dealership, Staff--> amount made by staff
//runs through the whole simulation and accumulates the total sales (money made by the FNCD) and the money made by employees(salary and bonus pay)
//output from the FNCDsimulation at the end of the run

public class Tracker implements Observer{
    HashMap<String, Double> tracker = new HashMap<String , Double>();
    InformationBroker broker;

    Tracker(InformationBroker broker){
        this.broker=broker;
        this.tracker.put("Day", 0.0);
        this.tracker.put("Staff", 0.0);
        this.tracker.put("FNCD", 0.0);
        broker.registerObserver(this);
    }

    public void showTracker(){
        System.out.println("Tracker: Day "+ Math.round(tracker.get("Day")));
        System.out.println("Total money earned by all Staff: $"+tracker.get("Staff"));
        System.out.println("Total money earned by the FNCD: $"+tracker.get("FNCD"));
    }

    public void update(Enums.EventType eventType, int dayNumber){//updating the current day variable
        if(eventType==Enums.EventType.NewDay) {
            tracker.replace("Day", (double) dayNumber);
        }
    }

    //bonus pay added to total staff pay
    public void update(Enums.EventType eventType, String event, double  pay){
        if(eventType==Enums.EventType.PaySalary ||eventType==Enums.EventType.Racing ||eventType==Enums.EventType.Washing || eventType==Enums.EventType.Selling || eventType==Enums.EventType.Fixing) {
            double oldValue = tracker.get("Staff");
            tracker.replace("Staff", Utility.format(oldValue+pay));
        }
    }
    //Salary pay added to total staff pay
    public void update(Enums.EventType eventType, double  pay){
        if(eventType==Enums.EventType.PaySalary) {
            double oldValue = tracker.get("Staff");
            tracker.replace("Staff", Utility.format(oldValue+pay));
        }
    }

    //Sales added to profit
    public void update(Enums.EventType eventType, String event, double bonus, double saleAmount){
        if(eventType==Enums.EventType.Selling){//if money is coming in from a sale add it to the money made by the FNCD
            double oldValue = tracker.get("FNCD");
            tracker.replace("FNCD",oldValue, Utility.format(oldValue+saleAmount));
        }
    }

}
