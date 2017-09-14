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

public class ChatClient extends JFrame {
	private JPanel panel;
	private JPanel titlePn;
	private JPanel idPn;
	private JPanel pwPn;
	private JPanel accountPn;
	private JLabel titleLb;
	private JLabel idLb;
	private JLabel pwLb;
	private JTextField userinField;
	private JPasswordField passwordField;
	private String id = "";
	private String pw = "";

	private JButton signUpBtn = new JButton("�쉶�썝媛��엯");

	public ChatClient() {
		DbDao dao = new DbDao();
		panel = new JPanel();
		titlePn = new JPanel();
		idPn = new JPanel();
		pwPn = new JPanel();
		accountPn = new JPanel();

		userinField = new JTextField(15);
		passwordField = new JPasswordField(15);

		titleLb = new JLabel("Atalk");
		Font font = new Font("Sign Up", Font.PLAIN, 100);

		titleLb.setFont(font);
		signUpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SignUpDialog();
			}
		});

		userinField.setText("Username");
		userinField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (userinField.getText().equals("Username")) {
					userinField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {

				if (userinField.getText().equals("")) {
					userinField.setText("Username");
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
					if(dao.userCheck(userinField.getText(), new String(passwordField.getPassword()))==1) {
						dispose();
					}
				} else if (e.getKeyChar() == e.VK_BACK_SPACE) {
					pw = pw.substring(0, pw.length() - 1);
					System.out.println("蹂�寃쎈맂 �뙣�뒪�썙�뱶: " + pw);
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
		userinField.setBounds(200, 50, 300, 50);
		pwLb.setBounds(160, 10, 50, 50);
		passwordField.setBounds(200, 10, 300, 50);
		signUpBtn.setBounds(200, 0, 100, 30);

		titlePn.add(titleLb);
		idPn.add(idLb);
		idPn.add(userinField);
		pwPn.add(pwLb);
		pwPn.add(passwordField);
		accountPn.add(signUpBtn);

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
		private JDialog set = new JDialog(this, "Atalk �쉶�썝媛��엯");
		private JPanel topPanel = new JPanel();
		private JPanel bottomPanel = new JPanel();

		private JLabel idLb = new JLabel("�븘�씠�뵒");
		private JLabel pwLb = new JLabel("鍮꾨�踰덊샇");
		private JLabel nameLb = new JLabel("�씠由�");
		private JLabel birthDayLb = new JLabel("�깮�뀈�썡�씪");
		private JLabel emailLb = new JLabel("�씠硫붿씪");
		private JLabel phoneLb = new JLabel("�쟾�솕踰덊샇");

		private JTextField idField = new JTextField(10);
		private JTextField pwField = new JTextField(10);
		private JTextField nameField = new JTextField(10);
		private JTextField birthDayField = new JTextField(10);
		private JTextField emailField = new JTextField(15);
		private JTextField phoneField = new JTextField(15);

		private JButton confirmShowBtn = new JButton("�빟愿� 蹂닿린");
		private JCheckBox confirmCheck = new JCheckBox("�빟愿� �룞�쓽");
		private JButton okBtn = new JButton("媛��엯");
		private JButton cancelBtn = new JButton("痍⑥냼");

		UserVO user = null;
		DbDao dao = new DbDao();

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
					System.out.println("�빟愿� �궡�슜 蹂댁씠湲�");
				}
			});

			okBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (confirmCheck.isSelected()) {
						user = new UserVO();
						user.setId(idField.getText());
						user.setPw(pwField.getText());
						user.setName(nameField.getText());
						user.setBirth(birthDayField.getText());
						user.seteMail(emailField.getText());
						user.setPhone(phoneField.getText());

						if (idField.getText().equals("") || pwField.getText().equals("") || nameField.getText().equals("")
								|| birthDayField.getText().equals("") || emailField.getText().equals("")
								|| phoneField.getText().equals("")) {
							JDialog dialog = new JDialog();
							JPanel errorPanel = new JPanel();
							JButton check = new JButton("�솗�씤");
							JLabel message = new JLabel("�쟾遺� �옉�꽦�빐二쇱꽭�슂");

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
						} else {
							int check = dao.insertUserInfo(user);
							if (check == 1) {
								hide();
							}
						}

					} else {
						System.out.println("�룞�쓽�븯�뀛�빞�빀�땲�떎.");
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
			setTitle("Atalk �쉶�썝媛��엯");
			setResizable(false);
			setSize(250, 300);
			setVisible(true);
			setDefaultCloseOperation(HIDE_ON_CLOSE);
		}
	}
	public static void main(String[] args) {
		new ChatClient();
	}
}