package chat;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogIn extends JFrame{
	private JPanel panel;
	private JPanel namePanel;
	private JPanel accountPanel;
	private JPanel anotherPanel;
	private JLabel lb;
	private JTextField idField;
	private JTextField pwField;
	private String id;
	private String pw;
	
	public LogIn() {
		panel = new JPanel();
		namePanel = new JPanel();
		accountPanel = new JPanel();
		anotherPanel = new JPanel();
		
		ImageIcon facebook = new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\Æë±Ï.jpg");
		JLabel fbLabel = new JLabel();
		fbLabel.setIcon(facebook);
		
		ImageIcon google = new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\¼ö±¹.jpg");
		JLabel ggLabel = new JLabel();
		fbLabel.setIcon(google);
		
		idField = new JTextField(15);
		pwField = new JTextField(15);
		
		lb = new JLabel("Atalk");
		Font font = new Font("µ¸¿ò", Font.PLAIN, 100);
		
		lb.setFont(font);
			
		idField.setText("Username");
		idField.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					if(idField.getText().equals("Username")){
						idField.setText("");
					}
				}
	
				@Override
				public void focusLost(FocusEvent e) {
					
					if(idField.getText().equals("")) {
						idField.setText("Username");
				}
			}
		});
		
		pwField.setText("Password");
		pwField.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					if(pwField.getText().equals("Password")){
						pwField.setText("");
					}
				}
	
				@Override
				public void focusLost(FocusEvent e) {
					
					if(pwField.getText().equals("")) {
						pwField.setText("Password");
					}
				}
		});
		
		pwField.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode()==e.VK_ENTER){
					// Try to Log in
				}else if(e.getKeyCode()==e.VK_ESCAPE){
					pw.substring(0, pw.length()-1);
				}else{
					// change it to star
				}
			}
		});
		
		accountPanel.setLayout(new BoxLayout(accountPanel, 1));
		panel.setLayout(new BoxLayout(panel, 1));

		namePanel.add(lb);
		accountPanel.add(idField);
		accountPanel.add(pwField);
		anotherPanel.add(fbLabel);
		anotherPanel.add(ggLabel);
		
		panel.add(anotherPanel);
		panel.add(namePanel);
		panel.add(accountPanel);
		
		add(panel);
		
		setTitle("ATalk");
		
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
	}
}
