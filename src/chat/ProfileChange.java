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
		JLabel introduceLb = new JLabel("자기 소개");
		JLabel githubLb = new JLabel("github url");
		JLabel emailLb = new JLabel("추가 E-Mail");
		JTextArea introduceTextArea = new JTextArea();
		JTextField githubTextField = new JTextField(15);
		JTextField emailtextField = new JTextField(15);
		JButton addBtn = new JButton("등록");
		JButton closeBtn = new JButton("닫기");
		
		Border lineBorder = BorderFactory.createLineBorder(Color.black, 1);
	    // 텍스트와 TextArea 경계 사이에 여백을 두기 위해서 emptyBorder를 생성합니다. 
	    Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
	    //TextArea에 lineBorder(검정테두리), emptyBorder(여백)로 구성된 복합 경계선을 설정합니다.
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
		
		addBtn.addActionListener(new ActionListener() {  //프로필 등록 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});

		closeBtn.addActionListener(new ActionListener() { //프로필 닫기 버튼
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
		ProfileChange a = new ProfileChange();
	}
}
