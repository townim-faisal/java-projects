import java.util.Date;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

    public MyTimerTask() {
    }


    
        public void run() {
        System.out.println("Timer task started at:"+new Date()); 
        
        completeTask();
        
        System.out.println("Timer task finished at:"+new Date());
        
                            }
        private void completeTask() {
        	
        try {
            //assuming it takes 20 seconds to complete the task
        	
            Thread.sleep(20000);
            
        } catch (InterruptedException e) {
        	
        	
        	System.out.println(e);
        }
    }

    
}
