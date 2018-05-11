import java.awt.*;

public class Paddle {

	private double x;
	private int width,height;
	
	public final int YPOSITION = DXBall.HEIGHT-100;
	
	public Paddle() {
		width = 100;
		height = 20;
		
		x = DXBall.WIDTH/2 - width/2;
		
	}
	
	public void update() {
		
		
	}
	
	public void draw(Graphics2D g) {
		g.setColor(new Color(0,51,102));
		g.fillOval((int)x, YPOSITION, width, height);
	}
	
	public Rectangle getRect() {
		return new Rectangle((int)this.x,YPOSITION,width,height);
	}
	
	
	public void mouseMoved(int mouseXPos) {
		this.x = mouseXPos;
		
		if(x > DXBall.WIDTH - this.width) {
			x = DXBall.WIDTH - this.width;
		}
	}

	public int getWidth() {
		return width;
	}

	
}
