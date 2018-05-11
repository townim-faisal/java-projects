import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends JFrame {
	
	//data fields
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	SimpleEncryptor simpleEncryptor = new SimpleEncryptor(10);
	
    /*System.out.println("Encrypt Data - " + simpleEncryptor.getEncryptedData());
    System.out.println("Key - "+ simpleEncryptor.getStringDecryptKey());
    System.out.println(simpleEncryptor.getDecryptData(simpleEncryptor.getEncryptedData(),simpleEncryptor.getRawDecryptKey()));*/
	
	//constructor
	public Server(){
		super("Instant Messenger");
		
		userText = new JTextField();
		userText.setEditable(false);
		
		//when a person type something
		userText.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//make encryption here before send text
					//as the message pass to sendMessage function here
					sendMessage(e.getActionCommand());
					//to make text field again blank after sending message
					userText.setText("");
				}
			}
		);
		
		add(userText, BorderLayout.NORTH);
		
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow));
		chatWindow.setBackground(Color.MAGENTA);
		
		//Sets the window size
		setSize(500, 650); 
		setVisible(true);
	}
	
	public void startRunning(){
		try{
			//6789 is a dummy port for testing, this can be changed. 
			//The 100 is the maximum people waiting to connect.
			server = new ServerSocket(6789, 100); 
			
			while(true){
				try{
					//Trying to connect and have conversation
					waitForConnection();
					setupStreams();
					whileChatting();
				}catch(EOFException ex){
					showMessage("\n Server ended the connection! ");
				} finally{
					//closing connection
					closeConnection(); 
				}
			}
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	//wait for connection, then display connection information
	private void waitForConnection() throws IOException {
		showMessage(" Waiting for someone to connect... \n");
		connection = server.accept();
		showMessage(" Now connected to " + connection.getInetAddress().getHostName());
	}
	
	//get stream to send and receive data
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		
		input = new ObjectInputStream(connection.getInputStream());
		
		showMessage("\n Streams are now setup \n");
	}
	
	//during the chat conversation
	private void whileChatting() throws IOException{
		String message = " You are now connected! ";
		sendMessage(message);
		ableToType(true);
		do{
			try{
				message = (String) input.readObject();
				showMessage("\n" + message);
			}catch(ClassNotFoundException ex){
				showMessage("The user has sent an unknown object!");
			}
		}while(!message.equals("CLIENT - END"));
	}
	
	//close socket and stream
	public void closeConnection(){
		showMessage("\n Closing Connections... \n");
		ableToType(false);
		try{
			output.close(); //Closes the output path to the client
			input.close(); //Closes the input path to the server, from the client.
			connection.close(); //Closes the connection between you can the client
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	//Send a message to the client
	private void sendMessage(String message){
		try{
			output.writeObject("SERVER - " + message);
			output.flush();
			showMessage("\nSERVER -" + message);
		}catch(IOException ioException){
			chatWindow.append("\n ERROR: CANNOT SEND MESSAGE, PLEASE RETRY");
		}
	}
	
	//update chat window
	private void showMessage(final String text){
		//put decrypted text here
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					chatWindow.append(text);
				}
			}
		);
	}
	
	//allow the user to type in text area as by default it is false
	private void ableToType(final boolean bool){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					userText.setEditable(bool);
				}
			}
		);
	}
}