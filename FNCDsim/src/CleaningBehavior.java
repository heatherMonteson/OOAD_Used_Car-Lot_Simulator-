package FNCDsim.src;
//STRATEGY PATTERN: interface for the different cleaning behaviors used by the interns implemented by the
//ChemicalClean, Detailed, and ElbowGrease classes
public interface CleaningBehavior {
    public boolean washVehicle(Vehicle car);
    public String getCleaningBehavior();
}