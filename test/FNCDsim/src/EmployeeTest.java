package FNCDsim.src;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EmployeeTest {

    @Test
    public void testNumGetStaffByType() {
        ArrayList<Employee> interns = new ArrayList<>();
        interns.add(new Intern());
        interns.add(new Intern());
        interns.add(new Intern());
        interns.add(new Mechanic());
        interns.add(new Salesperson());
        assertEquals("Number of interns should be 3", 3, Employee.getStaffByType(interns, Enums.StaffType.Intern).size());
    }
}