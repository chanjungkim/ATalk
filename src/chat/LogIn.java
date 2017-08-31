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
	private JPanel namePanel;
	private JPanel accountPanel;
	private JPanel anotherPanel;
	private JLabel lb;
	private JTextField idField;
	private JTextField pwField;
	private String id="";
	private String pw="";
	
	private JButton signUpBtn = new JButton("ȸ������");
	private final String MY_ID = "atalk";
	private final String MY_PW = "1234";
	
	public LogIn() {
		panel = new JPanel();
		namePanel = new JPanel();
		accountPanel = new JPanel();
		anotherPanel = new JPanel();

//		ImageIcon facebook = new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\���.jpg");
//		JLabel fbLabel = new JLabel();
//		fbLabel.setIcon(facebook);
//		
//		ImageIcon google = new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\����.jpg");
//		JLabel ggLabel = new JLabel();
//		fbLabel.setIcon(google);
		
		idField = new JTextField(15);
		idField.setToolTipText("id:atalk pw:1234�� �Է��ϸ� �α��� �˴ϴ�.");
		pwField = new JTextField(15);
		
		lb = new JLabel("Atalk");
		Font font = new Font("����", Font.PLAIN, 100);
		
		lb.setFont(font);
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
						System.out.println("�α��� ��...");
						dispose();
						RoomList roomList = new RoomList();
					}else{
						JDialog dialog = new JDialog();
						JPanel errorPanel = new JPanel();
						JButton check = new JButton("Ȯ��");
						JLabel message = new JLabel("���̵� Ȥ�� ��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �õ����ּ���.");
						
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
					System.out.println("����� �н�����: "+pw);
				}else{
					String stars = "";

					pw+=e.getKeyChar();
					for(int i = 0 ; i < pw.length()-1; i++) stars+="*";
					
					pwField.setText(stars);
					System.out.println("�߰��� �н�����: "+pw);
				}
			}
		});
		
		accountPanel.setLayout(new BoxLayout(accountPanel, 1));
		panel.setLayout(new BorderLayout());

		namePanel.add(lb);

		accountPanel.add(idField);
		accountPanel.add(pwField);
		accountPanel.add(signUpBtn);
		
//		anotherPanel.add(fbLabel);
//		anotherPanel.add(ggLabel);

		panel.add(namePanel, "North");
		panel.add(accountPanel, "Center");
		panel.add(anotherPanel, "South");
		
		add(panel);
		
		setTitle("ATalk");
		
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
	}
	public class SignUpDialog  extends JFrame{
		private JDialog set  = new JDialog(this,"Atalk ȸ������");
		private JPanel topPanel = new JPanel();
		private JPanel bottomPanel = new JPanel();
		
		private JLabel idLb = new JLabel("���̵�");
		private JLabel pwLb = new JLabel("��й�ȣ");
		private JLabel nameLb = new JLabel("�̸�");
		private JLabel birthDateLb = new JLabel("�������");
		private JLabel emailLb = new JLabel("�̸���");
		private JLabel phoneLb = new JLabel("��ȭ��ȣ");

		private JTextField idField = new JTextField(10);
		private JTextField pwField = new JTextField(10);
		private JTextField nameField = new JTextField(10);
		private JTextField birthDateField = new JTextField(10);
		private JTextField emailField = new JTextField(15);		
		private JTextField phoneField = new JTextField(15);
		
		private JButton confirmShowBtn = new JButton("��� ����");
		private JCheckBox confirmCheck = new JCheckBox("��� ����");
		
		private JButton okBtn = new JButton("����");
		private JButton cancelBtn = new JButton("���");
		public SignUpDialog () {

			topPanel.setLayout(new GridLayout(7,2));
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

			topPanel.add(birthDateLb);
			topPanel.add(birthDateField);

			topPanel.add(confirmShowBtn);
			topPanel.add(confirmCheck);

			topPanel.add(okBtn);
			topPanel.add(cancelBtn);

			add(topPanel);
	//		add(panel);
			setSize(250, 300);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	}
}

