package chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class Chat extends JFrame{
	private JPanel panel;
	private JPanel userListPanel;
	private JPanel messagesAreaPanel;
	private JPanel typeAreaPanel;
	private JPanel leftTopPanel;
	private JPanel leftBottomPanel;
	
	private JPanel leftPanel;
	private JPanel rightPanel;
	
	private JButton backBtn = new JButton("<");
	private JTextField typeField = new JTextField("Type");

	private JTextPane messageField = new JTextPane();
	private JScrollPane scrollFrame;
	
	private JButton user1 = new JButton("atalk");
	private JButton user2 = new JButton("USER-1");
	private JButton mic = new JButton("MIC");
	
	public Chat(){
		panel = new JPanel();
		userListPanel = new JPanel();
		messagesAreaPanel = new JPanel();
		scrollFrame =  new JScrollPane(messageField);
		typeAreaPanel = new JPanel();
		leftTopPanel = new JPanel();
		leftBottomPanel = new JPanel();
		
		leftPanel = new JPanel();
		leftPanel.setToolTipText("<는 뒤로가기를 뜻합니다. 현재 로그아웃처럼 로그인화면으로 빠져나갑니다. 가운데는 유저 리스트를 보여주고 맨 하단은 마이크 설정을 보여줍니다.");
		rightPanel = new JPanel();
		messageField.setToolTipText("메시지 창은 Edit할 수 없습니다. Typing하는 곳에 /help 혹은 /code을 치면 Bot이 대답합니다. 글이 많으면 스크롤이 활성화됩니다.");
		
		// Function
		messageField.setEditable(false);
		
		backBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Go to the previous frame.");
				dispose();
				RoomList roomList = new RoomList();
			}
		});
		
		typeField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				typeField.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		typeField.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()==e.VK_ENTER){
					StringTokenizer st = new StringTokenizer(typeField.getText());
					String commandChecker = st.nextToken();
					String text="";
					
					System.out.println(typeField.getText());					
					
					if(commandChecker.charAt(0) =='/'){
						switch(commandChecker.substring(1, commandChecker.length())){
						case "help":
							messageField.setText(messageField.getText()+"\nBot: need some help?\nThese commands are available now: \n/help: Show command list. \n/code ((none)/java/python): code mode(default is 'c'.) \n/sing: Bot sings\n/time: Show current time");
							System.out.println("need some help?");
							break;
						case "code":
							if(st.hasMoreTokens()){
								String type = st.nextToken();
								switch(type){
								case "java":
									messageField.setText(messageField.getText()+"\natalk:\n1: class Main{\n2:     public static void main(String[] args){\n3:       System.out.println(\"Hello, World!\");\n4:     }\n5: }");
									break;
								case "python":
									messageField.setText(messageField.getText()+"\natalk:\n1:print('Hello, World!')");
									break;
								default:
									messageField.setText(messageField.getText()+"\natalk:\n1:#include <stdio.h>\n2: \n3: int main(){\n4: printf(\"Hello, World!\");\n5: }");
									break;
								}
							}else{
								messageField.setText(messageField.getText()+"\natalk:\n1:#include <stdio.h>\n2: \n3: int main(){\n4: printf(\"Hello, World!\");\n5: }");
							}
							System.out.println("code mode.");
							break;
						case "time":
							Date dt = new Date();
							System.out.println(dt.toString());
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss a"); 
							
							messageField.setText(messageField.getText()+"\nBot: It's "+sdf.format(dt).toString());

							break;
						case "sing":
							messageField.setText(messageField.getText()+"\nBot: Row, row, row your boat, Gently down the stream, Merrily merrily, merrily, merrily Life is but a dream");
							break;
						default:
							messageField.setText(messageField.getText()+"\nBot: Incorrect command.");
						}
						
						if(st.hasMoreTokens()){
							while(st.hasMoreTokens()){
								text+=st.nextToken()+" ";
							}
							messageField.setText(messageField.getText()+"\natalk: "+text);
						}
					}else{
						messageField.setText(messageField.getText()+"\natalk: "+typeField.getText());
					}
					
					typeField.setText("");
				}
			}
		});
		
		mic.addActionListener(new ActionListener() {
			int i = 0;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(i == 0) {
					System.out.println("마이크가 음소거 되었습니다.");
					i+=1;
				}else {
					System.out.println("마이크가 활성화 되었습니다.");
					i=0;
				}
			}
		});
		
		// Layout
		messagesAreaPanel.setLayout(new BorderLayout());
		messageField.setLayout(new BorderLayout());
		userListPanel.setLayout(new BoxLayout(userListPanel, 1));
		typeAreaPanel.setLayout(new BorderLayout());
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
}
