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
import javax.swing.JTextPane;

import image.MyDrawing;

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

	public JButton backBtn = new JButton("<");

	private JTextPane messageField = new JTextPane();
	private JScrollPane scrollFrame;

	private JTextField typeField = new JTextField("Type");
	
	private JButton compileBtn = new JButton("compile");
	private JButton drawingBtn = new JButton("drawing");
	private JButton emoticonBtn = new JButton("emoticon");
	
	private JButton user1;
	private JButton user2 = new JButton("USER-1");
	private JButton mic = new JButton("MIC");

	private BufferedReader br;
	private BufferedWriter bw;

	private String id;
	private image.MyDrawing drawPane;
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
		leftPanel.setToolTipText("<占쏙옙 占쌘로곤옙占썩를 占쏙옙占쌌니댐옙. 占쏙옙占쏙옙 占싸그아울옙처占쏙옙 占싸깍옙占쏙옙화占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占싹댐옙. 占쏙옙占쏘데占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙트占쏙옙 占쏙옙占쏙옙占쌍곤옙 占쏙옙 占싹댐옙占쏙옙 占쏙옙占쏙옙크 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쌥니댐옙.");
		rightPanel = new JPanel();
		messageField
				.setToolTipText("占쌨쏙옙占쏙옙 창占쏙옙 Edit占쏙옙 占쏙옙 占쏙옙占쏙옙占싹댐옙. Typing占싹댐옙 占쏙옙占쏙옙 /help 혹占쏙옙 /code占쏙옙 치占쏙옙 Bot占쏙옙 占쏙옙占쏙옙爛求占�. 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙크占쏙옙占쏙옙 활占쏙옙화占싯니댐옙.");

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

		compileBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				compile.CompileFrame com = new compile.CompileFrame();
			}
		});
		drawingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//image.MultiChattingServer serv = new image.MultiChattingServer();
			
				
			}
		});;
		emoticonBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});;
		
		
		
		// Network
		// 占싱븝옙트 처占쏙옙占쏙옙(占쏙옙占쏙옙占쏙옙占쏙옙 占쌨쇽옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쌜억옙) 占쏙옙占�
		ChattingListener listener = new ChattingListener();
		/////////////////////////////////////////////////////
		// 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙트占쏙옙크 占쏙옙占쏙옙 占싸븝옙

		try {
			Socket socket = new Socket(InetAddress.getByName("70.12.115.61"), 5555);
			//image.MyDrawing 
			drawPane = new MyDrawing();
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占식울옙 占싻놂옙占쏙옙 占쌉뤄옙占쌔쇽옙 占쏙옙占쏙옙占싹깍옙
//			id = JOptionPane.showInputDialog(this, "占쏙옙화占쏙옙 占쌉뤄옙占싹쇽옙占쏙옙.", JOptionPane.INFORMATION_MESSAGE);

			bw.write(id + "\n");
			bw.flush();

			// 占싻놂옙占쏙옙 占쏙옙占쏙옙 占식울옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쌨쇽옙占쏙옙 占쌨댐옙 占쏙옙占쏙옙占쏙옙
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
					System.out.println("占쏙옙占쏙옙크占쏙옙 占쏙옙占쌀곤옙 占실억옙占쏙옙占싹댐옙.");
					i += 1;
				} else {
					System.out.println("占쏙옙占쏙옙크占쏙옙 활占쏙옙화 占실억옙占쏙옙占싹댐옙.");
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
		typeAreaPanel.add(compileBtn);
		typeAreaPanel.add(drawingBtn);
		typeAreaPanel.add(emoticonBtn);

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
	// 占싱븝옙트 처占쏙옙 클占쏙옙占쏙옙(채占시놂옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙)

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

	// 占쏙옙占쏙옙占싸븝옙占쏙옙 占쌨쇽옙占쏙옙占쏙옙 占쌨댐옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 클占쏙옙占쏙옙
	class ListenThread extends Thread {
		@Override
		public void run() {
			try {
				while (true) {

					Thread.sleep(1000);

					String receiveMsg = br.readLine();
					StringTokenizer st = new StringTokenizer(receiveMsg);
					String nickPart;
					String commandChecker="";
					if(receiveMsg.isEmpty()) {
						nickPart = id+": "; //혹占쏙옙 채占쏙옙 占쏙옙占쎈보占쏙옙占쏙옙 占쏙옙占쏙옙占시울옙 id占싫놂옙占쏙옙占쏙옙 占싹곤옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쌕꾸몌옙占�		
					}else {
						nickPart= st.nextToken();
						if(st.hasMoreTokens()) {
							commandChecker = st.nextToken();
						}
					}
					String text = "";
					System.out.println(commandChecker);
					if (commandChecker.isEmpty()!=true && commandChecker.charAt(0) == '/') {
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
