/*
    Citation links:
    Random picker from array: https://stackoverflow.com/questions/21726033/picking-a-random-item-from-an-array-of-strings-in-java
    List of car names taken from: http://www.namenerds.com/uucn/listofweek/cars.html
    Generating value from range from: https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
    Monster Truck names: https://www.rookieroad.com/monster-trucks/list-a-z-2027269/
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
import java.util.Objects;
import java.util.Random;

public abstract class Vehicle implements Name, Utility{

    protected String type;
    protected String name;
    protected String condition;
    protected String cleanliness;
    protected double cleaningBonus;
    protected double fixBonus;
    protected double salePrice;
    protected double cost;
    protected double saleBonus;
    protected static final String[] types = {"Car", "Pickup","Performance", "Motorcycle","Electric", "MonsterTruck" };
    protected final String[]conditions={"LikeNew", "Used", "Broken"};
    protected ArrayList<String> carNames;
    private static ArrayList<String> usedNames =new ArrayList<>();

    Vehicle(){}

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
    protected void setCleanliness() {
        int rand =Utility.findValue(1, 100);
        if(rand<=5){
            cleanliness="Sparkly";
        }
        else if (rand<=35){
            cleanliness="Clean";
        }
        else
            cleanliness="Dirty";
    }

    protected void setCondition() {
        condition=conditions[Utility.findValue(0,conditions.length-1)];
        if(Objects.equals(condition, "Broken"))
            cost=Utility.format(cost-(cost*.5));
        else if(Objects.equals(condition, "Used"))
            cost=Utility.format(cost-(cost*.2));
    }
    protected void setName() {
        name= generateName(carNames);
        int i=0;
        String holdName= name;
        while(usedNames.contains(name)){
            name= holdName+ i;
            i++;
        }
        usedNames.add(name);
    }

    public void downGradeCleanliness() {
        if(Objects.equals(cleanliness, "Dirty"))//if dirty cannot further downgrade
            return;
        if(Objects.equals(cleanliness, "Clean"))
            cleanliness="Dirty";
        else if(Objects.equals(cleanliness, "Sparkly"))
            cleanliness="Clean";
        FNCDsim.broker.out("Car cleanliness for the " + name + " was downgraded to " + cleanliness);
    }

    public void changeCarToBroken(){
        condition = "Broken";
    }
    public void changeCarToUsed(){
        if(Objects.equals(condition, "Broken"))
            salePrice=Utility.format(salePrice*1.50);
        condition="Used";
    }
    public void changeCarToLikeNew(){
        salePrice=Utility.format(salePrice*1.25);
        condition="LikeNew";
    }

    //Citation: Professor Montgomery's code for 2.2
    public static ArrayList<Vehicle> getVehiclesByType (ArrayList<Vehicle> vehicles, String type){
        ArrayList<Vehicle> typeList = new ArrayList<>();
        for (Vehicle vehicle: vehicles) {
            if (Objects.equals(vehicle.type, type)) typeList.add(vehicle);
        }
        return typeList;
    }
    public String getAddOnDes(){
        return "";
    }
}
//////////////////////////////////////////////////////
class Car extends Vehicle{
    Car(){
        super();
        carNames = new ArrayList<>(Arrays.asList("Accent", "Accord", "Altima", "Ariya", "Armada", "Artura", "Atlas", "Avalon", "Camry", "Clubman", "Compass", "Corolla","Crosair", "Crown", "Durango", "Edge", "Elantra", "Escape", "Forte", "Insight", "Jetta", "Odyssey", "Passat", "Passport","Prologue"));
        setName();
        this.type="Car";
        this.cost=Utility.format(Utility.findValue(10000, 20000));
        this.saleBonus=1000.0;
        this.cleaningBonus=250.0;
        this.fixBonus=450.0;
        setCondition();
        setCleanliness();
        this.salePrice=Utility.format(this.cost*2);
    }
}

//////////////////////////////////////////////////////
//Announcements: change in range
class Electric extends Vehicle {
    int range;
    Electric(){
        super();
        carNames = new ArrayList<>(Arrays.asList("Leaf", "Electra", "BMW i3", "BMW i4", "Bolt", "Mach-e", "Lightning", "Genesis","Hummer EV", "Ioniq", "KONA","I-Pace", "Niro","Maxwell ePro", "MX-30","Leaf Plus", "ARIYA", "Taycan", "Polestar 2", "Solterra", "Model X", "Model Y", "Model S Plaid", "C40 Recharge"));
        setName();
        this.type="Electric";
        this.cost=Utility.format(Utility.findValue(20000.0, 40000.0));
        this.saleBonus=3500.0;
        this.cleaningBonus=400.0;
        this.fixBonus=500.0;
        this.range=Utility.findValue(60, 400);
        setCondition();//function overloaded from Vehicle to add 100 miles to range if
        setCleanliness();
        this.salePrice=Utility.format(this.cost*2);
    }
    //overloaded parent setCondition to add to mileage range if like new
    protected void setCondition() {
        condition=conditions[Utility.findValue(0,conditions.length-1)];
        if(Objects.equals(condition, "LikeNew"))
            range+=100;
        else if(Objects.equals(condition, "Broken"))
            cost=Utility.format(cost-(cost*.5));
        else if(Objects.equals(condition, "Used"))
            cost=Utility.format(cost-(cost*.2));
    }
    //overloaded parent function to add 100 miles to range
    public void changeCarToLikeNew(){
        salePrice=Utility.format(salePrice*1.25);
        condition="LikeNew";
        range+=100;
        FNCDsim.broker.out(Enums.EventType.Fixing, "The " + name+ " has an improved range of " + range +" miles");
    }

    public int getRange(){
        return range;
    }
}

//////////////////////////////////////////////////////
//           Race car classes below here            //
//////////////////////////////////////////////////////

class Performance extends Vehicle implements RaceCar{
    private int racesWon=0;
    private double winBonus=450.0;

    Performance() {
        super();
        carNames = new ArrayList<>(Arrays.asList("Mustang", "Spider", "Firebird", "Cobra", "Owl", "Speedster", "Valhalla", "Viper", "Valkyrie", "Victor", "GT", "Sportback", "Divo", "Boldie", "Viper", "Prowler", "Getaway", "Stealth", "Prelude"));
        setName();
        this.type = "Performance";
        this.cost = Utility.format(Utility.findValue(20000.0, 40000.0));
        this.saleBonus = 4000.0;
        this.cleaningBonus = 400.0;
        this.fixBonus = 500.0;
        setCondition();
        setCleanliness();
        this.salePrice = Utility.format(this.cost * 2);
    }
    public int getRacesWon(){
        return racesWon;
    }
    public void addRaceWon(){
        racesWon+=racesWon;
        salePrice=1.10*salePrice;
    }
    public double getWinBonus(){
        return winBonus;
    }
}
//////////////////////////////////////////////////////
class Pickup extends Vehicle implements RaceCar{
    private int racesWon=0;
    private double winBonus=300.0;

    Pickup(){
        super();
        carNames =new ArrayList<>(Arrays.asList("Ram", "F150 ", "Tacoma", "Silverado", "Sierra", "Frontier", "Tundra", "Colorado", "Montana", "Strada", "Raptor", "Sierra", "Ridgline", "D-Max", "Frontier", "Navara", "Xenon"));
        setName();
        this.type="Pickup";
        this.cost=Utility.format(Utility.findValue(10000.0, 40000.0));
        this.saleBonus=3500.0;
        this.cleaningBonus=450.0;
        this.fixBonus=650.0;
        setCondition();
        setCleanliness();
        this.salePrice=Utility.format(this.cost*2);
    }
    public int getRacesWon(){
        return racesWon;
    }
    public void addRaceWon(){
        racesWon+=racesWon;
        salePrice=1.10*salePrice;
    }
    public double getWinBonus(){
        return winBonus;
    }

}
//////////////////////////////////////////////////////
class MonsterTruck extends Vehicle implements RaceCar{
    private int racesWon=0;
    private double winBonus=500.0;

    MonsterTruck(){
        super();
        carNames = new ArrayList<>(Arrays.asList("Destructor","Death Wheels","Mega Crush", "Avenger", "Batman", "2Xtream", "Bear Foot", "Big Foot", "Blue Thunder", "Bounty Hunter", "Brutus", "Bulldozer", "Game Over", "Grave Digger", "Grinder", "Worrier", "Oil Hog", "Monster Mutt", "Jacked Up", "Predator", "Terminator", "Swamp Thing", "The Felon", "Convict", "Lawless", "Killer", "Death Stomp", "War Wizard","The Machine", "Sudden Impact", "KO"));
        setName();
        this.type="MonsterTruck";
        this.cost=Utility.format(Utility.findValue(20000.0, 50000.0));
        this.saleBonus=4000.0;
        this.cleaningBonus=450.0;
        this.fixBonus=650.0;
        setCondition();
        setCleanliness();
        this.salePrice=Utility.format(this.cost*2);
    }
    public int getRacesWon(){
        return racesWon;
    }
    public void addRaceWon(){
        racesWon+=racesWon;
        salePrice=1.10*salePrice;
    }
    public double getWinBonus(){
        return winBonus;
    }
}
//////////////////////////////////////////////////////
//TODO: need to add truncated normal distribution for the engine rating
class Motorcycle extends Vehicle implements RaceCar{
    private int racesWon=0;
    private double winBonus=375.0;
    private double engineRating;

    Motorcycle(){
        super();
        carNames = new ArrayList<>(Arrays.asList("Venom", "Thunderbolt", "Spitfire", "X-74", "Commando", "Victor", "Silver Hawk", "Mach1 ", "Black Lightning", "Bullet", "Katana", "Intruder", "Trail 90", "Tiger", "Bandit", "Tiger", "Magna", "Sportster"));
        setName();
        this.type="Motorcycle";
        this.cost=Utility.format(Utility.findValue(5000.0, 25000.0));
        this.saleBonus=1500.0;
        this.cleaningBonus=150.0;
        this.fixBonus=300.0;
        setEngineRating();
        setCondition();
        setCleanliness();
        this.salePrice=Utility.format(this.cost*2);
    }
    private void setEngineRating() {
        //engineRating = ...
        Random rand = new Random();
        this.engineRating = rand.nextGaussian()*300+700;
    }
    public double getEngineRating(){
        return engineRating;
    }

    public int getRacesWon(){
        return racesWon;
    }
    public void addRaceWon(){
        racesWon+=racesWon;
        salePrice=1.10*salePrice;
    }
    public double getWinBonus(){
        return winBonus;
    }
}
