package chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProfileChange extends JFrame {
	public ProfileChange() {
		JDialog ProfileDialog = new JDialog();
		JPanel profilePanel = new JPanel();
		JLabel introduceLb = new JLabel("자기 소개");
		JLabel githubLb = new JLabel("github url");
		JLabel emailLb = new JLabel("E-Mail");
		JTextField introduceTextField = new JTextField(50);
		JTextField githubTextField = new JTextField(15);
		JTextField emailtextField = new JTextField(15);
		JButton addBtn = new JButton("등록");
		JButton closeBtn = new JButton("닫기");
		
		profilePanel.setLayout(null);
		introduceLb.setBounds(10, 5, 50, 50);
		introduceTextField.setBounds(70, 5, 100, 100);
		githubLb.setBounds(10, 105, 50, 50);
		githubTextField.setBounds(70, 105, 100, 100);
		emailLb.setBounds(10, 205, 50, 50);
		emailtextField.setBounds(70, 205, 50, 50);

		profilePanel.add(introduceLb);
		profilePanel.add(introduceTextField);
		profilePanel.add(githubLb);
		profilePanel.add(githubTextField);
		profilePanel.add(emailLb);
		profilePanel.add(emailtextField);
		ProfileDialog.add(profilePanel);


		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProfileDialog.dispose();
			}
		});
		
		ProfileDialog.setTitle("프로필 수정");
		ProfileDialog.setSize(400, 300);
		setDefaultCloseOperation(ProfileDialog.EXIT_ON_CLOSE);
		ProfileDialog.setVisible(true);
	}

	public static void main(String[] args) {
		ProfileShow a = new ProfileShow();
	}
}
