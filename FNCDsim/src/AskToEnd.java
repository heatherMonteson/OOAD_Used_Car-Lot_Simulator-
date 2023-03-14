package FNCDsim.src;

public class AskToEnd implements Command {

    public AskToEnd() {
        
    }

    public void execute() {
        System.out.println("Shutting down...");
    }
}