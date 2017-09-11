package chat;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ProfileShow extends JFrame {
	public ProfileShow(String id) {
		JDialog ProfileDialog = new JDialog();
		JPanel profilePanel = new JPanel();
		JLabel nameTLb = new JLabel("이름:");
		JLabel nameLb = new JLabel();
		JLabel birthTLb = new JLabel("생일:");
		JLabel birthLb = new JLabel();
		JLabel emailTLb = new JLabel("이메일:");
		JLabel emailLb = new JLabel();
		JLabel phoneTLb = new JLabel("전화번호:");
		JLabel phoneLb = new JLabel();
		JLabel introduceTLb = new JLabel("자기소개:");
		JTextArea introduceTextArea = new JTextArea();
		JButton githubBtn = new JButton(new ImageIcon("git.PNG"));
		JLabel otherEmailTLb = new JLabel("추가 이메일:");
		JLabel otherEmailLb = new JLabel();
		JButton closeBtn = new JButton("닫기");
		DbDao dao = new DbDao();

		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProfileDialog.dispose();
			}
		});

		UserVO memberInfo = new UserVO();
		memberInfo = dao.selectProfile(id);

		introduceTextArea.setLineWrap(true);
		introduceTextArea.setColumns(20);
		introduceTextArea.setRows(20);

		nameLb.setText(memberInfo.getName());
		birthLb.setText(memberInfo.getBirth());
		emailLb.setText(memberInfo.geteMail());
		phoneLb.setText(memberInfo.getPhone());
		introduceTextArea.setText(memberInfo.getIntroduce());
		otherEmailLb.setText(memberInfo.getOtherEmail());
		String url = memberInfo.getGithub();

		introduceTextArea.setEditable(false);

		profilePanel.setLayout(null);
		nameTLb.setBounds(8, 5, 50, 30);
		nameLb.setBounds(43, 5, 200, 30);
		birthTLb.setBounds(8, 30, 50, 30);
		birthLb.setBounds(43, 30, 200, 30);
		emailTLb.setBounds(8, 55, 50, 30);
		emailLb.setBounds(55, 55, 200, 30);
		otherEmailTLb.setBounds(8, 80, 80, 30);
		otherEmailLb.setBounds(82, 80, 200, 30);
		phoneTLb.setBounds(8, 105, 55, 30);
		phoneLb.setBounds(65, 105, 200, 30);
		introduceTLb.setBounds(8, 130, 55, 30);
		introduceTextArea.setBounds(65, 136, 320, 90);
		githubBtn.setBounds(300, 10, 80, 80);
		closeBtn.setBounds(155, 233, 80, 40);

		githubBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (url.contains("http://")) {
					openWebpage(url);
				} else {
					openWebpage("http://"+url);
				}
			}
		});

		profilePanel.add(nameTLb);
		profilePanel.add(nameLb);
		profilePanel.add(birthTLb);
		profilePanel.add(birthLb);
		profilePanel.add(emailTLb);
		profilePanel.add(emailLb);
		profilePanel.add(phoneTLb);
		profilePanel.add(phoneLb);
		profilePanel.add(introduceTLb);
		profilePanel.add(introduceTextArea);
		profilePanel.add(otherEmailTLb);
		profilePanel.add(otherEmailLb);
		profilePanel.add(githubBtn);
		profilePanel.add(closeBtn);
		profilePanel.setBackground(Color.yellow);
		ProfileDialog.add(profilePanel);

		ProfileDialog.setUndecorated(true);
		ProfileDialog.setLocation(400, 300);
		ProfileDialog.setTitle("Profile");
		ProfileDialog.setSize(400, 280);
		ProfileDialog.setVisible(true);
	}

	public static void openWebpage(String urlString) {
		try {
			Desktop.getDesktop().browse(new URL(urlString).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ProfileShow a = new ProfileShow("1");
	}
}
