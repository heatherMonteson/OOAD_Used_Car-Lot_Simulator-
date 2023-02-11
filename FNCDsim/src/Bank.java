import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Bank {
    private static double balance = 0.0;
    private static double emergencyFunds=0.0;
    private static double totalEmergencyFundsUsed=0.0;

    Bank(double balance, double emergencyFunds){
        Bank.balance=balance;
        Bank.emergencyFunds =emergencyFunds;
        formatBalance();
    }

    public static double getBalance(){
        return balance;
    }

    public static void addToBalance(double funds){
        balance=balance+funds;
    }

    //pass in value to see if there is enough in account and return value
    //funds are added if the amount needed sends the account to zero
    public static double getFunds(double amtNeeded){
        if(balance-amtNeeded<0)
            addEmergencyFunds();
        balance=balance-amtNeeded;
        formatBalance();
        return amtNeeded;
    }

    private static void addEmergencyFunds(){
        balance=emergencyFunds;
        totalEmergencyFundsUsed=totalEmergencyFundsUsed+emergencyFunds;
        formatBalance();
        System.out.println("Emergency funds of $"+emergencyFunds+" have been added to account.");
    }
    public static double getTotalEmergencyFundsUsed(){
        return totalEmergencyFundsUsed;
    }

    private static void formatBalance(){
        DecimalFormat formatting = new DecimalFormat("#.##");
        formatting.setRoundingMode(RoundingMode.DOWN);
        balance = Double.parseDouble(formatting.format(balance));
    }

}
