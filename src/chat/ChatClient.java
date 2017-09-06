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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ChatClient extends JFrame {
	private JPanel panel;
	private JPanel userListPanel;
	private JPanel messagesAreaPanel;
	private JPanel typeAreaPanel;
	private JPanel leftTopPanel;
	private JPanel leftBottomPanel;
	private JPanel chatSetPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JButton btn123;

	private JButton backBtn = new JButton("<");

	private JTextPane messageField = new JTextPane();
	private JScrollPane scrollFrame;

	private JTextField typeField = new JTextField("Type");
	private JButton chatSetBtn = new JButton("+");

	private JButton user1;
	private JButton user2 = new JButton("USER-1");
	private JButton mic = new JButton("MIC");

	private BufferedReader br;
	private BufferedWriter bw;

	private String id;

	public ChatClient(String id) {
		this.id = id;
		user1 = new JButton(id);
		panel = new JPanel();
		userListPanel = new JPanel();
		messagesAreaPanel = new JPanel();
		scrollFrame = new JScrollPane(messageField);
		typeAreaPanel = new JPanel();
		leftTopPanel = new JPanel();
		leftBottomPanel = new JPanel();
		chatSetPanel = new JPanel();

		leftPanel = new JPanel();
		leftPanel.setToolTipText("<�� �ڷΰ��⸦ ���մϴ�. ���� �α׾ƿ�ó�� �α���ȭ������ ���������ϴ�. ����� ���� ����Ʈ�� �����ְ� �� �ϴ��� ����ũ ������ �����ݴϴ�.");
		rightPanel = new JPanel();
		messageField
				.setToolTipText("�޽��� â�� Edit�� �� �����ϴ�. Typing�ϴ� ���� /help Ȥ�� /code�� ġ�� Bot�� ����մϴ�. ���� ������ ��ũ���� Ȱ��ȭ�˴ϴ�.");

		// Function
		messageField.setEditable(false);

		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Go to the previous frame.");
				dispose();
				RoomList roomList = new RoomList("");
			}
		});

		typeField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				typeField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {

			}
		});

		chatSetBtn.addActionListener(new ActionListener() {
			int i = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (i == 0) {
					messagesAreaPanel.setSize(messagesAreaPanel.getWidth(), messagesAreaPanel.getHeight() - 5);
					// rightPanel.add(chatSetPanel, "Center");
					chatSetPanel.setSize(messagesAreaPanel.getWidth(), 5);
					chatSetBtn.setText("�̸�Ƽ��");
					Dialog imo = new Dialog();
					
					i = 1;
				} else {
					messagesAreaPanel.setSize(messagesAreaPanel.getWidth(), messagesAreaPanel.getHeight() + 5);
					// rightPanel.add(chatSetPanel, "Center");
					chatSetPanel.setSize(messagesAreaPanel.getWidth(), 0);
					chatSetBtn.setText("+");

					i = 0;
				}

			}
		});
		// Network
		// �̺�Ʈ ó����(�������� �޼��� ������ �۾�) ���
		ChattingListener listener = new ChattingListener();
		/////////////////////////////////////////////////////
		// �������� ����� ���� ��Ʈ��ũ ���� �κ�
		try {
			Socket socket = new Socket(InetAddress.getByName("70.12.115.56"), 5555);

			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// ������ ������ �Ŀ� �г��� �Է��ؼ� �����ϱ�
//			id = JOptionPane.showInputDialog(this, "��ȭ�� �Է��ϼ���.", JOptionPane.INFORMATION_MESSAGE);

			bw.write(id + "\n");
			bw.flush();

			// �г��� ���� �Ŀ��� ������ ������ �޼��� �޴� ������
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
					System.out.println("����ũ�� ���Ұ� �Ǿ����ϴ�.");
					i += 1;
				} else {
					System.out.println("����ũ�� Ȱ��ȭ �Ǿ����ϴ�.");
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

		userListPanel.add(user1);
		userListPanel.add(user2);

		leftBottomPanel.add(mic);

		leftTopPanel.add(backBtn, "West");
		leftPanel.add(leftTopPanel, "North");
		leftPanel.add(userListPanel, "Center");
		leftPanel.add(leftBottomPanel, "South");

		messagesAreaPanel.add(scrollFrame);
		typeAreaPanel.add(typeField);
		typeAreaPanel.add(chatSetBtn);

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
	// �̺�Ʈ ó�� Ŭ����(ä�ó��� �������� ������)

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

	// �����κ��� �޼����� �޴� ���� ������ Ŭ����
	class ListenThread extends Thread {
		@Override
		public void run() {
			try {
				while (true) {

					Thread.sleep(1000);

					String receiveMsg = br.readLine();
					StringTokenizer st = new StringTokenizer(receiveMsg);
					String nickPart = st.nextToken();
					String commandChecker = st.nextToken();

					String text = "";
					System.out.println(commandChecker);
					if (commandChecker.charAt(0) == '/') {
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

//	public static void main(String[] args) {
//		new ChatClient();
//	}
}
