package FNCDsim.src;
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