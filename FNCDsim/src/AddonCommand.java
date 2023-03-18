package FNCDsim.src;
//OO pattern Command: implemented in FNCDsim(run function), DailyActivity(Online Shopping function), Employee(reciever
//class for many concrete commands), CLSim, CLI, Command, StringCommand, CommandInvoker, any file starting with
//"Ask" is a concrete command. The simplified flow through the command pattern is as follows:
//FLOW: User --> CLI --> Invoker --> Reciever
import java.util.ArrayList;

public interface AddonCommand extends Command {
    
    void execute(String message, ArrayList<Enums.AddOns> addOnList);

}
