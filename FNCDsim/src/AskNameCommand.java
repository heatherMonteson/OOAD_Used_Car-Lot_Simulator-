package FNCDsim.src;
//This class is a concrete command class, there will be one for each command in our menu

public class AskNameCommand implements Command {
    private Employee salesperson;

    public AskNameCommand(Employee salesperson) {
        this.salesperson = salesperson;
    }

    public void execute() {
        String name = salesperson.getName(); //fetching name from reciever Salesperson class
        System.out.println("Employee's name: " + name);
        
    }
}