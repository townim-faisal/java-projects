import java.awt.*;

public class Ball {	
	private double  x,y, dx, dy;
	private int ballSize = 15;
	
	
	
	public Ball() {
		x = 200;
		y = 200;
		dx = 1; 
		dy = 3;
		
	}
	
	
	public void update() {
		
		setPosition();
		
	}
	
	public void setPosition() {
		x += dx;
		y += dy;
 
		if(x<0) {
			dx = -dx;
		}
		
		if(y<0) {
			dy = -dy;
		}
		
		
		if(x > DXBall.WIDTH - ballSize) {
			dx = -dx ;
		}
		
		if(y > DXBall.HEIGHT - ballSize) {
			dy = -dy ;
		}
	}
	
	
	public void draw(Graphics2D g) {
		
		g.setColor(new Color(0,76,153));


		g.fillOval((int)x,(int)y,ballSize,ballSize) ; 

	}
	
	
	public Rectangle getRect() {
		return new Rectangle((int)this.x,(int)this.y,this.ballSize,this.ballSize);
	}
	
	
	public void setDY(double newDY) {
		this.dy = newDY;
	}
	
	public double getDY() {
		return this.dy;
	}

	public double getDX() {
		return dx;
	}


	public void setDX(double newDX) {
		this.dx = newDX;
	}


	public double getX() {
		return x;
	}
	
	public boolean lose() {
		boolean lose = false;
		
		if(this.y > DXBall.HEIGHT - ballSize*2) {
			lose = true;
		}
		
		
		return lose;
	}



	
	
}