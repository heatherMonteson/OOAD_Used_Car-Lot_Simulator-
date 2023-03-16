package FNCDsim.src;

public class AskBuy implements StringCommand {
    

    public AskBuy() {
        
    }

    public void execute() {
        //have in here, empty
    }


    public void execute(String message) {
        if(FNCDsim.currentDealership == Enums.FNCD_location.FNCD_North){
            OnlineShopping.onlineShop(message, CommandInvoker.Northsalesperson); //this is the reciever class

        }
        else{
            OnlineShopping.onlineShop(message, CommandInvoker.Southsalesperson); //this is the reciever class

        }
        
    }
}