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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogIn extends JFrame {
	private JPanel panel;
	private JPanel titlePn;
	private JPanel idPn;
	private JPanel pwPn;
	private JPanel accountPn;
	private JLabel titleLb;
	private JLabel idLb;
	private JLabel pwLb;
	private JTextField loginField;
	private JPasswordField passwordField;
	private String id = "";
	private String pw = "";

	private JButton signUpBtn = new JButton("ȸ������");

	public LogIn() {
		LoginDao dao = new LoginDao();
		panel = new JPanel();
		titlePn = new JPanel();
		idPn = new JPanel();
		pwPn = new JPanel();
		accountPn = new JPanel();

		// ImageIcon facebook = new ImageIcon("C:\\Users\\Public\\Pictures\\Sample
		// Pictures\\���.jpg");
		// JLabel fbLabel = new JLabel();
		// fbLabel.setIcon(facebook);
		//
		// ImageIcon google = new ImageIcon("C:\\Users\\Public\\Pictures\\Sample
		// Pictures\\����.jpg");
		// JLabel ggLabel = new JLabel();
		// fbLabel.setIcon(google);

		loginField = new JTextField(15);
		passwordField = new JPasswordField(15);

		titleLb = new JLabel("Atalk");
		Font font = new Font("����", Font.PLAIN, 100);

		titleLb.setFont(font);
		signUpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SignUpDialog();
			}
		});

		loginField.setText("Username");
		loginField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (loginField.getText().equals("Username")) {
					loginField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {

				if (loginField.getText().equals("")) {
					loginField.setText("Username");
				}
			}
		});

		passwordField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == e.VK_ENTER) {
<<<<<<< HEAD
=======

>>>>>>> d69dc146addd88d5499d07e6cfad93806e8f2c88
/////////////////////////////////////////////////////////////////////////////////////////////////////////
////�α��� ���� ��� ���� �� (�ݺ������� ��Ŭ���� �󿡼� ����Ʈ�� �̿��Ͽ� ��)				
//					for (int x = 0; x < dao.loginList.size(); x++) {  
//						if (dao.loginList.get(x).getId().equals(loginField.getText())
//								&& dao.loginList.get(x).getPw().equals(new String(passwordField.getPassword()))) {
//							System.out.println("�α��� ��...");
//							dispose();
//							RoomList roomList = new RoomList();
//							break;
//						} else if(x == dao.loginList.size() - 1
//                            && dao.loginList.get(x).getPw() != new String(passwordField.getPassword())){
//							JDialog dialog = new JDialog();
//							JPanel errorPanel = new JPanel();
//							JButton check = new JButton("Ȯ��");
//							JLabel message = new JLabel("���̵� Ȥ�� ��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �õ����ּ���.");
//
//							check.addActionListener(new ActionListener() {
//								@Override
//								public void actionPerformed(ActionEvent e) {
//									dialog.dispose();
//								}
//							});
//							errorPanel.setLayout(new BorderLayout());
//
//							errorPanel.add(message, "Center");
//							errorPanel.add(check, "South");
//
//							dialog.add(errorPanel);
//
//							dialog.pack();
//							dialog.setTitle("ERROR!!");
//							dialog.setVisible(true);
//						}
//					}
//////////////////////////////////////////////////////////////////////////////////////////////////////////
					//�Ʒ��� ��� �󿡼� �� ���� ����ϴ� �α��� ���� ���
					dao.loginConnect(loginField.getText(), new String(passwordField.getPassword()));
<<<<<<< HEAD
=======

					// Temporary Pass
					dispose();
					new RoomList();
					//End of Temporary Pass;
					for (int x = 0; x < dao.loginList.size(); x++) {
						System.out.println(dao.loginList.get(x).getId()+"/"+loginField.getText());
						System.out.println(dao.loginList.get(x).getPw()+"/"+new String(passwordField.getPassword()));
						System.out.println("--");
						if (dao.loginList.get(x).getId().equals(loginField.getText())
								&& dao.loginList.get(x).getPw().equals(new String(passwordField.getPassword()))) {
							System.out.println("�α��� ��...");
							dispose();
							RoomList roomList = new RoomList();
						} else if(x == dao.loginList.size() - 1
                            && dao.loginList.get(x).getPw() != new String(passwordField.getPassword())){
							JDialog dialog = new JDialog();
							JPanel errorPanel = new JPanel();
							JButton check = new JButton("Ȯ��");
							JLabel message = new JLabel("���̵� Ȥ�� ��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �õ����ּ���.");

							check.addActionListener(new ActionListener() {
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
					}

>>>>>>> d69dc146addd88d5499d07e6cfad93806e8f2c88
				} else if (e.getKeyChar() == e.VK_BACK_SPACE) {
					pw = pw.substring(0, pw.length() - 1);
					System.out.println("����� �н�����: " + pw);
				} 
			}
		});

		idLb = new JLabel("ID");
		pwLb = new JLabel("PW");

		panel.setLayout(new GridLayout(4, 1));
		titlePn.setLayout(null);
		idPn.setLayout(null);
		pwPn.setLayout(null);
		accountPn.setLayout(null);

		titleLb.setBounds(230, 20, 300, 110);
		idLb.setBounds(160, 50, 50, 50);
		loginField.setBounds(200, 50, 300, 50);
		pwLb.setBounds(160, 10, 50, 50);
		passwordField.setBounds(200, 10, 300, 50);
		signUpBtn.setBounds(200, 0, 100, 30);

		titlePn.add(titleLb);
		idPn.add(idLb);
		idPn.add(loginField);
		pwPn.add(pwLb);
		pwPn.add(passwordField);
		accountPn.add(signUpBtn);

		// anotherPanel.add(fbLabel);
		// anotherPanel.add(ggLabel);

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

	public class SignUpDialog extends JFrame {
		private JDialog set = new JDialog(this, "Atalk ȸ������");
		private JPanel topPanel = new JPanel();
		private JPanel bottomPanel = new JPanel();

		private JLabel idLb = new JLabel("���̵�");
		private JLabel pwLb = new JLabel("��й�ȣ");
		private JLabel nameLb = new JLabel("�̸�");
		private JLabel birthDayLb = new JLabel("�������");
		private JLabel emailLb = new JLabel("�̸���");
		private JLabel phoneLb = new JLabel("��ȭ��ȣ");

		private JTextField idField = new JTextField(10);
		private JTextField pwField = new JTextField(10);
		private JTextField nameField = new JTextField(10);
		private JTextField birthDayField = new JTextField(10);
		private JTextField emailField = new JTextField(15);
		private JTextField phoneField = new JTextField(15);

		private JButton confirmShowBtn = new JButton("��� ����");
		private JCheckBox confirmCheck = new JCheckBox("��� ����");
		private JButton okBtn = new JButton("����");
		private JButton cancelBtn = new JButton("���");

		LoginVO log = null;
		LoginDao dao = new LoginDao();

		public SignUpDialog() {
			topPanel.setLayout(new GridLayout(8, 2));
			// bottomPanel.setLayout(new BoxLayout(bottomPanel, 1));

			topPanel.add(idLb);
			topPanel.add(idField);

			topPanel.add(pwLb);
			topPanel.add(pwField);

			topPanel.add(nameLb);
			topPanel.add(nameField);

			topPanel.add(birthDayLb);
			topPanel.add(birthDayField);

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
					System.out.println("��� ���� ���̱�");
				}
			});

			okBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (confirmCheck.isSelected()) {
						log = new LoginVO();
						log.setId(idField.getText());
						log.setPw(pwField.getText());
						log.setName(nameField.getText());
						log.setBirth(birthDayField.getText());
						log.seteMail(emailField.getText());
						log.setPhone(phoneField.getText());

						dao.insertLogin(log);
						hide();
					} else {
						System.out.println("�����ϼž��մϴ�.");
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
			// add(panel);
			setTitle("Atalk ȸ������");
			setResizable(false);
			setSize(250, 300);
			setVisible(true);
			setDefaultCloseOperation(HIDE_ON_CLOSE);
		}
	}
}



