import java.util.ArrayList;

public class Customer implements ValueFromRange {
    private String [] types= Vehicle.getTypes();
    private int [] purchaseChance = {10, 40, 70};
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    public double chance;
    public String type;

    Customer(){
        int rand= findValue(0, types.length);
        type= types[rand];
        rand=findValue(0, purchaseChance.length);
        chance=purchaseChance[rand];
    }

    Customer(int numCustomers){
        for (int i=1; i<=numCustomers; i++){
            Customer newCustomer = new Customer();
            customers.add(newCustomer);
        }
    }

    public ArrayList<Customer> getCustomers(){
        return customers;
    }
}
