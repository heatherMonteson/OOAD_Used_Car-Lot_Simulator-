package FNCDsim.src;
//This class is a concrete command class, there will be one for each command in our menu

public class AskNewSales implements Command {
    private Employee salesperson;
    private Employee newSalesperson;

    public AskNewSales(Employee salesperson) {
        this.salesperson = salesperson;
        //we passed in the oldsalesperson
        //NEED TO UPDATE SALESPERSON NAME AFTER--> switching the salesperson does not change the getsalesperson name
    }

    public void execute() {
        newSalesperson = salesperson.getNewSalesPerson(salesperson);
        System.out.println("Your new salesperson is : " + newSalesperson.getName());
        //i need this new salesperson to be updated in the other files
    }
}
