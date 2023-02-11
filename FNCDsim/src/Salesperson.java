/*
Fast Lookup Salesperson child class of Employee public methods:
salesQuit()
sellCars()
 */

/*
OOAD Inheritance example:
Salesperson and all other subclasses of Employee inherit all the public and protected methods in the
Employee class, they also extend the Employee class to add their own capabilities. Objects of type Salesperson created
in other classes like the FNCDsimulation then not only have access to public Salesperson elements but also the inherited Employee public elements.
 */

import java.util.ArrayList;
import java.util.Objects;

public class Salesperson extends Employee {


    Salesperson() {
        super("Sales", 15.50, 25.00);
    }

    public void salesQuit() {
        ArrayList<Employee> sales = getSalePeople();
        if (findValue(0, 100) <= 10) {
            int randIndex = findValue(0, sales.size() - 1);
            System.out.println("Salesperson " + sales.get(randIndex).name + " has quit");
            removeEmployee(sales.get(randIndex));
        }
    }

    public void sellCars(int numCustomers) {

        if (numCustomers == 0)
            return;
        ArrayList<Customer> customers = (new Customer(numCustomers)).getCustomers();
        ArrayList<Employee> salesPeople = getSalePeople();
        if (salesPeople.size()==0){
            System.out.println("No sales people to help customers today");
            return;
        }

        ArrayList<Vehicle> carsToSell = new ArrayList<Vehicle>();
        carsToSell.addAll(Vehicle.getLikeNewCars());
        carsToSell.addAll(Vehicle.getUsedCars());
        if (carsToSell.size() == 0 ) {
            System.out.println("No Cars to sell today");
            return;
        }
        double saleBonus;
        int randIndex;
        double salePrice;

        for (Customer customer : customers) {
            Vehicle car=findCarToSell(carsToSell, customer.type);
            if(car==null){
                System.out.println("Error finding cars to sell"); // if there are cars it should always return a car
                break;
            }
            if(isSaleMade(car, customer)) { //sale is successful
                randIndex=findValue(0, salesPeople.size()-1);
                saleBonus =car.getSaleBonus();
                salePrice=car.getSalePrice();
                Vehicle.addSale(salePrice);
                Bank.addToBalance(salePrice);
                salesPeople.get(randIndex).payBonus(Bank.getFunds(saleBonus));
                car.removeFromInventory(car);
            }
        }
    }

    private boolean isSaleMade(Vehicle car, Customer customer){
        //find if change in purchase chance
        if(customer.type!= car.getType()){
            customer.chance= customer.chance - 20;
        }if (car.getCondition()=="LikeNew"|| car.getCleanliness()=="Sparkly"){
            customer.chance= customer.chance + 10;
        }
        //customer wants to buy
        if(customer.chance>0 && findValue(0, 100)<=customer.chance){
            return true;
        }
        return false;
    }

    private Vehicle findCarToSell(ArrayList<Vehicle> carsToSell, String type) {
        Vehicle carOfType = null;
        Vehicle otherType = null;

        if (carsToSell.size() > 0) {
            for (Vehicle car : carsToSell) {//find car to sell to customer
                if (Objects.equals(car.getType(), type)) { //finding desired type
                    if (carOfType == null)
                        carOfType = car;
                    else if (carOfType.getSalePrice() > car.getSalePrice())
                        carOfType = car;
                } else if (carOfType == null) { //finding most expensive for if no type exists.
                    if (otherType == null)
                        otherType = car;
                    else if (otherType.getSalePrice() > car.getSalePrice())
                        otherType = car;
                }
            }
        }else{return null;}//no cars

        if(carOfType!=null)
            return carOfType;
        else
            return otherType;
    }

}


