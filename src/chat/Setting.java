package chat;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.login.LoginContext;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Setting  extends JFrame{
	private JDialog set;
	private JPanel alarmPn;
	private JPanel backgroundPn;
	private JLabel alarmLb;
	private JLabel backgroundLb;
	private JButton alarmBtn;
	private JButton colorBtn;
	private JButton blackBtn;
	private JButton logoutBtn;

	public Setting() {
		set = new JDialog(this,"설정");
		alarmPn = new JPanel();
		backgroundPn = new JPanel();
		alarmLb = new JLabel("알람");
		backgroundLb = new JLabel("배경 색");
		alarmBtn = new JButton("켜기");
		colorBtn = new JButton("색깔");
		blackBtn = new JButton("블랙리스트");
		logoutBtn = new JButton("로그아웃");

		set.setLayout(new GridLayout(4,1));
		alarmPn.setLayout(new BorderLayout());
		backgroundPn.setLayout(new BorderLayout());

		alarmPn.add(alarmLb, BorderLayout.WEST);
		alarmPn.add(alarmBtn, BorderLayout.EAST);
		backgroundPn.add(backgroundLb, BorderLayout.WEST);
		backgroundPn.add(colorBtn, BorderLayout.EAST);
		
		logoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int t = JOptionPane.showConfirmDialog(null, "이 계정을 로그아웃 하시겠습니까?","",JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if(t==0) {
					System.out.println("Go to the previous: frame.");
					dispose();
					LogIn logInFrame = new LogIn();
				}
			}
			});
			
		set.add(alarmPn);
		set.add(backgroundPn);
		set.add(blackBtn);
		set.add(logoutBtn);

		
		set.setSize(200, 200);
		set.setVisible(true);
		setDefaultCloseOperation(set.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Setting s = new Setting();
	}
}
