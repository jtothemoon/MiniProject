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
	
	public String checkId(String userId) {
		connect();
		String checkUserId = "";
		String sql = "SELECT USER_ID FROM TB_CW_USER WHERE USER_ID = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			if(rs.next()) checkUserId = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return checkUserId;
	}
	
	public String checkEmail(String email) {
		connect();
		String checkEmail = "";
		String sql = "SELECT EMAIL FROM TB_CW_USER WHERE EMAIL = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			if(rs.next()) checkEmail = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return checkEmail;
	}
	
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
	
	public String userAuth(UserDTO dto) {
		connect();
		
		String pw = "";
		String sql = "SELECT PW FROM TB_CW_USER WHERE NAME = ? AND EMAIL = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getEmail());
			rs = psmt.executeQuery();
			if(rs.next()) pw = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return pw;
	}
	
	public int updatePw(String email, String pw) {
		connect();
		
		int row = 0;
		String sql = "UPDATE TB_CW_USER SET PW = ? WHERE EMAIL = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pw);
			psmt.setString(2, email);
			row = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return row;
	}
	
	public int userDelete(UserDTO dto) {
		connect();
		
		int row = 0;
		String sql = "DELETE FROM TB_CW_USER WHERE USER_ID = ? AND PW = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUserId());
			psmt.setString(2, dto.getPw());
			row = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return row;
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