import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ReadFile extends JFrame{
	
	private JTextField addressBar;
	private JEditorPane display;
	
	public ReadFile(){
		super("my web browser");
		addressBar = new JTextField("Enter a URL");
		addressBar.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						// pass the url as string
						loadCrap(e.getActionCommand());
					}
					
				}
		);
		
		add(addressBar, BorderLayout.NORTH);
		
		display = new JEditorPane();
		display.setEditable(false);
		display.addHyperlinkListener(
				new HyperlinkListener(){
					@Override
					public void hyperlinkUpdate(HyperlinkEvent e) {
						// TODO Auto-generated method stub
						if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED){
							loadCrap(e.getURL().toString());
						}
					}
				}
		);
		
		add(new JScrollPane(display), BorderLayout.CENTER);
		setSize(500,300);
		setVisible(true);
	}
	
	//go, read html file and display in screen
	private void loadCrap(String userText) {
		try{
			display.setPage(userText);
			addressBar.setText(userText);
		}catch(Exception e){
			System.out.println("wrong url");
		}
		
	}
}
