package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.UserDTO;

public class UserDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	private final String dbDriver = "oracle.jdbc.driver.OracleDriver";
	private final String dbUrl = "jdbc:oracle:thin:@project-db-cgi.smhrd.com:1524:xe";
	private final String dbId = "cgi_24IS_cloud3_p1_3";
	private final String dbPw = "smhrd3";
	
	public int userJoin(UserDTO dto) {
		connect();
		
		int row = 0;
		String sql = "INSERT INTO TB_CW_USER (USER_ID, PW, NAME, EMAIL) VALUES (?, ?, ?, ?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUserId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			row = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return row;
	}
	
	public String userLogin(UserDTO dto) {
		connect();
		
		String userId = "";
		String sql = "SELECT USER_ID FROM TB_CW_USER WHERE USER_ID = ? AND PW = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUserId());
			psmt.setString(2, dto.getPw());
			rs = psmt.executeQuery();
			if(rs.next()) userId = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return userId;
	}
	
	public void connect() {
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
