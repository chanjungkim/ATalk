package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

// <-------------------- ServerThread -------------------------
class ServerThread extends Thread {

	private ArrayList<UserThread> userThreadList = new ArrayList<>();
	private ServerSocket serverSocket;
	private int portNum;

	public ServerThread(int portNum) {
		this.portNum = portNum;
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(portNum);

			while (true) {
				System.out.println("Waiting for client...");
				Socket socketToUser;
				socketToUser = serverSocket.accept();

				System.out.println("Connected :" + socketToUser.getInetAddress());

				UserThread t = new UserThread(socketToUser);
				userThreadList.add(t);
				t.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void broadcastToUsers(String msg) {
		for (UserThread t : userThreadList) {
			t.sendMessage(msg);
		}
	}

	public void removeUserThread(UserThread uT) {
		userThreadList.remove(uT);
	}

	// <-------------------- UserThread -------------------------
	class UserThread extends Thread {
		private String nickname;
		private BufferedReader br;
		private BufferedWriter bw;

		public UserThread(Socket serverSocket) {
			try {
				br = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
				bw = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				nickname = br.readLine();
				broadcastToUsers(nickname + " entered the room.");

				while (true) {
					String msg = br.readLine();
					if (msg == null) {
						broadcastToUsers("" + nickname + "left the room.");
						break;
					} else {
						broadcastToUsers(nickname + ": " + msg);
					}
				}removeUserThread(this);

			} catch (IOException e) {
				removeUserThread(this);
				broadcastToUsers("" + nickname + "left the room.");
				e.printStackTrace();
			}
		}

		public void sendMessage(String msg) {
			try {
				bw.write(msg + "\n");
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}