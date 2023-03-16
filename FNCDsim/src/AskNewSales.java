package FNCDsim.src;
//This class is a concrete command class, there will be one for each command in our menu

public class AskNewSales implements Command {

    public AskNewSales() {
        
    }

    public void execute() {
        if(FNCDsim.currentDealership == Enums.FNCD_location.FNCD_North){
            CommandInvoker.Northsalesperson = CommandInvoker.Northsalesperson.getNewSalesPerson(CommandInvoker.Northsalesperson);
            System.out.println("Your new sales person is "+ CommandInvoker.Northsalesperson.getName());
        }else{
            CommandInvoker.Southsalesperson = CommandInvoker.Southsalesperson.getNewSalesPerson(CommandInvoker.Southsalesperson);
            System.out.println("Your new sales person is "+ CommandInvoker.Southsalesperson.getName());
        }
    }
}