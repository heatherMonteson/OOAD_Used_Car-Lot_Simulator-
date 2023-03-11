package FNCDsim.src;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
//OO patterns:

//SINGLETON with lazy instantiation

//OBSERVER
//Takes in all the events and just converts them to a single string to write out to a file
//Tracking information about: Selling, Racing, Emergency (funds), Washing, and Fixing **using enums as flags
public class Logger implements Observer {

    private String event;
    InformationBroker broker;
    private boolean writeAvailable;
    private String fileName;
    private volatile static Logger uniqueLogger;

    private Logger(){
        this.broker=FNCDsim.broker;
        this.broker.registerObserver(this);
        writeAvailable=false;//set false until a file connection is set up
    }

    public static Logger getLogger(){
        if(uniqueLogger==null){ //using lazy instantiation with double check locking
            synchronized (Logger.class){
                if(uniqueLogger==null){
                    uniqueLogger=new Logger();
                }
            }
        }
        return uniqueLogger;
    }

    /////////////////////////////////////////////////////////////////////////////////////
    ///All update methods:pull out expected event types and event to add to the file ////
    /////////////////////////////////////////////////////////////////////////////////////
    public void update(Enums.EventType eventType, int dayNumber){//updating the current day variable
        if(Enums.EventType.NewDay==eventType){
            try{
                fileName= "Logger-"+dayNumber + ".txt";
                createFile(fileName);
                writeAvailable=true;
            }catch (Exception e){
                System.out.println("error creating file for logger");
                writeAvailable=false;
            }
            this.event = "Day number :"+ dayNumber;
            if(writeAvailable)
                write();
        }
    }
    public void update(Enums.EventType eventType, String event){
        if(Enums.EventType.Selling==eventType||Enums.EventType.Emergency==eventType||Enums.EventType.Racing==eventType|| Enums.EventType.Washing==eventType||Enums.EventType.Fixing==eventType ){
            this.event=eventType + ": " + event;
            if(writeAvailable)
                write();
        }
    }
    public void update(Enums.EventType eventType, String event, double bonusOrEmergency){
        if(Enums.EventType.Selling==eventType||Enums.EventType.Emergency==eventType||Enums.EventType.Racing==eventType|| Enums.EventType.Washing==eventType||Enums.EventType.Fixing==eventType ){
            this.event=eventType + ": " + event;
            if(writeAvailable)
                write();
        }
    }
    public void update(Enums.EventType eventType, String event, double bonus, double saleAmount){
        if(Enums.EventType.Selling==eventType||Enums.EventType.Emergency==eventType||Enums.EventType.Racing==eventType|| Enums.EventType.Washing==eventType||Enums.EventType.Fixing==eventType ){
            this.event=eventType + ": " + event;
            if(writeAvailable)
                write();
        }
    }
    //Salary pay added to total staff pay
//    public void update(Enums.EventType eventType, double  pay){
//        if(eventType==Enums.EventType.PaySalary) {
//            this.event=eventType + ": $"+ pay + " removed from FNCD budget" ;
//            if(writeAvailable)
//                write();
//        }
//    }

    ////////////////////////////////////////////////////////////////////////
    //////   logger file handling    ///////////////////////////////////////
    //Creating a file: https://www.w3schools.com/java/java_files_create.asp
    private void createFile(String fileName){
        try {
            File myObj = new File(fileName);
            if(myObj.isFile()) //if file already exists don't want to append, remove and recreate
                try{
                    myObj.delete();
                }catch (Exception e){
                    System.out.println("error deleting" + fileName + " file.");
                }

            if (myObj.createNewFile()) {
                System.out.println("Logger file created: " + myObj.getName());
                writeAvailable=true;
            }
        } catch (IOException e) {
            System.out.println("An error occurred creating the logger file.");
            writeAvailable=false;
            e.printStackTrace();
        }
    }

    //Writing to a file: https://www.w3schools.com/java/java_files_create.asp
    //and https://www.baeldung.com/java-append-to-file
    private void write(){
        try {
            FileWriter writer = new FileWriter(fileName, true); //true will append to the file
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(event);
            bufferWriter.newLine();
            bufferWriter.close();
        } catch (IOException e) {
            writeAvailable=false;
            System.out.println("An error occurred writing to the logger file.");
            e.printStackTrace();
        }

    }
}
