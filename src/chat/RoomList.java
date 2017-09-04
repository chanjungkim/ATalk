package chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.scene.layout.Border;
import sun.net.www.content.image.jpeg;

public class RoomList extends JFrame {
	private JPanel panel;
	private JPanel topPanel;
	private JPanel listPanel;
	private JButton makeRoom;
	private JButton roomList;
	private JButton setting;
	private JButton room;

	public RoomList() {
		panel = new JPanel();
		topPanel = new JPanel();
		listPanel = new JPanel();
		makeRoom = new JButton("방 생성");
		roomList = new JButton("목록");
		setting = new JButton("설정");
		room = new JButton("안드로이드 스터디 방");

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
									JOptionPane.showMessageDialog(blackFm, "선택해주세요!");
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
		topPanel.setLayout(new BorderLayout(200, 10));
		panel.setLayout(new BorderLayout(10, 20));
		//
		topPanel.add(roomList, BorderLayout.WEST);
		topPanel.add(makeRoom, BorderLayout.CENTER);
		topPanel.add(setting, BorderLayout.EAST);
		listPanel.add(room);

		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(listPanel, BorderLayout.CENTER);

		add(panel);

		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	// Layout
	public class RoomMake extends JFrame {
		private JDialog roomMk;
		private JLabel title;
		private JLabel roomTitle;
		private JLabel password;
		private JPanel titlePn;
		private JPanel titleTPn;
		private JPanel passwordPn;
		private JPanel btnPn;
		private JTextField titleText;
		private JTextField passwordText;
		private JButton makeBtn;
		private JButton cancleBtn;

		public RoomMake() {
			roomMk = new JDialog();
			title = new JLabel("방 만들기");
			roomTitle = new JLabel("제목");
			password = new JLabel("비밀 번호");
			titlePn = new JPanel();
			titleTPn = new JPanel();
			passwordPn = new JPanel();
			btnPn = new JPanel();
			titleText = new JTextField(15);
			passwordText = new JTextField(15);
			makeBtn = new JButton("생성");
			cancleBtn = new JButton("취소");

			roomMk.setLayout(new GridLayout(4, 1, 0, 10));
			titleTPn.setLayout(new BorderLayout(5,0));
			passwordPn.setLayout(new BorderLayout(5,0));

			title.setBackground(Color.YELLOW);

			titlePn.add(title);
			titleTPn.add(roomTitle, BorderLayout.WEST);
			titleTPn.add(titleText, BorderLayout.CENTER);
			passwordPn.add(password, BorderLayout.WEST);
			passwordPn.add(passwordText, BorderLayout.CENTER);
			btnPn.add(makeBtn);
			btnPn.add(cancleBtn);

			roomMk.add(titlePn);
			roomMk.add(titleTPn);
			roomMk.add(passwordPn);
			roomMk.add(btnPn);

			roomMk.setSize(300, 200);
			roomMk.setVisible(true);
			setDefaultCloseOperation(roomMk.EXIT_ON_CLOSE);
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
