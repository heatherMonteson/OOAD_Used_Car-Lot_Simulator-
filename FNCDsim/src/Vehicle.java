import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Vehicle {
    private String type;
    private String name;
    private String condition;
    private String cleanliness;
    private double cost;
    private double salePrice;
    private double cleaningBonus;
    private double saleBonus;
    private double fixBonus;
    private double washBonus;
    private static ArrayList<Vehicle> currentInventory = new ArrayList<>();
    private static ArrayList<Vehicle> soldInventory = new ArrayList<>();
    private static ArrayList<String> carNames = new ArrayList<String>(); //array that will hold read in car names
    private static final String[] types = {"Car", "Pickup","Performance"};
    protected String[] conditions = {"LikeNew", "Used", "Broken"};
    protected String[] cleanlinessLevel = {"Sparkly", "Clean", "Dirty"};
    protected static double dailySales=0.0; //adding sales is handled in the sales person class, don't need to worry about it at all
    protected static double totalSales=0.0;
    Vehicle(){ //default constructor
        type = "";
        name = "";
        condition = "";
        cleanliness = "";
        cleaningBonus = 0.0;
        saleBonus = 0.0;
        fixBonus = 0.0;
        washBonus = 0.0;
        cost = 0.0;
        salePrice = 0.0;
    }
    Vehicle(String inType, String inName, String inCondition, String inCleanliness, double inCost, double inSalePrice, double inCleaningBonus, double inSaleBonus, double inFixBonus, double inWashBonus){ //parameterized constructor
        type = intype;
        name = inName;
        condition = inCondition;
        cleanliness = inCleanliness;
        cost = inCost;
        salePrice = inSalePrice;
        cleaningBonus = inCleaningBonus;
        saleBonus = inSaleBonus;
        fixBonus = inFixBonus;
        washBonus = inWashBonus;
        currentInventory.add(this);
    }
    private Vehicle CreateVehicle(){
        setType();
        setName();
        setCondition();
        setCleanliness();
        cost = 0.0;
        saleBonus = 0.0;
        fixBonus = 0.0;
        washBonus = 0.0;
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
    public double getCleaningBonus() {
        return this.cleaningBonus;
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
    protected void setType(){
        Random random = new Random();
        int typesIndex = random.nextInt(types.length); //https://stackoverflow.com/questions/21726033/picking-a-random-item-from-an-array-of-strings-in-java
        this.type = types[typesIndex];
    }
    protected void setName(){
        Random random = new Random();
        this.name = random.nextInt(carNames.length);
        carNames.remove(name);//removing selected car from array so all cars are unique/name is not reused
    }
    protected void setCondition(){
        Random random = new Random();
        int conditonIndex = random.nextInt(conditions.length);
        this.conditon = conditions[conditionIndex];
    }
    protected void setCleanliness(){
        Random random = new Random();
        int cleanlinessIndex = random.nextInt(cleanlinessLevel.length);
        this.cleanliness = cleanlinessLevel[cleanlinessIndex];
    }
    protected void setCleaningBonus(double inCleaningBonus){
        this.cleaningBonus = inCleaningBonus;
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
    protected void setSalePrice(double inSalePrice){
        this.salePrice = inSalePrice;
    }
    protected void setCost(double inCost){
        this.cost = inCost;
    }
    //getters for arrays
    public static ArrayList<Vehicle> getSoldCars() {
    }

    public static String[] getTypes(){
            return types;
    }
    public static ArrayList<Vehicle> getDirtyCars() {

    }
    public static ArrayList<Vehicle> getCleanCars() {

    }
    public static ArrayList<Vehicle> getBrokenCars() {

    }

    public static ArrayList<Vehicle> getUsedCars() {

    }
    public static ArrayList<Vehicle> getLikeNewCars() {

    }
    public static ArrayList<Vehicle> getPickups(){

    }
    public static ArrayList<Vehicle> getCars(){

    }
    public static ArrayList<Vehicle> getPerformanceCars(){

    }
    //setters for arrays
    public void changeCarToDirty() {
    }
    public void changeCarToClean() {
    }
    public void changeCarToSparkly() {
    }
    public void downGradeCleanliness() {
        //sparkling --> clean ---> dirty
        //if dirty do nothing
    }
    public void changeCarToUsed(){
        //increase sale price by 50%
    }
    public void changeCarToLikeNew(){
        //increase sale price by 25%
    }
//    public double getSalePrice() { //think salePrice will go into the subclasses
//        return this.salePrice;
//    }
    public void removeFromInventory(Vehicle car) {
        //need to remove from static array list but add it to the sold cars array list
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
    private static readCarNames(){ //helper to read in text file of car names
        throws IOException{ //readAllLines gotten from https://www.geeksforgeeks.org/read-file-into-an-array-in-java/
            List<String> nameStream = new ArrayList<String>();
            nameStream = Files.readAllLines(Paths.get("Car_Names.txt")); // names gotten from http://www.namenerds.com/uucn/listofweek/cars.html
            carNames = nameStream.toArray(new String[0]);
        }
    }
    public int generateCost(int min, int max){ //helper to generate a random cost between vehicles max and min
        Random random = new Random();
        return int cost = random.nextInt(max-min) + max + min; //https://stackoverflow.com/questions/5271598/java-generate-random-number-between-two-given-values
    }
}