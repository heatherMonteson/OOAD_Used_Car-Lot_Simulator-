
package FNCDsim.src;

//OO pattern Command: implemented in FNCDsim(run function), DailyActivity(Online Shopping function), Employee(reciever
//class for many concrete commands), CLSim, CLI, Command, StringCommand, CommandInvoker, any file starting with
//"Ask" is a concrete command. The simplified flow through the command pattern is as follows:
//FLOW: User --> CLI --> Invoker --> Reciever

import java.util.Random;

import FNCDsim.src.Enums.StaffType;

import java.util.ArrayList;
//This class is a concrete command class, there will be one for each command in our menu

public class AskName implements Command {
   //private ArrayList<Employee> salesPeople;
    //private Employee salesperson;
    public static ArrayList<Employee> salesPeopleNorth;
    public static ArrayList<Employee> salesPeopleSouth;

    public AskName() {
        //current staff checks for fncd north or south dealership
        //salesPeople = Employee.getStaffByType(FNCDsim.currentStaff(), StaffType.Salesperson);
        salesPeopleNorth = Employee.getStaffByType(FNCDsim.getNorthstaff(), StaffType.Salesperson);
        salesPeopleSouth = Employee.getStaffByType(FNCDsim.getSouthstaff(), StaffType.Salesperson);
    }

    public void execute() {
   
        //NORTH SALESPERSON      
        Random rand = new Random();
        int randomIndex = rand.nextInt(salesPeopleNorth.size());
        //select a salesPerson in current staff
        CommandInvoker.Northsalesperson = salesPeopleNorth.get(randomIndex);
        //String name = CommandInvoker.Northsalesperson.getName(); //fetching name from reciever Salesperson class
        
    
        //SOUTH SALESPERSON      
        CommandInvoker.Southsalesperson = salesPeopleSouth.get(randomIndex);

        if(FNCDsim.currentDealership == Enums.FNCD_location.FNCD_North){
            System.out.println("Employee's name: " + CommandInvoker.Northsalesperson.getName());
        }
        else{
            System.out.println("Employee's name: " + CommandInvoker.Southsalesperson.getName());

        }
        
    }
}