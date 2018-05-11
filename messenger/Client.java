import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Client extends JFrame{
	
	//data fields
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverIP; //IP address of server whom you are talking to
	private Socket connection;
	SimpleEncryptor simpleEncryptor = new SimpleEncryptor(10);
	
	//constructor
	public Client(String host){
		super("Client");
		serverIP = host;
		
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent event){
					//encrypted here before send text
					sendMessage(event.getActionCommand());
					userText.setText("");
				}
			}
		);
		
		add(userText, BorderLayout.NORTH);
		
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow));
		chatWindow.setBackground(Color.BLUE);
		
		//Sets the window size
		setSize(500, 650); 
		setVisible(true);
	}
	
	//connect to server
	public void startRunning(){
		try{
			connectToServer();
			setupStreams();
			whileChatting();
		}catch(EOFException ex){
			showMessage("\n Client terminated the connection");
		}catch(IOException ex){
			ex.printStackTrace();
		}finally{
			closeConnection();
		}
	}
	
	//connect to server
	private void connectToServer() throws IOException{
		showMessage("Attempting connection... \n");
		
		//6789 is port
		connection = new Socket(InetAddress.getByName(serverIP), 6789);
		
		showMessage("Connection Established! Connected to: " + connection.getInetAddress().getHostName());
	}
	
	//set up streams
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		
		input = new ObjectInputStream(connection.getInputStream());
		
		showMessage("\n The streams are now set up! \n");
	}
	
	//while chating with server
	private void whileChatting() throws IOException{
		ableToType(true);
		do{
			try{
				message = (String) input.readObject();
				showMessage("\n" + message);
			}catch(ClassNotFoundException ex){
				showMessage("Unknown data received!");
			}
		}while(!message.equals("SERVER - END"));	
	}
	
	//Close connection
	private void closeConnection(){
		showMessage("\n Closing the connection!");
		ableToType(false);
		try{
			output.close();
			input.close();
			connection.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	//send message to server
	private void sendMessage(String message){
		try{
			output.writeObject("CLIENT - " + message);
			output.flush();
			showMessage("\nCLIENT - " +message);
		}catch(IOException ex){
			chatWindow.append("\n Oops! Something went wrong!");
		}
	}
	
	//update chat window
	private void showMessage(final String message){
		//put decrypted text here
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					chatWindow.append(message);
				}
			}
		);
	}
	
	//allows user to type
	private void ableToType(final boolean tof){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					userText.setEditable(tof);
				}
			}
		);
	}
}