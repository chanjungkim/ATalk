package chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Profile {
	public Profile() {
		JDialog ProfileDialog = new JDialog();
		JPanel profilePanel = new JPanel();
		JButton checkBtn = new JButton("´Ý±â");
		

		checkBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProfileDialog.dispose();
			}
		});
		profilePanel.setLayout(new BorderLayout());



		ProfileDialog.setTitle("Profile");
		setDefaultCloseOperation(ProfileDialog.EXIT_ON_CLOSE);
		ProfileDialog.setVisible(true);
	}

	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}
}
