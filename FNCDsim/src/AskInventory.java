package FNCDsim.src;
//OO pattern Command: implemented in FNCDsim(run function), DailyActivity(Online Shopping function), Employee(reciever
//class for many concrete commands), CLSim, CLI, Command, StringCommand, CommandInvoker, any file starting with
//"Ask" is a concrete command. The simplified flow through the command pattern is as follows:
//FLOW: User --> CLI --> Invoker --> Reciever
import java.util.ArrayList;
//This class is a concrete command class, there will be one for each command in our menu

public class AskInventory implements Command {

    public AskInventory() {
    
    }

    public void execute() {
        System.out.println("-------------------------------------------");
        System.out.println("Listing inventory for "+ FNCDsim.currentDealership);
        ArrayList<Vehicle> inventory = FNCDsim.inventory();
        for (int i = 0; i < inventory.size(); i++){
            System.out.println(inventory.get(i).getName());
        }
        
    }
}