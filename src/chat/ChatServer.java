package chat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class ChatServer extends JFrame{
    private ServerSocket serverSocket;
    private List<ChattingThread> threadList;
    private JPanel panel = new JPanel();
    private JLabel lb = new JLabel("Waiting for clients...");
  
    public ChatServer() {
        threadList = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(5555);
            drawing.DrawingServer serv = new drawing.DrawingServer();
            serv.start();
            while (true) {
                System.out.println("...");
                Socket socket = serverSocket.accept();
                System.out.println("connected :" + socket.getInetAddress());
 
            
                ChattingThread t = new ChattingThread(socket);
                threadList.add(t);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(lb);
        add(panel);
        setTitle("ATalk Server");
        setSize(200,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
 
    public void broadcast(String msg) {
        for (ChattingThread t : threadList) {
            t.speak(msg);
        }
    }
 
    public void removeThread(ChattingThread t) {
        threadList.remove(t);
    }
 
    class ChattingThread extends Thread {
        private String nickname;
        private BufferedReader br;
        private BufferedWriter bw;
 
        public ChattingThread(Socket socket) {
            try {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 
        @Override
        public void run() {
            try {
                nickname = br.readLine();
                broadcast(nickname+" entered the room.");
 
                while (true) {
                    String msg = br.readLine();
                    broadcast(nickname + ": " + msg);
                }
            } catch (IOException e) {
                removeThread(this);
                broadcast("" + nickname + "left the room.");
//              e.printStackTrace();
            }
        }
 
        public void speak(String msg) {
            try {
                bw.write(msg + "\n");
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
     
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
    }
}