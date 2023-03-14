package FNCDsim.src;

public class AskBuy implements StringCommand {
    private Employee salesperson;

    public AskBuy(Employee salesperson) {
        this.salesperson = salesperson;
        
    }

    public void execute() {
        //have in here, empty
    }


    public void execute(String message) {
        OnlineShopping.onlineShop(message, salesperson); //this is the reciever class
        
    }
}
