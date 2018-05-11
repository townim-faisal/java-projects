import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class GamePanel extends JPanel {

	boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private PlayerMouseMotionListener gameMouseListener;
	private int mouseX;
	Ball gameBall;
	Paddle gamePaddle;
	Map gameMap;
	HUD gameHud;
	
	
	public GamePanel() {
		initialize();
	}
	
	public void initialize() {
		
		gameBall =  new Ball();
		gamePaddle = new Paddle();
		gameMap = new Map(5,10);
		gameHud = new HUD();
		mouseX = 0;
		
		gameMouseListener = new PlayerMouseMotionListener();
		addMouseMotionListener(gameMouseListener);
		
		this.running = true;
		this.image = new BufferedImage(DXBall.WIDTH,DXBall.HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		this.g = (Graphics2D) image.getGraphics();

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
	}
	
	public void playGame() {
		
		while (running) {
	
			update();		
			
			draw();
		
			repaint(); 
			
			try {
				Thread.sleep(10);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	public void checkCollision() {
		Rectangle ballRect = gameBall.getRect();
		Rectangle paddleRect = gamePaddle.getRect();
		
		if(ballRect.intersects(paddleRect)) {
			gameBall.setDY(-gameBall.getDY());			
		}
		
		
		for(int row = 0; row<gameMap.getMapArr().length; row++) {
			for(int col = 0; col<gameMap.getMapArr()[0].length; col++) {
				
				
				if(gameMap.getMapArr()[row][col] > 0) {
					int brickX = col * gameMap.getBrickWidth() + gameMap.HOR_PAD;
					int brickY = row * gameMap.getBrickHeight() + gameMap.VERT_PAD;
					int brickWidth = gameMap.getBrickWidth();
					int brickHeight = gameMap.getBrickHeight();
					
					Rectangle brickRect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
					
					if(ballRect.intersects(brickRect)) {
						gameMap.brickHit(row, col);
						gameBall.setDY(-gameBall.getDY());
						
						
						gameHud.addScore(50);
						
						break;
				}
				
				}
				
				
			}
		}
		
		
	}
	
	
	public void update() {
		checkCollision();
		gameBall.update();
	}
	

	public void draw() {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, DXBall.WIDTH, DXBall.HEIGHT);
		
		gameBall.draw(g);
		gamePaddle.draw(g);
		gameMap.draw(g);
		gameHud.draw(g);
		
		if(gameMap.winORlose()) {
			this.win();
			running = false;
		}
		
		if (gameBall.lose()) {
			this.lose();
			running = false;
		}
	}
	
	public void win() {
		g.setColor(Color.RED);
		g.setFont(new Font("Courier New", Font.BOLD,50));
		g.drawString("You Have Won!", 130, 200);
	}
	
	public void lose() {
		g.setColor(Color.RED);
		g.setFont(new Font("Courier New", Font.BOLD,50));
		g.drawString("You Have LOST!", 130, 200);
	}
	

	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(this.image, 0, 0, DXBall.WIDTH, DXBall.HEIGHT, null);
	
		g2.dispose();
	
	
	
	}
	
	
	
	private class PlayerMouseMotionListener implements MouseMotionListener {

		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseMoved(MouseEvent e) {
			mouseX = e.getX();
			gamePaddle.mouseMoved(e.getX());
		}
		
		
	}
	
	

	
	
}















