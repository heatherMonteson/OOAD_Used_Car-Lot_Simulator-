package FNCDsim.src;
import java.util.ArrayList;

public class AskBuy implements AddonCommand {
    public AskBuy() {
    }
    public void execute() {
        //have in here, empty
    }
    public void execute(String message) {
        // if(FNCDsim.currentDealership == Enums.FNCD_location.FNCD_North){
        //     OnlineShopping.onlineShop(message, CommandInvoker.Northsalesperson); //this is the reciever class
        // }
        // else{
        //     OnlineShopping.onlineShop(message, CommandInvoker.Southsalesperson); //this is the reciever class
        // }
        
    }
    
    public void execute(String message, ArrayList<Enums.AddOns> addOnList) {
        if(FNCDsim.currentDealership == Enums.FNCD_location.FNCD_North){
            OnlineShopping.onlineShop(message, addOnList, CommandInvoker.Northsalesperson); //this is the reciever class
        }
        else{
            OnlineShopping.onlineShop(message, addOnList, CommandInvoker.Southsalesperson); //this is the reciever class
        }

    }

}