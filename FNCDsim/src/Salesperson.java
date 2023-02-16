package FNCDsim.src;

/*
OOAD Inheritance example:
Salesperson and all other subclasses of Employee inherit all the public and protected methods in the
Employee class, they also extend the Employee class to add their own capabilities. Objects of type Salesperson created
in other classes like the FNCDsimulation then not only have access to public Salesperson elements but also the inherited Employee public elements.
 */

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Salesperson extends Employee {


    Salesperson() {
        super("Sales", 250, 375.0);
    }

    public Vehicle sellCar(Customer customer, ArrayList<Vehicle> inventory){
       Vehicle car = findCarToSell(inventory, customer.getType());
       if(car!=null){
           if(isSaleMade(car, customer))
               return car;
       }
       return null;
    }

    private boolean isSaleMade(Vehicle car, Customer customer){
    //find if change in purchase chance
    if(customer.getType()!= car.getType()){
        customer.chance= customer.chance - 20;
    }if (car.getCondition()=="LikeNew"|| car.getCleanliness()=="Sparkly"){
        customer.chance= customer.chance + 10;
    }
    //customer wants to buy
    if(customer.chance>0 && ThreadLocalRandom.current().nextInt(1, 101)<=customer.chance){
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


