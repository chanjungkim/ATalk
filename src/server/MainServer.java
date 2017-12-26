package server;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chat.DbDao;
 
// <---------------------- Main Server
public class MainServer extends JFrame{
    public static String MAIN_SERVER_ADDR = "70.12.115.56";
    public static int MAIN_SERVER_PORT = 5555;
     
    private static int portSeed = 10000;
     
    private List<ServerThread> serverThreadList;
    private List<DrawingServerThread> drawingThreadList;
    private DbDao db = new DbDao();
    private ServerSocket mainServerSocket;
     
    private static int serverCount=0;
     
    private JPanel panel = new JPanel();
    private JLabel lb1 = new JLabel("MAIN SERVER ADDR");     
    private JTextField ipField = new JTextField();
    private JLabel lb2 = new JLabel("MAIN SERVER PORT");     
    private JTextField portField = new JTextField();
    private JLabel lb3 = new JLabel("DB DRIVER");     
    private JTextField dbDriver = new JTextField();
    private JLabel lb4 = new JLabel("DB URL");     
    private JTextField dbUrl = new JTextField();
    private JLabel lb5 = new JLabel("DB ID");     
    private JTextField dbId = new JTextField();
    private JLabel lb6 = new JLabel("DB PW");     
    private JTextField dbPw = new JTextField();
    private JButton resumeBtn = new JButton("RESUME");

    public MainServer() {
        serverThreadList = new ArrayList<>();
        drawingThreadList = new ArrayList<>();
  
        db.resetJoin();
        db.resetRoom();
         
        try {
            mainServerSocket = new ServerSocket(MAIN_SERVER_PORT);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        panel.setLayout(new BoxLayout(panel, 1));
        panel.add(lb1);
        panel.add(ipField);
        panel.add(lb2);
        panel.add(portField);
        panel.add(lb3);
        panel.add(dbDriver);
        panel.add(lb4);
        panel.add(dbUrl);
        panel.add(lb5);
        panel.add(dbId);
        panel.add(lb6);
        panel.add(dbPw);
        panel.add(resumeBtn);
        ipField.setText(MAIN_SERVER_ADDR);
        portField.setText(Integer.toString(MAIN_SERVER_PORT));
        dbDriver.setText(db.getDB_DRIVER());
        dbUrl.setText(db.getDB_URL());
        dbId.setText(db.getDB_ID());
        dbPw.setText(db.getDB_PW());
        
        resumeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMAIN_SERVER_ADDR(ipField.getText());
				setMAIN_SERVER_PORT(Integer.parseInt(portField.getText()));
				db.setDB_DRIVER(dbDriver.getText());
				db.setDB_URL(dbUrl.getText());
				db.setDB_ID(dbId.getText());
				db.setDB_PW(dbPw.getText());
			}
		});
        
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
                DrawingServerThread cT= new DrawingServerThread(portSeed+1);
                 
                serverThreadList.add(sT);
                drawingThreadList.add(cT);
                 
                sT.start();
                cT.start();
                 
//              new DrawingServer(portSeed+1).start();
                 
                portSeed += 10;
                System.out.println("down: portNum"+portSeed);
               
//              ServerThread sT = new ServerThread(socket);
//              serverThreadList.add(sT);
//              sT.start();
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
    
    public static String getMAIN_SERVER_ADDR() {
		return MAIN_SERVER_ADDR;
	}

	public static void setMAIN_SERVER_ADDR(String mAIN_SERVER_ADDR) {
		MAIN_SERVER_ADDR = mAIN_SERVER_ADDR;
	}

	public static int getMAIN_SERVER_PORT() {
		return MAIN_SERVER_PORT;
	}

	public static void setMAIN_SERVER_PORT(int mAIN_SERVER_PORT) {
		MAIN_SERVER_PORT = mAIN_SERVER_PORT;
	}

	public static void main(String[] args) {
        MainServer server = new MainServer();
    }
    
}