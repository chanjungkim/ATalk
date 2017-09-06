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

	private JLabel 	master = new JLabel("방장:");
	private JLabel count = new JLabel("인원:");
	
	public RoomList() {
		panel = new JPanel();
		menuPanel = new JPanel();
		menuPanel.setBackground(Color.DARK_GRAY);
		listPanel = new JPanel();
		listPanel.setBackground(Color.GREEN);
		scrollFrame = new JScrollPane(listPanel);
		scrollFrame.enable(true);

		roomListBtn = new JButton();

		createRoomBtn = new JButton(new ImageIcon("balloon.PNG")); // 방생성
		roomListBtn = new JButton(new ImageIcon("menu.png"));
		settingBtn = new JButton(new ImageIcon("setting.PNG")); // 세팅

		listPanel.setAutoscrolls(true);
		listPanel.setLayout(new FlowLayout());
		panel.setLayout(new BorderLayout());

//		EtchedBorder eborder;
//		eborder = new EtchedBorder(EtchedBorder.LOWERED);
		
		// Setting Dialogue
		settingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SettingDialogue settingDialogue = new SettingDialogue();

				settingDialogue.logoutBtn.addActionListener(new ActionListener() { // 로그아웃 버튼
					@Override
					public void actionPerformed(ActionEvent e) {
						int t = JOptionPane.showConfirmDialog(null, "이 계정을 로그아웃 하시겠습니까?", "",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (t == 0) {
							System.out.println("Go to the previous: frame.");
							settingDialogue.dispose();
							dispose();
							LogIn logInFrame = new LogIn();
						}
					}
				});

				settingDialogue.blackBtn.addActionListener(new ActionListener() { // 블랙리스트 버튼
					JFrame blackFm = new JFrame();
					JPanel bottomPn = new JPanel();
					JLabel nameLb = new JLabel("닉네임");
					JTextField nameText = new JTextField(7);
					JButton addBtn = new JButton("추가");
					JButton delBtn = new JButton("삭제");
					List nameList = new List();

					@Override
					public void actionPerformed(ActionEvent e) {

						addBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String name = nameText.getText();
								nameList.add(name);
								nameText.setText(""); // 텍스트 필드내용 지우기
								nameText.requestFocus(); // 텍스트 필드에 포커스 주기
							}
						});
						delBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								int pos = nameList.getSelectedIndex(); // namelist의 선택한 항목의 인덱스값 얻어오기

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

						blackFm.setTitle("블랙리스트");
						blackFm.setSize(300, 400);
						setDefaultCloseOperation(blackFm.EXIT_ON_CLOSE); // 이 창만 종료
						blackFm.setVisible(true);
					}
				});

				settingDialogue.alarmBtn.addActionListener(new ActionListener() { // 알람 버튼
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

					}
				});

				settingDialogue.colorBtn.addActionListener(new ActionListener() { // 색상 변경 버튼
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
							JButton check = new JButton("확인");
							JLabel message = new JLabel("제목을 입력해주세요.");
							
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
							JButton check = new JButton("확인");
							JLabel message = new JLabel("비밀번호를 입력해주세요.");
							
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
							room.add(new RoomPanel(createRoomDialogue.getTitleField(), "아무개",  createRoomDialogue.getPopulation(), "C언어", createRoomDialogue.getPasswordField()));
							for(int i = 0 ; i < room.size(); i++) {

								listPanel.add(room.get(i));
								System.out.println(room.size());
							}
//							panel.add(listPanel);
//							add(panel);
							
							validate();

							createRoomDialogue.hide();
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
	class CreateRoomDialogue extends JFrame implements ItemListener { //방 생성
		private JDialog createRoomDialogue = new JDialog(this, "방 만들기");
		private JLabel roomTitleLb = new JLabel("제목");
		private JTextField titleField;
		
		private JLabel passwordLb = new JLabel("비밀 번호");
		private JTextField passwordField;
		
		private JLabel populationLb = new JLabel("인원");
		private JComboBox<Integer> population; 
		
		private JPanel topPn;
		private JPanel titleTPn;
		private JPanel passwordPn;
		private JPanel populationPn;
		private JPanel bottomPn;
		private JCheckBox checkBtn;
		private JButton createBtn = new JButton("생성");
		private JButton cancelBtn = new JButton("취소");
		
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
			population = new JComboBox<>(numberList); // 방 인원수
			population.setEnabled(true);
			
			checkBtn.addItemListener(this);
//			population.addActionListener(new ActionListener() { //인원 수 선택 이벤트
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

		public String getTitleField() {
			return titleField.getText();
		}

		public String getPasswordField() {
			return passwordField.getText();
		}
		
		public int getPopulation() {
			return population.getItemCount();
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
		private JButton logoutBtn;

		public SettingDialogue() {
			set = new JDialog(this, "설정");
			alarmPn = new JPanel();
			backgroundPn = new JPanel();
			alarmLb = new JLabel("알람");
			backgroundLb = new JLabel("배경 색");
			alarmBtn = new JButton("켜기");
			colorBtn = new JButton("색깔");
			blackBtn = new JButton("블랙리스트");
			logoutBtn = new JButton("로그아웃");

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
	public static void main(String[] args) {
		new RoomList();
	}
}
