package chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
		rightPanel = new JPanel();
		
		// Function
		backBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Go to the previous frame.");
				dispose();
				LogIn logInFrame = new LogIn();
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
							messageField.setText(messageField.getText()+"\nBot: need some help?");
							System.out.println("need some help?");
							break;
						case "code":
							messageField.setText(messageField.getText()+"\nBot:\n1: class Main{\n2:     public static void main(String[] args){\n3:       System.out.println(\"Hello, World!\");\n4:    }\n6: }");
							System.out.println("code mode.");
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
		messageField.setBackground(Color.gray);

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
		
		leftBottomPanel.setSize(500, 800);
		typeAreaPanel.setSize(500, 800);
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
