package FNCDsim.src;
import java.time.LocalTime;
//This class is a concrete command class, there will be one for each command in our menu

public class AskTime implements Command {
    private Employee salesperson;

    public AskTime(Employee salesperson) {
        this.salesperson = salesperson;
    }

    public void execute() {
        //add getTime function in employee
        System.out.println("The current time is: " + salesperson.getTime());
    }
}
