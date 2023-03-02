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

    static void doRace(ArrayList<Driver> drivers, ArrayList<Vehicle> raceCars){
        ArrayList<Integer> list = new ArrayList<Integer>(); //citation at top
        for (int i=1; i<21; i++) list.add(i);
        Collections.shuffle(list);
        int counter = 0;
        Driver driver = (Driver) drivers.get(counter);
        for (Vehicle raceCar: raceCars) {
            if (list.get(counter) <= 3){
                //need to cast the raceCar to be the type we are racing so we can access addRaceWon() etc.
                if(raceCar.getType()=="MonsterTruck") {
                    MonsterTruck monsterTruckRacing = (MonsterTruck) raceCar;
                    monsterTruckRacing.addRaceWon();
                    driver.get(counter).addRaceWon();
                    FNCDsim.broker.out(Enums.EventType.Racing, driver.get(counter).getName() + " driving " + monsterTruckRacing.getName() + " has placed " + counter + " in the race and its sale price is now " + monsterTruckRacing.getSalePrice());
                    driver.get(counter).payBonus(FNCDsim.getFunds(monsterTruckRacing.getWinBonus()));
                    FNCDsim.broker.out(Enums.EventType.Racing, driver.get(counter).getName() + " has been paid " + monsterTruckRacing.getWinBonus());
                    //etc.
                }
                else if (raceCar.getType()=="Pickup"){
                    Pickup pickupRacing = (Pickup) raceCar;
                    pickupRacing.addRaceWon();
                    driver.get(counter).addRaceWon();
                    FNCDsim.broker.out(Enums.EventType.Racing, driver.get(counter).getName() + " driving " + pickupRacing.getName() + " has placed " + counter + " in the race and its sale price is now " + pickupRacing.getSalePrice());
                    driver.get(counter).payBonus(FNCDsim.getFunds(pickupRacing.getWinBonus()));
                    FNCDsim.broker.out(Enums.EventType.Racing, driver.get(counter).getName() + " has been paid " + pickupRacing.getWinBonus());
                }
                else if (raceCar.getType()=="Performance"){
                    Performance performanceRacing = (Performance) raceCar;
                    performanceRacing.addRaceWon();
                    driver.get(counter).addRaceWon();
                    FNCDsim.broker.out(Enums.EventType.Racing, driver.get(counter).getName() + " driving " + performanceRacing.getName() + " has placed " + counter + " in the race and its sale price is now " + performanceRacing.getSalePrice());
                    driver.get(counter).payBonus(FNCDsim.getFunds(performanceRacing.getWinBonus()));
                    FNCDsim.broker.out(Enums.EventType.Racing, driver.get(counter).getName() + " has been paid " + performanceRacing.getWinBonus());
                }
                else if (raceCar.getType()=="Motorcycle"){
                    Motorcycle motorcycleRacing = (Motorcycle) raceCar;
                    motorcycleRacing.addRaceWon();
                    driver.get(counter).addRaceWon();
                    FNCDsim.broker.out(Enums.EventType.Racing, driver.get(counter).getName() + " driving " + motorcycleRacing.getName() + " has placed " + counter + " in the race and its sale price is now " + motorcycleRacing.getSalePrice());
                    driver.get(counter).payBonus(FNCDsim.getFunds(motorcycleRacing.getWinBonus()));
                    FNCDsim.broker.out(Enums.EventType.Racing, driver.get(counter).getName() + " has been paid " + motorcycleRacing.getWinBonus());
                }
            }
            else if (list.get(counter) >= 15){
                raceCar.changeCarToBroken();
                int rand = Utility.findValue(1,100);
                if (rand <= 30){
                    //racer injured stuff here
                    FNCDsim.departedStaff.add(staffByType.get(driver.get(counter)));
                    FNCD.currentStaff.remove(driver.get(counter));
                }
            }
            else{
                FNCDsim.brokerout(Enums.EventType.Racing, driver.get(counter).getName() + " driving " + raceCar.getName() + " has placed " + counter + " in the race.");
            }
            counter++;
            driver = (Driver) drivers.get(counter);
        }
    }
}
