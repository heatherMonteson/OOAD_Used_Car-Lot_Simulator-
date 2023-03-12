package FNCDsim.src;

import java.util.Objects;

//OO pattern: Vehicle factory and MakeCars used to implement the factory pattern to make new instances of vehicles
public abstract class VehicleFactory {
    public Vehicle buyCars(Enums.VehicleType type){
        return makeCars(type);
    }
    protected abstract Vehicle makeCars(Enums.VehicleType type);
}

class MakeCars extends VehicleFactory {

    protected Vehicle makeCars(Enums.VehicleType type){
        Vehicle vehicle =null;
        if(Objects.equals(type, Enums.VehicleType.Car))
            vehicle=new Car();
        else if(Objects.equals(type, Enums.VehicleType.PerfCar))
            vehicle = new Performance();
        else if(Objects.equals(type, Enums.VehicleType.Pickup))
            vehicle=new Pickup();
        else if(Objects.equals(type, Enums.VehicleType.Motorcycle))
            vehicle=new Motorcycle();
        else if(Objects.equals(type, Enums.VehicleType.Electric))
            vehicle=new Electric();
        else if(Objects.equals(type, Enums.VehicleType.Monster))
            vehicle=new MonsterTruck();
        return vehicle;
    }
}
