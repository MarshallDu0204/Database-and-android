import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class ServerWindow {
	private JFrame frame;  
	private JPanel pane_buttom;  
	private JSplitPane pane_center; 
	private JSplitPane pane_north;
	
	private JScrollPane pane_showWindow;  
	private JScrollPane pane_inputWindow; 
	private JScrollPane pane_indexWindow;  
	private JScrollPane uselesswindow1;  
	
	private JTextArea area_showWindow;  
	private JTextArea area_inputWindow;
	private  JTextArea area_indexWindow;
	private  JTextArea uselesswindow;
	
	private JButton btn_send;
	private JButton btn_broadcast;
	private JButton btn_kick;
	private JButton btn_list;
	private JButton btn_state;
	private JButton btn_stop;
	
	private Dimension dimension;
	private Dimension dimension2;
	
	public ServerWindow(){
		frame = new JFrame();  
	    pane_buttom = new JPanel();  
	    pane_showWindow = new JScrollPane();  
	    pane_inputWindow = new JScrollPane();  
	    pane_indexWindow = new JScrollPane();
	    uselesswindow1 = new JScrollPane();
	    
	    
	    area_showWindow = new JTextArea();  
	    area_inputWindow = new JTextArea(); 
	    area_indexWindow = new JTextArea();
	    uselesswindow = new JTextArea();
	    
	    
	   pane_center = new JSplitPane(JSplitPane.VERTICAL_SPLIT, false, pane_showWindow, pane_inputWindow);
	    pane_north = new JSplitPane(JSplitPane.VERTICAL_SPLIT,false,uselesswindow1,pane_indexWindow);
	    btn_send = new JButton("send");  
	    btn_broadcast = new JButton("Broadcast");
	    btn_kick = new JButton("kick");
	    btn_list = new JButton("list");
	    btn_state = new JButton("state");
	    btn_stop = new JButton("stop");
	    dimension = new Dimension(50, 100); 
	    dimension2 = new Dimension(1, 1);
	}
	
	public void showFrame() throws UnsupportedEncodingException, IOException{  
	    initFrame();  
	    initChatTextArea();  
	    initButton();
	    send();
	    speak();
	    kick();
	    list();
	    state();
	    stop();
	} 
	
	public void initFrame(){  
	    frame.setTitle("server");  
	    int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();  
	    int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();  
	    frame.setBounds(width/2 , height/2 ,  400, 450);  
	    frame.setVisible(true);  
	}
	
	public void initButton(){  
	    pane_buttom.add(btn_send);  
	    pane_buttom.add(btn_broadcast); 
	    pane_buttom.add(btn_kick);
	    pane_buttom.add(btn_list);
	    pane_buttom.add(btn_state);
	    pane_buttom.add(btn_stop);
	    frame.add(pane_buttom, BorderLayout.SOUTH);  
	} 
	
	private void initChatTextArea(){  
	    pane_showWindow.getViewport().add(area_showWindow);  
	    pane_inputWindow.getViewport().add(area_inputWindow); 
	    pane_inputWindow.setMinimumSize(dimension);
	    pane_indexWindow.getViewport().add(area_indexWindow);
	    pane_indexWindow.setMinimumSize(dimension);
	    
	    uselesswindow1.getViewport().add(uselesswindow);
	    uselesswindow1.setMaximumSize(dimension2);
	    uselesswindow.setEditable(false);
	    area_showWindow.setEditable(false);  
	    pane_showWindow.setMinimumSize(dimension);
	    
	    frame.add(pane_center,BorderLayout.CENTER);
	    frame.add(pane_north, BorderLayout.NORTH);
	}  
	
	private void send(){  
	    btn_send.addActionListener(new ActionListener() {  

	        @Override  
	        public void actionPerformed(ActionEvent e) {
	        	 String info = area_inputWindow.getText();
	        	 String number1 = area_indexWindow.getText();
	        	 int number = -1;
	        	 try {
					number = Integer.parseInt(number1);
				} catch (Exception e2) {
					// TODO: handle exception
				}
	        	MultipleServer aMultipleServer = new MultipleServer();
	        	aMultipleServer.send(info,number);
	        	 area_showWindow.append("client£º "+info+"\r\n");  
	             area_inputWindow.setText("");//clear the text area each time
	             area_indexWindow.setText("");
	        }  
	    });  
	}  
	
	private void speak()  {
		btn_broadcast.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String aString = area_inputWindow.getText();
				MultipleServer aMultipleServer = new MultipleServer();
	        	aMultipleServer.speak(aString);
				area_showWindow.append("server£º "+aString+"\r\n"); 
				area_inputWindow.setText("");
			}
		});
		
	}
	
	private void kick() {
		btn_kick.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 String info = area_indexWindow.getText(); 
				 int number = -1;
				 try{
					  number = Integer.parseInt(info);
					  MultipleServer aMultipleServer = new MultipleServer();
					  aMultipleServer.stop(number);
					  area_showWindow.append("server£º "+"hasstop"+"\r\n");
				 }
				 catch (Exception e) {
					// TODO: handle exception
				}
				 
				
			}
		});
	}
	
	public void list(){
		btn_list.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MultipleServer aMultipleServer = new MultipleServer();
				aMultipleServer.list();
				
			}
		});
		
	}
	
	public void state(){
		btn_state.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MultipleServer aMultipleServer = new MultipleServer();
				aMultipleServer.state();
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void  stop() {
		btn_stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MultipleServer aMultipleServer = new MultipleServer();
				try {
					aMultipleServer.stop();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	void show(String a){
		area_showWindow.setText(a);
	}
	
	void show1(String a){
		uselesswindow.setText(a);
	}
}
