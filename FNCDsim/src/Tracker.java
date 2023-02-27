package FNCDsim.src;


import java.util.HashMap;
import java.util.Map;

//runs through the whole simulation
//output from the FNCDsimulation at the end of the run
public class Tracker implements Observer{
//    Map<String , Double> tracker = new HashMap<>();
    int day;
    HashMap<String, Double> tracker = new HashMap<String , Double>();
    InformationBroker broker;

    Tracker(InformationBroker broker){
        this.broker=broker;
        this.tracker.put("Staff", 0.0);
        this.tracker.put("FNCD", 0.0);
        broker.registerObserver(this);
    }

    public void showTracker(){
        System.out.println("Tracker: Day "+ day);
        System.out.println("Total money earned by all Staff: $"+tracker.get("Staff"));
        System.out.println("Total money earned by the FNCD: $"+tracker.get("FNCD"));
    }

    public void update(Enums.EventType eventType, int dayNumber){//updating the current day variable
        if(eventType==Enums.EventType.NewDay)
            day=dayNumber;
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
