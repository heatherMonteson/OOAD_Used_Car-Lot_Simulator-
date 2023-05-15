package FNCDsim.src;

import org.junit.Test;

import static org.junit.Assert.*;

public class InternTest {

    @Test
    public void promoteIntern() {
        FNCDsim sim = new FNCDsim();
        sim.run(1);
        Employee intern = Employee.getStaffByType(FNCDsim.currentStaff(), Enums.StaffType.Intern).get(0);
        assertEquals("Returning intern type", Enums.StaffType.Intern, intern.getType());
        //promotion check
        Intern intern1=(Intern) intern;
        assertEquals("Promte to sales check ", Enums.StaffType.Salesperson, intern1.promoteIntern(Enums.StaffType.Salesperson).getType());
        assertEquals("Promte to sales check ", Enums.StaffType.Mechanic, intern1.promoteIntern(Enums.StaffType.Mechanic).getType());

    }
}