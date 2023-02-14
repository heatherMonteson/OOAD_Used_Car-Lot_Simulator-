import java.util.Random;
public class Car extend Vehicle{
    private int costMin = 10,000;
    private int costMax = 20,000;

    Car(){
        cost = generateCost(costMin, costMax);
        salePrice = cost * 2;
        saleBonus = 1,000;
    }
    public static void addCars(int i) {
        //i is number to add
        // output when added to inventory and take cost of each out of budget

    }
}
