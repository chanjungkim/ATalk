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
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ChatRoom extends JFrame {
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

	private JTextField typeField = new JTextField("Type");
	private JButton mic = new JButton("MIC");

	private BufferedReader br;
	private BufferedWriter bw;

	private String id;
	private String masterID;

	public ChatRoom(String id, String masterID) {
		this.id = id;
		
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

		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				DbDao joinDao = new DbDao();
				joinDao.deleteJoinedMember(masterID);
				RoomList roomList = new RoomList(id);
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
				drawing.MyDrawing draw = new drawing.MyDrawing();
			}
		});

		// Network
		//
		ChattingListener listener = new ChattingListener();
		//
		try {
			Socket socket = new Socket(InetAddress.getByName("70.12.115.61"), 5555);

			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//
			bw.write(id + "\n");
			bw.flush();

			//
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

		// end

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
				bw.write(msg + "\n");
				bw.flush();
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

					String receiveMsg = br.readLine();
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
									+ "\nBot: need some help?\nThese commands are available now: \n/help: Show command list. \n/code ((none)/java/python): code mode(default is 'c'.) \n/sing: Bot sings\n/time: Show current time\n");
							System.out.println("need some help?\n");
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
	// public static void main(String[] args) {
	// new ChatClient("Chan", "eee");
	// }
}
