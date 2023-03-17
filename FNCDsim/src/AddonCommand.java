package FNCDsim.src;

import java.util.ArrayList;

public interface AddonCommand extends Command {
    
    void execute(String message, ArrayList<Enums.AddOns> addOnList);

}
