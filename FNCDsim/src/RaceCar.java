package FNCDsim.src;

import java.util.ArrayList;
import java.util.Objects;

public interface RaceCar implements Utility{
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

    static void doRace(ArrayList<Vehicle> raceCars){
        int ranNum1 = Utility.findValue(1, 20);
        int ranNum2 = Utility.findValue(1, 20);
        int ranNum3 = Utility.findValue(1, 20);
        boolean flag1 = false;
        boolean flag2 = true;
        boolean flag3 = true;
        for (Vehicle raceCar: raceCars){
            if (flag1 = false){
                if (ranNum1 = 1 || ranNum1 = 2 || ranNum1 = 3 ){
                    Objects.addRaceWon();
                    salePrice = Utility.format(salePrice*1.10);
                }
                else if (ranNum1 >= 15){
                    Objects.changeCarToDamaged();
                }
                flag1 = true;
                flag2 = false;
            }
            else if (flag2 = false){
                if (ranNum2 = 1 || ranNum2 = 2 || ranNum2 = 3 ){
                    Objects.addRaceWon();
                    salePrice = Utility.format(salePrice*1.10);
                }
                else if (ranNum2 >= 15){
                    Objects.changeCarToDamaged();
                }
                flag2 = true;
                flag3 = false;
            }
            else if (flag3 = false){
                if (ranNum3 = 1 || ranNum3 = 2 || ranNum3 = 3 ){
                    Objects.addRaceWon();
                    salePrice = Utility.format(salePrice*1.10);
                }
                else if (ranNum3 >= 15){
                    Objects.changeCarToDamaged();
                }
                flag3 = true;
            }
        }
    }
}
