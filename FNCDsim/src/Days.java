package FNCDsim.src;

//Class just keeps track of days 1->7 (monday==1->sunday==7), increment days when newDay is called
public class Days {

    private int today=0;
    private int numberDays=0;

    Days(){
    }
    public void newDay(){
        numberDays++;
        if(today==7)
            today=1;
        else
            today=today+1;
    }
    public int getToday(){
        return today;
    }

    public int getNumDays() {
        return numberDays;
    }
}
