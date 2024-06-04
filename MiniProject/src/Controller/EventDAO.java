package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import Model.EventDTO;

public class EventDAO {
	public EventDTO ran() {
		EventDTO rDto = new EventDTO(0, null, null, null);
		
		PreparedStatement psmt = null;
		Connection conn = null;
		ResultSet rs = null;
		Random rd = new Random();
		int random = rd.nextInt(109) + 1;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-cgi.smhrd.com:1524:xe";
			String user = "cgi_24IS_cloud3_p1_3";
			String password = "smhrd3";

			conn = DriverManager.getConnection(url, user, password);

			String sql = "SELECT EVENT_NUMBER, EVENT_TEXT, EVENT_ANS1, EVENT_ANS2 FROM TB_CW_RANDOMEVENT WHERE EVENT_NUMBER = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, random);

			rs = psmt.executeQuery();

			if (rs.next() == true) {

				rDto.setEventNumber(rs.getInt("EVENT_NUMBER"));
				rDto.setEventText(rs.getString("EVENT_TEXT"));
				rDto.setEventAns1(rs.getString("EVENT_ANS1"));				
				rDto.setEbentAns2(rs.getString("EVENT_ANS2"));

				random = rs.getInt("EVENT_NUMBER");
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rDto;

	}
}
