package FNCDsim.src;

//CLI will pass an index to CommandInvoker and then it will look up the the index to its corresponding command
//in the stored array. Invoker calls executeCommand which executes a method on reciever through the concrere command lesson

import java.util.ArrayList;

public class CommandInvoker {
    private Command[] commands;
    //public static Employee salesperson;
    public static Employee Northsalesperson;
    public static Employee Southsalesperson;
    public CommandInvoker() {
        
        commands = new Command[] {
            //set up our array of stored commands
            new AskFNCD(), //index 0
            new AskName(),//index 1
            new AskTime(), //index 2
            new AskNewSales(), //index 3
            new AskInventory(), //index 4
            new AskItemDet(), //index 5
            new AskBuy(), //index 6
            new AsktoEnd() //index 7 (last one)
        };
    }

    public void executeCommand(int commandIndex) {
        commands[commandIndex].execute();
        //takes out concrete command and executes method in there
    }
    //this executeCommand is intended for 6. get details on inventory vehicle
    //uses StringCommand interface
    public void executeCommand2(int commandIndex,  String commandLine) {
        if(commands[commandIndex] instanceof StringCommand) {
            //type cast it into a stringCommand
            StringCommand stringCommand = (StringCommand) commands[commandIndex];
            stringCommand.execute(commandLine);
            //executes in concrete command, with our string being passed in
        } 
    
    }

    public void executeCommand3(int commandIndex,  String commandLine, ArrayList<Enums.AddOns> addOnList) {
        if(commands[commandIndex] instanceof AddonCommand) {
            //type cast it into a AddonCommand
            AddonCommand addOnCommand = (AddonCommand) commands[commandIndex];
            addOnCommand.execute(commandLine, addOnList);
            //executes in concrete command, with our string being passed in
        } 
    
    }

}