import javax.swing.*;

public class ClientTest {
	public static void main(String[] args) {
		//127.0.0.1 is localhost
		Client client = new Client("127.0.0.1");; 
		client.startRunning();
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}