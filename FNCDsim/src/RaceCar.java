package FNCDsim.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public interface RaceCar{
    //the class should also have a variable to hold the number of races won for setting and returning
    int getRacesWon();
    void addRaceWon();
    double getWinBonus();

    static ArrayList<Enums.VehicleType> getRaceTypes(){
        ArrayList<Enums.VehicleType> types = new ArrayList<>();
        types.add(Enums.VehicleType.PerfCar);
        types.add(Enums.VehicleType.Pickup);
        types.add(Enums.VehicleType.Monster);
        types.add(Enums.VehicleType.Motorcycle);
        return types;
    }

    static ArrayList<Vehicle> getRaceCarsByType(ArrayList<Vehicle> allCars, Enums.VehicleType raceType) {
        //finds race cars based on type and returns max 3 of that type
        //cars cannot be broken
        ArrayList<Vehicle> raceCars = new ArrayList<>();
        for(Vehicle raceCar: allCars){
            if(Objects.equals(raceCar.getType(), raceType) && !Objects.equals(raceCar.getCondition(),  Enums.Condition.Broken))
                raceCars.add(raceCar);
            if(raceCars.size()>3)
                return raceCars;
        }
        return raceCars;
    }


    static void doRace(ArrayList<Driver> drivers, ArrayList<Vehicle> raceCars){
        ArrayList<Integer> list = new ArrayList<Integer>(); //citation at top
        for (int i=1; i<21; i++) list.add(i);
        Collections.shuffle(list);
        int counter = 0;
        int wins=0;
        double bonus =0;

        for (Vehicle raceCar: raceCars) {
            wins =0;
            bonus =0;
            Driver driver = drivers.get(counter);
            if (list.get(counter) <= 3){
                //need to cast the raceCar to be the type we are racing so we can access addRaceWon() etc.
                if(Objects.equals(raceCar.getType(), Enums.VehicleType.Monster)) {
                    MonsterTruck monsterTruckRacing = (MonsterTruck) raceCar;
                    monsterTruckRacing.addRaceWon();
                    wins = monsterTruckRacing.getRacesWon();
                    bonus=monsterTruckRacing.getWinBonus();
                }
                else if (Objects.equals(raceCar.getType(), Enums.VehicleType.Pickup)){
                    Pickup pickupRacing = (Pickup) raceCar;
                    pickupRacing.addRaceWon();
                    wins = pickupRacing.getRacesWon();
                    bonus=pickupRacing.getWinBonus();
                }
                else if (Objects.equals(raceCar.getType(), Enums.VehicleType.PerfCar)){
                    Performance performanceRacing = (Performance) raceCar;
                    performanceRacing.addRaceWon();
                    wins = performanceRacing.getRacesWon();
                    bonus=performanceRacing.getWinBonus();

                }
                else if (Objects.equals(raceCar.getType(), Enums.VehicleType.Motorcycle)){
                    Motorcycle motorcycleRacing = (Motorcycle) raceCar;
                    motorcycleRacing.addRaceWon();
                    wins = motorcycleRacing.getRacesWon();
                    bonus=motorcycleRacing.getWinBonus();
                }
                driver.addRaceWon();
                FNCDsim.broker.out(Enums.EventType.Racing, "WINNER! "+drivers.get(counter).getName() + " driving " + raceCar.getName() + " has placed " + list.get(counter) + " in the race. (new sale price $" + raceCar.getSalePrice()+ ", driver bonus $"+bonus +")",bonus);
                FNCDsim.broker.out(Enums.EventType.Racing, "That's " + drivers.get(counter).getRaceWon()+ " win(s) for "+ drivers.get(counter).getName() + " and " +wins+ " win(s) for the "+raceCar.getName() );
                driver.payBonus(FNCDsim.getFunds(bonus));
            }
            else if (list.get(counter) >= 15){
                raceCar.changeCarToBroken();
                int rand = Utility.findValue(1,100);
                if (rand <= 30){
                    //racer injured stuff here
                    FNCDsim.departedStaff.add(driver);
                    FNCDsim.currentStaff.remove(driver);
                    FNCDsim.broker.out(Enums.EventType.Racing, driver.getName() + " driving " + raceCar.getName() + " has placed " + list.get(counter) + " in the race. Vehicle is now damaged and driver is injured and will not longer race.");
                }
                else
                    FNCDsim.broker.out(Enums.EventType.Racing, drivers.get(counter).getName() + " driving " + raceCar.getName() + " has placed " + list.get(counter) + " in the race. Vehicle is now damaged.");
            }
            else{
                FNCDsim.broker.out(Enums.EventType.Racing, drivers.get(counter).getName() + " driving " + raceCar.getName() + " has placed " + list.get(counter) + " in the race.");
            }
            counter++;
        }
    }
}
