package FNCDsim.src;

import java.util.ArrayList;
import java.util.Objects;

public interface RaceCar {
    //the class should also have a variable to hold the number of races won for setting and returning
    int getRacesWon();
    void addRaceWon();
    double getWinBonus();

    static ArrayList<Vehicle> getRaceCarsByType(ArrayList<Vehicle> allCars, String raceType) {
        //finds race cars based on type and returns max 3 of that type
        //cars cannot be broken
        ArrayList<Vehicle> raceCars = new ArrayList<>();
        for(Vehicle raceCar: allCars){
            if(Objects.equals(raceCar.getType(), raceType) && !Objects.equals(raceCar.getCondition(), "Broken"))
                raceCars.add(raceCar);
            if(raceCars.size()>3)
                return raceCars;
        }
        return raceCars;
    }

    static String[] getRaceTypes (){
        return new String[]{"Pickup","Performance", "Motorcycle", "MonsterTruck" };
    }
}
