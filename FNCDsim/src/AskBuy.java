package FNCDsim.src;
import java.util.ArrayList;
//OO pattern Command: implemented in FNCDsim(run function), DailyActivity(Online Shopping function), Employee(reciever
//class for many concrete commands), CLSim, CLI, Command, StringCommand, CommandInvoker, any file starting with
//"Ask" is a concrete command. The simplified flow through the command pattern is as follows:
//FLOW: User --> CLI --> Invoker --> Reciever
public class AskBuy implements AddonCommand {
    public AskBuy() {
    }
    public void execute() {
        //have in here, empty
    }

    
    public void execute(String message, ArrayList<Enums.AddOns> addOnList) {
        if(FNCDsim.currentDealership == Enums.FNCD_location.FNCD_North){
            OnlineShopping.onlineShop(message, addOnList, CommandInvoker.Northsalesperson); //this is the reciever class
        }
        else{
            OnlineShopping.onlineShop(message, addOnList, CommandInvoker.Southsalesperson); //this is the reciever class
        }

    }

    public void execute(String message) {
        // if(FNCDsim.currentDealership == Enums.FNCD_location.FNCD_North){
        //     OnlineShopping.onlineShop(message, CommandInvoker.Northsalesperson); //this is the reciever class
        // }
        // else{
        //     OnlineShopping.onlineShop(message, CommandInvoker.Southsalesperson); //this is the reciever class
        // }

    }

}