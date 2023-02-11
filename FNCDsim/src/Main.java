import java.util.ArrayList;
/*
OOAD Principal Abstraction:
In the main method we want to run the FNCDsimulation so all we need to know is the number of days to simulate
to pass over to the FNCDsimulation object. We do not need to know any other information about how the simulation runs, what
methods are used, what other objects are created etc. Just knowing the number of days and the method that runs the simpulation
is enough to see n days at the FNCD.
 */



public class Main {
    public static void main(String[] args) {
        FNCDsimulation newSimulation = new FNCDsimulation();
        newSimulation.run(30);
    }
}