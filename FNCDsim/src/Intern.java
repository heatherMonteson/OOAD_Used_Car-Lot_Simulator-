/*
Fast Lookup Intern child class of Employee public methods:
hireIntern()
promoteInternToSales()
promoteInternToMechanic()
internQuit()
washCars()
 */

import java.util.ArrayList;
import java.util.Random;

public class Intern extends Employee {

    private final int[] drtCln_drtSprk={80, 10};//probability dirty becomes clean or sparkling
    private final int[] clnDrt_clnSprk = {5, 30}; //probability clean becomes dirty or sparkling

    Intern(){
        super("Intern", 8.50,15.0);
    }

    public void hireIntern(){
        Employee newIntern = new Intern();
        System.out.println("Hired new intern "+ newIntern.name);
    }

    public void promoteInternToSales(){
        try {
            ArrayList<Employee> interns = getInterns();
            if(interns.size()==0)
                System.out.println("No interns to promote");
            else {
                Employee intern = interns.get(findValue(0, interns.size()-1));
                Employee newSales = new Salesperson();
                newSales.name=intern.name;
                newSales.daysWorked= intern.daysWorked;
                newSales.incomeToDate = intern.incomeToDate;
                removeEmployee(intern);
                System.out.println("Intern "+ newSales.name +" has received a promotion to sales");
            }
        }catch (Exception e){
            System.out.println("Error promoting an intern to sales");
        }
    }

    public void promoteInternToMechanic(){
        try {
            ArrayList<Employee> interns = getInterns();
            if(interns.size()==0)
                System.out.println("No interns to promote");
            else {
                Employee intern = interns.get(findValue(0, interns.size()-1));
                Employee newMech = new Mechanic();
                newMech.name=intern.name;
                newMech.daysWorked= intern.daysWorked;
                newMech.incomeToDate = intern.incomeToDate;
                removeEmployee(intern);
                System.out.println("Intern "+ newMech.name +" has received a promotion to sales");
            }
        }catch (Exception e)
        {
            System.out.println("Error promoting an inter to mechanic");
        }
    }

    public void internQuit(){
        ArrayList<Employee> interns = getInterns();
        if(interns.size() == 0)
            return;
        if(findValue(0, 100)<=10){
            int randIndex = findValue(0, interns.size() - 1);
            System.out.println("Intern "+ interns.get(randIndex).name +" has quit");
            removeEmployee(interns.get(randIndex));
        }
    }

    public void washCars(){
        int j=0;
        int lower=1;
        int upper =100;
        ArrayList<Employee> interns = getInterns();
        ArrayList<Vehicle> dirtyCars = Vehicle.getDirtyCars();
        ArrayList<Vehicle> cleanCars = Vehicle.getCleanCars();

        if(interns.size()==0){
            System.out.println("You have no interns to wash cars");
            return;
        }
        if(dirtyCars.size()==0 && cleanCars.size()==0){
            System.out.println("You have no cars that require cleaning");
            return;
        }

        while(j<=1 && (cleanCars.size()>0 || dirtyCars.size()>0)){
            for (Employee intern : interns) {

                if (dirtyCars.size() > 0) {//there are dirty cars to clean
                    //generate probability if a car is cleaned and if it goes from dirty-->clean or --> sparkly
                    int toClean = findValue(lower, upper);
                    //enter if current employee is going to clean a car
                    if (toClean <= (drtCln_drtSprk[0] + drtCln_drtSprk[1])) {

                        int randIndex = findValue(0, dirtyCars.size() - 1);
                        Vehicle dirtyCar = dirtyCars.get(randIndex);

                        if (toClean <= drtCln_drtSprk[0]){ //dirty --> clean
                            System.out.println("Intern "+ intern.name+ " washed "+ dirtyCar.getType()+ " "+dirtyCar.getName()+ " and made it clean");
                            dirtyCar.changeCarToClean();
                            cleanCars.add(dirtyCar);
                            dirtyCars.remove(dirtyCar);
                        }
                        else if (toClean <= (drtCln_drtSprk[0] + drtCln_drtSprk[1])){ //dirty --> sparkling
                            System.out.println("Intern "+ intern.name+ " washed "+ dirtyCar.getType()+ " "+dirtyCar.getName()+ " and made it sparkly(earned a $"+ dirtyCar.getCleaningBonus()+" bonus)");
                            dirtyCar.changeCarToSparkly();
                            payBonus(dirtyCar.getCleaningBonus());
                            dirtyCars.remove(dirtyCar);
                        }
                    }
                }
                //no more dirty cars, move on to clean
                else if (cleanCars.size()>0) {
                    //probability if a car is cleaned and if it goes from clean-->dirty or --> sparkly
                    //same logic as above
                    int toClean = findValue(lower, upper);

                    if (toClean <= (drtCln_drtSprk[0] + drtCln_drtSprk[1])) {
                        int randIndex = findValue(0, cleanCars.size() - 1);
                        Vehicle cleanCar = cleanCars.get(randIndex);

                        if (toClean <=clnDrt_clnSprk[0]){ //clean-->dirty
                            cleanCar.changeCarToDirty();
                            dirtyCars.add(cleanCar);
                            cleanCars.remove(cleanCar);
                        }
                        else if (toClean <= (drtCln_drtSprk[0] + drtCln_drtSprk[1])){ //clean --> sparkling
                            System.out.println("Intern "+ intern.name+ " washed "+ cleanCar.getType()+ " "+cleanCar.getName()+ " and made it sparkly(earned a $"+ cleanCar.getCleaningBonus()+" bonus)");
                            cleanCar.changeCarToSparkly();
                            payBonus(cleanCar.getCleaningBonus());
                            cleanCars.remove(cleanCar);
                        }
                    }
                }
            }
            j++;
        }
    }
}


