import java.util.ArrayList;

public class Vehicle {
    private String type;
    private String name;
    private String condition;
    private String cleanliness;
    protected double cleaningBonus;

    public String getType(){

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


}
