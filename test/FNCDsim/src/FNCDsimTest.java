package FNCDsim.src;

import org.junit.Test;

import static org.junit.Assert.*;

public class FNCDsimTest {
    @Test
    public void checkLoggerAndTracker() {
        //testing that a broker, logger and tracker are made when FNCD sim object is created
        FNCDsim newSim = new FNCDsim();
        assertNotEquals("Verify a broker object is made", null, FNCDsim.broker);
        assertNotEquals("Verify tracker singleton created", null, Tracker.getTracker());
        assertNotEquals("Verify logger singleton created", null, Logger.getLogger());
    }
    @Test
    public void checkMakingStaffAndVehicles (){
        FNCDsim newSim = new FNCDsim();
        OpenShop.openShop();
        //check number of cars should be the set number of cars for each type
        assertEquals("Verify num pickup objects", OpenShop.setCarNum, Vehicle.getVehiclesByType(FNCDsim.inventory(), Enums.VehicleType.Pickup).size());
        assertEquals("Verify num car objects", OpenShop.setCarNum, Vehicle.getVehiclesByType(FNCDsim.inventory(), Enums.VehicleType.Car).size());
        assertEquals("Verify num perfCar objects", OpenShop.setCarNum, Vehicle.getVehiclesByType(FNCDsim.inventory(), Enums.VehicleType.PerfCar).size());
        assertEquals("Verify num Motorcycle objects", OpenShop.setCarNum, Vehicle.getVehiclesByType(FNCDsim.inventory(), Enums.VehicleType.Motorcycle).size());
        assertEquals("Verify num Monster objects", OpenShop.setCarNum, Vehicle.getVehiclesByType(FNCDsim.inventory(), Enums.VehicleType.Monster).size());
        assertEquals("Verify num Sedan objects", OpenShop.setCarNum, Vehicle.getVehiclesByType(FNCDsim.inventory(), Enums.VehicleType.Sedan).size());
        assertEquals("Verify num SUV objects", OpenShop.setCarNum, Vehicle.getVehiclesByType(FNCDsim.inventory(), Enums.VehicleType.SUV).size());
        assertEquals("Verify num Electric objects", OpenShop.setCarNum, Vehicle.getVehiclesByType(FNCDsim.inventory(), Enums.VehicleType.Electric).size());
        //should make setCarNumber x number of enum values for total inventory created
        assertEquals("Verify num starting vehicle objects", OpenShop.setCarNum*Enums.VehicleType.values().length,FNCDsim.inventory().size());
        //check number of employees per type == 3 (setEmployee number)
        assertEquals("Verify num interns", OpenShop.setStaffNum, Employee.getStaffByType(FNCDsim.currentStaff(), Enums.StaffType.Intern).size());
        assertEquals("Verify num sales", OpenShop.setStaffNum, Employee.getStaffByType(FNCDsim.currentStaff(), Enums.StaffType.Salesperson).size());
        assertEquals("Verify num mechanics", OpenShop.setStaffNum, Employee.getStaffByType(FNCDsim.currentStaff(), Enums.StaffType.Mechanic).size());
        assertEquals("Verify num drivers", OpenShop.setStaffNum, Employee.getStaffByType(FNCDsim.currentStaff(), Enums.StaffType.Driver).size());
        //should make setEmployee number x number of staff enum values for current staff
        assertEquals("Verify num starting employee objects", OpenShop.setStaffNum*Enums.StaffType.values().length, FNCDsim.currentStaff().size());
    }
}