import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Vehicle implements ValueFromRange{
    private String type;
    private String name;
    private String condition;
    private String cleanliness;
    private double cost;
    private double salePrice;
    protected double saleBonus;
    protected double fixBonus;
    protected double washBonus;
    protected static ArrayList<Vehicle> currentInventory = new ArrayList<>();
    protected static ArrayList<Vehicle> carInventory = new ArrayList<>();
    protected static ArrayList<Vehicle> pickupInventory = new ArrayList<>();
    protected static ArrayList<Vehicle> performanceInventory = new ArrayList<>();
    protected static ArrayList<Vehicle> soldInventory = new ArrayList<>();
    protected static ArrayList<Vehicle> soldCarInv = new ArrayList<>();
    private static ArrayList<String> carNames = new ArrayList<>(Arrays.asList("4Runner","Cordoba","Gran Fury","Nubira","Sonic","Acadia","Corniche","Gran Turismo","Oasis","Sonoma","Accent","Corolla","Grand Am","Odyssey","Sorento","Acclaim","Coronet","Grand Prix","Omega","Soul","Accord","Corrado","Grand Vitara","Omni","Spark","Achieva","Corsair","Grand Voyager","Optima","Spectra","Aerio","Corsica","Greiz","Outback","Spectrum","Aerostar","Cortina","Gremlin","Outlander","Spider","Aileron","Corvette","Grenada","Paceman","Spirior","Airstream","Cougar","Highlander","Pacer","Spirit","Alero","Countach","Hobio","Pacifica","Sportage","Allante","Courier","Hombre","Pampa","Sportvan","Alliance","Cressida","Horizon","Panamera","Sprint","Alpine","Crider","Hornet","Parisienne","Sprinter","Altima","Crossfire","Hummer","Park Avenue","Spyder","Amanti","Crosstrek","Hunter","Park Ward","Squire","Amaze","Crown Victoria","Huracan","Paseo","St. Regis","Amigo","Cruze","Husky","Passat","Stanza","Anglia","Cube","Ikon","Passport","Starion","Aperta","Cutlass","Impala","Pathfinder","Starlet")); //array that will hold read in car names
    private static final String[] types = {"Car", "Pickup","Performance"};
    protected String[] conditions = {"LikeNew", "Used", "Broken"};
    protected String[] cleanlinessLevel = {"Sparkly", "Clean", "Dirty"};
    protected static double dailySales=0.0; //adding sales is handled in the sales person class, don't need to worry about it at all
    protected static double totalSales=0.0;
    Vehicle(){ //default constructor

    }
    Vehicle(String type, double saleBonus, double washBonus, double fixBonus, double costMin, double costMax){ //parameterized constructor
        setName();
        setCondition();
        setCleanliness();
        setType(type);
        setSaleBonus(saleBonus);
        setWashBonus(washBonus);
        setFixBonus(fixBonus);
        setCost(costMin, costMax);
        setSalePrice(this.getCost());
        currentInventory.add(this);
    }
    //getters for attributes
    public String getType(){
        return this.type;
    }
    public String getName(){
        return this.name;
    }
    public String getCondition() {
        return this.condition;
    }
    public String getCleanliness() {
        return this.cleanliness;
    }
    public double getSaleBonus() {
        return this.saleBonus;
    }
    public double getFixBonus(){
        return this.fixBonus;
    }
    public double getWashBonus(){
        return this.washBonus;
    }
    public double getSalePrice(){
        return this.salePrice;
    }
    public double getCost(){
        return this.cost;
    }
    //setters for attributes
    protected void setType(String inType){
        this.type = inType;
//        Random random = new Random();
//        int typesIndex = random.nextInt(types.length); //https://stackoverflow.com/questions/21726033/picking-a-random-item-from-an-array-of-strings-in-java
//        this.type = types[typesIndex];
    }
    protected void setName(){
        Random random = new Random();
        int index = random.nextInt(carNames.size());
        this.name = carNames.get(index);
        carNames.remove(name);//removing selected car from array so all cars are unique/name is not reused
    }
    protected void setCondition(){
        Random random = new Random();
        int conditionIndex = random.nextInt(conditions.length);
        this.condition = conditions[conditionIndex];
    }
    protected void setCleanliness(){
        Random random = new Random();
        int cleanlinessIndex = random.nextInt(cleanlinessLevel.length);
        this.cleanliness = cleanlinessLevel[cleanlinessIndex];
    }
    protected void setSaleBonus(double inSaleBonus){
        this.saleBonus = inSaleBonus;
    }
    protected void setFixBonus(double inFixBonus){
        this.fixBonus = inFixBonus;
    }
    protected void setWashBonus(double inWashBonus){
        this.washBonus = inWashBonus;
    }
    protected void setSalePrice(double cost){
        this.salePrice = cost * 2;
    }
    protected void setCost(double min, double max){
        this.cost = findValue(min, max);
    }
    //getters for arrays
    public static ArrayList<Vehicle> getSoldCars() {
//        for (int i = 0; i < soldInventory.size(); i++){
//            if (soldInventory.get(i).type.equals("Car")){
//                soldCarInv.add(soldInventory.get(i));
//            }
//        }
        return soldCarInv;
    }

    public static ArrayList<Vehicle> getSoldInventory() {
        return soldInventory;
    }
/

    public static ArrayList<Vehicle> getDirtyCars() {
        return dirtyCars();
    }
    public static ArrayList<Vehicle> getCleanCars() {
        return cleanCars();
    }
    public static ArrayList<Vehicle> getBrokenCars() {
        return brokenCars();
    }

    public static ArrayList<Vehicle> getUsedCars() {
        return usedCars();
    }
    public static ArrayList<Vehicle> getLikeNewCars() {
        return likeNewCars();
    }
    public static ArrayList<Vehicle> getPickups(){
        return pickupsList();
    }
    public static ArrayList<Vehicle> getCars(){
        return carsList();
    }
    public static ArrayList<Vehicle> getPerformanceCars(){
        return performanceList();
    }
    //setters for arrays
    public void changeCarToDirty() {
            this.cleanliness = "Dirty";
    }
    public void changeCarToClean() {
        this.cleanliness = "Clean";
    }
    public void changeCarToSparkly() {
        this.cleanliness = "Sparkly";
    }
    public void downGradeCleanliness() {
        //sparkling --> clean ---> dirty
        //if dirty do nothing
        if (this.cleanliness == "Sparkly"){
            this.changeCarToClean();
        }
        else if (this.cleanliness == "Clean") {
            this.changeCarToDirty();
        }
    }
    public void changeCarToUsed(){
        //increase sale price by 50%
        this.condition = "Used";
        this.salePrice = this.salePrice * 1.5;
    }
    public void changeCarToLikeNew(){
        //increase sale price by 25%
        this.condition = "Like New";
        this.salePrice = this.salePrice * 1.25;
    }
    public void removeFromInventory(Vehicle car) {
        //need to remove from static array list but add it to the sold cars array list
        soldInventory.add(car);
        carInventory.remove(car);
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
    //helpers
}