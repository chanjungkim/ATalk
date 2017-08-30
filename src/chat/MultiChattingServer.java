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
 
// 서버는 클라이언트의 수만큼 쓰레드를 리스트에 유지하고
// 클라이언트가 추가 될때마다 소켓을 생성하여 쓰레드에게 전달함.
public class MultiChattingServer extends JFrame{
    private ServerSocket serverSocket;
    private List<ChattingThread> threadList;
    private JPanel panel = new JPanel();
    private JLabel lb = new JLabel("서버 실행중...");
    // 서버 생성자
    public MultiChattingServer() {
        threadList = new ArrayList<>();
 
        try {
            serverSocket = new ServerSocket(5555);
            while (true) {
                System.out.println("클라이언트를 기다리는 중..");
                Socket socket = serverSocket.accept();
                System.out.println("접속함:" + socket.getInetAddress());
 
                // 새로운 클라이언트 접속하면 새로운
                // 쓰레드 객체를 생성해서 리스트에 추가함.
                ChattingThread t = new ChattingThread(socket);
                threadList.add(t);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(lb);
        add(panel);
        setTitle("서버");
        setSize(200,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
 
    // 서버의 리스트에 있는 모든 쓰레드에게 메세지 발송 명령해서
    // 모든 클라이언트에게 메세지 방송하기 메소드
    public void broadcast(String msg) {
        for (ChattingThread t : threadList) {
            t.speak(msg);
        }
    }
 
    // 쓰레드 목록에서 특정 쓰레드 삭제하기
    public void removeThread(ChattingThread t) {
        threadList.remove(t);
    }
 
    // 하나의 클라이언트가 접속했을 때 담당 쓰레드 클래스
    class ChattingThread extends Thread {
        private String nickname;
        private BufferedReader br;
        private BufferedWriter bw;
 
        public ChattingThread(Socket socket) {
            // 서버로부터 해당 클라이언트 소켓 전달받아서
            // 채팅 초기화 작업 수행하기
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
                broadcast(nickname+"님이 입장하셨습니다.");
 
                while (true) {
                    String msg = br.readLine();
                    broadcast(nickname + ": " + msg);
                }
            } catch (IOException e) {
                // 담당 클라이언트가 퇴장했을 때
                removeThread(this);
                broadcast("[" + nickname + "]님이 퇴장하였습니다.");
//              e.printStackTrace();
            }
        }
 
        // 현재 쓰레드가 담당하는 클라이언트에게 메세지 보내기
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
        MultiChattingServer server = new MultiChattingServer();
    }
}