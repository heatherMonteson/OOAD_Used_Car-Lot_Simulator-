package FNCDsim.src;
//import java.time.LocalTime;
//This class is a concrete command class, there will be one for each command in our menu

public class AskTime implements Command {
    public AskTime() {}
    public void execute() {
        //add getTime function in employee
        //does not matter which salesperson calls the time function
        if(CommandInvoker.Northsalesperson!=null)
            System.out.println("The current time is: " + CommandInvoker.Northsalesperson.getTime());
        else
            System.out.println("You need to meet your sales person before you can ask them the time");
    }
}