import java.util.*;

//Runnable interface contains run() method
public class Tuna implements Runnable{
    String name;
    int time;
    Random r = new Random();
    
    public Tuna(String x){
        name = x;
        time = r.nextInt(999); //between 0-1 second
    }
    
    //this runs when you start thread
    public void run(){
        try{
            System.out.printf("%s is sleeping for %d\n", name, time);
            Thread.sleep(time); //how long do u want to sleep for?
            System.out.printf("%s is done\n", name);
        }catch(Exception e){}
    }
}