public class Performance extends Vehicle{
    private int costMin = 20,000;
    private int costMax = 40,000;
    Performance(){
        cost = generateCost(costMin, costMax);
        salePrice = cost * 2;
        saleBonus = 3,000;
    }
    public static void addPerformance(int i) {
        //i is number to add
        // output when added to inventory and take cost of each out of budget
    }
}
