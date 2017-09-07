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
 

public class MultiChattingServer {
    private ServerSocket serverSocket;
    private List<ChattingThread> threadList;
     
    public MultiChattingServer() {
        threadList = new ArrayList<>();
 
        try {
            serverSocket = new ServerSocket(5555);
            while (true) {
                System.out.println(".....");
                Socket socket = serverSocket.accept();
                System.out.println("connected :" + socket.getInetAddress());
 
              
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
    public void broadcast(PaintInfoVO recInfo) {
        for (ChattingThread t : threadList) {
            t.speak(recInfo);
        }
    }
 
    // ������ ��Ͽ��� Ư�� ������ �����ϱ�
    public void removeThread(ChattingThread t) {
        threadList.remove(t);
    }
 
    // �ϳ��� Ŭ���̾�Ʈ�� �������� �� ��� ������ Ŭ����
    class ChattingThread extends Thread {
    	private ObjectOutputStream os;
    	private ObjectInputStream is;
 
        public ChattingThread(Socket socket) {
            // �����κ��� �ش� Ŭ���̾�Ʈ ���� ���޹޾Ƽ�
            // ä�� �ʱ�ȭ �۾� �����ϱ�
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
				// TODO Auto-generated catch block
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
                // ��� Ŭ���̾�Ʈ�� �������� ��
                removeThread(this);
              //  broadcast("[" + nickname + "]���� �����Ͽ����ϴ�.");
//              e.printStackTrace();
            } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        }
 
//        // ���� �����尡 ����ϴ� Ŭ���̾�Ʈ���� �޼��� ������
//        public void speak( recInfo) {
//            try {
//            	bw.write(arr);
//                bw.flush();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
     
    public static void main(String[] args) {
        MultiChattingServer server = new MultiChattingServer();
    }
}