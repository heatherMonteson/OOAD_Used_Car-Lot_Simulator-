package FNCDsim.src;

import java.util.ArrayList;
import java.util.Objects;

//class for tabular output from the simulation
public class SimulationTableOutput {

    public static void endOfDayOutput(ArrayList<Employee> finalEmployees, int numQuitters, ArrayList<Vehicle> finalCars, int totalSold, double totalSales, double accountBalance){

        System.out.printf("----------------------------------------------------------------------------------------------------------%n");
        System.out.println("FNCD Employees");
        System.out.printf("----------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-20s | %-12s | %-15s | %-10s | %-15s | %-15s | %n", "Employee", "Department", "Days Worked", "Rate","Total Pay", "Total Bonus Pay");
        System.out.printf("----------------------------------------------------------------------------------------------------------%n");

        for(Employee employee: finalEmployees){
            System.out.printf("| %-20s | %-12s | %-15s | %-10s | %-15s | %-15s | %n", employee.getName(), employee.getType(), employee.getDaysWorked(), employee.getSalary(),employee.getIncomeToDate(), employee.getTotalBonusPay());
        }

        System.out.printf("----------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.println("FNCD Inventory");
        System.out.printf("----------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-20s | %-15s | %-12s | %-12s | %-12s | %-12s | %-15s | %n", "Vehicle", "Type", "Sale Price","Cost($)" ,"Condition($)", "Cleanliness", "Features");
        System.out.printf("----------------------------------------------------------------------------------------------------------------------------------%n");

        for(Vehicle car: finalCars){

            if(Objects.equals(car.getType(), "Electric")) {
                Electric eCar = (Electric) car;
                System.out.printf("| %-20s | %-15s | %-12s | %-12s | %-12s | %-12s | %-15s | %n", eCar.getName(), eCar.getType(), eCar.getSalePrice(), eCar.getCost(),eCar.getCondition(), eCar.getCleanliness(), "E-Milage: "+ eCar.getRange()+ "mi");
            }
            else if(Objects.equals(car.getType(), "Motorcycle")){
                Motorcycle mCar = (Motorcycle) car;
                System.out.printf("| %-20s | %-15s | %-12s | %-12s | %-12s | %-12s | %-15s | %n", mCar.getName(), mCar.getType(), mCar.getSalePrice(), mCar.getCost(),mCar.getCondition(), mCar.getCleanliness(), "Engine:"+mCar.getEngineRating()+ "cc");
            }
            else
                System.out.printf("| %-20s | %-15s | %-12s | %-12s | %-12s | %-12s | %-15s | %n", car.getName(), car.getType(), car.getSalePrice(), car.getCost(),car.getCondition(), car.getCleanliness(), "-");

        }
        System.out.printf("----------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.println("=========================================");
        System.out.println("Total number departed Staff: "+ numQuitters);
        System.out.println("Total vehicles sold: "+ totalSold);
        System.out.println("Total Sales: $"+ totalSales);
        System.out.println("Remaining Budget: $"+ accountBalance);
        System.out.println("=========================================");
    }

}
