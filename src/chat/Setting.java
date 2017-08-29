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
		set = new JDialog(this,"����");
		alarmPn = new JPanel();
		backgroundPn = new JPanel();
		alarmLb = new JLabel("�˶�");
		backgroundLb = new JLabel("��� ��");
		alarmBtn = new JButton("�ѱ�");
		colorBtn = new JButton("����");
		blackBtn = new JButton("������Ʈ");
		logoutBtn = new JButton("�α׾ƿ�");

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
				int t = JOptionPane.showConfirmDialog(null, "�� ������ �α׾ƿ� �Ͻðڽ��ϱ�?","",JOptionPane.OK_CANCEL_OPTION,
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
