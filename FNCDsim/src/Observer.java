package FNCDsim.src;

//OBSERVER PATTERN: Both the Tracker and the Logger implement the observer
//each overrides the method for the formatted information that may be of interest
//in each class they use the Enums.EventType to determine if the event has relevant information
public interface Observer {
    public default void update(String event){}//arbitrary output
    public default void update(Enums.EventType eventType,int dayNumber){}// used generally for day of the week
    public default void update(Enums.EventType eventType,double salary){}//used generally for salary
    public default void update(Enums.EventType eventType, String event){}//update basic string event where no financial changes were made/general state changes
    public default void update(Enums.EventType eventType, String event, double bonusOrEmergency){} //update basic string event where a bonus was made or emergency funds were added
    public default void update(Enums.EventType eventType, String event, double bonus, double saleAmount){}//update basic string event where a bonus and a sale was made

}

