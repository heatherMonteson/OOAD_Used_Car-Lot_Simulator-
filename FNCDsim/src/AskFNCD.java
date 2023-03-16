package FNCDsim.src;

public class AskFNCD implements StringCommand {
    public AskFNCD() {
    
    }

    public void execute() {
        // Do nothing (or provide some default behavior)
        System.out.println("Has entered command execute, instead of string command execute");
    }

    public void execute(String dealership) {
        //take the given dealership in message and set our currDealership
        //String north = "North"; // example value for dealership variables
        //String south = "South";
        if (dealership.equalsIgnoreCase("north")) {
            System.out.println("The FNCD dealership you have selected is North!");
            //need to set the currDealership
            FNCDsim.currentDealership = Enums.FNCD_location.FNCD_North;
            System.out.println( FNCDsim.currentDealership);
        } else if (dealership.equalsIgnoreCase("south")){
            System.out.println("The FNCD dealership you have selected is South!");
            FNCDsim.currentDealership = Enums.FNCD_location.FNCD_South;
            System.out.println( FNCDsim.currentDealership);
        }
        else{
            System.out.println("You have not entered a valid dealership! Please try again.");
        }
    }
}