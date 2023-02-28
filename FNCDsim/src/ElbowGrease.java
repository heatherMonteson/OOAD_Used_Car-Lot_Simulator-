package FNCDsim.src;

public class ElbowGrease implements CleaningBehavior, Utility {
    static void washVehicle(Vehicle car) {
        int randomNum = Utility.findValue(1, 100);
        if (Objects.equals(car.getCleanliness(), "Dirty")) {
            if (randomNum <= 5) {
                car.changeCarToSparkly();
                System.out.println("Intern " + name + " cleaned " + car.getName() + " using elbow grease and made it " + car.getCleanliness() + " (earned a $" + car.getCleaningBonus() + ")");
                payBonus(FNCDsim.getFunds(car.getCleaningBonus()));
            } else if (randomNum <= 70) {
                car.changeCarToClean();
                System.out.println("Intern " + name + " cleaned " + car.getName() + " using elbow grease and made it " + car.getCleanliness());
            }
        }
        else if (Objects.equals(car.getCleanliness(), "Clean")) {
            if (randomNum <= 15){ //how to set probability of two events with equal chances? maybe do one <= 7 and <=15?) {
                car.changeCarToDirty();
                System.out.println("Intern "+ name + " tried to clean " + car.getName() + " using elbow grease but made it " + car.getCleanliness());
            }
            else if (randomNum <= 30) {//clean --> sparkly
                car.changeCarToSparkly();
                System.out.println("Intern "+ name + " cleaned " + car.getName() + " using elbow grease and made it " + car.getCleanliness() +" (earned a $"+ car.getCleaningBonus()+")");
                payBonus(FNCDsim.getFunds(car.getCleaningBonus()));
            }
        }
        if (randNum <= 10){
            car.changeCarToLikeNew();
            System.out.println("Intern "+ name + " cleaned " + car.getName() + " using chemical cleaning and made it " + car.getCondition();
        }
    }
}

