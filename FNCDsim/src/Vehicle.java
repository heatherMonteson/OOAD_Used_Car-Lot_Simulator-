/*
    Citation links:
    Random picker from array: https://stackoverflow.com/questions/21726033/picking-a-random-item-from-an-array-of-strings-in-java
    List of car names taken from: http://www.namenerds.com/uucn/listofweek/cars.html
    Generating value from range from: https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
 */

/*
OOAD Principle Cohesion and Encapsulation examples:

Cohesion: The methods in the vehicle class are examples of cohesion because they only relate directly to information about
vehicles: accessing and adjusting its state.

Encapsulation: All the private variables and methods hidden within Vehicle are encapsulated as they are
not accessible to external entities (unless granted permission through getters/setters). This prevents users of objects of
type Vehicle and it's subclasses to access only what is needed to gain information about vehicles.

 */
package FNCDsim.src;

import java.util.ArrayList;
import java.util.Arrays;

public class Vehicle implements ValueFromRange, NameGenerator{

    private String type;
    private String name;
    private String condition;
    private String cleanliness;
    private double cleaningBonus=0.0;
    private double fixBonus=0.0;
    private double salePrice =0.0;
    private double cost=0.0;
    private double saleBonus=0.0;
    private static final String[] types = {"Car", "Pickup","Performance"};
    private String[]conditions={"LikeNew", "Used", "Broken"};
    private static ArrayList<String> carNames = new ArrayList<>(Arrays.asList("4Runner","Cordoba","Gran Fury","Nubira","Sonic","Acadia","Corniche","Gran Turismo","Oasis","Sonoma","Accent","Corolla","Grand Am","Odyssey","Sorento","Acclaim","Coronet","Grand Prix","Omega","Soul","Accord","Corrado","Grand Vitara","Omni","Spark","Achieva","Corsair","Grand Voyager","Optima","Spectra","Aerio","Corsica","Greiz","Outback","Spectrum","Aerostar","Cortina","Gremlin","Outlander","Spider","Aileron","Corvette","Grenada","Paceman","Spirior","Airstream","Cougar","Highlander","Pacer","Spirit","Alero","Countach","Hobio","Pacifica","Sportage","Allante","Courier","Hombre","Pampa","Sportvan","Alliance","Cressida","Horizon","Panamera","Sprint","Alpine","Crider","Hornet","Parisienne","Sprinter","Altima","Crossfire","Hummer","Park Avenue","Spyder","Amanti","Crosstrek","Hunter","Park Ward","Squire","Amaze","Crown Victoria","Huracan","Paseo","St. Regis","Amigo","Cruze","Husky","Passat","Stanza","Anglia","Cube","Ikon","Passport","Starion","Aperta","Cutlass","Impala","Pathfinder","Starlet")); //array that will hold read in car names
    private static ArrayList<String> usedNames =new ArrayList<>();

    Vehicle(){}

    Vehicle(String type, double saleBonus, double washBonus, double fixBonus, double costMin, double costMax){
        setName();
        this.type=type;
        this.cost=findValue(costMin, costMax);
        this.saleBonus=saleBonus;
        this.cleaningBonus=washBonus;
        this.fixBonus=fixBonus;
        setCondition();
        setCleanliness();
        this.salePrice=this.cost*2;
    }

    private void setCleanliness() {
        int rand = findValue(1, 100);
        if(rand<=5){
            cleanliness="Sparkly";
        }
        else if (rand<=35){
            cleanliness="Clean";
        }
        else
            cleanliness="Dirty";
    }
    private void setCondition() {
        condition=conditions[findValue(0,conditions.length-1)];
        if(condition=="Broken")
            cost=cost-(cost*.5);
        else if(condition=="Used")
            cost=cost-(cost*.2);
    }
    private void setName() {
     name=generateName(carNames);
     int i=0;
     String holdName= name;
     while(usedNames.contains(name)){
         name= holdName+ " "+ i;
         i++;
     }
        usedNames.add(name);
    }
    public double getSalePrice() {
        return salePrice;
    }

    public String getCondition() {
        return condition;
    }

    public String getCleanliness() {
        return cleanliness;
    }

    public double getSaleBonus() {
        return saleBonus;
    }

    public double getCost() {
        return cost;
    }

    public String getType(){
        return type;
    }

    public static String[] getTypes(){
            return types;
    }

    public double getFixBonus(){
        return fixBonus;
    }

    public double getCleaningBonus() {
        return cleaningBonus;
    }

    public String getName(){
        return name;
    }

    public void changeCarToDirty() {
        cleanliness="Dirty";
    }

    public void changeCarToClean() {
        cleanliness="Clean";
    }

    public void changeCarToSparkly() {
        cleanliness="Sparkly";
    }

    public void downGradeCleanliness() {
        if(cleanliness=="Clean")
            cleanliness="Dirty";
        else if(cleanliness=="Sparkly")
            cleanliness="Clean";
    }
    public void changeCarToUsed(){
        if(condition=="Broken")
            salePrice=salePrice+(saleBonus*.5);
        condition="Used";
    }
    public void changeCarToLikeNew(){
        salePrice=salePrice+(salePrice*.25);
        condition="LikeNew";
    }


}
