
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SecondaryLoop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class MultipleServer implements Runnable{
	static List<Map<Integer, Socket>> list = new ArrayList<Map<Integer, Socket>>();
	static List<Map<Integer, Socket>> lista1 = new ArrayList<Map<Integer, Socket>>();//list to show the user
	static List<Map<Integer, InputStream>> list1 = new ArrayList<Map<Integer, InputStream>>();
	static List<Map<Integer, OutputStream>> list2 = new ArrayList<Map<Integer, OutputStream>>();
	static List<Map<Integer, String>> list3 = new ArrayList<Map<Integer, String>>();//list about the commands
	private Socket connection;
	private String TimeStamp;
	private int ID;
	private InputStream inStream = null;
	private OutputStream outStream = null;
	static int rcount;
	static ServerWindow aServerWindow;//static so  there are only one window 
	
	public MultipleServer() {
		
	}
	
	public MultipleServer(Socket connection,int id){
		this.connection=connection;
		this.ID = id;
	}
	
	
	
void send(String s,int number){  //send the message to the specific client
	 
	 for(int i=0;i<list2.size();i++){
		Socket socket = list.get(i).get(i);
		OutputStream outStream = (OutputStream) list2.get(i).get(i);
		if(i==number){
			synchronized (socket) {
				try {
					outStream.write(s.getBytes("UTF-8"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}	  
	}
}  


void speak(String a)  {//broadcast the message
	try {
		broadcast(a);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}

	
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		int port = 3339;
		int count = 0;
		MultipleServer aMultipleServer = new MultipleServer();
		aServerWindow = new ServerWindow();
		aServerWindow.showFrame();
		//aMultipleServer.showFrame();
		try{
			ServerSocket socket1 = new ServerSocket(port);
			aServerWindow.show("start");
			aServerWindow.show1("ID:");
			while (true) {
				Socket connection = socket1.accept();//accept the client
				Map<Integer, Socket> a = new HashMap<Integer, Socket>();
				a.put(count, connection);
				list.add(a);
				lista1.add(a);
				aMultipleServer.broadcast(count +"connect to server");
				Runnable runnable = new MultipleServer(connection,count);//create the new server thread
				Thread thread = new Thread(runnable);
				thread.start();
				aServerWindow.show(count+"connect to server");
				rcount=count;
				count++;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	@Override
	public void run() {
		try {
			inStream = connection.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			outStream = connection.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Integer, InputStream> b = new HashMap<Integer,InputStream>();//each time start add the outstream and instream to the list
		Map<Integer, OutputStream>c = new HashMap<Integer,OutputStream>();
		b.put(rcount, inStream);
		c.put(rcount, outStream);
		list1.add(b);
		list2.add(c);
		createReadThread();//create read and write thread respectively
		createWriteThread();
		// TODO Auto-generated method stub
		
	}
	
	public void createReadThread() {
		Thread readThread = new Thread() {
	public void run() {
		while (connection.isConnected()) {
			try {
				byte[] readBuffer = new byte[200];
				int num = inStream.read(readBuffer);
				if (num > 0) {
					byte[] arrayBytes = new byte[num];
					System.arraycopy(readBuffer, 0, arrayBytes, 0, num);
					String recvedMessage = new String(arrayBytes, "UTF-8");
					if(recvedMessage.equals("{Broadcast---}")){
						broadcast("client broadcast");
						addstate("broadcast");
					}
					else if(recvedMessage.equals("{stop---}"))
					{
						broadcast("client has stop");
						lista1.remove(lista1.size()-1);
						addstate("stop");
					}
					else{
						aServerWindow.show("client:"+recvedMessage);
						addstate("send");
					}
				} else {
					//notify();
				};
	//System.arraycopy();

			} catch (SocketException se) {
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

		while (connection.isConnected()) {
			try {
				BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
				sleep(100);
				String typedMessage = inputReader.readLine();
				if (typedMessage != null && typedMessage.length() > 0) {
					synchronized (connection) {
						outStream.write(typedMessage.getBytes("UTF-8"));
					sleep(100);
					}
				}
				else if(typedMessage.equals("stop")){
					stop();
				}/* else {
				}
	notify();
	}*/
				;
	//System.arraycopy();

			} catch (IOException i) {
				i.printStackTrace();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

			}
		}
		};
		writeThread.setPriority(Thread.MAX_PRIORITY);
		writeThread.start();

	}
	
	
	void broadcast(String content) throws UnsupportedEncodingException, IOException{
		String typedMessage = "server sent "+content;
		for(int i=0;i<list.size();i++){
			Socket socket = (Socket) list.get(i).get(i);
			if(content!=null&&content.length()>0){
				
				PrintWriter out=new PrintWriter(socket.getOutputStream()); 
				out.println("socket:  "+socket+"server sent "+content); 
				out.flush();  
				
			}
		}
	}
	
	void stop(int id) throws IOException{//stop the client with specific id
		for(int i=0;i<list2.size();i++){
			Socket socket = list.get(i).get(i);
			OutputStream outStream = (OutputStream) list2.get(i).get(i);
			String typedMessage = "stop";
			if(i==id){
				synchronized (socket) {
					outStream.write(typedMessage.getBytes("UTF-8"));
				}

			}	  
		}
		broadcast(id+"has stop");
		lista1.remove(id);
	}
	
	public void list(){//show the current list
		int[] number = null;
		for(int i=0;i<lista1.size();i++){
			Map<Integer,Socket> a = lista1.get(i);
			System.out.println("connect"+lista1.indexOf(a));
			//aServerWindow.show("connect"+list.indexOf(a));
		}
		
	}
	
	public void addstate(String x){
		Map<Integer, String> a = new HashMap<Integer, String>();
		a.put(list3.size(),x);
		list3.add(a);
	}
	
	public void state(){
		for(int i=0;i<list3.size();i++){
			Map<Integer,String> a = list3.get(i);
			System.out.println("state:"+a.toString());
			//aServerWindow.show("connect"+list.indexOf(a));
		}
	}
	
	public void stop() throws UnsupportedEncodingException, IOException{
		broadcast("server stop");
	}
	
}
