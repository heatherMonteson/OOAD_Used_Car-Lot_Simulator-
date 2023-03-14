package FNCDsim.src;
import java.util.ArrayList;
import java.util.Random;


public class CLSim {
    public static void CLSimRun(int day) {
        if (day == 31){    
            //randomly getting a SalesPerson from FNCDsim current staff inventory 
            ArrayList<Employee> salesPeople = Employee.getStaffByType(FNCDsim.currentStaff, "Sales");
            Random rand = new Random();
            int randomIndex = rand.nextInt(salesPeople.size());
            //select a salesPerson in current staff
            Employee randomSalesPerson = salesPeople.get(randomIndex);
            CLI cli = new CLI(randomSalesPerson); 
            cli.run(); //displays menu
        }
    }
}

