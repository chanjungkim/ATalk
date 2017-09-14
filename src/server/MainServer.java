package server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chat.DbDao;

// <---------------------- Main Server
public class MainServer extends JFrame{
	public static final String MAIN_SERVER_ADDR = "70.12.115.61";
	public static final int MAIN_SERVER_PORT = 5555;
	
	private static int portSeed = 10000;
	
	private List<ServerThread> serverThreadList;
	private DbDao db = new DbDao();
    private ServerSocket mainServerSocket;
	
	private static int serverCount=0;
    
    private JPanel panel = new JPanel();
    private JLabel lb = new JLabel("Waiting for server...");	 
    
    public MainServer() {
    	serverThreadList = new ArrayList<>();
    	
    	db.resetJoin();
    	db.resetRoom();
    	
    	try {
			mainServerSocket = new ServerSocket(MAIN_SERVER_PORT);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
        panel.add(lb);
        add(panel);
        setTitle("ATalk Server");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        while (true) {
            System.out.println("Waiting for server...");
            Socket socket=null;
            BufferedWriter bw;
            
			try {
				socket = mainServerSocket.accept();
	            System.out.println("Connected :" + socket.getInetAddress());
	            
	            try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	            System.out.println("up: portNum"+portSeed);
	            
	            
	            bw.write(portSeed+"\n");
	            bw.flush();
	            
	            // 방 하나에 채팅서버랑 그림서버 실행시키기
	            ServerThread sT = new ServerThread(portSeed);
	            serverThreadList.add(sT);
	            sT.start();
	            
//	            new DrawingServer(portSeed+1).start();
	            
	            portSeed += 10;
	            System.out.println("down: portNum"+portSeed);
	          
//	            ServerThread sT = new ServerThread(socket);
//	            serverThreadList.add(sT);
//	            sT.start();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        }
    }
    
    public int broadcastToServer() {
    	return serverCount;
    }
    
    public void removeServerThread(ServerThread sT) {
    	System.out.println("one server has been removed.");
        serverThreadList.remove(sT);
    }
    
//    public static void setServerCount() {
//    	
//    }
//    
//    public static int getServerCount() {
//    	return serverCount;
//    }  

    // <-------------------- ServerThread -------------------------
//    class ServerThread extends Thread {
//    	private final static int BASIC_PORT_NUM = 5555;
//    	private final static int DRAWING_PORT_NUM = 7777;
//    	
//    	private ArrayList<UserThread> userThreadList = new ArrayList<>();
//        private ServerSocket serverSocket;
//
//        private String masterID;
//        private int serverNum;
//        private int portNum;
//        
//        private BufferedReader mainToServerBr;
//        private BufferedWriter serverToMainBw;
//
//        public ServerThread(Socket mainServerSocket) {
//    		try {
//            	// Start setting up Port Num.
//    			mainToServerBr = new BufferedReader(new InputStreamReader(mainServerSocket.getInputStream()));
//    	        serverToMainBw = new BufferedWriter(new OutputStreamWriter(mainServerSocket.getOutputStream()));
//    	        
//    	    	int serverNum = Integer.parseInt(mainToServerBr.readLine());
//            	int portNum = BASIC_PORT_NUM+serverNum;
//            	int drawingPortNum = DRAWING_PORT_NUM+serverNum;
//
//                serverSocket = new ServerSocket(portNum);
//
//    			Socket socketToServer = new Socket(InetAddress.getByName("127.0.0.1"), portNum);
//
//                DrawingServer drawingServer = new DrawingServer(drawingPortNum);
//                drawingServer.start();
//            	// End of setting up Port Num.
//
//    			serverToMainBw.flush();
//    		} catch (IOException e1) {
//    			// TODO Auto-generated catch block
//    			e1.printStackTrace();
//    		}
//            while (true) {
//                System.out.println("Waiting for client...");
//                Socket socketToUser;
//    			try {
//    				socketToUser = serverSocket.accept();
//    				
//    	            System.out.println("Connected :" + socketToUser.getInetAddress());
//    	            
//    	            UserThread uT = new UserThread(socketToUser);
//    	            userThreadList.add(uT);
//    	            uT.start();
//    			} catch (IOException e) {
//    				// TODO Auto-generated catch block
//    				e.printStackTrace();
//    			}
//            }
//        }
//      
//        
//        @Override
//        public void run() {
//            try {
//                while (true) {
//                	String msg = mainToServerBr.readLine();  // useless
//                }
//            } catch (IOException e) {
//            	
//                removeServerThread(this);
//                System.out.println(portNum+"방이 제거 되었습니다.");
//              e.printStackTrace();
//            }
//        }
//        public void speak(String msg) {
//            try {
//                serverToMainBw.write("1"); // add one
//                serverToMainBw.flush();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        public void broadcastToUsers(String msg) {
//            for (UserThread uT : userThreadList) {
//                uT.sendMessage(msg);
//            }
//        }
//        
//        public int getServerCount(){
//        	return serverNum;
//        }
//        
//        public void removeUserThread(UserThread uT) {
//            userThreadList.remove(uT);
//        }
//                
//        //<-------------------- UserThread -------------------------
//        class UserThread extends Thread {
//            private String nickname;
//            private BufferedReader serverToUserBr;
//            private BufferedWriter userToServerBw;
//     
//            public UserThread(Socket serverSocket) {
//                try {
//                    serverToUserBr = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
//                    userToServerBw = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//     
//            @Override
//            public void run() {
//                try {
//                    nickname = serverToUserBr.readLine();
//                    broadcastToUsers(nickname+" entered the room.");
//     
//                    while (true) {
//                        String msg = serverToUserBr.readLine();
//                        broadcastToUsers(nickname + ": " + msg);
//                    }
//                } catch (IOException e) {
//                    removeUserThread(this);
//                    broadcastToUsers("" + nickname + "left the room.");
//                  e.printStackTrace();
//                }
//            }
//     
//            public void sendMessage(String msg) {
//                try {
//                    userToServerBw.write(msg + "\n");
//                    userToServerBw.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
    public static void main(String[] args) {
        MainServer server = new MainServer();
    }
}
