package FNCDsim.src;
import java.util.ArrayList;
//This class is a concrete command class, there will be one for each command in our menu

public class AskInventory implements Command {
    //private Employee salesperson;

    public AskInventory() {
        //his.salesperson = salesperson;
        
    }

    public void execute() {
        ArrayList<Vehicle> inventory = FNCDsim.getInventory();
        for (int i = 0; i < inventory.size(); i++){
            //System.out.println(inventory.get(i));
            System.out.println(inventory.get(i).getName());
        }
        
    }
}

