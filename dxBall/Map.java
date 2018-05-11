import java.awt.*;
import java.util.Random;

public class Map {

	private int[][] gameMap;
	private int brickHeight, brickWidth;
	
	public final int HOR_PAD = 80, VERT_PAD = 50;
	Random random = new Random();
	
	public Map(int row, int col) {
		initMap(row,col);
		
		brickWidth = (DXBall.WIDTH - 2 * HOR_PAD) /col;
		brickHeight = (DXBall.HEIGHT/3 - VERT_PAD) /row;
		
		
	}
	
	
	public void initMap(int row, int col) {
		
		gameMap = new int[row][col];
		for(int i = 0; i<gameMap.length; i++) {
			for(int j = 0; j<gameMap[0].length; j++) {
				
				int b = (int) (Math.random() * 3 + 1);
				gameMap[i][j] = b;
			}
		}
		
	}
	



	public void draw(Graphics2D g) {
		
		for(int row = 0; row<gameMap.length; row++) {
			for(int col = 0; col<gameMap[0].length; col++) {

				if(gameMap[row][col] > 0) {
					if(gameMap[row][col] == 1) {
						g.setColor(new Color(204,229,255));
					}
					if(gameMap[row][col] == 2) {
						g.setColor(new Color(102,178,255));
					}
					if(gameMap[row][col] == 3) {
						g.setColor(new Color(0,128,255));
					}
					g.fillOval(col*brickWidth+ HOR_PAD, row*brickHeight+VERT_PAD, brickWidth, brickHeight);	
					g.setStroke(new BasicStroke(2));
					g.setColor(Color.WHITE);
					g.drawOval(col*brickWidth+ HOR_PAD, row*brickHeight+VERT_PAD, brickWidth, brickHeight);
			
				}
			}
		}
		
		
	}
	
	public int[][] getMapArr() {
		return this.gameMap;
	}
	
	public void setBrick(int row, int col, int value) {
		gameMap[row][col] = value;
	}
	
	public int getBrickValue() {
		return 0;
		
	}


	public int getBrickHeight() {
		return this.brickHeight;
	}

	public int getBrickWidth() {
		return this.brickWidth;
	}
	
	public void brickHit (int row, int col) {
		gameMap[row][col] -= 1;
		if (gameMap[row][col] <0 ) {
			gameMap[row][col] = 0;
		}
	}
	
	public boolean winORlose() {
		boolean win = false;
		int bricksRemaining = 0;

		for(int row = 0; row<gameMap.length; row++) {
			for(int col = 0; col<gameMap[0].length; col++) {
				bricksRemaining += gameMap[row][col];
				
			}
		}
		
		if(bricksRemaining == 0) {
			win = true;
		}

		return win;
	}
}
