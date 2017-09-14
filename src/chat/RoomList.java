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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
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

import server.MainServer;

public class RoomList extends JFrame {
	private final static String MAIN_SERVER_ADDR = "70.12.115.61";
	private final static int MAIN_SERVER_PORT = 5555;
	private JPanel panel;
	private JPanel menuPanel;
	private JPanel listPanel;
	private JScrollPane scrollFrame;

	private JButton createRoomBtn;
	private JButton refreshBtn;
	private JButton settingBtn;

	private ArrayList<RoomPanel> roomPanel = new ArrayList<>();
	private ArrayList<RoomVO> rooms = new ArrayList<>();
	
	private JLabel master = new JLabel("방장:");
	private JLabel count = new JLabel("인원:");

	private Socket serverToMainServerSocket;

	
	public RoomList(String id) {
		panel = new JPanel();
		menuPanel = new JPanel();
		menuPanel.setBackground(Color.DARK_GRAY);
		listPanel = new JPanel();
		listPanel.setBackground(Color.GREEN);
		scrollFrame = new JScrollPane(listPanel);
		scrollFrame.enable(true);

		refreshBtn = new JButton();

		createRoomBtn = new JButton(new ImageIcon("balloon.PNG")); // 방생성
		refreshBtn = new JButton(new ImageIcon("menu.png"));
		settingBtn = new JButton(new ImageIcon("setting.PNG")); // 세팅

		listPanel.setAutoscrolls(true);
		listPanel.setLayout(new FlowLayout());
		panel.setLayout(new BorderLayout());
		DbDao user = new DbDao();

		// EtchedBorder eborder;
		// eborder = new EtchedBorder(EtchedBorder.LOWERED);

		// Load Rooms from ROOM TABLE

		rooms = user.getRoomList();
		
		// make roomPanels and add into roomPanel ArrayList.
		for(int i = 0 ; i < rooms.size() ; i++) {
			String title = rooms.get(i).getTitle();
			String masterID = rooms.get(i).getMasterID();
			int population = rooms.get(i).getPopulation();
			String lang = rooms.get(i).getLanguage();
			String pw = rooms.get(i).getPassword();
//			int portNum = rooms.get(i).getPortNum();
			
			roomPanel.add(new RoomPanel(title, masterID, population, lang, pw));
			
			System.out.println(title+" "+masterID+" "+population+" "+lang+" "+pw);
		}
		
		// Add rooms into listPanel & Clickable(functionally) Rooms
		for (int j = 0; j < roomPanel.size(); j++) {
			int k = j;
			listPanel.add(roomPanel.get(j));
			
			roomPanel.get(j).roomBtn.addActionListener(new ActionListener() {
				ChatRoom chat;
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					chat = new ChatRoom(id, rooms.get(k).getMasterID());
					chat.backBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							chat.hide();
						}
					});
				}
			});// End of Function
		}

		// End of loading Rooms
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
							ChatClient logInFrame = new ChatClient();
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

								String idToBlock = nameText.getText();

								int x = user.blockUser(id, idToBlock);

								if (x == 1) {
									nameList.add(idToBlock);
								}
								nameText.setText(""); // 텍스트 필드내용 지우기
								nameText.requestFocus(); // 텍스트 필드에 포커스 주기
							}
						});

						delBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								int pos = nameList.getSelectedIndex(); // namelist의 선택한 항목의 인덱스값 얻어오기

								String idToBlock = nameList.getSelectedItem();
								if (pos == -1) {

									return;
								}
								user.blockUserDelete(id, idToBlock);
								nameList.remove(idToBlock);

							}
						});
						java.util.List<UserVO> black = user.blockUserSelect(id);
						for (UserVO u : black) {
							String x = u.getBlackId();
							nameList.add(x);
						}
						
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

				settingDialogue.profileChangeBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ProfileChange p = new ProfileChange(id);
					}
				});

				settingDialogue.alarmBtn.addActionListener(new ActionListener() { // 알람 버튼
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
						if (createRoomDialogue.titleField.getText().isEmpty()) {
							JDialog dialog = new JDialog();
							JPanel errorPanel = new JPanel();
							JButton check = new JButton("확인");
							JLabel message = new JLabel("제목을 입력해주세요.");

							check.addActionListener(new ActionListener() {
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
						} else if (createRoomDialogue.passwordField.getText().isEmpty()
								&& createRoomDialogue.checkBtn.isSelected()) {
							// Start Dialog
							JDialog dialog = new JDialog();
							JPanel errorPanel = new JPanel();
							JButton check = new JButton("확인");
							JLabel message = new JLabel("비밀번호를 입력해주세요.");

							check.addActionListener(new ActionListener() {
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
						} else {
							DbDao roomDao = new DbDao(1);

							String title = createRoomDialogue.getTitleField();
							String masterID = id;
							int population = createRoomDialogue.getPopulation();
							String lang = createRoomDialogue.getLanguage();
							String pw = createRoomDialogue.getPasswordField();
							
//							Socket socket = new Socket();
//							ServerThread t = new ServerThread(socket);
							
							// 방 생성시 메인 서버와 네트워크 연결
							int roomPort = getRoomPortToMainServer();
							
							System.out.println("방만들기 디비 작업");
							RoomVO roomVo = new RoomVO(title, masterID, population, lang, pw, roomPort);
							roomDao.insertRoomInfo(roomVo);

							roomPanel.add(new RoomPanel(title, masterID, population, lang, pw));
							for (int i = 0; i < roomPanel.size(); i++) {
								listPanel.add(roomPanel.get(i));
								System.out.println(roomPanel.size());
							}

							validate();

							ChatRoom chatRoom = new ChatRoom(id, masterID);
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

		refreshBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

				new RoomList(id);

			}
		});
		// End of Create Room Dialogue

		// Add Panels
		menuPanel.add(createRoomBtn);
		menuPanel.add(refreshBtn);
		menuPanel.add(settingBtn);

		// scrollFrame.add(listPanel);
		panel.add(menuPanel, BorderLayout.NORTH);
		panel.add(listPanel);

		add(panel);

		setSize(700, 500);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	// -------------- init netword to MainServer------------	
	public int getRoomPortToMainServer() {
		System.out.println("getRoomPortToMainServer call");
		int roomPort = 0;
		try {
			serverToMainServerSocket = new Socket(InetAddress.getByName(MAIN_SERVER_ADDR), MAIN_SERVER_PORT);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(serverToMainServerSocket.getInputStream()));
			
			String roomPortStr = null;

			roomPortStr = br.readLine();
			
			System.out.println(roomPortStr);

			roomPort =  Integer.parseInt(roomPortStr);
			
			br.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return roomPort;
	}
	

	// <--------------------- Inner Classes -------------------------->
	class CreateRoomDialogue extends JFrame implements ItemListener { // 방 생성
		private JDialog createRoomDialogue = new JDialog(this, "방 만들기");
		private JLabel roomTitleLb = new JLabel("제목");
		private JTextField titleField;

		private JLabel passwordLb = new JLabel("비밀 번호");
		private JTextField passwordField;

		private JLabel populationLb = new JLabel("인원");
		private JComboBox<Integer> population;

		private JLabel langaugeLb = new JLabel("언어");
		private JComboBox<String> language;

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
			Integer numberList[] = { 1, 2, 3, 4, 5, 6 };
			population = new JComboBox<>(numberList); // 방 인원수
			String languageList[] = { "JAVA", "C", "C++", "C#", "X" };
			language = new JComboBox<>(languageList);
			population.setEnabled(true);
			language.setEnabled(true);

			checkBtn.addItemListener(this);
			// population.addActionListener(new ActionListener() { //인원 수 선택 이벤트
			// @Override
			// public void actionPerformed(ActionEvent e) {
			// int num = (int) ((JComboBox)e.getSource()).getSelectedItem();
			// }
			// });

			// Layout
			createRoomDialogue.setLayout(new GridLayout(5, 1, 0, 10));
			titleTPn.setLayout(null);
			passwordPn.setLayout(null);
			populationPn.setLayout(null);
			passwordField.setBackground(Color.decode("#AAAAAA"));

			roomTitleLb.setBounds(0, -10, 50, 50);
			titleField.setBounds(60, 0, 280, 30);
			passwordLb.setBounds(0, -10, 55, 50);
			passwordField.setBounds(60, 0, 280, 30);
			checkBtn.setBounds(345, -30, 100, 100);
			populationLb.setBounds(0, -10, 50, 50);
			population.setBounds(60, 0, 50, 30);
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
			if (source == checkBtn) {
				passwordField.setEnabled(true);
				passwordField.setBackground(Color.white);
			}
			if (e.getStateChange() == ItemEvent.DESELECTED) {
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
			if (lang.isEmpty()) {
				lang = "JAVA";
			}
			return lang;
		}

		// public void setPopulation(JComboBox<Integer> population) {
		// this.population = population;
		// }

	}

	class SettingDialogue extends JFrame {
		private JDialog set;
		private JPanel alarmPn;
		private JLabel alarmLb;
		private JButton alarmBtn;
		private JButton blackBtn;
		private JButton profileChangeBtn;
		private JButton logoutBtn;

		public SettingDialogue() {
			set = new JDialog(this, "설정");
			alarmPn = new JPanel();
			alarmLb = new JLabel("알람");
			alarmBtn = new JButton("켜기");
			blackBtn = new JButton("블랙리스트");
			profileChangeBtn = new JButton("회원정보 수정");
			logoutBtn = new JButton("로그아웃");

			set.setLayout(new GridLayout(5, 1));
			alarmPn.setLayout(new BorderLayout());
			
			alarmPn.add(alarmLb, BorderLayout.WEST);
			alarmPn.add(alarmBtn, BorderLayout.EAST);

			set.add(alarmPn);
			set.add(profileChangeBtn);
			set.add(blackBtn);
			set.add(logoutBtn);

			set.setSize(200, 200);
			set.setVisible(true);
			setDefaultCloseOperation(set.EXIT_ON_CLOSE);
		}
	}
}
