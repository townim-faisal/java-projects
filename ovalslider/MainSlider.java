import java.awt.*;
import javax.swing.*;

public class MainSlider {
	
	/*public void paint(Graphics g){
		super.paint(g);
		g.drawString("wow this actually worked?", 25, 25);
		
	}*/
	
	/*private double sum;
	
	public void init(){
		String fn = JOptionPane.showInputDialog("Enter first number");
		String sn = JOptionPane.showInputDialog("Enter second number");
		
		double n1 = Double.parseDouble(fn);
		double n2 = Double.parseDouble(sn);
		
		sum=n1+n2;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawString("the sum is "+sum, 20, 30);
	}*/
	
	public static void main(String[] args){
		TheWindow w = new TheWindow();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setSize(230,280);
		w.setVisible(true);
	}
}
