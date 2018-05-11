import javax.swing.JFrame;

public class DXBall {
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	public static void main(String[] args) {
		
	
		JFrame gameFrame = new JFrame("DX Ball");
		
		GamePanel gamePanel = new GamePanel();
		
		gameFrame.setLocation(500,200);
		gameFrame.setResizable(false);
		gameFrame.setSize(WIDTH,HEIGHT);
		gameFrame.add(gamePanel);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		
		gamePanel.playGame();
			
	}
	
	

}
