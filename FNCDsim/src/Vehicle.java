import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Vehicle implements ValueFromRange, NameGenerator{

    protected String type;
    protected String name;
    protected String condition;
    protected String cleanliness;
    protected double cleaningBonus=0.0;
    protected double fixBonus=0.0;
    protected double salePrice =0.0;
    protected double cost=0.0;
    protected double saleBonus=0.0;
    private static ArrayList<Vehicle> currentInventory =new ArrayList<>();
    private static ArrayList<Vehicle> soldInventory =new ArrayList<>();
    private static final String[] types = {"Car", "Pickup","Performance"};
    protected String[]conditions={"LikeNew", "Used", "Broken"};
    protected static double dailySales=0.0; //adding sales is handled in the sales person class, don't need to worry about it at all
    protected static double totalSales=0.0;
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
        currentInventory.add(this);
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

    public static ArrayList<Vehicle> getSoldCars() {
        return soldInventory;
    }
    public String getType(){
        return type;
    }
    public static String[] getTypes(){
            return types;
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

    public double getFixBonus(){
        return fixBonus;
    }

    public double getCleaningBonus() {
        return cleaningBonus;
    }

    public static ArrayList<Vehicle> getDirtyCars() {
        return searchClean("Dirty");
    }
    public static ArrayList<Vehicle> getCleanCars() {
        return searchClean("Clean");
    }

    private static  ArrayList<Vehicle> searchClean(String value) {
        ArrayList<Vehicle> cars = new ArrayList<>();
        for (Vehicle car : currentInventory) {
            if (Objects.equals(car.cleanliness, value)) {
                cars.add(car);
            }
        }
        return cars;
    }
    public static ArrayList<Vehicle> getBrokenCars() {
        return searchCondition("Broken");
    }
    public static ArrayList<Vehicle> getUsedCars() {
        return searchCondition("Used");
    }
    public static ArrayList<Vehicle> getLikeNewCars() {return searchCondition("LikeNew");
    }

    private static  ArrayList<Vehicle> searchCondition(String value){
        ArrayList<Vehicle> cars = new ArrayList<>();
        for (Vehicle car : currentInventory) {
            if (Objects.equals(car.condition, value)) {
                cars.add(car);
            }
        }
        return cars;
    }
    public static ArrayList<Vehicle> getCurrentInventory(){
        return currentInventory;
    }
    public static ArrayList<Vehicle> getPickups(){
        return searchType("Pickup");
    }
    public static ArrayList<Vehicle> getCars(){
        return searchType("Car");
    }
    public static ArrayList<Vehicle> getPerformanceCars(){
        return searchType("Performance");
    }

    private static  ArrayList<Vehicle> searchType(String value){
        ArrayList<Vehicle> cars = new ArrayList<>();
        for (Vehicle car : currentInventory) {
            if (Objects.equals(car.type, value)) {
                cars.add(car);
            }
        }
        return cars;
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

    public void removeFromInventory(Vehicle car) {
        soldInventory.add(car);
        currentInventory.remove(car);
    }

    public double getSaleBonus() {
        return saleBonus;
    }

    //done
    public static double getDailySales() {
        return dailySales;
    }
    //done
    public static void resetDailySales() {
        dailySales=0.0;
    }
    //done
    protected static void addSale(double salesMade){
        dailySales=dailySales+salesMade;
        totalSales=totalSales+dailySales;
    }
    public static double getTotalSales(){
        return totalSales;
    }

    public double getCost() {
        return cost;
    }
}
