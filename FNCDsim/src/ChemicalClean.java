package FNCDsim.src;
//for chemical methods of cleaning for interns
public class ChemicalClean implements CleaningBehavior{
    static void washVehicle(Vehicle car) {
        int randomNum = Utility.findValue(1, 100);
        if (Objects.equals(car.getCleanliness(), "Dirty")) {
            if (randomNum <= 10) {
                car.changeCarToSparkly();
                System.out.println("Intern " + name + " cleaned " + car.getName() + " using chemical cleaning and made it " + car.getCleanliness() + " (earned a $" + car.getCleaningBonus() + ")");
                payBonus(FNCDsim.getFunds(car.getCleaningBonus()));
            } else if (randomNum <= 80) {
                car.changeCarToClean();
                System.out.println("Intern " + name + " cleaned " + car.getName() + " using chemical cleaning and made it " + car.getCleanliness());
            }
        }
        else if (Objects.equals(car.getCleanliness(), "Clean")) {
            if (randomNum <= 10){
                car.changeCarToDirty();
                System.out.println("Intern "+ name + " tried to clean " + car.getName() + " using chemical cleaning but made it " + car.getCleanliness());
            }
            else if (randomNum <= 20) {
                car.changeCarToSparkly();
                System.out.println("Intern "+ name + " cleaned " + car.getName() + " using chemical cleaning and made it " + car.getCleanliness() +" (earned a $"+ car.getCleaningBonus()+")");
                payBonus(FNCDsim.getFunds(car.getCleaningBonus()));
            }
        }
        if (randNum <= 10){
            car.changeCarToBroken();
            System.out.println("Intern "+ name + " cleaned " + car.getName() + " using chemical cleaning and made it " + car.getCondition();
        }
    }
}

