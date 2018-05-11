import java.util.Timer;
import java.util.TimerTask;
import java.io.FileNotFoundException;
import java.util.Formatter; //This class provides support for layout justification and alignment, common formats for numeric, string, and date/time data, and locale-specific output.
import java.util.Scanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;  
  
class OnlineTest extends JFrame implements ActionListener  
{  
    JLabel l;  
    JRadioButton jb[]=new JRadioButton[5];  
    JButton b1,b2;  
    ButtonGroup bg;  
    int count=0,current=0,x=1,y=1,now=0;  
    int m[]=new int[10]; 
    
    OnlineTest(String s){  
        
        super(s);  
        l=new JLabel();  
        add(l);  
        bg=new ButtonGroup();  
        
        for(int i=0;i<5;i++){  
            jb[i]=new JRadioButton();     
            add(jb[i]);  
            bg.add(jb[i]);  
        }  
        
        b1=new JButton("Next");  
        b2=new JButton("reserve");  
        b1.addActionListener(this);  
        b2.addActionListener(this);  
        add(b1);add(b2);  
        set();  
        //.....................................structure.......................................................................
        l.setBounds(30,40,450,20);  
        jb[0].setBounds(50,80,100,20);  
        jb[1].setBounds(50,110,100,20);  
        jb[2].setBounds(50,140,100,20);  
        jb[3].setBounds(50,170,100,20);  
        b1.setBounds(100,240,100,30);  
        b2.setBounds(270,240,100,30);  
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLayout(null);  

        setVisible(true);  
        setSize(600,350);  
    }  
    
    
    public void actionPerformed(ActionEvent e){  
        if(e.getSource()==b1){  
            if(check())  
                count=count+1;  
            current++;  
            set();    
            
            if(current==9){  
                b1.setEnabled(false);  
                b2.setText("Result");  
               }
          }
        
        if(e.getActionCommand().equals("reserve")){  
            JButton bk=new JButton("reserve"+x);  
            bk.setBounds(480,20+30*x,100,30);  
            add(bk);  
            bk.addActionListener(this);  
            
            m[x]=current;  
            x++;  
            current++;  
            set();    
            
            if(current==9)  
                b2.setText("Result");  
            setVisible(false);  
            setVisible(true);  
           }
        
        for(int i=0,y=1;i<x;i++,y++){ 
        	 
         
          if(e.getActionCommand().equals("reserve"+y))
          {  
            if(check())  
                count=count+1;  
                     now=current;  
                         current=m[y];  
            set();  
            ((JButton)e.getSource()).setEnabled(false);  
            current=now;  
          }
        }
      
        if(e.getActionCommand().equals("Result")){  
            if(check())  
                count=count+1;  
            current++;  
            
            JOptionPane.showMessageDialog(this,"correct ans="+count); 
            if(count==10) {
            	JOptionPane.showMessageDialog(this,"congratulations");
            }
                   
            
            System.exit(0);  
        
        }
    }
    
    

	void set()  
    {  
        jb[4].setSelected(true);  
        
        
        if(current==0){  
            l.setText("Que1: Which one among these error detected by the compiler ?");  
            jb[0].setText(" Runtime ");jb[1].setText("Syntax ");jb[2].setText("Logic");jb[3].setText("Compile ");   
        }  
        
        if(current==1){  
            l.setText("Que2: Which one among these is not a primitive datatype?");  
            jb[0].setText("char");jb[1].setText("int");jb[2].setText("Float");jb[3].setText("boolean");  
        }  
        
        if(current==2){  
            l.setText("Que3:What is the full form of NIC?");  
            jb[0].setText("network I card");jb[1].setText("none of them");jb[2].setText("net interface card ");jb[3].setText("network interface card");  
        }  
        
        if(current==3){  
            l.setText("Que4: String class is defined in which package?");  
            jb[0].setText("lang");jb[1].setText("Swing");jb[2].setText("Applet");jb[3].setText("awt");  
        }  
        
        if(current==4){  
            l.setText("Que5: Which language CPU understands?");  
            jb[0].setText("English");jb[1].setText("high-level language");jb[2].setText("machine language");jb[3].setText("B$C");  
        }  
        
        if(current==5){  
            l.setText("Que6: Which one among these is not a keyword?");  
            jb[0].setText("class");jb[1].setText("int");jb[2].setText("get");jb[3].setText("if");  
        }  
        
        if(current==6){  
            l.setText("Que7: Which one among these is not a class? ");  
            jb[0].setText("Swing");jb[1].setText("Actionperformed");jb[2].setText("ActionEvent");  
                        jb[3].setText("Button");  
        }  
        
        if(current==7){  
            l.setText("Que8: ++ variable indicates:-?");  
            jb[0].setText("Predecrement");jb[1].setText("Postincrement");jb[2].setText("Postdecrement");  
                        jb[3].setText("Preincrement");         
        }  
        
        if(current==8){  
            l.setText("Que9: What is the size of long long variable(in bytes)?");  
            jb[0].setText("64");jb[1].setText("8");jb[2].setText("32");jb[3].setText("3");  
        } 
        
        if(current==9){  
            l.setText("Que10: 1 Bytes is equal to hoe many bits?");  
            jb[0].setText("18");jb[1].setText("81");jb[2].setText("8");  
                        jb[3].setText("80");  
        }  
        
        
        l.setBounds(30,40,450,20);  
        for(int i=0,j=0;i<=90;i+=30,j++)  
            jb[j].setBounds(50,80+i,200,20);  
    }  
    
    
    boolean check()  
    {  
        if(current==0)  
            return(jb[1].isSelected());  
        if(current==1)  
            return(jb[2].isSelected());  
        if(current==2)  
            return(jb[3].isSelected());  
        if(current==3)  
            return(jb[0].isSelected());  
        if(current==4)  
            return(jb[2].isSelected());  
        if(current==5)  
            return(jb[2].isSelected());  
        if(current==6)  
            return(jb[1].isSelected());  
        if(current==7)  
            return(jb[3].isSelected());  
        if(current==8)  
            return(jb[1].isSelected());  
        if(current==9)  
            return(jb[2].isSelected());  
        return false;  
    }
    
    
//............................................................file........................................................
    
    public static void main(String s[])  
    {  
        
         //OnlineTest = new File();
              String  id;
              String name;
    		
			try {
				Formatter formatter = new Formatter ("C:/Users/Dell/eclipse-workspace/file/person/student.txt"); //...path.......
				
				Scanner input = new Scanner(System.in);
				 
			
				for(int i = 1; i<2; i++)
				{
					System.out.println("Enter student id and name = ");
				
					id =input.next();
					
					name =input.next();
					
					formatter.format ("%s %s\r\n",id,name) ;
                                        
				}
				
				formatter.close();
                                
				
			}
                        
			catch (Exception e) {
				System.out.println(e);
			}
                        
                        finally{
                           new OnlineTest("Online Test Of Java");
                             }
                        System.out.println();
                                        

        TimerTask timerTask = new  MyTimerTask();

        Timer timer = new Timer(true);
        
        timer.scheduleAtFixedRate(timerTask, 0, 10*1000);
        
        System.out.println("TimerTask started");
        
        try {
            Thread.sleep(120000);
            
        } catch (InterruptedException e) {
        	
            e.printStackTrace();
        }
        timer.cancel();
        
        System.out.println("TimerTask cancelled");
        
        try {
            Thread.sleep(30000);
            
        } catch (InterruptedException e) {
        	
            e.printStackTrace();
        }
    }  
} 



