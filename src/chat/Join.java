package chat;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Test02;

public class Join extends JFrame {

	JFrame register;

	JLabel idLabel = new JLabel("ID");
	JLabel pwLabel = new JLabel("PW");
	JLabel checkPwLabel = new JLabel("PW 확인");
	JLabel nameLabel = new JLabel("Name");
	JLabel emailLabel = new JLabel("e-mail");
	JLabel phoneLabel = new JLabel("연락처");
	
	JTextField id = new JTextField(15); 
	JPasswordField pw = new JPasswordField(15); 
	JPasswordField checkPw = new JPasswordField(15);
	JTextField name = new JTextField(15);
	JTextField email = new JTextField(15);
	JTextField phone = new JTextField(15);
	
	JButton registerBtn = new JButton("회원가입"); 
	JButton cancelBtn = new JButton("취소");

	JPanel informationField = new JPanel();
	JPanel buttonField = new JPanel();

	public Join() {
			register = new JFrame("회원가입");
			
			informationField.setLayout(new GridLayout(0, 2));
			informationField.add(idLabel);
			informationField.add(id);
			informationField.add(pwLabel);
			informationField.add(pw);
			informationField.add(checkPwLabel);
			informationField.add(checkPw);
			informationField.add(nameLabel);
			informationField.add(name);
			informationField.add(emailLabel);
			informationField.add(email);
			informationField.add(phoneLabel);
			informationField.add(phone);
			
			cancelBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					register.setVisible(false);
					dispose();
				}
			});
			
			registerBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					/*if(id.getText() != null &&
							pw.getText() != null &&
							checkPw.getText() != null &&
							name.getText() != null ) {*/
						Test02 test = new Test02(id.getText(), id.getText(), name.getText(),email.getText(), phone.getText());
				//	}
					
				}
			});
			
			
			
			buttonField.add(registerBtn);
			
			
			buttonField.add(cancelBtn);
			
			register.add(informationField,BorderLayout.PAGE_START);
			register.add(buttonField,BorderLayout.PAGE_END);
			
			register.setSize(300, 400);
			register.setVisible(true);
			register.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
		}

	public static void main(String[] args) {
		Join join = new Join();
	}
}
