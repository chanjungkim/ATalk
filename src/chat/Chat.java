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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
	
	private JButton backBtn;
	private JTextField typeField;
	private JTextPane messageField;

	public Chat(){
		panel = new JPanel();
		userListPanel = new JPanel();
		messagesAreaPanel = new JPanel();
		messagesAreaPanel.setLayout(new BorderLayout());
		typeAreaPanel = new JPanel();
		leftTopPanel = new JPanel();
		leftBottomPanel = new JPanel();
		
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		
		backBtn = new JButton("<");
		typeField = new JTextField("Type");

		messageField = new JTextPane();
		// Function
		
		backBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Go to the previous frame.");
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

					System.out.println(typeField.getText());					
					
					if(commandChecker.charAt(0) =='/'){
						switch(commandChecker.substring(1, commandChecker.length())){
						case "help":
							System.out.println("need some help?");
							break;
						case "code":
							System.out.println("code mode.");
							break;
						}
					}

					typeField.setText("");
				}
			}
		});
		
		// Layout
		messageField.setLayout(new BorderLayout());
		messagesAreaPanel.setLayout(new BorderLayout());
		typeAreaPanel.setLayout(new BorderLayout());
		leftPanel.setLayout(new BorderLayout());
		rightPanel.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());

		// Change this area
		userListPanel.setBackground(Color.YELLOW);
		messageField.setBackground(Color.gray);

		// end
		
		// Size
		leftTopPanel.add(backBtn, "West");
		leftPanel.add(leftTopPanel, "North");
		leftPanel.add(userListPanel, "Center");
		leftPanel.add(leftBottomPanel, "South");

		messagesAreaPanel.add(messageField);
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
