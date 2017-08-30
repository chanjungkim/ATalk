package chat;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RoomList extends JFrame{
	private JPanel panel;
	private JPanel topPanel;
	private JPanel listPanel;
	private JButton currentRoom;
	private JButton roomList;
	private JButton setting;
	private JButton room;
	
	public RoomList(){
		panel = new JPanel();
		topPanel = new JPanel();
		listPanel = new JPanel();
		currentRoom = new JButton("���� ��");
		roomList = new JButton("���"); 
		setting = new JButton("����");
		room = new JButton("�ȵ���̵� ���͵� ��");

		
		// Fucntion
		room.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Chat chat = new Chat();
			}
		});
		
		setting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Setting set = new Setting();
				set.logoutBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int t = JOptionPane.showConfirmDialog(null, "�� ������ �α׾ƿ� �Ͻðڽ��ϱ�?","",JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if(t==0) {
							System.out.println("Go to the previous: frame.");
							set.dispose();
							dispose();
							LogIn logInFrame = new LogIn();
						}
					}
				});
			}
		});
		
		// Layout
		panel.setLayout(new BoxLayout(panel, 1));
		
		// 
		topPanel.add(roomList);
		topPanel.add(currentRoom);
		topPanel.add(setting);
		listPanel.add(room);		
		
		panel.add(topPanel);
		panel.add(listPanel);
		
		add(panel);
		
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
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
				
			set.add(alarmPn);
			set.add(backgroundPn);
			set.add(blackBtn);
			set.add(logoutBtn);

			
			set.setSize(200, 200);
			set.setVisible(true);
			setDefaultCloseOperation(set.EXIT_ON_CLOSE);
		}
	}
}
