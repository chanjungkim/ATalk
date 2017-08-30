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
 
// ������ Ŭ���̾�Ʈ�� ����ŭ �����带 ����Ʈ�� �����ϰ�
// Ŭ���̾�Ʈ�� �߰� �ɶ����� ������ �����Ͽ� �����忡�� ������.
public class MultiChattingServer {
    private ServerSocket serverSocket;
    private List<ChattingThread> threadList;
     
    // ���� ������
    public MultiChattingServer() {
        threadList = new ArrayList<>();
 
        try {
            serverSocket = new ServerSocket(5555);
            while (true) {
                System.out.println("Ŭ���̾�Ʈ�� ��ٸ��� ��..");
                Socket socket = serverSocket.accept();
                System.out.println("������:" + socket.getInetAddress());
 
                // ���ο� Ŭ���̾�Ʈ �����ϸ� ���ο�
                // ������ ��ü�� �����ؼ� ����Ʈ�� �߰���.
                ChattingThread t = new ChattingThread(socket);
                threadList.add(t);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    // ������ ����Ʈ�� �ִ� ��� �����忡�� �޼��� �߼� ����ؼ�
    // ��� Ŭ���̾�Ʈ���� �޼��� ����ϱ� �޼ҵ�
    public void broadcast(String msg) {
        for (ChattingThread t : threadList) {
            t.speak(msg);
        }
    }
 
    // ������ ��Ͽ��� Ư�� ������ �����ϱ�
    public void removeThread(ChattingThread t) {
        threadList.remove(t);
    }
 
    // �ϳ��� Ŭ���̾�Ʈ�� �������� �� ��� ������ Ŭ����
    class ChattingThread extends Thread {
        private String nickname;
        private BufferedReader br;
        private BufferedWriter bw;
 
        public ChattingThread(Socket socket) {
            // �����κ��� �ش� Ŭ���̾�Ʈ ���� ���޹޾Ƽ�
            // ä�� �ʱ�ȭ �۾� �����ϱ�
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
                broadcast("[" + nickname + "]���� �����ϼ̽��ϴ�.");
 
                while (true) {
                    String msg = br.readLine();
                    broadcast(nickname + ":" + msg);
                }
            } catch (IOException e) {
                // ��� Ŭ���̾�Ʈ�� �������� ��
                removeThread(this);
                broadcast("[" + nickname + "]���� �����Ͽ����ϴ�.");
//              e.printStackTrace();
            }
        }
 
        // ���� �����尡 ����ϴ� Ŭ���̾�Ʈ���� �޼��� ������
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