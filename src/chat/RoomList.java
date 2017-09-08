package chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class RoomList extends JFrame {
	private JPanel panel;
	private JPanel menuPanel;
	private JPanel listPanel;
	private JScrollPane scrollFrame;
	
	private JButton createRoomBtn;
	private JButton roomListBtn;
	private JButton settingBtn;
	
	private ArrayList<RoomPanel> room = new ArrayList<>();

	private JLabel 	master = new JLabel("����:");
	private JLabel count = new JLabel("�ο�:");
	
	public RoomList(String id) {
		panel = new JPanel();
		menuPanel = new JPanel();
		menuPanel.setBackground(Color.DARK_GRAY);
		listPanel = new JPanel();
		listPanel.setBackground(Color.GREEN);
		scrollFrame = new JScrollPane(listPanel);
		scrollFrame.enable(true);

		roomListBtn = new JButton();

		createRoomBtn = new JButton(new ImageIcon("balloon.PNG")); // �����
		roomListBtn = new JButton(new ImageIcon("menu.png"));
		settingBtn = new JButton(new ImageIcon("setting.PNG")); // ����

		listPanel.setAutoscrolls(true);
		listPanel.setLayout(new FlowLayout());
		panel.setLayout(new BorderLayout());
		DbDao user = new DbDao();

//		EtchedBorder eborder;
//		eborder = new EtchedBorder(EtchedBorder.LOWERED);
		
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

								String idToBlock = nameText.getText();
								
								user.blockUser(id, idToBlock);
								
								nameList.add(idToBlock);
								
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
							// Start Dialog
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
							// Dialog
						}else {
							DbDao roomDao = new DbDao(1);

							String title = createRoomDialogue.getTitleField();
							String masterID = id;
							int population = createRoomDialogue.getPopulation();
							String lang = createRoomDialogue.getLanguage();
							String pw = createRoomDialogue.getPasswordField();
							
							RoomVO roomVo = new RoomVO(title, masterID, population, lang, pw);
							roomDao.insertRoomInfo(roomVo);
							
							room.add(new RoomPanel(title, masterID, population, lang, pw));
							for(int i = 0 ; i < room.size(); i++) {
								listPanel.add(room.get(i));
								System.out.println(room.size());
								
								room.get(i).roomBtn.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent arg0) {
										ChatClient chat = new ChatClient(id, masterID);
										setVisible(false);
										chat.backBtn.addActionListener(new ActionListener() {
												@Override
												public void actionPerformed(ActionEvent e) {
													roomDao.deleteRoom(roomVo);
												}
										});
									}
								});// End of Function
							}
							
//							panel.add(listPanel);
//							add(panel);
							// Fucntion

							validate();

							ChatClient chatRoom = new ChatClient(id, masterID);
							createRoomDialogue.hide();
							dispose();
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
		
		roomListBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		// End of Create Room Dialogue
		
		// Add Panels
		menuPanel.add(createRoomBtn);
		menuPanel.add(roomListBtn);
		menuPanel.add(settingBtn);
		
		//scrollFrame.add(listPanel);
		panel.add(menuPanel, BorderLayout.NORTH);
		panel.add(listPanel);
		
		add(panel);
		
		setSize(700, 500);
		setResizable(false);
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
		
		private JLabel langaugeLb = new JLabel("���");
		private JComboBox<String> language;
		
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
			Integer numberList[] = {1, 2, 3, 4, 5, 6};   
			population = new JComboBox<>(numberList); // �� �ο���
			String languageList[] = {"JAVA","C","C++","C#","X"};
			language = new JComboBox<>(languageList);
			population.setEnabled(true);
			language.setEnabled(true);
			
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
			langaugeLb.setBounds(140, -10, 50, 50);
			language.setBounds(180, 0, 80, 30);
			
			passwordField.setEnabled(false);
			
			titleTPn.add(roomTitleLb);
			titleTPn.add(titleField);
			passwordPn.add(passwordLb);
			passwordPn.add(passwordField);
			passwordPn.add(checkBtn);
			populationPn.add(populationLb);
			populationPn.add(population);
			populationPn.add(langaugeLb);
			populationPn.add(language);
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
		
		
		public String getTitleField() {
			return titleField.getText();
		}

		public String getPasswordField() {
			return passwordField.getText();
		}
		
		public int getPopulation() {
			return Integer.parseInt(population.getSelectedItem().toString());
		}
		
		public String getLanguage() {
			String lang = language.getSelectedItem().toString();
			if(lang.isEmpty()) {
				lang = "JAVA";
			}
			return lang;
		}

//		public void setPopulation(JComboBox<Integer> population) {
//			this.population = population;
//		}
		
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
		private JButton profileChangeBtn;
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
			profileChangeBtn = new JButton("������ ����");
			logoutBtn = new JButton("�α׾ƿ�");

			set.setLayout(new GridLayout(5, 1));
			alarmPn.setLayout(new BorderLayout());
			backgroundPn.setLayout(new BorderLayout());

			alarmPn.add(alarmLb, BorderLayout.WEST);
			alarmPn.add(alarmBtn, BorderLayout.EAST);
			backgroundPn.add(backgroundLb, BorderLayout.WEST);
			backgroundPn.add(colorBtn, BorderLayout.EAST);

			set.add(alarmPn);
			set.add(backgroundPn);
			set.add(profileChangeBtn);
			set.add(blackBtn);
			set.add(logoutBtn);
			

			set.setSize(200, 200);
			set.setVisible(true);
			setDefaultCloseOperation(set.EXIT_ON_CLOSE);
		}
	}
}
