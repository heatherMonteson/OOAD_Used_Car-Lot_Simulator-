import java.util.Arrays;

public class Car extends Vehicle{
//    private double costMin = 10000.0;
//    private double costMax = 20000.0;


    Car(){
        super("Car", 1000.0, 500.0, 1000.0, 10000.0, 20000.0);
        saleBonus = 1000.0;
        fixBonus = 1000.0;
        washBonus = 500.0;
    }
    public static void addCars(int i) { //I was told to avoid using static methods because it makes it harder and this should be in the overall FNCD class. Another said it doesnt make since to put here since you have to create and object of this class to create another, which throws off the actual number of cars etc.
        //i is number to add
        // output when added to inventory and take cost of each out of budget
//        ArrayList<Vehicle> carList = new ArrayList<Car>();
        for (int j = 0; j < i; j++){
            Car newCar = new Car();
            currentInventory.add(newCar);
        }
        for (int j = 0; j < currentInventory.size(); j++){
            System.out.println(currentInventory.get(j));
        }
    }
}
