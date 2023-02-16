package FNCDsim.src;

import java.util.concurrent.ThreadLocalRandom;

public class Mechanic extends Employee{
    private int chanceFix = 80;

    Mechanic() {
        super("Mechanic", 175.0,280.0);
    }

    public boolean fixCar( Vehicle car){

        int randomNum = ThreadLocalRandom.current().nextInt(1, 101);

        if(randomNum<=80){
            if(car.getCondition()=="Broken"){
                car.changeCarToUsed();
                return true;
            }
            if(car.getCondition()=="Used"){
                car.changeCarToLikeNew();
                return true;
            }
        }
        return false;
    }
}


