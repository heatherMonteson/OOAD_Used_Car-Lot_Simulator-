package FNCDsim.src;

import java.util.Objects;

//STRATEGY: methods for the chemical cleaning behavior used by interns
public class ChemicalClean implements CleaningBehavior{
    public String getCleaningBehavior() {
        return "Chemical Clean";
    }
    public boolean washVehicle(Vehicle car) {

        boolean changeState = false;

        if (Objects.equals(car.getCleanliness(), Enums.Cleanliness.Dirty)) {
            if (Utility.findValue(1, 100)<= 10) { //dirty --> sparkly
                car.changeCarToSparkly();
                changeState =true;
            }
            else if (Utility.findValue(1, 100) <= 80) {//dirty --> clean
                car.changeCarToClean();
                changeState =true;
            }
        }
        else if (Objects.equals(car.getCleanliness(), Enums.Cleanliness.Clean)) {
            if (Utility.findValue(1, 100)<= 10){//clean-->dirty
                car.changeCarToDirty();
                changeState =true;
            }
            else if (Utility.findValue(1, 100) <= 20) {//clean to sparkly
                car.changeCarToSparkly();
                changeState =true;
            }
        }
        //potentially change to broken
        if (Utility.findValue(1, 100) <= 10){
            car.changeCarToBroken();
        }
        return changeState;
    }
}

