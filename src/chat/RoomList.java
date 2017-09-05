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
	private JPanel listPanel;
	private JButton createRoomBtn;
	private JButton roomList;
	private JButton settingBtn;
	private JButton room;

	private JPanel panel1, panel2;

	private JButton menu;
	private ImageIcon image, image2, image3;
	private JLabel count;
	private JLabel master;


	
	public RoomList() {
		panel = new JPanel();
		listPanel = new JPanel();

		roomList = new JButton("���");
		room = new JButton("�ȵ���̵� ���͵� ��");

		createRoomBtn = new JButton(new ImageIcon("��ǳ��.PNG")); // �����
		menu = new JButton(new ImageIcon("�޴�.png"));
		settingBtn = new JButton(new ImageIcon("����.PNG")); // ����

		// Fucntion
		room.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ChatClient chat = new ChatClient();
			}
		});// End of Function

		// Setting Dialogue
		settingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SettingDialogue settingDialogue = new SettingDialogue();

				settingDialogue.logoutBtn.addActionListener(new ActionListener() { // �α׾ƿ� ��ư
					@Override
					public void actionPerformed(ActionEvent e) {
						int t = JOptionPane.showConfirmDialog(null, "�� ������ �α׾ƿ� �Ͻðڽ��ϱ�?", "",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (t == 0) {
							System.out.println("Go to the previous: frame.");
							settingDialogue.dispose();
							dispose();
							LogIn logInFrame = new LogIn();
						}
					}
				});

				settingDialogue.blackBtn.addActionListener(new ActionListener() { // ������Ʈ ��ư
					JFrame blackFm = new JFrame();
					JPanel bottomPn = new JPanel();
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

						bottomPn.add(nameLb);
						bottomPn.add(nameText);
						bottomPn.add(addBtn);
						bottomPn.add(delBtn);

						blackFm.add(bottomPn, BorderLayout.NORTH);
						blackFm.add(nameList, BorderLayout.CENTER);

						blackFm.setTitle("������Ʈ");
						blackFm.setSize(300, 400);
						setDefaultCloseOperation(blackFm.EXIT_ON_CLOSE); // �� â�� ����
						blackFm.setVisible(true);
					}
				});

				settingDialogue.alarmBtn.addActionListener(new ActionListener() { // �˶� ��ư
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

					}
				});

				settingDialogue.colorBtn.addActionListener(new ActionListener() { // ���� ���� ��ư
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

					}
				});

			}
		});
		// End of Setting Dialogue
		
		// Create Room Dialogue
		createRoomBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateRoomDialogue createRoomDialogue = new CreateRoomDialogue();
				createRoomDialogue.createBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(createRoomDialogue.titleField.getText().isEmpty()) {
							JDialog dialog = new JDialog();
							JPanel errorPanel = new JPanel();
							JButton check = new JButton("Ȯ��");
							JLabel message = new JLabel("������ �Է����ּ���.");
							
							check.addActionListener(new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent e) {
									dialog.dispose();
								}
							});
							errorPanel.setLayout(new BorderLayout());
							
							errorPanel.add(message, "Center");
							errorPanel.add(check, "South");
		
							dialog.add(errorPanel);
							
							dialog.pack();
							dialog.setTitle("ERROR!!");
							dialog.setVisible(true);
						}else if(createRoomDialogue.passwordField.getText().isEmpty() && createRoomDialogue.checkBtn.isSelected()){
							JDialog dialog = new JDialog();
							JPanel errorPanel = new JPanel();
							JButton check = new JButton("Ȯ��");
							JLabel message = new JLabel("��й�ȣ�� �Է����ּ���.");
							
							check.addActionListener(new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent e) {
									dialog.dispose();
								}
							});
							errorPanel.setLayout(new BorderLayout());
							
							errorPanel.add(message, "Center");
							errorPanel.add(check, "South");
		
							dialog.add(errorPanel);
							
							dialog.pack();
							dialog.setTitle("ERROR!!");
							dialog.setVisible(true);
						}else {
							panel.add(room);
							createRoomDialogue.hide();
							repaint();
						}
					}
				});
				
				createRoomDialogue.cancelBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						createRoomDialogue.hide();
					}
				});
			}
		});
		// End of Create Room Dialogue


		
		// Layout
		EtchedBorder eborder;
		eborder = new EtchedBorder(EtchedBorder.LOWERED);

		createRoomBtn.setBounds(0, 0, 80, 80);
		menu.setBounds(300, 0, 80, 80);
		settingBtn.setBounds(600, 0, 80, 80);
		
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

		panel.setLayout(null);

		panel.add(roomList);
		panel.add(createRoomBtn);
		panel.add(settingBtn);
		panel.add(master);
		panel.add(count);

		panel.add(menu);

		add(panel);

		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	// <--------------------- Inner Classes -------------------------->
	class CreateRoomDialogue extends JFrame implements ItemListener { //�� ����
		private JDialog createRoomDialogue = new JDialog(this, "�� �����");
		private JLabel roomTitleLb = new JLabel("����");
		private JTextField titleField;
		
		private JLabel passwordLb = new JLabel("��� ��ȣ");
		private JTextField passwordField;
		
		private JLabel populationLb = new JLabel("�ο�");
		private JComboBox<Integer> population; 
		
		private JPanel topPn;
		private JPanel titleTPn;
		private JPanel passwordPn;
		private JPanel populationPn;
		private JPanel bottomPn;
		private JCheckBox checkBtn;
		private JButton createBtn = new JButton("����");
		private JButton cancelBtn = new JButton("���");
		
		public CreateRoomDialogue() {               
			topPn = new JPanel();
			titleTPn = new JPanel();
			passwordPn = new JPanel();
			populationPn = new JPanel();
			bottomPn = new JPanel();
			titleField = new JTextField(15);
			passwordField = new JTextField(15);
			
			
			checkBtn = new JCheckBox();	
			Integer numberList[] = {1, 2, 3, 4, 5, 10};   
			population = new JComboBox<>(numberList); // �� �ο���
			population.setEnabled(true);
			
			checkBtn.addItemListener(this);
//			population.addActionListener(new ActionListener() { //�ο� �� ���� �̺�Ʈ
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					int num = (int) ((JComboBox)e.getSource()).getSelectedItem();
//				}
//			});

			// Layout
			createRoomDialogue.setLayout(new GridLayout(5, 1, 0, 10));
			titleTPn.setLayout(null);
			passwordPn.setLayout(null);
			populationPn.setLayout(null);
			passwordField.setBackground(Color.decode("#AAAAAA"));
			
			roomTitleLb.setBounds(0, -10, 50, 50);
			titleField.setBounds(60,0,280,30);
			passwordLb.setBounds(0, -10, 55, 50);
			passwordField.setBounds(60, 0, 280, 30);
			checkBtn.setBounds(345, -30, 100, 100);
			populationLb.setBounds(0, -10, 50, 50);
			population.setBounds(60,0,50,30);
			
			passwordField.setEnabled(false);
			
			titleTPn.add(roomTitleLb);
			titleTPn.add(titleField);
			passwordPn.add(passwordLb);
			passwordPn.add(passwordField);
			passwordPn.add(checkBtn);
			populationPn.add(populationLb);
			populationPn.add(population);
			bottomPn.add(createBtn);
			bottomPn.add(cancelBtn);

			createRoomDialogue.add(topPn);
			createRoomDialogue.add(titleTPn);
			createRoomDialogue.add(passwordPn);
			createRoomDialogue.add(populationPn);
			createRoomDialogue.add(bottomPn);
			
			createRoomDialogue.setSize(400, 250);
			createRoomDialogue.setVisible(true);
			setDefaultCloseOperation(createRoomDialogue.EXIT_ON_CLOSE);
			// End of Layout
		} // End of CreateRoom Constructor
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			Object source = e.getItemSelectable();
			if(source == checkBtn) {
				passwordField.setEnabled(true);
				passwordField.setBackground(Color.white);
			}
			if(e.getStateChange() == ItemEvent.DESELECTED) {
				passwordField.setEnabled(false);
				passwordField.setBackground(Color.decode("#AAAAAA"));
			}		
		}
	}

	class SettingDialogue extends JFrame {
		private JDialog set;
		private JPanel alarmPn;
		private JPanel backgroundPn;
		private JLabel alarmLb;
		private JLabel backgroundLb;
		private JButton alarmBtn;
		private JButton colorBtn;
		private JButton blackBtn;
		private JButton logoutBtn;

		public SettingDialogue() {
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
