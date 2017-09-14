package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class DrawingServer extends Thread {
	private ServerSocket serverSocket;
	private List<ChattingThread> threadList;
	private int portNum;

//	public static void main(String[] args) {
//		DrawingServer server = new DrawingServer();
//	}
	
	public DrawingServer(int portNum) {
		threadList = new ArrayList<>();
		this.portNum = portNum;
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(portNum);
			while (true) {
				System.out.println("Drawing Server started running.");
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

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}