public class Days {

    private int today=0;

    Days(){
    }
    public void newDay(){
        if(today==7)
            today=1;
        else
            today=today+1;
    }
    public int getToday(){
        return today;
    }

}
