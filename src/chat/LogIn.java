package chat;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogIn extends JFrame{
	private JPanel panel;
	private JPanel titlePn;
	private JPanel idPn;
	private JPanel pwPn;
	private JPanel accountPn;
	private JLabel titleLb;
	private JLabel idLb;
	private JLabel pwLb;
	private JTextField idField;
	private JTextField pwField;
	private String id="";
	private String pw="";
	
	private JButton signUpBtn = new JButton("회원가입");
	private final String MY_ID = "atalk";
	private final String MY_PW = "1234";
	
	public LogIn() {
		panel = new JPanel();
		titlePn = new JPanel();
		idPn = new JPanel();
		pwPn = new JPanel();
		accountPn = new JPanel();
		
		
//		ImageIcon facebook = new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\펭귄.jpg");
//		JLabel fbLabel = new JLabel();
//		fbLabel.setIcon(facebook);
//		
//		ImageIcon google = new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\수국.jpg");
//		JLabel ggLabel = new JLabel();
//		fbLabel.setIcon(google);
		
		idField = new JTextField(15);
		idField.setToolTipText("id:atalk pw:1234를 입력하면 로그인 됩니다.");
		pwField = new JTextField(15);
		
		titleLb = new JLabel("Atalk");
		Font font = new Font("돋움", Font.PLAIN, 100);
		
		titleLb.setFont(font);
		signUpBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SignUpDialog();
			}
		});
		
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
				if(e.getKeyChar()==e.VK_ENTER){
					if(idField.getText().equals(MY_ID) & pw.equals(MY_PW)){
						System.out.println("로그인 중...");
						dispose();
						RoomList roomList = new RoomList();
					}else{
						JDialog dialog = new JDialog();
						JPanel errorPanel = new JPanel();
						JButton check = new JButton("확인");
						JLabel message = new JLabel("아이디 혹은 비밀번호가 틀렸습니다. 다시 시도해주세요.");
						
						check.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								dialog.dispose();
							}
						});
						errorPanel.setLayout(new BorderLayout());
						
						errorPanel.add(message, "Center");
						errorPanel.add(check, "South");
	
						dialog.add(errorPanel);
						
						dialog.pack();
						dialog.setTitle("ERROR!!");
						dialog.setVisible(true);
					}
				}else if(e.getKeyChar()==e.VK_BACK_SPACE){
					pw=pw.substring(0, pw.length()-1);
					System.out.println("변경된 패스워드: "+pw);
				}else{
					String stars = "";

					pw+=e.getKeyChar();
					for(int i = 0 ; i < pw.length()-1; i++) stars+="*";
					
					pwField.setText(stars);
					System.out.println("추가된 패스워드: "+pw);
				}
			}
		});
		
		idLb = new JLabel("ID");
		pwLb = new JLabel("PW");
		
	    
		panel.setLayout(new GridLayout(4,1));
		titlePn.setLayout(null);
		idPn.setLayout(null);
		pwPn.setLayout(null);
		accountPn.setLayout(null);
		
		titleLb.setBounds(230, 20, 300, 110);
		idLb.setBounds(160, 50, 50, 50);
		idField.setBounds(200, 50, 300, 50);
		pwLb.setBounds(160, 10, 50, 50);
		pwField.setBounds(200, 10, 300, 50);
		signUpBtn.setBounds(200, 0, 100, 30);
		
		titlePn.add(titleLb);
		idPn.add(idLb);
		idPn.add(idField);
		pwPn.add(pwLb);
		pwPn.add(pwField);
		accountPn.add(signUpBtn);
		
//		anotherPanel.add(fbLabel);
//		anotherPanel.add(ggLabel);

		panel.add(titlePn);
		panel.add(idPn);
		panel.add(pwPn);
		panel.add(accountPn);
		
		add(panel);
		
		setTitle("ATalk");
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
	}
	public class SignUpDialog  extends JFrame{
		private JDialog set  = new JDialog(this,"Atalk 회원가입");
		private JPanel topPanel = new JPanel();
		private JPanel bottomPanel = new JPanel();
		
		private JLabel idLb = new JLabel("아이디");
		private JLabel pwLb = new JLabel("비밀번호");
		private JLabel nameLb = new JLabel("이름");
		private JLabel birthDateLb = new JLabel("생년월일");
		private JLabel emailLb = new JLabel("이메일");
		private JLabel phoneLb = new JLabel("전화번호");

		private JTextField idField = new JTextField(10);
		private JTextField pwField = new JTextField(10);
		private JTextField nameField = new JTextField(10);
		private JTextField birthDateField = new JTextField(10);
		private JTextField emailField = new JTextField(15);		
		private JTextField phoneField = new JTextField(15);
		
		private JButton confirmShowBtn = new JButton("약관 보기");
		private JCheckBox confirmCheck = new JCheckBox("약관 동의");
		
		private JButton okBtn = new JButton("가입");
		private JButton cancelBtn = new JButton("취소");
		public SignUpDialog () {

			topPanel.setLayout(new GridLayout(8,2));
//			bottomPanel.setLayout(new BoxLayout(bottomPanel, 1));
			
			topPanel.add(idLb);
			topPanel.add(idField);

			topPanel.add(pwLb);
			topPanel.add(pwField);

			topPanel.add(nameLb);
			topPanel.add(nameField);
			
			topPanel.add(birthDateLb);
			topPanel.add(birthDateField);
			
			topPanel.add(emailLb);
			topPanel.add(emailField);

			topPanel.add(phoneLb);
			topPanel.add(phoneField);

			topPanel.add(confirmShowBtn);
			topPanel.add(confirmCheck);

			topPanel.add(okBtn);
			topPanel.add(cancelBtn);

			// Function
			confirmShowBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("약관 내용 보이기");
				}
			});
			
			okBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(confirmCheck.isSelected()) {
						System.out.println("가입완료");
						hide();
					}else {
						System.out.println("동의하셔야합니다.");
					}
				}
			});
			
			cancelBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					hide();
				}
			});
			
			add(topPanel);
	//		add(panel);
			setTitle("Atalk 회원가입");
			setResizable(false);
			setSize(250, 300);
			setVisible(true);
			setDefaultCloseOperation(HIDE_ON_CLOSE);
		}
	}
}

