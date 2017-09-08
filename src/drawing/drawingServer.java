package drawing;

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

public class drawingServer extends Thread {
	private ServerSocket serverSocket;
	private List<ChattingThread> threadList;

	public drawingServer() {
		threadList = new ArrayList<>();
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(6666);
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
				// broadcast("[" + nickname + "]���� �����Ͽ����ϴ�.");
				// e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		// // ���� �����尡 ����ϴ� Ŭ���̾�Ʈ���� �޼��� ������
		// public void speak( recInfo) {
		// try {
		// bw.write(arr);
		// bw.flush();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
	}

	public static void main(String[] args) {
		drawingServer server = new drawingServer();
	}
}