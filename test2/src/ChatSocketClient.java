
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.Timestamp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class ChatSocketClient {
private Socket socket = null;
private InputStream inStream = null;
private OutputStream outStream = null;
private String Timestamp;


private JFrame frame;  
private JPanel pane_buttom;  
private JSplitPane pane_center; 

private JScrollPane pane_showWindow;  
private JScrollPane pane_inputWindow;  
private JTextArea area_showWindow;  
private JTextArea area_inputWindow;

private JButton btn_send;
private JButton btn_broadcast;
private JButton btn_stop;


private Dimension dimension;
  
private boolean set = false;
private boolean broadcast = false;
private boolean stop = false;


public ChatSocketClient() {//add jframe and window to the constructure
	 frame = new JFrame();  
	    pane_buttom = new JPanel();  
	    pane_showWindow = new JScrollPane();  
	    pane_inputWindow = new JScrollPane();  
	    area_showWindow = new JTextArea();  
	    area_inputWindow = new JTextArea();  
	    pane_center = new JSplitPane(JSplitPane.VERTICAL_SPLIT, false, pane_showWindow, pane_inputWindow);  
	    btn_send = new JButton("send");  
	    btn_broadcast = new JButton("Broadcast");
	    btn_stop = new JButton("stop");
	    dimension = new Dimension(50, 300);  
}


public void showFrame() throws UnsupportedEncodingException, IOException{  //add components to the frame
    initFrame();  
    initChatTextArea();  
    initButton();  
    send();//method bond with button
    broadcast();
    stop();
   createSocket();
}  

public void initFrame(){  //init the frame
    frame.setTitle("Client");  
    int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();  
    int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();  
    frame.setBounds(width / 2, height / 2, 400, 450);  
    frame.setVisible(true);  
}  

public void initButton(){  //add button to frame
    pane_buttom.add(btn_send);  
    pane_buttom.add(btn_broadcast); 
    pane_buttom.add(btn_stop);
    frame.add(pane_buttom, BorderLayout.SOUTH);  
} 


private void initChatTextArea(){  
    pane_showWindow.getViewport().add(area_showWindow);  
    pane_inputWindow.getViewport().add(area_inputWindow);  
    area_showWindow.setEditable(false);  
    pane_showWindow.setMinimumSize(dimension);  
    frame.add(pane_center, BorderLayout.CENTER);  
}  

private void send(){  //send message to the server
    btn_send.addActionListener(new ActionListener() {  

        @Override  
        public void actionPerformed(ActionEvent e) {  
            String info = area_inputWindow.getText(); 
           
            	 synchronized (socket) {
     				try {
     					outStream.write(info.getBytes("UTF-8"));
     				} catch (IOException e1) {
     					// TODO Auto-generated catch block
     					e1.printStackTrace();
     				}
     			}
            	
           
			area_showWindow.append("client£º "+info+"\r\n"); 
			area_inputWindow.setText("");
        }  
    });  
}  


private void broadcast()  {//broadcast the message
	btn_broadcast.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String typedMessage="{Broadcast---}";
			synchronized (socket) {
				try {
					outStream.write(typedMessage.getBytes("UTF-8"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			area_showWindow.append("client£º "+"broadcast"+"\r\n");  
		}
	});
	
}

private void broadcast1(){
	String typedMessage="{Broadcast---}";
	synchronized (socket) {
		try {
			outStream.write(typedMessage.getBytes("UTF-8"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	String typedMessage1="{stop---}";
	synchronized (socket) {
		try {
			outStream.write(typedMessage1.getBytes("UTF-8"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

private void stop() {//stop the client and broadcast
	btn_stop.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			area_showWindow.append("client£º "+"hasstop"+"\r\n"); 
			broadcast1();
			frame.setVisible(false);
			try {
				inStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
}
public void createSocket() {
	try {
		socket = new Socket("localHost", 3339);
		area_showWindow.setText("connected");
		inStream = socket.getInputStream();
		outStream = socket.getOutputStream();
		createReadThread();
		createWriteThread();
	} catch (UnknownHostException u) {
		u.printStackTrace();
	} catch (IOException io) {
		io.printStackTrace();
	}
}

public void createReadThread() {
	Thread readThread = new Thread() {
public void run() {
	while (socket.isConnected()) {

		try {
			byte[] readBuffer = new byte[200];
			int num = inStream.read(readBuffer);

			if (num > 0) {
				byte[] arrayBytes = new byte[num];
				System.arraycopy(readBuffer, 0, arrayBytes, 0, num);
				String recvedMessage = new String(arrayBytes, "UTF-8");
				if(recvedMessage.equals("stop")){
					synchronized (socket) {
						String info = "{Broadcast---}";
						outStream.write(info.getBytes("UTF-8"));
					}
					area_showWindow.setText("hasstop");
					socket.close();
				}
				else{area_showWindow.append("server£º "+recvedMessage+"\r\n");}
			}
		}catch (SocketException se){
			System.exit(0);

		} catch (IOException i) {
			i.printStackTrace();
		}

	}
	}
	};
	readThread.setPriority(Thread.MAX_PRIORITY);
	readThread.start();
	}

public void createWriteThread() {
	Thread writeThread = new Thread() {
public void run() {
	while (socket.isConnected()) {
			try {
				BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
				String typedMessage = inputReader.readLine();
				if (typedMessage != null && typedMessage.length() > 0&&!typedMessage.equals("stop")&&stop==false) {
					synchronized (socket) {
						outStream.write(typedMessage.getBytes("UTF-8"));
					}
				}
			} catch (IOException i) {
				i.printStackTrace();
			}
		
		}
		

	}
	};
	writeThread.setPriority(Thread.MAX_PRIORITY);
	writeThread.start();
	}



public static void main(String[] args) throws Exception {
	ChatSocketClient myChatClient = new ChatSocketClient();
	myChatClient.showFrame();
	}
}