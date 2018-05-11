public class MainThread {
    
	public static void main(String[] args) {
      
        Thread t1 = new Thread(new Tuna("one"));
        Thread t2 = new Thread(new Tuna("two"));
        Thread t3 = new Thread(new Tuna("three"));
      
        t1.start();
        t2.start();
        t3.start();
    }
}