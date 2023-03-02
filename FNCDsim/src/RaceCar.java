package FNCDsim.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public interface RaceCar{
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

        for (Vehicle raceCar: raceCars) {
            if (list.get(counter) <= 3){
                //need to cast the raceCar to be the type we are racing so we can access addRaceWon() etc.
                if(Objects.equals(raceCar.getType(), "MonsterTruck")) {
                    MonsterTruck monsterTruckRacing = (MonsterTruck) raceCar;
                    monsterTruckRacing.addRaceWon();
                    drivers.get(counter).addRaceWon();
                    FNCDsim.broker.out(Enums.EventType.Racing, "WINNER! "+drivers.get(counter).getName() + " driving " + monsterTruckRacing.getName() + " has placed " + list.get(counter) + " in the race. (new sale price $" + monsterTruckRacing.getSalePrice()+ ", driver bonus $"+monsterTruckRacing.getWinBonus() +")" ,monsterTruckRacing.getWinBonus());
                    FNCDsim.broker.out(Enums.EventType.Racing, "That's " + drivers.get(counter).getRaceWon()+ " win(s) for "+ drivers.get(counter).getName() + " and " +monsterTruckRacing.getRacesWon()+ " win(s) for the "+monsterTruckRacing.getName() );
                    drivers.get(counter).payBonus(FNCDsim.getFunds(monsterTruckRacing.getWinBonus()));
                    //etc.
                }
                else if (Objects.equals(raceCar.getType(), "Pickup")){
                    Pickup pickupRacing = (Pickup) raceCar;
                    pickupRacing.addRaceWon();
                    drivers.get(counter).addRaceWon();
                    FNCDsim.broker.out(Enums.EventType.Racing, "WINNER! "+drivers.get(counter).getName() + " driving " + pickupRacing.getName() + " has placed " + list.get(counter) + " in the race. (new sale price $" + pickupRacing.getSalePrice()+ ", driver bonus $"+pickupRacing.getWinBonus() +")" ,pickupRacing.getWinBonus());
                    FNCDsim.broker.out(Enums.EventType.Racing, "That's " + drivers.get(counter).getRaceWon()+ " win(s) for "+ drivers.get(counter).getName() + " and " +pickupRacing.getRacesWon()+ " win(s) for the "+pickupRacing.getName());
                    drivers.get(counter).payBonus(FNCDsim.getFunds(pickupRacing.getWinBonus()));
                }
                else if (Objects.equals(raceCar.getType(), "Performance")){
                    Performance performanceRacing = (Performance) raceCar;
                    performanceRacing.addRaceWon();
                    drivers.get(counter).addRaceWon();
                    FNCDsim.broker.out(Enums.EventType.Racing, "WINNER! "+drivers.get(counter).getName() + " driving " + performanceRacing.getName() + " has placed " + list.get(counter) + " in the race. (new sale price $" + performanceRacing.getSalePrice()+ ", driver bonus $"+performanceRacing.getWinBonus()+")" ,performanceRacing.getWinBonus());
                    FNCDsim.broker.out(Enums.EventType.Racing, "That's " + drivers.get(counter).getRaceWon()+ " win(s) for "+ drivers.get(counter).getName() + " and " +performanceRacing.getRacesWon()+ " win(s) for the "+performanceRacing.getName() );
                    drivers.get(counter).payBonus(FNCDsim.getFunds(performanceRacing.getWinBonus()));

                }
                else if (Objects.equals(raceCar.getType(), "Motorcycle")){
                    Motorcycle motorcycleRacing = (Motorcycle) raceCar;
                    motorcycleRacing.addRaceWon();
                    drivers.get(counter).addRaceWon();
                    FNCDsim.broker.out(Enums.EventType.Racing, "WINNER! "+drivers.get(counter).getName() + " driving " + motorcycleRacing.getName() + " has placed " + list.get(counter) + " in the race. (new sale price $" + motorcycleRacing.getSalePrice()+ ", driver bonus $"+motorcycleRacing.getWinBonus() +")",motorcycleRacing.getWinBonus());
                    FNCDsim.broker.out(Enums.EventType.Racing, "That's " + drivers.get(counter).getRaceWon()+ " win(s) for "+ drivers.get(counter).getName() + " and " +motorcycleRacing.getRacesWon()+ " win(s) for the "+motorcycleRacing.getName() );
                    drivers.get(counter).payBonus(FNCDsim.getFunds(motorcycleRacing.getWinBonus()));

                }
            }
            else if (list.get(counter) >= 15){
                raceCar.changeCarToBroken();
                int rand = Utility.findValue(1,100);
                if (rand <= 30){
                    //racer injured stuff here
                    FNCDsim.departedStaff.add(drivers.get(counter));
                    FNCDsim.currentStaff.remove(drivers.get(counter));
                    FNCDsim.broker.out(Enums.EventType.Racing, drivers.get(counter).getName() + " driving " + raceCar.getName() + " has placed " + list.get(counter) + " in the race. Vehicle is now damaged and driver is injured and will not longer race.");
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
