package FNCDsim.src;

import java.util.ArrayList;

//Customers are assigned a chance (probability) that they will purchase a car
//they are also assigned a type of car that they are looking for
//Can create a single customer or multiple that are kept and in a local array list of customers

public class Customer implements Utility{
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    public double chance;
    public String type;

    Customer(){
        String[] types = Vehicle.getTypes();
        int[] purchaseChance = {10, 40, 70};//Level of probability of buying a car
        type= types[Utility.findValue(0, purchaseChance.length-1)];
        chance= purchaseChance[Utility.findValue(0, purchaseChance.length-1)];
    }

    //construct a list size=numCustomers
    Customer(int numCustomers){
        for (int i=1; i<=numCustomers; i++){
            Customer newCustomer = new Customer();
            customers.add(newCustomer);
        }
    }

    public String getType(){
        return type;
    }

    public ArrayList<Customer> getCustomers(){
        return customers;
    }
}
