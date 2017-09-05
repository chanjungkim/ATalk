package chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginDao {
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_ID = "atalk";
	private static final String DB_PW = "sds1501";

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	ArrayList<LoginVO> loginList = new ArrayList<>();

	public LoginDao() {
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "SELECT * FROM JOINMEMBER";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				LoginVO lv = new LoginVO();
				lv.setId(rs.getString(1));
				lv.setPw(rs.getString(2));
				lv.setName(rs.getString(3));
				lv.setBirth(rs.getString(4));
				lv.seteMail(rs.getString(5));
				lv.setPhone(rs.getString(6));

				loginList.add(lv);
			}
			for (LoginVO l : loginList) {
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

	////////////////////////////////////////////////////////////////
	// 삽입
	public void insertLogin(LoginVO log) {
		int result = 0;
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

			String sql = "INSERT INTO JOINMEMBER(ID,PW,NAME,BIRTHDAY,EMAIL,PHONE) VALUES(?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, log.getId());
			ps.setString(2, log.getPw());
			ps.setString(3, log.getName());
			ps.setString(4, log.getBirth());
			ps.setString(5, log.geteMail());
			ps.setString(6, log.getPhone());

			loginList.add(new LoginVO(log.getId(), log.getPw(), log.getName(), log.getBirth(), log.geteMail(),
					log.getPhone()));

			result = ps.executeUpdate();
			System.out.println("쿼리 수행 결과(1.수행됨): + result");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePstmt();
			closeConnection();
		}
	}

	//////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////
	//현재 로그인 접속 방식
	public void loginConnect(String id, String pw) {
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			String sql = "SELECT * FROM JOINMEMBER WHERE ID=? AND PW=?";

			ps = con.prepareStatement(sql);

			ps.setString(1, id);
			ps.setString(2, pw);

			rs = ps.executeQuery();
			
			while (rs.next()) {
				LoginVO lv = new LoginVO();
				lv.setId(rs.getString(1));
				lv.setPw(rs.getString(2));
			}
			
			if(rs.wasNull()) {
				Interrupt();
			}else {
				RoomList roomList = new RoomList();
			}

		} catch (SQLException e) {
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
			e.printStackTrace();
		} finally {
			closeRS();
			closePstmt();
			closeConnection();
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////

	private void Interrupt() {
		// TODO Auto-generated method stub
		
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
