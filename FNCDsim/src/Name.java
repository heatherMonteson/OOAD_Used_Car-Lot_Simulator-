package FNCDsim.src;

import java.util.ArrayList;
import java.util.Random;

//Randomly generate an index to return a name from an array list of names
public interface Name {

    default String generateName(ArrayList<String> names) {
        try {
            String name;
            Random generator = new Random();
            int i = generator.nextInt(names.size());
            return names.get(i);
        } catch (Exception e) {
            System.out.println("Error with name list returning null");
        }
        return null;
    }
}