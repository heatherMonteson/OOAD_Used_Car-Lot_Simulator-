package FNCDsim.src;

/*
OOAD Principal Abstraction:
In the main method we want to run the FNCDsimulation as such, all we need to know is the number of days to simulate
to pass over to the FNCDsimulation object. We do not need to know any other information about how the simulation runs, what
methods are used, what other objects are created etc. Just knowing the number of days and the method that runs the simulation
is enough to see said number of days at the FNCD.
 */

public class Main {
    public static void main(String[] args) {
        FNCDsim newSimulation = new FNCDsim();
        newSimulation.run(30);
    }
}