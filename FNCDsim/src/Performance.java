public class Performance extends Vehicle{
    Performance(){
        super("Performance", 3000.0, 1500.0, 3000.0,20000.0, 40000.0);
        saleBonus = 3000.0;
        fixBonus = 3000.0;
        washBonus = 1500.0;
    }
    public static void addPerformance(int i) {
        //i is number to add
        // output when added to inventory and take cost of each out of budget
        //        ArrayList<Vehicle> carList = new ArrayList<Car>();
        for (int j = 0; j < i; j++){
            Performance newPerformance = new Performance();
            currentInventory.add(newPerformance);
            performanceInventory.add(newPerformance);
        }
    }
}
