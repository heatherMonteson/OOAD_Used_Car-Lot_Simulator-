package FNCDsim.src;

public class Detailed implements CleaningBehavior{
    static void washVehicle(Vehicle car) {
        int randomNum = Utility.findValue(1, 100);
        if (Objects.equals(car.getCleanliness(), "Dirty")) {
            if (randomNum <= 20) {
                car.changeCarToSparkly();
                System.out.println("Intern " + name + " cleaned " + car.getName() + " using detailing and made it " + car.getCleanliness() + " (earned a $" + car.getCleaningBonus() + ")");
                payBonus(FNCDsim.getFunds(car.getCleaningBonus()));
            } else if (randomNum <= 60) {
                car.changeCarToClean();
                System.out.println("Intern " + name + " cleaned " + car.getName() + " using detailing and made it " + car.getCleanliness());
            }
        }
        else if (Objects.equals(car.getCleanliness(), "Clean")) {
            if (randomNum <= 5){ //how to set probability of two events with equal chances? maybe do one <= 7 and <=15?) {
                car.changeCarToDirty();
                System.out.println("Intern "+ name + " tried to clean " + car.getName() + " using detailing but made it " + car.getCleanliness());
            }
            else if (randomNum <= 40) {//clean --> sparkly
                car.changeCarToSparkly();
                System.out.println("Intern "+ name + " cleaned " + car.getName() + " using detailing and made it " + car.getCleanliness() +" (earned a $"+ car.getCleaningBonus()+")");
                payBonus(FNCDsim.getFunds(car.getCleaningBonus()));
            }
        }
    }
}
