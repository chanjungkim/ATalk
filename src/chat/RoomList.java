package chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class RoomList extends JFrame {
	private JPanel panel;
	private JPanel topPanel;
	private JPanel listPanel;
	private JButton makeRoom;
	private JButton roomList;
	private JButton setting;
	private JButton room;

	private JPanel panel1, panel2;

	private JButton menu;
	private ImageIcon image, image2, image3;
	private JLabel count;
	private JLabel master;

	public RoomList() {
		panel = new JPanel();
		topPanel = new JPanel();
		listPanel = new JPanel();
		

		roomList = new JButton("���");
		room = new JButton("�ȵ���̵� ���͵� ��");

		makeRoom = new JButton(new ImageIcon("��ǳ��.PNG")); // �����
		menu = new JButton(new ImageIcon("�޴�.png"));
		setting = new JButton(new ImageIcon("����.PNG")); // ����

		makeRoom.setBounds(0, 0, 80, 80);
		menu.setBounds(300, 0, 80, 80);
		setting.setBounds(600, 0, 80, 80);

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

		///////////////////////////////////////////////////////////////////////////////////
		// �� �����

		makeRoom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RoomMake roomMake = new RoomMake();
			}
		});

		//////////////////////////////////////////////////////////////////////////////////

		// Layout

		EtchedBorder eborder;
		eborder = new EtchedBorder(EtchedBorder.LOWERED);

		room.setBorder(eborder);
		room.setBounds(0, 80, 350, 60);
		room.setFont(new Font("����", Font.BOLD, 25));
		
		master = new JLabel("����:");
		master.setBorder(eborder);
		master.setBounds(350, 80, 200, 60);
		master.setFont(new Font("����", Font.BOLD, 25));
		
		count = new JLabel("�ο�:");
		count.setBorder(eborder);
		count.setBounds(550, 80, 150, 60);
		count.setFont(new Font("����", Font.BOLD, 25));


		//

		panel.setLayout(null);

		panel.add(room);
		panel.add(roomList);
		panel.add(makeRoom);
		panel.add(setting);
		panel.add(master);
		panel.add(count);

		panel.add(menu);

		add(panel);

		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	// Layout
	public class RoomMake extends JFrame implements ItemListener { //�� ����
		private JDialog roomMk;
		private JLabel titleLb;
		private JLabel roomTitleLb;
		private JLabel passwordLb;
		private JLabel peopleNLb;
		private JPanel titlePn;
		private JPanel titleTPn;
		private JPanel passwordPn;
		private JPanel peopleNumPn;
		private JPanel btnPn;
		private JTextField titleText;
		private JTextField passwordText;
		private JButton makeBtn;
		private JButton cancleBtn;
		private JComboBox peopleNum; 
		private JCheckBox checkBtn;

		public RoomMake() {               
			roomMk = new JDialog();
			titleLb = new JLabel("�� �����");
			roomTitleLb = new JLabel("����");
			passwordLb= new JLabel("��� ��ȣ");
			peopleNLb = new JLabel("�ο�");
			titlePn = new JPanel();
			titleTPn = new JPanel();
			passwordPn = new JPanel();
			peopleNumPn = new JPanel();
			btnPn = new JPanel();
			titleText = new JTextField(15);
			passwordText = new JTextField(15);
			makeBtn = new JButton("����");
			cancleBtn = new JButton("���");
			checkBtn = new JCheckBox();
			
		
			String numberList[] = {"1","2","3","4","5","6","7","8"};   
			peopleNum = new JComboBox(numberList); // �� �ο���
			peopleNum.setEnabled(true);
			peopleNum.addActionListener(new ActionListener() { //�ο� �� ���� �̺�Ʈ
				@Override
				public void actionPerformed(ActionEvent e) {
					int num = (int) ((JComboBox)e.getSource()).getSelectedItem();
				}
			});

			roomMk.setLayout(new GridLayout(5, 1, 0, 10));
			titleTPn.setLayout(null);
			passwordPn.setLayout(null);
			peopleNumPn.setLayout(null);
			titleLb.setBackground(Color.YELLOW);
			
			roomTitleLb.setBounds(0, -10, 50, 50);
			titleText.setBounds(60,0,280,30);
			passwordLb.setBounds(0, -10, 55, 50);
			passwordText.setBounds(60, 0, 280, 30);
			checkBtn.setBounds(345, -30, 100, 100);
			peopleNLb.setBounds(0, -10, 50, 50);
			peopleNum.setBounds(60,0,50,30);
			
			passwordText.setEnabled(false);
			
			checkBtn.addItemListener(this);

			titlePn.add(titleLb);
			titleTPn.add(roomTitleLb);
			titleTPn.add(titleText);
			passwordPn.add(passwordLb);
			passwordPn.add(passwordText);
			passwordPn.add(checkBtn);
			peopleNumPn.add(peopleNLb);
			peopleNumPn.add(peopleNum);
			btnPn.add(makeBtn);
			btnPn.add(cancleBtn);

			roomMk.add(titlePn);
			roomMk.add(titleTPn);
			roomMk.add(passwordPn);
			roomMk.add(peopleNumPn);
			roomMk.add(btnPn);

			roomMk.setSize(400, 250);
			roomMk.setVisible(true);
			setDefaultCloseOperation(roomMk.EXIT_ON_CLOSE);
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			Object source = e.getItemSelectable();
			if(source == checkBtn) {
				passwordText.setEnabled(true);
			}
			if(e.getStateChange() == ItemEvent.DESELECTED) {
				passwordText.setEnabled(false);
			}		
		}
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
