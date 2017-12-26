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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DbDao {
	private static String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static String DB_URL = "jdbc:oracle:thin:@70.12.115.61:1521:xe";
	private static String DB_ID = "atalk";
	private static String DB_PW = "sds1501";

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	ArrayList<UserVO> userList = new ArrayList<>();
	ArrayList<RoomVO> roomList = new ArrayList<>();

	// START MEMBER
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
				lv.setIntroduce(rs.getString(7));
				lv.setGithub(rs.getString(8));
				lv.setOtherEmail(rs.getString(9));

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

	//////////////////////////////////////////////////////////////////////
	// ȸ������ ����
	// INSERT MEMBER

	public int insertUserInfo(UserVO user) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "INSERT INTO MEMBER(ID,PW,NAME,BIRTHDATE,EMAIL,PHONE) VALUES(?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, user.getId());
			ps.setString(2, user.getPw());
			ps.setString(3, user.getName());
			ps.setString(4, user.getBirth());
			ps.setString(5, user.geteMail());
			ps.setString(6, user.getPhone());

			userList.add(new UserVO(user.getId(), user.getPw(), user.getName(), user.getBirth(), user.geteMail(),
					user.getPhone()));

			result = ps.executeUpdate();
			System.out.println("ȸ�� ���� ���� ���� ���(1: �����, 0: ����): + result");
		} catch (SQLIntegrityConstraintViolationException e) {
			JDialog dialog = new JDialog();
			JPanel errorPanel = new JPanel();
			JButton check = new JButton("Ȯ��");
			JLabel message = new JLabel("���̵� �ߺ��ƽ��ϴ�.");

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

	//////////////////////////////////////////////////////////
	// ���� �α��� ���� ���
	// USER CHECK
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
	// End of MEMBER

	// Start ROOM
	// Start BLACKLIST
	// INSERT BLACK_ID
	public int blockUser(String id, String idToBlock) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "INSERT INTO BLACKLIST(ID,BLACK_ID) VALUES(?,?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, id);
			for (int x = 0; x < userList.size(); x++) {
				if (userList.get(x).getId().equals(idToBlock)) {
					ps.setString(2, idToBlock);
					break;
				}
			}
			result = ps.executeUpdate();

			System.out.println("�� ó�� ���� ���� ���(1: ����, 2: ����): " + result);
		} catch (SQLIntegrityConstraintViolationException e) {
			JDialog dialog = new JDialog();
			JPanel errorPanel = new JPanel();
			JButton check = new JButton("Ȯ��");
			JLabel message = new JLabel("�ߺ��� ���̵� �Դϴ�.");

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
		} catch (SQLException e) {
			JDialog dialog = new JDialog();
			JPanel errorPanel = new JPanel();
			JButton check = new JButton("Ȯ��");
			JLabel message = new JLabel("���� ���̵� �Դϴ�.");

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
		} finally {
			closePstmt();
			closeConnection();
		}
		return result;
	}

	// ������Ʈ ����
	public int blockUserDelete(String id, String idToBlock) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "DELETE FROM BLACKLIST WHERE BLACK_ID=?";

			ps = con.prepareStatement(sql);

			ps.setString(1, idToBlock);

			result = ps.executeUpdate();

			System.out.println("�� ���� ó�� ���� ���� ���(1: ����, 2: ����):" + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePstmt();
			closeConnection();

		}
		return result;
	}

	// ������Ʈ �˻�
	public List blockUserSelect(String id) {
		ArrayList<UserVO> blackList = new ArrayList<>();

		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "SELECT BLACK_ID FROM BLACKLIST WHERE ID = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				UserVO b = new UserVO();
				b.setBlackId(rs.getString(1));
				blackList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeRS();
			closePstmt();
			closeConnection();
		}
		return blackList;
	}
	// END of BLACKLIST

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
				RoomVO room = new RoomVO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4),
						rs.getString(5), rs.getInt(6));
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

	// getRoom
	public ArrayList<RoomVO> getRoomList() {
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "SELECT * FROM ROOM";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			roomList.clear();

			while (rs.next()) {
				roomList.add(new RoomVO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4),
						rs.getString(5), rs.getInt(6)));
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
		return roomList;
	}

	public RoomVO getRoomInfo(String masterId) {
		RoomVO roomInfo = null;
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "SELECT * FROM ROOM where master_id=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, masterId);
			rs = ps.executeQuery();

			if (rs.next()) {
				roomInfo = new RoomVO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getInt(6));
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
		return roomInfo;
	}

	// End of getRoom
	// INSERT ROOM
	public int insertRoomInfo(RoomVO room) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "INSERT INTO ROOM(TITLE, MASTER_ID, POPULATION, LANGUAGE, PW, PORT_NUM) VALUES(?, ?, ?, ?, ?, ?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, room.getTitle());
			ps.setString(2, room.getMasterID());
			ps.setInt(3, room.getPopulation());
			ps.setString(4, room.getLanguage());
			ps.setString(5, room.getPassword());
			ps.setInt(6, room.getPortNum());

			roomList.add(new RoomVO(room.getTitle(), room.getMasterID(), room.getPopulation(), room.getLanguage(),
					room.getPassword(), room.getPortNum()));

			result = ps.executeUpdate();

			// Add master into JOIN when he creates a room
			insertJoinedMember(room.getMasterID(), room.getMasterID());

			System.out.println("�� ���� ���� ���� ���(1: �����, 0: ����): " + result);
		} catch (SQLIntegrityConstraintViolationException e) {
			JDialog dialog = new JDialog();
			JPanel errorPanel = new JPanel();
			JButton check = new JButton("Ȯ��");
			JLabel message = new JLabel("�ϳ��� ���� �����մϴ�.");

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

	// DELETE ROOM
	public int deleteRoom(String masterID) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "DELETE FROM ROOM WHERE MASTER_ID=?";

			ps = con.prepareStatement(sql);

			ps.setString(1, masterID);

			// for (RoomVO l : roomList) {
			// if (l.getMasterID().equals(masterID)) {
			// System.out.println("����Ʈ���� �����");
			// roomList.remove(l);
			// }else {
			// // Just leave the room.
			// System.out.println("�׳� ����");
			// }
			// }

			result = ps.executeUpdate();

			// Delete master from JOIN when he leaves a room
			// deleteJoinedMember(room.getMasterID());

			System.out.println("�� ���� ���� ���� ���(1: �����, 2: ����): " + result);
		} catch (SQLIntegrityConstraintViolationException e) {
			JDialog dialog = new JDialog();
			JPanel errorPanel = new JPanel();
			JButton check = new JButton("Ȯ��");
			JLabel message = new JLabel("�ϳ��� ���� �����մϴ�.");

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

	public int resetRoom() {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "DELETE FROM ROOM";

			ps = con.prepareStatement(sql);

			result = ps.executeUpdate();

			System.out.println("�� ���� ���� ���� ���(1: �����, 2: ����): " + result);
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
	// INSERT JOIN
	public int insertJoinedMember(String id, String masterID) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "INSERT INTO JOIN(ID, MASTER_ID) VALUES(?, ?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, id);
			ps.setString(2, masterID);

			result = ps.executeUpdate();
			System.out.println(id + "�� �� ���� ���� ���(1: �����, 0: ����): " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePstmt();
			closeConnection();
		}
		return result;
	}

	// SELECT JOIN
	public List<UserVO> selectJoinedMember(String id) {
		ArrayList<UserVO> joinList = new ArrayList<>();
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "SELECT ID FROM JOIN WHERE MASTER_ID = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				UserVO lv = new UserVO();
				lv.setId(rs.getString(1));
				joinList.add(lv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeRS();
			closePstmt();
			closeConnection();
		}
		return joinList;
	}

	// DELETE JOIN
	public int deleteJoinedMember(String id, String master_id) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "DELETE FROM JOIN WHERE ID=? AND MASTER_ID=?";

			ps = con.prepareStatement(sql);

			ps.setString(1, id);
			ps.setString(2, master_id);

			result = ps.executeUpdate();
			System.out.println(id + "�� ���� ���� ���� ���(1: �����, 0: ����): " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePstmt();
			closeConnection();
		}
		return result;
	}

	public int resetJoin() {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "DELETE FROM JOIN";

			ps = con.prepareStatement(sql);

			result = ps.executeUpdate();

			System.out.println("���� ���� ���� ���� ���(1: �����, 2: ����): " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePstmt();
			closeConnection();
		}
		return result;
	}
	// END JOIN

	//////////////////////////////////////////////////////////
	// START ������ ������Ʈ
	public void updateProfile(UserVO user) {
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			for (int x = 0; x < userList.size(); x++)
				if (userList.get(x).getId().equals(user.getId())) {
					if (user.getIntroduce().length() == 0) {
						user.setIntroduce(userList.get(x).getIntroduce());
					}
					if (user.getGithub().length() == 0) {
						user.setGithub(userList.get(x).getGithub());
					}
					if (user.getOtherEmail().length() == 0) {
						user.setOtherEmail(userList.get(x).getOtherEmail());
					}
					if (user.getPw().length() == 0) {
						user.setPw(userList.get(x).getPw());
					}
					if (user.getPhone().length() == 0) {
						user.setPhone(userList.get(x).getPhone());
					}
				}

			String sql = "UPDATE MEMBER SET INTRODUCTION=?,GITHUB=?,OTHEREMAIL=?,PW=?,PHONE=? WHERE ID=?";
			ps = con.prepareStatement(sql);

			ps.setString(1, user.getIntroduce());
			ps.setString(2, user.getGithub());
			ps.setString(3, user.getOtherEmail());
			ps.setString(4, user.getPw());
			ps.setString(5, user.getPhone());
			ps.setString(6, user.getId());

			int result = ps.executeUpdate();
			System.out.println("���� ���� ���(1.�����): + result");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePstmt();
			closeConnection();
		}
	}

	// ������ �˻� �� ȭ�鿡 ���
	public UserVO selectProfile(String id) {
		UserVO lv = new UserVO();
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "SELECT NAME,BIRTHDATE,EMAIL,PHONE,INTRODUCTION,GITHUB,OTHEREMAIL FROM MEMBER "
					+ "WHERE ID = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				lv.setName(rs.getString(1));
				lv.setBirth(rs.getString(2));
				lv.seteMail(rs.getString(3));
				lv.setPhone(rs.getString(4));
				lv.setIntroduce(rs.getString(5));
				lv.setGithub(rs.getString(6));
				lv.setOtherEmail(rs.getString(7));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeRS();
			closePstmt();
			closeConnection();
		}
		return lv;
	}

	/////////////////////////////////////////////////////////////////////////////////////
	// OTHER
	// ----------------------------------------------------------------------------

	private void Interrupt() {
		JDialog dialog = new JDialog();
		JPanel errorPanel = new JPanel();
		JButton check = new JButton("Ȯ��");
		JLabel message = new JLabel("���̵� Ȥ�� ��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �õ����ּ���.");

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

	public static String getDB_DRIVER() {
		return DB_DRIVER;
	}

	public static void setDB_DRIVER(String dB_DRIVER) {
		DB_DRIVER = dB_DRIVER;
	}

	public static String getDB_URL() {
		return DB_URL;
	}

	public static void setDB_URL(String dB_URL) {
		DB_URL = dB_URL;
	}

	public static String getDB_ID() {
		return DB_ID;
	}

	public static void setDB_ID(String dB_ID) {
		DB_ID = dB_ID;
	}

	public static String getDB_PW() {
		return DB_PW;
	}

	public static void setDB_PW(String dB_PW) {
		DB_PW = dB_PW;
	}
	
}
