package FNCDsim.src;
//OO pattern Command: implemented in FNCDsim(run function), DailyActivity(Online Shopping function), Employee(reciever
//class for many concrete commands), CLSim, CLI, Command, StringCommand, CommandInvoker, any file starting with
//"Ask" is a concrete command. The simplified flow through the command pattern is as follows:
//FLOW: User --> CLI --> Invoker --> Reciever

public class CLSim {
    public static void CLSimRun(int day) {
        if (day == 31){    
            CLI cli = new CLI(); 
            cli.run(); //displays menu
        }
    }
}