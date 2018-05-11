import java.awt.*;

public class HUD {
	private int score;
	
	
	public HUD() {
		
		initialize();
	}
	
	public void initialize() {
		score = 0;
		
	}

	public void draw(Graphics2D g) {

		g.setFont(new Font("Helvetica", Font.BOLD,15));
		g.setColor(Color.BLUE);
		g.drawString("Score: " + this.score , 20, 20);
		g.drawString("Brick Hits: " + this.score/50, 20, 40);	
		
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void addScore(int newScore) {
		this.score += newScore;
	}
	
	
	
	
	
}
