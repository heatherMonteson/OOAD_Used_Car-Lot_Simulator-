package FNCDsim.src;

public class AsktoEnd implements Command {

    public AsktoEnd() {
        
    }

    public void execute() {
        System.out.println("Shutting down...");
    }
}