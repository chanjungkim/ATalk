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

public class ProfileShow extends JFrame {
	public ProfileShow() {
		JDialog ProfileDialog = new JDialog();
		JPanel profilePanel = new JPanel();
		JButton githubBtn = new JButton(new ImageIcon("git.PNG"));
		JButton closeBtn = new JButton("´Ý±â");

		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProfileDialog.dispose();
			}
		});

		profilePanel.setLayout(null);
		githubBtn.setBounds(130, 120, 80, 80);
		closeBtn.setBounds(155, 220, 80, 40);
		profilePanel.add(githubBtn);
		profilePanel.add(closeBtn);
		ProfileDialog.add(profilePanel);

		ProfileDialog.setTitle("Profile");
		ProfileDialog.setSize(400, 300);
		setDefaultCloseOperation(ProfileDialog.EXIT_ON_CLOSE);
		ProfileDialog.setVisible(true);
	}

	public static void main(String[] args) {
		ProfileShow a = new ProfileShow();
	}
}
