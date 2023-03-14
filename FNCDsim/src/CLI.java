package FNCDsim.src;
import java.util.Scanner;
public class CLI {
    private CommandInvoker invoker;
    //public static Employee salesperson;
    public CLI(Employee salesperson) {
        // we want to grab a random salesperson
        //this.salesperson = salesperson;
        invoker = new CommandInvoker(salesperson);
        //issue: i want to change this salesperson, when i ask for a new one; how do i do this?
    }

    public int run() {
        Scanner scanner = new Scanner(System.in);
        int commandIndex = -1; //this is what commandIndex is set to, if nothing has been selected at beginning
        //as long as commandIndex is not end user, keep table up and running
        while (commandIndex != 8) {
            System.out.println();
            System.out.println("---------------------------------------------");
            System.out.println("WELCOME TO THE FNCD COMMAND LINE INTERFACE!");
            System.out.println("Please select a command:");
            System.out.println("1. Choose an FNCD location"); //index 0
            System.out.println("2. Ask the salesperson their name");
            System.out.println("3. Ask the salesperson what time it is");
            System.out.println("4. Ask for a different salesperson");
            System.out.println("5. Get current inventory");
            System.out.println("6. Get details on inventory vehicle");
            System.out.println("7. Buy a car!");
            System.out.println("8. End user interactions");

            commandIndex = scanner.nextInt() - 1; //takes in user input, minus one for indexing in array?
            
            if (commandIndex == 5) { //index is 5 bc we subtracted one above for array indexing
                //need more info, need requested vehicle name
                System.out.println("Please enter the vehicle name you are interested in!");
                scanner.nextLine(); // consume the newline character
                String vehicleId = scanner.nextLine();
                invoker.executeCommand2(commandIndex, vehicleId); // pass out index and vehicleId to invoker
            } else if(commandIndex == 6){ //buy a car condition
                System.out.println("Please enter the vehicle name you would like to purchase!");
                scanner.nextLine(); // consume the newline character
                String vehicleId2 = scanner.nextLine();
                invoker.executeCommand2(commandIndex, vehicleId2);
            } else {
                invoker.executeCommand(commandIndex); //pass out index to invoker
                if(commandIndex == 7){ //shuttind down the online shopping portal
                    return 1;
                }
            }
        }

        scanner.close();
        return 1;
    }
}



