package FNCDsim.src;

import java.util.Objects;

//for chemical methods of cleaning for interns
public class ChemicalClean implements CleaningBehavior{
    public String getCleaningBehavior() {
        return "Chemical Clean";
    }
    public boolean washVehicle(Vehicle car) {

        boolean changeState = false;

        if (Objects.equals(car.getCleanliness(), "Dirty")) {
            if (Utility.findValue(1, 100)<= 10) { //dirty --> sparkly
                car.changeCarToSparkly();
                changeState =true;
            }
            else if (Utility.findValue(1, 100) <= 80) {//dirty --> clean
                car.changeCarToClean();
                changeState =true;
            }
        }
        else if (Objects.equals(car.getCleanliness(), "Clean")) {
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

