package image;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
     

    public MultiChattingServer() {
        threadList = new ArrayList<>();
 
        try {
            serverSocket = new ServerSocket(5555);
            while (true) {
                System.out.println("클라이언트 접속중..");
                Socket socket = serverSocket.accept();
                System.out.println("접속 : " + socket.getInetAddress());
 
 
                ChattingThread t = new ChattingThread(socket);
                threadList.add(t);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 

    public void broadcast(PaintInfoVO recInfo) {
        for (ChattingThread t : threadList) {
            t.speak(recInfo);
        }
    }
 
    public void removeThread(ChattingThread t) {
        threadList.remove(t);
    }
 
    class ChattingThread extends Thread {
    	private ObjectOutputStream os;
    	private ObjectInputStream is;
 
        public ChattingThread(Socket socket) {
            try {
            	os = new ObjectOutputStream(socket.getOutputStream());
    			is = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 
        public void speak(PaintInfoVO recInfo) {
        	try {
				os.writeObject(recInfo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
        public void run() {
            try {
                while (true) {
                	PaintInfoVO recInfo = (PaintInfoVO) is.readObject();
                   
                    broadcast(recInfo);
                }
            } catch (IOException e) {
                removeThread(this);
            } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        }
    }
     
    public static void main(String[] args) {
        MultiChattingServer server = new MultiChattingServer();
    }
}