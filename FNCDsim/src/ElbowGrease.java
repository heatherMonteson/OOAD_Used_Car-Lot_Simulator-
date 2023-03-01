package FNCDsim.src;

import java.util.Objects;
//STRATEGY: methods for the elbow crease cleaning behavior used by interns
public class ElbowGrease implements CleaningBehavior {

    public String getCleaningBehavior() {
        return "Elbow Grease";
    }

    public boolean washVehicle(Vehicle car) {

        boolean changeState = false;

        if (Objects.equals(car.getCleanliness(), "Dirty")) {

            if (Utility.findValue(1, 100) <= 5) {  //Dirty --> sparkly
                car.changeCarToSparkly();
                changeState = true;
            } else if (Utility.findValue(1, 100) <= 70) {//Dirty--> clean
                car.changeCarToClean();
                changeState = true;
            }
        }
        else if (Objects.equals(car.getCleanliness(), "Clean")) {

            if (Utility.findValue(1, 100) <= 15) { //clean-->dirty
                car.changeCarToDirty();
                changeState = true;
            } else if (Utility.findValue(1, 100) <= 30) { //clean --> sparkly
                car.changeCarToSparkly();
                changeState = true;
            }
        }
        if (Utility.findValue(1, 100) <= 10) //there is 10% change that the car is fixed
            car.changeCarToLikeNew();

        return changeState;
    }
}

