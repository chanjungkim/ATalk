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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

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

	private JButton signUpBtn = new JButton("Sign up");

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
				} 
//				else if (e.getKeyChar() == e.VK_BACK_SPACE) {
//					pw = pw.substring(0, pw.length() - 1);
//					System.out.println("aaaa: " + pw);
//				}
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
		private JDialog set = new JDialog(this, "Atalk Sign up");
		private JPanel topPanel = new JPanel();
		private JPanel bottomPanel = new JPanel();

		private JLabel idLb = new JLabel("ID");
		private JLabel pwLb = new JLabel("PW");
		private JLabel nameLb = new JLabel("Name");
		private JLabel birthDayLb = new JLabel("Birthday");
		private JLabel emailLb = new JLabel("E-mail");
		private JLabel phoneLb = new JLabel("Phone");

		private JTextField idField = new JTextField(10);
		private JTextField pwField = new JTextField(10);
		private JTextField nameField = new JTextField(10);
		private JTextField birthDayField = new JTextField(10);
		private JTextField emailField = new JTextField(15);
		private JTextField phoneField = new JTextField(15);

		private JButton confirmShowBtn = new JButton("Terms of Use");
		private JCheckBox confirmCheck = new JCheckBox("Agree");
		private JButton okBtn = new JButton("Finish");
		private JButton cancelBtn = new JButton("Cancel");

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
					JDialog dialog = new JDialog();
					JPanel errorPanel = new JPanel();
					JButton check = new JButton("OK");

					JTextPane message = new JTextPane();
					JScrollPane scroll = new JScrollPane(message);
					scroll.createVerticalScrollBar();
					scroll.setWheelScrollingEnabled(true);
					message.setText("These Terms of Service (“Terms”) govern your access to and use of our services, including our various websites, SMS, APIs, email notifications, applications, buttons, widgets, ads, commerce services, and our other covered services (https://support.twitter.com/articles/20172501) that link to these Terms (collectively, the “Services”), and any information, text, links, graphics, photos, videos, or other materials or arrangements of materials uploaded, downloaded or appearing on the Services (collectively referred to as “Content”). By using the Services you agree to be bound by these Terms.\r\n" + 
							"1. Who May Use the Services\r\n" + 
							"\r\n" + 
							"2. Privacy\r\n" + 
							"\r\n" + 
							"3. Content on the Services\r\n" + 
							"\r\n" + 
							"4. Using the Services\r\n" + 
							"\r\n" + 
							"5. Disclaimers and Limitations of Liability\r\n" + 
							"\r\n" + 
							"6. General\r\n" + 
							"\r\n" + 
							"1. Who May Use the Services\r\n" + 
							"You may use the Services only if you agree to form a binding contract with Twitter and are not a person barred from receiving services under the laws of the applicable jurisdiction. In any case, you must be at least 13 years old to use the Services. If you are accepting these Terms and using the Services on behalf of a company, organization, government, or other legal entity, you represent and warrant that you are authorized to do so.\r\n" + 
							"2. Privacy\r\n" + 
							"Our Privacy Policy (https://www.twitter.com/privacy) describes how we handle the information you provide to us when you use our Services. You understand that through your use of the Services you consent to the collection and use (as set forth in the Privacy Policy) of this information, including the transfer of this information to the United States, Ireland, and/or other countries for storage, processing and use by Twitter and its affiliates.");

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

					dialog.setSize(800, 600);
					dialog.setTitle("Terms of Use");
					dialog.setVisible(true);
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
							JButton check = new JButton("OK");
							JLabel message = new JLabel("Please fill out the form.");

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
						JDialog dialog = new JDialog();
						JPanel errorPanel = new JPanel();
						JButton check = new JButton("OK");
						JLabel message = new JLabel("You should check 'agree' to sign up.");

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
			});

			cancelBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					hide();
				}
			});

			add(topPanel);
			// add(panel);
			setTitle("Atalk Sign up");
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