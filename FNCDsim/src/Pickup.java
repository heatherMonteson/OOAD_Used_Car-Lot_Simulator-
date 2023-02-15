public class Pickup extends Vehicle{
    Pickup(){
        super("Pickup", 2000.0, 1000.0, 2000.0,10000.0, 40000.0);
        saleBonus = 2000.0;
        fixBonus = 2000.0;
        washBonus = 1000.0;
    }

    public static void addPickups(int i) {
        //i is number to add
        // output when added to inventory and take cost of each out of budget
        //        ArrayList<Vehicle> carList = new ArrayList<Car>();
        for (int j = 0; j < i; j++){
            Pickup newPickup = new Pickup();
            currentInventory.add(newPickup);
            pickupInventory.add(newPickup);
        }
    }
}
