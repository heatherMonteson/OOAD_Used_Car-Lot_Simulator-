import java.util.ArrayList;
//TODO: put all the information for employees, vehicles, sales and budget into tables to display during and at the end of the simulation

public class SimulationTableOutput {

    public static void endOfDayOutput(){
        ArrayList<Employee> finalEmployees = Employee.getCurrentEmployees();
        ArrayList<Vehicle> finalCars = Vehicle.getCars();
        //ending budget and sales
        Vehicle.getDailySales();
        Bank.getBalance();
    }

    public static void finalOutput(){
        //Ending employees
        ArrayList<Employee> finalEmployees = Employee.getCurrentEmployees();

        //Ending inventory
        ArrayList<Vehicle> finalCars = Vehicle.getCars();

        //ending budget
        Bank.getBalance();

        //departed employees
        ArrayList<Employee> exEmployees = Employee.getExEmployees();

        //sold inventory
        ArrayList<Vehicle> soldCars = Vehicle.getSoldCars();

        //totoal emergency funds added
        Bank.getTotalEmergencyFundsUsed();

        //total sales
        Vehicle.getTotalSales();
    }

}
