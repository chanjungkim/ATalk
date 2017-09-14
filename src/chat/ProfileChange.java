package chat;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

public class ProfileChange extends JFrame {

	public ProfileChange(String id) {
		DbDao dao = new DbDao();
		JDialog ProfileDialog = new JDialog();
		JPanel profilePanel = new JPanel();
		JLabel introduceLb = new JLabel("�ڱ� �Ұ�");
		JLabel githubLb = new JLabel("github url");
		JLabel emailLb = new JLabel("�߰� E-Mail");
		JLabel pwLb = new JLabel("PW");
		JLabel phoneLb = new JLabel("PHONE");
		JTextArea introduceTextArea = new JTextArea();
		JTextField githubTextField = new JTextField(15);
		JTextField emailtextField = new JTextField(15);
		JTextField pwTextField = new JTextField(15);
		JTextField phoneTextField = new JTextField(15);
		JButton addBtn = new JButton("���");
		JButton closeBtn = new JButton("�ݱ�");

		introduceTextArea.setLineWrap(true);

		Border lineBorder = BorderFactory.createLineBorder(Color.black, 1);
		// �ؽ�Ʈ�� TextArea ��� ���̿� ������ �α� ���ؼ� emptyBorder�� �����մϴ�.
		Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
		// TextArea�� lineBorder(�����׵θ�), emptyBorder(����)�� ������ ���� ��輱�� �����մϴ�.
		introduceTextArea.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		githubTextField.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		emailtextField.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		pwTextField.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		phoneTextField.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));

		profilePanel.setLayout(null);
		introduceLb.setBounds(5, -5, 70, 50);
		introduceTextArea.setBounds(80, 10, 300, 100);
		githubLb.setBounds(5, 108, 70, 50);
		githubTextField.setBounds(80, 120, 300, 30);
		emailLb.setBounds(5, 150, 70, 50);
		emailtextField.setBounds(80, 160, 300, 30);
		pwLb.setBounds(5, 190, 70, 50);
		pwTextField.setBounds(80, 200, 300, 30);
		phoneLb.setBounds(5, 230, 70, 50);
		phoneTextField.setBounds(80, 240, 300, 30);
		addBtn.setBounds(105, 280, 80, 30);
		closeBtn.setBounds(205, 280, 80, 30);

		profilePanel.add(introduceLb);
		profilePanel.add(introduceTextArea);
		profilePanel.add(githubLb);
		profilePanel.add(githubTextField);
		profilePanel.add(emailLb);
		profilePanel.add(emailtextField);
		profilePanel.add(pwLb);
		profilePanel.add(pwTextField);
		profilePanel.add(phoneLb);
		profilePanel.add(phoneTextField);
		profilePanel.add(addBtn);
		profilePanel.add(closeBtn);
		ProfileDialog.add(profilePanel);

		addBtn.addActionListener(new ActionListener() { // ������ ��� ��ư
			@Override
			public void actionPerformed(ActionEvent e) {
				UserVO user = new UserVO();
				user.setIntroduce(introduceTextArea.getText());
				user.setGithub(githubTextField.getText());
				user.setOtherEmail(emailtextField.getText());
				user.setPw(pwTextField.getText());
				user.setPhone(phoneTextField.getText());
				user.setId(id);
				DbDao dao = new DbDao();
				dao.updateProfile(user);
				ProfileDialog.dispose();
			}
		});

		closeBtn.addActionListener(new ActionListener() { // ������ �ݱ� ��ư
			@Override
			public void actionPerformed(ActionEvent e) {
				ProfileDialog.dispose();
			}
		});
		ProfileDialog.setResizable(false);
		ProfileDialog.setTitle("ȸ������ ����");
		ProfileDialog.setSize(400, 350);
		setDefaultCloseOperation(ProfileDialog.DISPOSE_ON_CLOSE);
		ProfileDialog.setVisible(true);
	}
}
