import java.net.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.applet.*;

public class Sites extends JApplet{
	
	private HashMap<String, URL> webSiteInfo;
	private ArrayList<String> titles;
	private JList mainList;
	
	private void grabHTMLInfo() {
		// TODO Auto-generated method stub
		String title, address;
		URL url;
		int count =0;
		title = getParameter("title"+count);
		
		while(title!=null){
			address = getParameter("address"+count);
			try{
				url = new URL(address);
				webSiteInfo.put(title, url);
				titles.add(title);
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
			count++;
			title = getParameter("title"+count);
		}
	}
	
	public void init(){
		webSiteInfo = new HashMap<String, URL> ();
		titles = new ArrayList<String>();
		mainList = new JList(titles.toArray());
		
		this.grabHTMLInfo();
		add(new JLabel("what website you want to visit?"), BorderLayout.NORTH);
		
		mainList.addListSelectionListener(
			new ListSelectionListener(){
				@Override
				public void valueChanged(ListSelectionEvent event) {
					// TODO Auto-generated method stub
					Object object = mainList.getSelectedValue();
					URL newDocument = webSiteInfo.get(object); 
					AppletContext browser = getAppletContext();
					browser.showDocument(newDocument);
				}}	
		);
		
	}

	
}
