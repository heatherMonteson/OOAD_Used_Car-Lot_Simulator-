
package FNCDsim.src;
import java.util.Scanner;
import java.util.InputMismatchException;

public class CLI {
    //this is called from CLSim
    private Command[] commands;
    private CommandInvoker invoker;
    public static int nameCount; //can access anywhere
    public CLI() {
        invoker = new CommandInvoker();
        
    }

    public int run() {
        nameCount = 0;
        Scanner scanner = new Scanner(System.in);

        int commandIndex = -1; //this is what commandIndex is set to, if nothing has been selected at beginning

        //as long as commandIndex is not end user, keep table up and running
        while (commandIndex != 8) {
            System.out.println();
            System.out.println("---------------------------------------------");
            System.out.println("WELCOME TO THE FNCD COMMAND LINE INTERFACE!");
            System.out.println("Please select a command:");
            System.out.println("1. Choose an FNCD location"); //index 0
            System.out.println("2. Meet/ask the salesperson their name");
            System.out.println("3. Ask the salesperson what time it is");
            System.out.println("4. Ask for a different salesperson");
            System.out.println("5. Get current inventory");
            System.out.println("6. Get details on inventory vehicle");
            System.out.println("7. Buy a car!");
            System.out.println("8. End user interactions");

            boolean flag=false;
            while (!flag)
            try{
                commandIndex = scanner.nextInt() - 1; //takes in user input, minus one for indexing in array
                flag=true;
            }catch (InputMismatchException e){
                System.out.println("Error: Invalid input. Please enter an integer 1-8.");
                scanner.nextLine(); // consume the invalid input
            }
            if(commandIndex>=8 || commandIndex <0){ //check value is not out of indexing range
                System.out.println("Invalid numerical input, please enter a value 1-8");
            }
            else if (commandIndex == 0){
                //calls command 0 : AskFNCD
                System.out.println("Please enter the FNCD dealership you are interested in! (north or south)");
                scanner.nextLine(); // consume the newline character
                String dealership = scanner.nextLine();
                invoker.executeCommand2(commandIndex, dealership); //pass our 
                
            }else if(commandIndex == 1){ //ask name
                if(nameCount == 0){ //this can only be called once so we keep the same salesperson
                    //sets both fncd north and south salesperson
                    invoker.executeCommand(commandIndex);
                }else{
                    if(FNCDsim.currentDealership == Enums.FNCD_location.FNCD_North && CommandInvoker.Northsalesperson!=null){
                        System.out.println("Employee's name: " + CommandInvoker.Northsalesperson.getName());
                    }
                    else if(CommandInvoker.Southsalesperson!=null){
                        System.out.println("Employee's name: " + CommandInvoker.Southsalesperson.getName());
                    }
                    else
                        System.out.println("No employee found");
                }
                nameCount++;
            }else if(commandIndex == 5){ 
                //need more info, need requested vehicle name
                System.out.println("Please enter the vehicle name you are interested in!");
                scanner.nextLine(); // consume the newline character
                String vehicleId = scanner.nextLine();
                invoker.executeCommand2(commandIndex, vehicleId); // pass out index and vehicleId to invoker
            }else if(commandIndex == 6){
                if(CommandInvoker.Northsalesperson == null){
                    System.out.println("ERROR: Please meet your salesperson before purchasing!");
                    continue;
                }
                System.out.println("Please enter the vehicle you would like to purchase!");
                scanner.nextLine(); // consume the newline character
                String carBuy = scanner.nextLine();
                invoker.executeCommand2(commandIndex, carBuy); // pass out index and vehicleId to invoker
            }else if(commandIndex == 3){
                if(CommandInvoker.Northsalesperson == null){
                    System.out.println("ERROR: Please meet your salesperson before switching salespersons!");
                    continue;
                }
                invoker.executeCommand(commandIndex);
            }
            else {
                invoker.executeCommand(commandIndex);
                if(commandIndex == 7){ //shutting down the online shopping portal
                    return 1;
                }
            }
            //  need to add condition if input is not a valid number or if it is a string
        }
        scanner.close();
        return 1;
    }
}
