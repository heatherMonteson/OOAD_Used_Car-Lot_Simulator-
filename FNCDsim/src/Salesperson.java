/*
Fast Lookup Salesperson child class of Employee public methods:
salesQuit()
sellCars()
 */

import java.util.ArrayList;

public class Salesperson extends Employee{

    Salesperson(){
        super("Sales", 15.50,25.00);
    }

    public void salesQuit(){
        ArrayList<Employee> sales= getSalePeople();
        if(findValue(0, 100)<=10){
            int randIndex = findValue(0, sales.size() - 1);
            System.out.println("Salesperson "+ sales.get(randIndex).name +" has quit");
            removeEmployee(sales.get(randIndex));
        }
    }

    public void sellCars(int numCustomers){
        ArrayList<Employee> salesPeople= getSalePeople();
        int[] interest = {10, 40, 70};
        String [] carTypes = {"Performance", "Car", "Pickup"};
        ArrayList<Vehicle> carsToSell= new ArrayList<Vehicle>();
        carsToSell.addAll(Vehicle.getLikeNewCars());
        carsToSell.addAll(Vehicle.getUsedCars());

        for(int i=0; i<=numCustomers; i++){
            //generate interest and desired car type for customer
            int customerInterest = interest [findValue(0, interest.length - 1)];
            String carType = carTypes[findValue(0, carTypes.length - 1)];

            //find sales person to help
            int salesIndex = findValue(0, salesPeople.size() - 1);

            if(findValue(0, 101)<customerInterest){

            }
        }
    }


}
