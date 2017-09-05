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
		

		roomList = new JButton("목록");
		room = new JButton("안드로이드 스터디 방");

		makeRoom = new JButton(new ImageIcon("말풍선.PNG")); // 방생성
		menu = new JButton(new ImageIcon("메뉴.png"));
		setting = new JButton(new ImageIcon("설정.PNG")); // 세팅

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
		// 설정창 구현
		setting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Setting set = new Setting();

				set.logoutBtn.addActionListener(new ActionListener() { // 로그아웃 버튼
					@Override
					public void actionPerformed(ActionEvent e) {
						int t = JOptionPane.showConfirmDialog(null, "이 계정을 로그아웃 하시겠습니까?", "",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (t == 0) {
							System.out.println("Go to the previous: frame.");
							set.dispose();
							dispose();
							LogIn logInFrame = new LogIn();
						}
					}
				});

				set.blackBtn.addActionListener(new ActionListener() { // 블랙리스트 버튼
					JFrame blackFm = new JFrame();
					JPanel BtnPn = new JPanel();
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

						BtnPn.add(nameLb);
						BtnPn.add(nameText);
						BtnPn.add(addBtn);
						BtnPn.add(delBtn);

						blackFm.add(BtnPn, BorderLayout.NORTH);
						blackFm.add(nameList, BorderLayout.CENTER);

						blackFm.setTitle("블랙리스트");
						blackFm.setSize(300, 400);
						setDefaultCloseOperation(blackFm.EXIT_ON_CLOSE); // 이 창만 종료
						blackFm.setVisible(true);
					}
				});

				set.alarmBtn.addActionListener(new ActionListener() { // 알람 버튼
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

					}
				});

				set.colorBtn.addActionListener(new ActionListener() { // 색상 변경 버튼
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

					}
				});

			}
		});
		////////////////////////////////////////////////////////////////////////////////////

		///////////////////////////////////////////////////////////////////////////////////
		// 방 만들기

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
		room.setFont(new Font("바탕", Font.BOLD, 25));
		
		master = new JLabel("방장:");
		master.setBorder(eborder);
		master.setBounds(350, 80, 200, 60);
		master.setFont(new Font("바탕", Font.BOLD, 25));
		
		count = new JLabel("인원:");
		count.setBorder(eborder);
		count.setBounds(550, 80, 150, 60);
		count.setFont(new Font("바탕", Font.BOLD, 25));


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
	public class RoomMake extends JFrame implements ItemListener { //방 생성
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
			titleLb = new JLabel("방 만들기");
			roomTitleLb = new JLabel("제목");
			passwordLb= new JLabel("비밀 번호");
			peopleNLb = new JLabel("인원");
			titlePn = new JPanel();
			titleTPn = new JPanel();
			passwordPn = new JPanel();
			peopleNumPn = new JPanel();
			btnPn = new JPanel();
			titleText = new JTextField(15);
			passwordText = new JTextField(15);
			makeBtn = new JButton("생성");
			cancleBtn = new JButton("취소");
			checkBtn = new JCheckBox();
			
		
			String numberList[] = {"1","2","3","4","5","6","7","8"};   
			peopleNum = new JComboBox(numberList); // 방 인원수
			peopleNum.setEnabled(true);
			peopleNum.addActionListener(new ActionListener() { //인원 수 선택 이벤트
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
}
