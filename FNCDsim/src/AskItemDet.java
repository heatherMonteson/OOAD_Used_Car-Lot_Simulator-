package FNCDsim.src;
//OO pattern Command: implemented in FNCDsim(run function), DailyActivity(Online Shopping function), Employee(reciever
//class for many concrete commands), CLSim, CLI, Command, StringCommand, CommandInvoker, any file starting with
//"Ask" is a concrete command. The simplified flow through the command pattern is as follows:
//FLOW: User --> CLI --> Invoker --> Reciever

//This class is a concrete command class, there will be one for each command in our menu

public class AskItemDet implements StringCommand {
    //private Employee salesperson;

    public AskItemDet() {
    }

    public void execute() {
        // Do nothing (or provide some default behavior)
        System.out.println("It has entered command execute, not string command execute");
    }

    public void execute(String message) {
        //System.out.println(message);
        //i want to take the message and get details of that vehicle
        boolean inventoryFound = false;
        for (Vehicle vehicle : FNCDsim.inventory()) {
            if (vehicle.getName().equals(message)) {
                inventoryFound = true;
                System.out.println("This car is a : " + vehicle.type);
                System.out.println("This car is: " + vehicle.condition);
                System.out.println("This car is: " + vehicle.cleanliness);
                System.out.println("This car costs: " + vehicle.salePrice);
                
            }
        }
        if (inventoryFound == false){
            System.out.println("This car is not in inventory, sorry!");
        }
        
    }

}