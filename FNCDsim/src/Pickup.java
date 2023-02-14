public class Pickup extends Vehicle{
    private int costMin = 10,000;
    private int costMax = 40,000;
    Pickup(){
        cost = generateCost(costMin, costMax);
        salePrice = cost * 2;
        saleBonus = 2,000;
    }

    public static void addPickups(int i) {
        //i is number to add
        // output when added to inventory and take cost of each out of budget
    }
}
