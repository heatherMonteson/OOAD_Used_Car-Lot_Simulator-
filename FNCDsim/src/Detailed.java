package FNCDsim.src;

import java.util.EnumMap;
import java.util.Objects;

public class Detailed implements CleaningBehavior{

    public String getCleaningBehavior() {
        return "Detailing";
    }

    public boolean washVehicle(Vehicle car) {

        boolean changeState = false;

        int randomNum = Utility.findValue(1, 100);
        if (Objects.equals(car.getCleanliness(), "Dirty")) {
            if (randomNum <= 20){
                car.changeCarToSparkly();
                changeState = true;
            }
             else if (randomNum <= 60){
                car.changeCarToClean();
                changeState = true;
            }
        }
        else if (Objects.equals(car.getCleanliness(), "Clean")) {
            if (randomNum <= 5){
                car.changeCarToDirty();
                changeState = true;
            }
            else if (randomNum <= 40) { //clean --> sparkly
                car.changeCarToSparkly();
                changeState = true;
            }
        }
        return changeState;
    }
}
