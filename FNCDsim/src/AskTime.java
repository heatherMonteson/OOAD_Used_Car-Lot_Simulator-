package FNCDsim.src;
//OO pattern Command: implemented in FNCDsim(run function), DailyActivity(Online Shopping function), Employee(reciever
//class for many concrete commands), CLSim, CLI, Command, StringCommand, CommandInvoker, any file starting with
//"Ask" is a concrete command. The simplified flow through the command pattern is as follows:
//FLOW: User --> CLI --> Invoker --> Reciever

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