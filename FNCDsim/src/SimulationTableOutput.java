package FNCDsim.src;

import java.util.ArrayList;

//class for tabular output from the simulation
public class SimulationTableOutput {

    public static void endOfDayOutput(ArrayList<Employee> finalEmployees, int numQuitters, ArrayList<Vehicle> finalCars, int totalSold, double totalSales, double accountBalance){

        System.out.printf("----------------------------------------------------------------------------------------------------------------------------%n");
        System.out.println("FNCD Employees");
        System.out.printf("----------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-20s | %-12s | %-15s | %-10s | %-15s | %-15s | %n", "Employee", "Department", "Days Worked", "Rate","Total Pay", "Total Bonus Pay");
        System.out.printf("----------------------------------------------------------------------------------------------------------------------------%n");

        for(Employee employee: finalEmployees){
            System.out.printf("| %-20s | %-12s | %-15s | %-10s | %-15s | %-15s | %n", employee.getName(), employee.getType(), employee.getDaysWorked(), employee.getSalary(),employee.getIncomeToDate(), employee.getTotalBonusPay());
        }

        System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.println("FNCD Inventory");
        System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-15s | %-15s | %n", "Vehicle", "Type", "Sale Price","Cost" ,"Condition", "Cleanliness");
        System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------%n");

        for(Vehicle car: finalCars){
            System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-15s | %-15s | %n", car.getName(), car.getType(), car.getSalePrice(), car.getCost(),car.getCondition(), car.getCleanliness());
        }

        System.out.printf("------------------------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.println("=========================================");
        System.out.println("Total number departed Staff: "+ numQuitters);
        System.out.println("Total vehicles sold: "+ totalSold);
        System.out.println("Total Sales: $"+ totalSales);
        System.out.println("Remaining Budget: $"+ accountBalance);
        System.out.println("=========================================");
    }

}
