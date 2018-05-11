import javax.swing.*;

public class ServerTest {
	public static void main(String[] args) {
		Server server = new Server();
		server.startRunning();
		server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}