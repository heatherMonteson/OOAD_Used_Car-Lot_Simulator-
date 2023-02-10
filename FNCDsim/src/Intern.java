import java.lang.reflect.Array;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Random;

public class Intern extends Employee {

    private static ArrayList<Intern> pastInterns;
    private final double[] saleryRange={8.50,15.0};
    private final int[] drtCln_drtSprk={80, 10};//probability dirty becomes clean or sparkling
    private final int[] clnDrt_clnSprk = {5, 30}; //probability clean becomes dirty or sparkling
    private Salesperson salesperson = new Salesperson();
    private Mechanic mechanic = new Mechanic();

    Intern(){
        Employee newEmp = new Employee();
        newEmp.type="Intern";
        newEmp.setName();
        newEmp.setSalery(saleryRange[0], saleryRange[1]);
        addEmployee(newEmp);
    }

    public void promoteInternToSales(){
        ArrayList<Employee> interns = getInterns();
        Employee intern = interns.get(findValue(0, interns.size()-1));
        intern.type="Sales";
        double[] range = salesperson.getSaleryRange();
        salesperson.setSalery(range[0], range[1]);
        intern.salery = salesperson.getSalery();
    }

    public void promoteInternToMechanic(){
        ArrayList<Employee> interns = getInterns();
        Employee intern = interns.get(findValue(0, interns.size()-1));
        intern.type="Mechanic";
        double[] range = mechanic.getSaleryRange();
        mechanic.setSalery(range[0], range[1]);
        intern.salery = mechanic.getSalery();
    }

    public void internQuit(){
        ArrayList<Employee> interns = getInterns();
        if(findValue(0, 100)<=10){
            int randIndex = findValue(0, interns.size() - 1);
            removeEmployee(interns.get(randIndex));
        }
    }

    public void washCars(){
        int j=0;
        int lower=1;
        int upper =100;
        ArrayList<Employee> interns = getInterns();
        ArrayList<Vehicle> dirtyCars = cars.getDirtyCars();
        ArrayList<Vehicle> cleanCars = cars.getCleanCars();

        while(j<=1 && (cleanCars.size()>0 || dirtyCars.size()>0)){
            for (Employee intern : interns) {

                //there are still dirty cars to clean
                if (dirtyCars.size() > 0) {

                    //generate probability if a car is cleaned and if it goes from dirty-->clean or --> sparkly
                    int toClean = findValue(lower, upper);

                    //enter if current employee is going to clean a car
                    if (toClean <= (drtCln_drtSprk[0] + drtCln_drtSprk[1])) {

                        int randIndex = findValue(0, dirtyCars.size() - 1);
                        Vehicle dirtyCar = dirtyCars.get(randIndex);

                        if (toClean <= drtCln_drtSprk[0]) //dirty --> clean
                        {
                            System.out.println("Intern "+ intern.name+ " washed "+ dirtyCar.getType()+ " "+dirtyCar.getName()+ " and made it clean");
                            dirtyCar.changeCarToClean();
                            cleanCars.add(dirtyCar);
                            dirtyCars.remove(dirtyCar);
                        }
                        else if (toClean <= (drtCln_drtSprk[0] + drtCln_drtSprk[1])) //dirty --> sparkling
                        {
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
                    int toClean = findValue(lower, upper);

                    if (toClean <= (drtCln_drtSprk[0] + drtCln_drtSprk[1])) {

                        int randIndex = findValue(0, cleanCars.size() - 1);
                        Vehicle cleanCar = cleanCars.get(randIndex);

                        if (toClean <=clnDrt_clnSprk[0]) //clean-->dirty
                        {
                            cleanCar.changeCarToDirty();
                            dirtyCars.add(cleanCar);
                            cleanCars.remove(cleanCar);
                        }
                        else if (toClean <= (drtCln_drtSprk[0] + drtCln_drtSprk[1])) //clean --> sparkling
                        {
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


