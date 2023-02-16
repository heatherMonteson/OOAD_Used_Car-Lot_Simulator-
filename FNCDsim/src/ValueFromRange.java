import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

//Citation:
//Generating a value from a range https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
//Truncating a double: https://stackoverflow.com/questions/7747469/how-can-i-truncate-a-double-to-only-two-decimal-places-in-java#:~:text=will%20output%203.54-,DecimalFormat(%22%23.,for%20both%20Positive%20%26%20Negative%20values.
public interface ValueFromRange {

    //from a lower and upper bound generate a random number in that range
    // returns a double truncated to 2 decimal places
    default double findValue(double lower,double upper){

        Random r = new Random();
        double value = lower + (upper - lower) * r.nextDouble();

        DecimalFormat formatting = new DecimalFormat("#.##");
        formatting.setRoundingMode(RoundingMode.DOWN);
        value = Double.parseDouble(formatting.format(value));

        return value;
    }

    //from a lower and upper bound generate a random number in that range
    // returns an int
   default int findValue(int lower, int upper){

        Random random=new Random();
        int value= random.nextInt(lower, upper+1);

        return value;
    }
}
