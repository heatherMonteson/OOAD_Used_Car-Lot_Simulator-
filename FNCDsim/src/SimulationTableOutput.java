import java.util.ArrayList;
public class SimulationTableOutput {

    public static void endOfDayOutput(ArrayList<Employee> finalEmployees, ArrayList<Employee> quitters,  ArrayList<Vehicle> finalCars,  ArrayList<Vehicle> soldCars){
        System.out.printf("----------------------------------------------------------------------------------------------------------------------------%n");
        System.out.println("FNCD Employees");
        System.out.printf("----------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-20s | %-12s | %-15s | %-10s | %-15s | %-15s | %-15s |%n", "Employee", "Department", "Days Worked", "Rate","Total Pay", "Total Bonus Pay", "Employed/Quit");
        System.out.printf("----------------------------------------------------------------------------------------------------------------------------%n");

        for(Employee employee: finalEmployees){
            System.out.printf("| %-20s | %-12s | %-15s | %-10s | %-15s | %-15s | %-15s |%n", employee.getName(), employee.getType(), employee.getDaysWorked(), employee.getSalary(),employee.getIncomeToDate(), employee.getTotalBonusPay(), "Employed");
        }
        for(Employee employee: quitters){
            System.out.printf("| %-20s | %-12s | %-15s | %-10s | %-15s | %-15s | %-15s |%n", employee.getName(),employee.getType(), employee.getDaysWorked(), employee.getSalary(), employee.getIncomeToDate(), employee.getTotalBonusPay(), "Quit");
        }


//add in cost and sold or instock
        System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.println("FNCD Inventory");
        System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-15s | %-15s | %-15s |%n", "Vehicle", "Type", "Sale Price","Cost" ,"Condition", "Cleanliness", "In Stock/Sold");
        System.out.printf("---------------------------------------------------------------------------------------------------------------------------------------------------%n");

        for(Vehicle car: finalCars){
            System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-15s | %-15s | %-15s |%n", car.getName(), car.getType(), car.getSalePrice(), car.getCost(),car.getCondition(), car.getCleanliness(), "In Stock");
        }
        for(Vehicle car: soldCars){
            System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-15s | %-15s | %-15s |%n", car.getName(), car.getType(), car.getSalePrice(), car.getCost(),car.getCondition(), car.getCleanliness(), "Sold");
        }
        System.out.printf("------------------------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.println("=========================================");
        System.out.println("Daily Sales: " + Bank.getDailySales());
        System.out.println("Remaining Budget: " +Bank.getBalance());
        System.out.println("=========================================");
    }

}
