package FNCDsim.src;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SalespersonTest {
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionNullCustomer()
    {
        //test case where a null customer is passed to sell cars
        //expecting to catch an exception
        Salesperson salesperson = new Salesperson();
        ArrayList<Vehicle> cars = new ArrayList<>();
        cars.add(new Pickup());
        salesperson.sellCar(null,cars);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionNullCars()
    {
        //test where null list is sent to a sales person to sell cars
        //expecting to catch an exception
        Salesperson salesperson = new Salesperson();
        salesperson.sellCar(new Customer(), null);
    }

}