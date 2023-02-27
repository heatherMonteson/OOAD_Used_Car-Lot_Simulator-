package FNCDsim.src;

import java.util.ArrayList;
import java.util.List;

//OBSERVER: Part of the observer implementation is the information broker. All state and financial updates are sent to the broker
//the updates are also connected to some enum flag so that the observers can determine if they want to take the information or not
public class InformationBroker{

    private List<Observer> observers=new ArrayList<>();

    public void registerObserver(Observer o){
        observers.add(o);
    }
    public void removeObserver(Observer o){
        observers.remove(o);
    }

    public void out(String event){//general/arbitrary events
        System.out.println(event);
        if(observers!=null) {
            for (Observer observer : observers) {
                observer.update(event);
            }
        }
    }

    public void errorOut(String error){
        System.out.println(error);
    }
    public void out(Enums.EventType eventType,int num){ //newday
        if(eventType== Enums.EventType.NewDay)
                System.out.println("***Day Number: " + num + "***");
        else
            System.out.println(eventType + ": " + num);
        if(observers!=null) {
            for (Observer observer: observers){
                observer.update(eventType, num);
            }
        }
    }
    public void out(Enums.EventType eventType,double num){ //salary
        if(observers!=null) {
            for (Observer observer: observers){
                observer.update(eventType, num);
            }
        }
    }
    public void out(Enums.EventType eventType, String event){//significant event announcements: fix, wash, race w/o $ and start of day
        System.out.println(eventType + ": " + event);
        if(observers!=null) {
            for (Observer observer : observers) {
                observer.update(eventType, event);
            }
        }
    }
    public void out(Enums.EventType eventType, String event, double money){ //activity bonuses: fix, wash, race
        System.out.println(eventType + ": " + event);
        if(observers!=null) {
            for (Observer observer : observers) {
                observer.update(eventType, event, money);
            }
        }
    }
    public void out(Enums.EventType eventType, String event, double bonus, double saleAmount){ //activity with selling: selling
        System.out.println(eventType + ": " + event);
        if(observers!=null) {
            for (Observer observer : observers) {
                observer.update(eventType, event, bonus, saleAmount);
            }
        }
    }
}
