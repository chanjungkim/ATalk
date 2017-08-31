package chat;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RoomList extends JFrame {
	private JPanel panel;
	private JPanel topPanel;
	private JPanel listPanel;
	private JButton currentRoom;
	private JButton roomList;
	private JButton setting;
	private JButton room;

	public RoomList() {
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
				ChatClient chat = new ChatClient();
			}
		});

		////////////////////////////////////////////////////////////////////////
		// ����â ����
		setting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Setting set = new Setting();
				set.logoutBtn.addActionListener(new ActionListener() { // �α׾ƿ� ��ư
					@Override
					public void actionPerformed(ActionEvent e) {
						int t = JOptionPane.showConfirmDialog(null, "�� ������ �α׾ƿ� �Ͻðڽ��ϱ�?", "",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (t == 0) {
							System.out.println("Go to the previous: frame.");
							set.dispose();
							dispose();
							LogIn logInFrame = new LogIn();
						}
					}
				});

				set.blackBtn.addActionListener(new ActionListener() { // ������Ʈ ��ư
					JFrame blackFm = new JFrame();
					JPanel BtnPn = new JPanel();
					JLabel nameLb = new JLabel("�г���");
					JTextField nameText = new JTextField(7);
					JButton addBtn = new JButton("�߰�");
					JButton delBtn = new JButton("����");
					List nameList = new List();

					@Override
					public void actionPerformed(ActionEvent e) {

						addBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String name = nameText.getText();
								nameList.add(name);
								nameText.setText(""); // �ؽ�Ʈ �ʵ峻�� �����
								nameText.requestFocus(); // �ؽ�Ʈ �ʵ忡 ��Ŀ�� �ֱ�
							}
						});
						delBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								int pos = nameList.getSelectedIndex(); // namelist�� ������ �׸��� �ε����� ������

								if (pos == -1) {
									JOptionPane.showMessageDialog(blackFm, "�������ּ���!");
									return;
								}
								nameList.remove(pos);
							}
						});

						BtnPn.add(nameLb);
						BtnPn.add(nameText);
						BtnPn.add(addBtn);
						BtnPn.add(delBtn);

						blackFm.add(BtnPn, BorderLayout.NORTH);
						blackFm.add(nameList, BorderLayout.CENTER);

						blackFm.setTitle("������Ʈ");
						blackFm.setSize(300, 400);
						setDefaultCloseOperation(blackFm.EXIT_ON_CLOSE); // �� â�� ����
						blackFm.setVisible(true);
					}
				});

				set.alarmBtn.addActionListener(new ActionListener() { // �˶� ��ư
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

					}
				});

				set.colorBtn.addActionListener(new ActionListener() { // ���� ���� ��ư
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

					}
				});

			}
		});
		////////////////////////////////////////////////////////////////////////////////////

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

	public class Setting extends JFrame {
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
			set = new JDialog(this, "����");
			alarmPn = new JPanel();
			backgroundPn = new JPanel();
			alarmLb = new JLabel("�˶�");
			backgroundLb = new JLabel("��� ��");
			alarmBtn = new JButton("�ѱ�");
			colorBtn = new JButton("����");
			blackBtn = new JButton("������Ʈ");
			logoutBtn = new JButton("�α׾ƿ�");

			set.setLayout(new GridLayout(4, 1));
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
