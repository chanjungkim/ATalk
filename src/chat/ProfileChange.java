package chat;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ProfileChange extends JFrame {
	public ProfileChange() {
		DbDao dao = new DbDao();
		JDialog ProfileDialog = new JDialog();
		JPanel profilePanel = new JPanel();
		JLabel introduceLb = new JLabel("�ڱ� �Ұ�");
		JLabel githubLb = new JLabel("github url");
		JLabel emailLb = new JLabel("�߰� E-Mail");
		JTextArea introduceTextArea = new JTextArea();
		JTextField githubTextField = new JTextField(15);
		JTextField emailtextField = new JTextField(15);
		JButton addBtn = new JButton("���");
		JButton closeBtn = new JButton("�ݱ�");
		
		Border lineBorder = BorderFactory.createLineBorder(Color.black, 1);
	    // �ؽ�Ʈ�� TextArea ��� ���̿� ������ �α� ���ؼ� emptyBorder�� �����մϴ�. 
	    Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
	    //TextArea�� lineBorder(�����׵θ�), emptyBorder(����)�� ������ ���� ��輱�� �����մϴ�.
	    introduceTextArea.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
	    githubTextField.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
	    emailtextField.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		
		profilePanel.setLayout(null);
		introduceLb.setBounds(5, -5, 70, 50);
		introduceTextArea.setBounds(80, 10, 300, 100);
		githubLb.setBounds(5, 108, 70, 50);
		githubTextField.setBounds(80, 120, 300, 30);
		emailLb.setBounds(5, 150, 70, 50);
		emailtextField.setBounds(80, 160, 300, 30);
		addBtn.setBounds(105, 220, 80, 30);
		closeBtn.setBounds(205, 220, 80, 30);
		profilePanel.add(introduceLb);
		profilePanel.add(introduceTextArea);
		profilePanel.add(githubLb);
		profilePanel.add(githubTextField);
		profilePanel.add(emailLb);
		profilePanel.add(emailtextField);
		profilePanel.add(addBtn);
		profilePanel.add(closeBtn);
		ProfileDialog.add(profilePanel);
		
		addBtn.addActionListener(new ActionListener() {  //������ ��� ��ư
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});

		closeBtn.addActionListener(new ActionListener() { //������ �ݱ� ��ư
			@Override
			public void actionPerformed(ActionEvent e) {
				ProfileDialog.dispose();
			}
		});
		
		ProfileDialog.setTitle("������ ����");
		ProfileDialog.setSize(400, 300);
		setDefaultCloseOperation(ProfileDialog.EXIT_ON_CLOSE);
		ProfileDialog.setVisible(true);
	}

	public static void main(String[] args) {
		ProfileChange a = new ProfileChange();
	}
}
