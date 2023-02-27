package FNCDsim.src;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

//Takes in all the events and just converts them to a single string to write out to a file
//Tracking information about: Selling, Racing, Emergency (funds), Washing, and Fixing **using enums as flags
public class Logger implements Observer {

    private String event;
    InformationBroker broker;

    Logger(InformationBroker broker){
        try{
            createFile();
        }catch (Exception e){
            System.out.println("error creating file for tracker");
        }
        this.broker=broker;
        this.broker.registerObserver(this);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    ///All update methods:pull out expected event types and event to add to the file ////
    /////////////////////////////////////////////////////////////////////////////////////
    public void update(Enums.EventType eventType, int dayNumber){//updating the current day variable
        if(Enums.EventType.NewDay==eventType){
            this.event = "Day number :"+ dayNumber;
            write();
        }
    }
    public void update(Enums.EventType eventType, String event){
        if(Enums.EventType.Selling==eventType||Enums.EventType.Emergency==eventType||Enums.EventType.Racing==eventType|| Enums.EventType.Washing==eventType||Enums.EventType.Fixing==eventType ){
            this.event=eventType + ": " + event;
            write();
        }
    }
    public void update(Enums.EventType eventType, String event, double bonusOrEmergency){
        if(Enums.EventType.Selling==eventType||Enums.EventType.Emergency==eventType||Enums.EventType.Racing==eventType|| Enums.EventType.Washing==eventType||Enums.EventType.Fixing==eventType ){
            this.event=eventType + ": " + event;
            write();
        }
    }
    public void update(Enums.EventType eventType, String event, double bonus, double saleAmount){
        if(Enums.EventType.Selling==eventType||Enums.EventType.Emergency==eventType||Enums.EventType.Racing==eventType|| Enums.EventType.Washing==eventType||Enums.EventType.Fixing==eventType ){
            this.event=eventType + ": " + event;
            write();
        }
    }

    ////////////////////////////////////////////////////////////////////////
    //////   logger file handling    ///////////////////////////////////////
    //Creating a file: https://www.w3schools.com/java/java_files_create.asp
    private void createFile(){
        try {
            File myObj = new File("Logger-n.txt");
            if(myObj.isFile()) //if file already exists don't want to append, remove and recreate
                try{
                    myObj.delete();
                }catch (Exception e){
                    System.out.println("error deleting Logger-n.txt file");
                }

            if (myObj.createNewFile()) {
                System.out.println("Logger file created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred creating the logger file.");
            e.printStackTrace();
        }
    }

    //Writing to a file: https://www.w3schools.com/java/java_files_create.asp
    //and https://www.baeldung.com/java-append-to-file
    private void write(){
        try {
            FileWriter writer = new FileWriter("Logger-n.txt", true); //true will append to the file
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(event);
            bufferWriter.newLine();
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred writing to the logger file.");
            e.printStackTrace();
        }

    }
}
