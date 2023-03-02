package FNCDsim.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public interface RaceCar{
    //the class should also have a variable to hold the number of races won for setting and returning
    int getRacesWon();
    void addRaceWon();
    double getWinBonus();

    static void raceType(ArrayList<Driver> drivers, ArrayList<Vehicle> vehicles) {
        //define in race car types
    }

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

    static void doRace(ArrayList<Driver> drivers, ArrayList<Vehicle> raceCars, String type){

        //if list sizes are not equal algorithm will break
        if((raceCars.size()==0 || drivers.size()==0) || raceCars.size()!=drivers.size()){
            FNCDsim.broker.errorOut("Error with race car and drivers lists");
            return;
        }
        //create 20 placements to select from in race
        ArrayList<Integer> list = new ArrayList<Integer>(); //citation at top
        for (int i=1; i<21; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        int counter = 0;
        int winsHad;
        double winBonus;
        int position;

        FNCDsim.broker.out("Race outcomes: ");
        //determine outcomes
        for(Vehicle raceCar: raceCars){
            winsHad=0;
            winBonus=0;
            position=list.get(counter);
            Driver driver = drivers.get(counter);

            //casting to specific type to add races or damage cars based on standing
            if(Objects.equals(raceCar.getType(), "Pickup")){
                Pickup pickup= (Pickup) raceCar;
                if(position>=25)//last 5 position, break car
                    pickup.changeCarToBroken();
                else if (position<=3)//top 3 position add race
                    pickup.addRaceWon();
                winsHad=pickup.getRacesWon();
                winBonus=pickup.getWinBonus();
            }
            else if(Objects.equals(raceCar.getType(), "Performance")){
                Performance performance= (Performance) raceCar;
                if(position>=25)
                    performance.changeCarToBroken();
                else if (position<=3)
                    performance.addRaceWon();
                winsHad=performance.getRacesWon();
                winBonus=performance.getWinBonus();
            }
            else if(Objects.equals(raceCar.getType(), "Motorcycle")){
                Motorcycle motorcycle= (Motorcycle) raceCar;
                if(position>=25)
                    motorcycle.changeCarToBroken();
                else if (position<=3)
                    motorcycle.addRaceWon();
                winsHad=motorcycle.getRacesWon();
                winBonus=motorcycle.getWinBonus();
            }
            else if(Objects.equals(raceCar.getType(), "MonsterTruck")){
                MonsterTruck monsterTruck= (MonsterTruck) raceCar;
                if(position>=25)
                    monsterTruck.changeCarToBroken();
                else if (position<=3)
                    monsterTruck.addRaceWon();
                winsHad=monsterTruck.getRacesWon();
                winBonus=monsterTruck.getWinBonus();
            }

            //output standings
            if(position>=15){//looser
                if (Utility.findValue(1,100)<= 30){//racer is injured, remove from staff
                    FNCDsim.departedStaff.add(driver);
                    FNCDsim.currentStaff.remove(driver);
                    FNCDsim.broker.out(Enums.EventType.Racing, " Position: "+ position + " Driver: " +driver.getName() +", Races Won: "+driver.getRaceWon() +", New Condition: Injured, Race Car: " +raceCar.getType() +", "+ raceCar.getName() + ", Races Won: "+winsHad+", New Condition: Damaged");
                }
                else {//driver not injured
                    FNCDsim.broker.out(Enums.EventType.Racing, " Position: "+ position + " Driver: " +driver.getName() +", Races Won: "+driver.getRaceWon() +", Race Car: " +raceCar.getType() +", "+ raceCar.getName() + ", Races Won: "+winsHad+", New Condition: Damaged");
                }
            }
            else if(position<=3){//winner
                driver.addRaceWon();
                driver.payBonus(FNCDsim.getFunds(winBonus));
                FNCDsim.broker.out(Enums.EventType.Racing, "WINNER! Position: "+ position + " Driver: " +driver.getName() +" Bonus: $" +winBonus +", Races Won: "+driver.getRaceWon() +", Race Car: " +raceCar.getType() +", "+ raceCar.getName() + ", Races Won: "+winsHad, winBonus);
            }
            else{//no significant placement
                FNCDsim.broker.out(Enums.EventType.Racing, "Position: "+ position + " Driver: " +driver.getName() +", Races Won: "+driver.getRaceWon() +", Race Car: " +raceCar.getType() +", "+ raceCar.getName() + ", Races Won: "+winsHad);
            }
            counter++;
        }

    }
}
