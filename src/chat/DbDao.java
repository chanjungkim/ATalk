package chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DbDao {
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@70.12.115.61:1521:xe";
	private static final String DB_ID = "atalk";
	private static final String DB_PW = "sds1501";

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	ArrayList<UserVO> userList = new ArrayList<>();
	ArrayList<RoomVO> roomList = new ArrayList<>();
	
	// MEMBER DAO CONSTRUCTOR
	public DbDao() {
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "SELECT * FROM MEMBER";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				UserVO lv = new UserVO();
				lv.setId(rs.getString(1));
				lv.setPw(rs.getString(2));
				lv.setName(rs.getString(3));
				lv.setBirth(rs.getString(4));
				lv.seteMail(rs.getString(5));
				lv.setPhone(rs.getString(6));

				userList.add(lv);
			}
			for (UserVO l : userList) {
				System.out.println(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeRS();
			closePstmt();
			closeConnection();
		}
	}
	
	// 회원가입 삽입
	public int insertUserInfo(UserVO user) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "INSERT INTO MEMBER(ID,PW,NAME,BIRTHDAY,EMAIL,PHONE) VALUES(?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, user.getId());
			ps.setString(2, user.getPw());
			ps.setString(3, user.getName());
			ps.setString(4, user.getBirth());
			ps.setString(5, user.geteMail());
			ps.setString(6, user.getPhone());

			userList.add(
					new UserVO(user.getId(), user.getPw(), user.getName(), user.getBirth(), user.geteMail(), user.getPhone()));

			result = ps.executeUpdate();
			System.out.println("회원 가입 쿼리 수행 결과(1: 수행됨, 0: 실패): + result");
		} catch (SQLIntegrityConstraintViolationException e) {
			JDialog dialog = new JDialog();
			JPanel errorPanel = new JPanel();
			JButton check = new JButton("확인");
			JLabel message = new JLabel("아이디가 중복됐습니다.");

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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePstmt();
			closeConnection();
		}
		return result;
	}
	// 현재 로그인 접속 방식
	public int userCheck(String id, String pw) {
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "SELECT * FROM MEMBER WHERE ID=? AND PW=?";

			ps = con.prepareStatement(sql);

			ps.setString(1, id);
			ps.setString(2, pw);

			rs = ps.executeQuery();

			if (rs.next()) {
				UserVO lv = new UserVO();
				lv.setId(rs.getString(1));
				lv.setPw(rs.getString(2));
				System.out.println(lv.getId());
				System.out.println(lv.getPw());
				RoomList roomList = new RoomList(id);
				return 1;
			}
			if (rs.wasNull()) {
			} else {

			}
		} catch (SQLException e) {
			Interrupt();
			e.printStackTrace();
		} finally {
			closeRS();
			closePstmt();
			closeConnection();
		}
		return 0;
	}


	////////////////////////////////////////////////////////////
	// 블랙리스트 추가

	////////////////////////////////////////////////////////////

// Start ROOM
	// ROOM DAO CONSTRUCTOR
	public DbDao(int number) {
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "SELECT * FROM ROOM";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				RoomVO room = new RoomVO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4) ,rs.getString(5));
				roomList.add(room);
			}
			for (RoomVO l : roomList) {
				System.out.println(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeRS();
			closePstmt();
			closeConnection();
		}
	}
	// INSERT ROOM
	public int insertRoomInfo(RoomVO room) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			
			String sql = "INSERT INTO ROOM(TITLE, MASTERID, POPULATION, LANGUAGE, PW) VALUES(?, ?, ?, ?, ?)";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, room.getTitle());
			ps.setString(2, room.getMasterID());
			ps.setInt(3, room.getPopulation());
			ps.setString(4, room.getLanguage());
			ps.setString(5, room.getPassword());
			roomList.add(new RoomVO(room.getTitle(), room.getMasterID(), room.getPopulation(), room.getLanguage(), room.getPassword()));

			result = ps.executeUpdate();
			System.out.println("방 생성 쿼리 수행 결과(1: 수행됨, 0: 실패): "+ result);
		} catch (SQLIntegrityConstraintViolationException e) {
			JDialog dialog = new JDialog();
			JPanel errorPanel = new JPanel();
			JButton check = new JButton("확인");
			JLabel message = new JLabel("하나만 생성 가능합니다.");

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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePstmt();
			closeConnection();
		}
		return result;
	}
	public int deleteRoom(RoomVO room) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			
			String sql = "DELETE FROM ROOM WHERE ID=?";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, room.getMasterID());
			
			for(RoomVO l : roomList) {
				if(l.getMasterID().equals(room.getMasterID())) {
					roomList.remove(l);
				}
			}
			
			result = ps.executeUpdate();
			System.out.println("방 삭제 쿼리 수행 결과(1: 수행됨, 0: 실패): "+ result);
		} catch (SQLIntegrityConstraintViolationException e) {
			JDialog dialog = new JDialog();
			JPanel errorPanel = new JPanel();
			JButton check = new JButton("확인");
			JLabel message = new JLabel("하나만 생성 가능합니다.");

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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePstmt();
			closeConnection();
		}
		return result;
	}
	public int updateRoom() {
		System.out.println("Unbuilt");
		return 0;
	}
// End of ROOM

// Start JOIN
	// ROOM DAO CONSTRUCTOR
	public DbDao(String id, String masterID) {
			try {
				Class.forName(DB_DRIVER);
				insertJoinedMember(id, masterID);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	// INSERT ROOM
	public int insertJoinedMember(String id, String masterID) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			
			String sql = "INSERT INTO JOIN(ID, MASTERID) VALUES(?, ?)";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, id);
			ps.setString(2, masterID);

			result = ps.executeUpdate();
			System.out.println("방 생성 쿼리 수행 결과(1: 수행됨, 0: 실패): "+ result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePstmt();
			closeConnection();
		}
		return result;
	}
	public int deleteJoinedMember(String id) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			
			String sql = "DELETE FROM JOIN WHERE ID=?";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, id);
			
			result = ps.executeUpdate();
			System.out.println("방 삭제 쿼리 수행 결과(1: 수행됨, 0: 실패): "+ result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePstmt();
			closeConnection();
		}
		return result;
	}
// END JOIN

// Start BLACKLIST
	public int blockUser(String id, String idToBlock) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "INSERT INTO BLACKLIST(ID,BLACKID) VALUES(?,?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, id);
			ps.setString(2, idToBlock);

			result = ps.executeUpdate();
			System.out.println("블랙 처리 쿼리 수행 결과(1: 수행, 2: 실패): + result");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePstmt();
			closeConnection();
		}
		return result;
	}
// END of BLACKLIST
	
	/////////////////////////////////////////////////////////////////////////////////////////////////

	private void Interrupt() {
		JDialog dialog = new JDialog();
		JPanel errorPanel = new JPanel();
		JButton check = new JButton("확인");
		JLabel message = new JLabel("아이디 혹은 비밀번호가 틀렸습니다. 다시 시도해주세요.");

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
	}

	public void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closePstmt() {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeRS() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
