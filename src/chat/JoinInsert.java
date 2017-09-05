package chat;

import java.sql.*;
import java.util.Scanner;

public class JoinInsert {
	
	String id, name, email, phone, pw;

	public JoinInsert(String id, String pw, String name, String email, String phone) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 커넥션 생성
			con = DriverManager.getConnection("jdbc:mysql://70.12.115.81:3306/MEMBER", "root", "sds1501");
			String sql = "INSERT INTO information (USER_ID, USER_PW, NAME, EMAIL, PHONE) VALUES (?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			System.out.println(id + pw + name+ email+ phone);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, phone);
			
			int result = pstmt.executeUpdate();

			System.out.println("쿼리수행 결과 " + result);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 오류");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7. 사용한 자원 반납
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
