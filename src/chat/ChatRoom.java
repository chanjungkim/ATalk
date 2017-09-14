package chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ChatRoom extends JFrame {
	private final static String MAIN_SERVER_ADDR = "70.12.115.61";
	private JPanel panel;
	private JPanel userListPanel;
	private JPanel messagesAreaPanel;
	private JPanel typeAreaPanel;
	private JPanel leftTopPanel;
	private JPanel leftBottomPanel;
	private JPanel chatSetPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JButton compile;
	private JButton drawing;

	public JButton backBtn = new JButton("<");

	private AutoTextToImagePanel messageField = new AutoTextToImagePanel();
	private JScrollPane scrollFrame;

	private JTextField typeField = new JTextField("Type");;
	private JButton mic = new JButton("MIC");

	private String id;
	private String masterID;
	////////////////////////////////////////////////////////
	// �쁽�옱 �겢�씪�씠�뼵�듃媛� �냼�냽�맂 諛� �꽌踰�(梨꾪똿�꽌踰�, 洹몃┝�꽌踰�)�� �넻�떊�븷 硫ㅻ쾭蹂��닔�뱾
	private Socket sockToRoomServer;
	private Socket sockToDrawingServer;

	private BufferedReader brChat;
	private BufferedWriter bwChat;

	private ObjectOutputStream osDraw;
	private ObjectInputStream isDraw;
	/////////////////////////////////////////////////////////
	private RoomVO roomInfo;

	private DbDao dao = new DbDao();

	public ChatRoom(String id, String masterID) {
		this.id = id;
		this.masterID = masterID;

		panel = new JPanel();
		userListPanel = new JPanel();
		messagesAreaPanel = new JPanel();
		scrollFrame = new JScrollPane(messageField);
		typeAreaPanel = new JPanel();
		leftTopPanel = new JPanel();
		leftBottomPanel = new JPanel();
		chatSetPanel = new JPanel();

		leftPanel = new JPanel();
		rightPanel = new JPanel();

		// Function
		messageField.setEditable(false);

		typeField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				typeField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {

			}
		});

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				dao.deleteJoinedMember(id, masterID);
				if (id.equals(masterID)) {
					dao.deleteRoom(masterID);
					RoomList roomList = new RoomList(id);
				} else {
					RoomList roomList = new RoomList(id);
				}
				System.exit(0);
			}
		});

		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChatRoom.this.dispose();
			}
		});

		compile = new JButton("compile");
		compile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				compile.CompileFrame com = new compile.CompileFrame();
			}
		});
		drawing = new JButton("drawing");
		drawing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawing.MyDrawing draw = new drawing.MyDrawing(isDraw, osDraw);
			}
		});

		// Network
		//
		ChattingListener listener = new ChattingListener();
		//
		try {
			roomInfo = dao.getRoomInfo(masterID);
			sockToRoomServer = new Socket(InetAddress.getByName(MAIN_SERVER_ADDR), roomInfo.getPortNum());
			// sockToDrawingServer = new
			// Socket(InetAddress.getByName(MainServer.MAIN_SERVER_ADDR),
			// roomInfo.getPortNum()+1);

			bwChat = new BufferedWriter(new OutputStreamWriter(sockToRoomServer.getOutputStream()));
			brChat = new BufferedReader(new InputStreamReader(sockToRoomServer.getInputStream()));

			// isDraw = new ObjectInputStream(sockToDrawingServer.getInputStream());
			// osDraw = new ObjectOutputStream(sockToDrawingServer.getOutputStream());

			bwChat.write(id + "\n");
			bwChat.flush();

			new ListenThread().start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		typeField.addActionListener(listener);

		mic.addActionListener(new ActionListener() {
			int i = 0;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (i == 0) {
					System.out.println("Mic on.");
					i += 1;
				} else {
					System.out.println("Mic off.");
					i = 0;
				}
			}
		});

		// Layout
		messagesAreaPanel.setLayout(new BorderLayout());
		messageField.setLayout(new BorderLayout());
		userListPanel.setLayout(new BoxLayout(userListPanel, 1));
		typeAreaPanel.setLayout(new BoxLayout(typeAreaPanel, 0));
		leftPanel.setLayout(new BorderLayout());
		rightPanel.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());

		// Change this area
		userListPanel.setBackground(Color.YELLOW);
		messageField.setBackground(Color.ORANGE);

		ArrayList<JButton> userBtnList = new ArrayList<>();
		// end
		List<UserVO> join = dao.selectJoinedMember(masterID);

		for (int i = 0; i < join.size(); i++) {
			userBtnList.add(new JButton(join.get(i).getId()));
			int k = i;
			userBtnList.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ProfileShow profile = new ProfileShow(join.get(k).getId());
				}
			});
			userListPanel.add(userBtnList.get(i));
		}
		// Add

		leftBottomPanel.add(mic);

		leftTopPanel.add(backBtn, "West");
		leftPanel.add(leftTopPanel, "North");
		leftPanel.add(userListPanel, "Center");
		leftPanel.add(leftBottomPanel, "South");

		messagesAreaPanel.add(scrollFrame);
		typeAreaPanel.add(typeField);
		typeAreaPanel.add(compile);
		typeAreaPanel.add(drawing);

		rightPanel.add(messagesAreaPanel, "Center");
		rightPanel.add(typeAreaPanel, "South");

		panel.add(leftPanel, "West");
		panel.add(rightPanel, "Center");
		add(panel);

		// Definition
		setTitle("ATalk");
		leftBottomPanel.setSize(500, 800);
		typeAreaPanel.setSize(500, 800);
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	// End

	class ChattingListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String msg = typeField.getText();
			typeField.setText("");

			try {
				bwChat.write(msg + "\n");
				bwChat.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	// Start ListenThread
	class ListenThread extends Thread {
		@Override
		public void run() {
			try {
				while (true) {

					Thread.sleep(1000);

					String receiveMsg = brChat.readLine();
					StringTokenizer st = new StringTokenizer(receiveMsg);
					String nickPart;
					String commandChecker = "";
					if (receiveMsg.isEmpty()) {
						nickPart = id + ": "; // Show id in the messageArea
					} else {
						nickPart = st.nextToken();
						if (st.hasMoreTokens()) {
							commandChecker = st.nextToken();
						}
					}
					String text = "";
					System.out.println(commandChecker);
					if (commandChecker.isEmpty() != true && commandChecker.charAt(0) == '/') {
						switch (commandChecker.substring(1, commandChecker.length())) {
						case "help":
							messageField.setText(messageField.getText()
									+ "\nBot: need some help?\nThese commands are available now: \n/help: Show command list. \n/code ((none)/java/python): code mode(default is 'c'.) \n/sing: Bot sings\n/time: Show current time\n"
									+"emoticon: # -(hit) @ @(angry) - -(mad) ; ( (cry) ; )(wink) : D(smile) ^ ^(lovely) < 3(heart)");
							break;
						case "code":
							if (st.hasMoreTokens()) {
								String type = st.nextToken();
								switch (type) {
								case "java":
									messageField.setText(messageField.getText() + "\n" + nickPart
											+ "\n1: class Main{\n2:     public static void main(String[] args){\n3:       System.out.println(\"Hello, World!\");\n4:     }\n5: }\n");
									break;
								case "python":
									messageField.setText(
											messageField.getText() + "\n" + nickPart + "\n1:print('Hello, World!')\n");
									break;
								default:
									messageField.setText(messageField.getText() + "\n1" + nickPart
											+ "\n1:#include <stdio.h>\n2: \n3: int main(){\n4: printf(\"Hello, World!\");\n5: }\n");
									break;
								}
							} else {
								messageField.setText(messageField.getText() + "\n" + nickPart
										+ "\n1:#include <stdio.h>\n2: \n3: int main(){\n4: printf(\"Hello, World!\");\n5: }\n");
							}
							System.out.println("code mode.");
							break;
						case "time":
							Date dt = new Date();
							System.out.println(dt.toString());
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss a");

							messageField.setText(
									messageField.getText() + "\nBot: It's " + sdf.format(dt).toString() + "\n");

							break;
						case "sing":
							messageField.setText(messageField.getText()
									+ "\nBot: Row, row, row your boat, Gently down the stream, Merrily merrily, merrily, merrily Life is but a dream\n");
							break;
						default:
							messageField.setText(messageField.getText() + "\nBot: Incorrect command.\n");
						}

						if (st.hasMoreTokens()) {
							while (st.hasMoreTokens()) {
								text += st.nextToken() + " ";
							}
							messageField.setText(messageField.getText() + text + "\n");
						}
					} else {
						messageField.setText(messageField.getText() + receiveMsg + "\n");
					}
					messageField.setCaretPosition(messageField.getText().length());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void dispose() {
		System.out.println("창 꺼짐:" + id + "/" + masterID);
		try {
			sockToRoomServer.close();
		} catch (IOException e1) {
			System.out.println("client back socket ex:" + e1);
			e1.printStackTrace();
		}

		DbDao joinDao = new DbDao();
		joinDao.deleteJoinedMember(id, masterID);
		if (id.equals(masterID)) {
			joinDao.deleteRoom(masterID);
			RoomList roomList = new RoomList(id);
		} else {
			RoomList roomList = new RoomList(id);
		}
		super.dispose();
	}
}
