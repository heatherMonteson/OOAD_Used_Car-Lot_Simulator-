import java.util.ArrayList;

public class Vehicle {
    private String type;
    private String name;
    private String condition;
    private String cleanliness;
    protected double cleaningBonus;
    protected double salePrice =0.0;
    private static ArrayList<Vehicle> currentInventory =new ArrayList<>();
    private static ArrayList<Vehicle> soldInventory =new ArrayList<>();
    private static final String[] types = {"Car", "Pickup","Performance"};
    protected String[]conditions={"LikeNew", "Used", "Broken"};
    protected String[]cleanlinessLevel ={"Sparkly", "Clean", "Dirty"};
    protected static double dailySales=0.0; //adding sales is handled in the sales person class, don't need to worry about it at all
    protected static double totalSales=0.0;

    Vehicle(){}

    public static ArrayList<Vehicle> getSoldCars() {
    }

    public String getType(){

    }
    public static String[] getTypes(){
            return types;
    }

    public String getName(){

    }
    public static ArrayList<Vehicle> getDirtyCars() {

    }
    public static ArrayList<Vehicle> getCleanCars() {

    }
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

    public double getFixBonus(){

    }

    public double getCleaningBonus() {
        return 0.0;
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

    public double getSalePrice() {
    }

    public String getCondition() {
    }

    public String getCleanliness() {
    }

    public void removeFromInventory(Vehicle car) {
        //need to remove from static array list but add it to the sold cars array list
    }

    public double getSaleBonus() {
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
}
