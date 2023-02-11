/*
Fast Lookup Salesperson child class of Employee public methods:
mechanicQuit()
fixCars()
 */

import java.util.ArrayList;

public class Mechanic extends Employee{
    private int chanceFix = 80;

    Mechanic() {
        super("Mechanic", 15.50,25.00);
    }

    public void mechanicQuit(){
        ArrayList<Employee> mechanics= getMechanics();
        if(mechanics.size()==0)
            return;
        if(findValue(0, 100)<=10){
            int randIndex = findValue(0, mechanics.size() - 1);
            System.out.println("Mechanic "+ mechanics.get(randIndex).name +" has quit");
            removeEmployee(mechanics.get(randIndex));
        }
    }

    public void fixCars() {
        int j = 0;
        int lower = 1;
        int upper = 100;
        ArrayList<Employee> mechanics = getMechanics();
        ArrayList<Vehicle> brokenCars = Vehicle.getBrokenCars();
        ArrayList<Vehicle> usedCars = Vehicle.getUsedCars();

        if(mechanics.size()==0){
            System.out.println("There are no mechanics to fix cars");
            return;
        }
        if(brokenCars.size()==0 && usedCars.size()==0){
            System.out.println("You have no cars that require fixing");
            return;
        }

        //let mechanics fix up to 2 cars
        while (j <= 1 && (brokenCars.size() > 0 || usedCars.size() > 0)) {
            for (Employee mechanic : mechanics) {

                //probability that a car is repaired
                int toFix = findValue(lower, upper);

                //still have broken cars to fix
                if (brokenCars.size() > 0) {
                    int randIndex = findValue(0, brokenCars.size() - 1);
                    Vehicle brokenCar = brokenCars.get(randIndex);
                    brokenCar.downGradeCleanliness();//all cars worked on go down cleanliness
                    if (toFix < chanceFix + 1) {
                        System.out.println("Mechanic " + mechanic.name + " fixed " + brokenCar.getType() + " " + brokenCar.getName() + " and made it used (earned a $" + brokenCar.getFixBonus() + " bonus)");
                        brokenCar.changeCarToUsed();
                        mechanic.payBonus(brokenCar.getFixBonus());
                        usedCars.add(brokenCar);
                        brokenCars.remove(brokenCar);
                    }
                    //no more broken cars to fix, move to used
                    else if (usedCars.size() > 0) {
                        int randIndex2 = findValue(0, usedCars.size() - 1);
                        Vehicle usedCar = usedCars.get(randIndex2);
                        usedCar.downGradeCleanliness();//all cars worked on go down cleanliness
                        if (toFix < chanceFix + 1) {
                            System.out.println("Mechanic " + mechanic.name + " fixed " + usedCar.getType() + " " + usedCar.getName() + " and made it like new (earned a $" + usedCar.getFixBonus() + " bonus)");
                            usedCar.changeCarToLikeNew();
                            mechanic.payBonus(usedCar.getFixBonus());
                            usedCars.remove(usedCar);
                        }
                    }
                }

            }
        }
    }


}


