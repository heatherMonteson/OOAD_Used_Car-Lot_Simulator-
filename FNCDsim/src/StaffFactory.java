package FNCDsim.src;

import java.util.Objects;

//OO pattern: StaffFactory and Hire staff used to implement the factory pattern
public abstract class StaffFactory {

    public Employee hireStaff(Enums.StaffType type){
        return makeStaff(type);
    }
    protected abstract Employee makeStaff(Enums.StaffType type);
}

class HireStaff extends StaffFactory{

    protected Employee makeStaff(Enums.StaffType type){
        Employee employee = null;
        if(Objects.equals(type, Enums.StaffType.Intern))
            employee=new Intern();
        else if(Objects.equals(type, Enums.StaffType.Salesperson))
            employee=new Salesperson();
        else if (Objects.equals(type, Enums.StaffType.Mechanic))
            employee=new Mechanic();
        else if (Objects.equals(type, Enums.StaffType.Driver))
            employee=new Driver();
        return employee;
    }
}
