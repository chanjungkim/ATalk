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

public class ChatClient extends JFrame{
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
	
	private JButton user1 = new JButton("atalk");
	private JButton user2 = new JButton("USER-1");
	private JButton mic = new JButton("MIC");
	
    private BufferedReader br;
    private BufferedWriter bw;
	
    private String nickname;
    
	public ChatClient(){
		panel = new JPanel();
		userListPanel = new JPanel();
		messagesAreaPanel = new JPanel();
		scrollFrame =  new JScrollPane(messageField);
		typeAreaPanel = new JPanel();
		leftTopPanel = new JPanel();
		leftBottomPanel = new JPanel();
		chatSetPanel = new JPanel();
		
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

			}
		});
		chatSetBtn.addActionListener(new ActionListener() {
			int i = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(i==0) {
					messagesAreaPanel.setSize(messagesAreaPanel.getWidth(), messagesAreaPanel.getHeight()-5);
					//rightPanel.add(chatSetPanel, "Center");
					chatSetPanel.setSize(messagesAreaPanel.getWidth(), 5);
					chatSetBtn.setText("이모티콘");
					Dialog imo=new Dialog();
					i = 1;
				}
				else{
					messagesAreaPanel.setSize(messagesAreaPanel.getWidth(), messagesAreaPanel.getHeight()+5);
					//rightPanel.add(chatSetPanel, "Center");
					chatSetPanel.setSize(messagesAreaPanel.getWidth(), 0);
					chatSetBtn.setText("+");
					
				
					i = 0;
				}
				
			}
		});
		// Network
        // 이벤트 처리기(서버에게 메세지 보내는 작업) 등록
        ChattingListener listener = new ChattingListener();
        /////////////////////////////////////////////////////
        // 서버와의 통신을 위한 네트워크 설정 부분
        try {
            Socket socket = new Socket(InetAddress.getByName("70.12.115.56"),5555);
             
            bw = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            // 서버와 연결한 후에 닉네임 입력해서 전송하기
            nickname = JOptionPane.showInputDialog
                    (this, "대화명 입력하세요.",JOptionPane.INFORMATION_MESSAGE);
             
            bw.write(nickname+"\n");
            bw.flush(); 
             
            // 닉네임 전송 후에는 서버가 보내는 메세지 받는 쓰레드
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
	
    // 이벤트 처리 클래스(채팅내용 서버에게 보내기)
    class ChattingListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String msg = typeField.getText();
            typeField.setText("");
             
            try {
                bw.write(msg+"\n");
                bw.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    
    // 서버로부터 메세지를 받는 내부 쓰레드 클래스
    class ListenThread extends Thread{
        @Override
        public void run() {
            try {
                while (true) {
                	
                	Thread.sleep(1000);
                	
                    String receiveMsg = br.readLine();
                    StringTokenizer st = new StringTokenizer(receiveMsg);
					String nickPart = st.nextToken();
					String commandChecker = st.nextToken();
					
					String text="";
					System.out.println(commandChecker);
					if(commandChecker.charAt(0) =='/'){
						switch(commandChecker.substring(1, commandChecker.length())){
						case "help":
							messageField.setText(messageField.getText()+"\nBot: need some help?\nThese commands are available now: \n/help: Show command list. \n/code ((none)/java/python): code mode(default is 'c'.) \n/sing: Bot sings\n/time: Show current time\n");
							System.out.println("need some help?\n");
							break;
						case "code":
							if(st.hasMoreTokens()){
								String type = st.nextToken();
								switch(type){
								case "java":
									messageField.setText(messageField.getText()+"\n"+nickPart+"\n1: class Main{\n2:     public static void main(String[] args){\n3:       System.out.println(\"Hello, World!\");\n4:     }\n5: }\n");
									break;
								case "python":
									messageField.setText(messageField.getText()+"\n"+nickPart+"\n1:print('Hello, World!')\n");
									break;
								default:
									messageField.setText(messageField.getText()+"\n1"+nickPart+"\n1:#include <stdio.h>\n2: \n3: int main(){\n4: printf(\"Hello, World!\");\n5: }\n");
									break;
								}
							}else{
								messageField.setText(messageField.getText()+"\n"+nickPart+"\n1:#include <stdio.h>\n2: \n3: int main(){\n4: printf(\"Hello, World!\");\n5: }\n");
							}
							System.out.println("code mode.");
							break;
						case "time":
							Date dt = new Date();
							System.out.println(dt.toString());
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss a"); 
							
							messageField.setText(messageField.getText()+"\nBot: It's "+sdf.format(dt).toString()+"\n");

							break;
						case "sing":
							messageField.setText(messageField.getText()+"\nBot: Row, row, row your boat, Gently down the stream, Merrily merrily, merrily, merrily Life is but a dream\n");
							break;
						default:
							messageField.setText(messageField.getText()+"\nBot: Incorrect command.\n");
						}
						
						if(st.hasMoreTokens()){
							while(st.hasMoreTokens()){
								text+=st.nextToken()+" ";
							}
							messageField.setText(messageField.getText()+text + "\n");
						}
					}else{
	                    messageField.setText(messageField.getText()+receiveMsg + "\n");
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
 
    public static void main(String[] args) {
    	new ChatClient();
	}
}
