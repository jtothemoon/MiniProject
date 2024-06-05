package Controller;

import Model.PlayDTO;

public class PlayDAO extends UserDAO {
	public int checkPlayDays(String userId) {
		connect();

		int playDays = 0;
		String sql = "SELECT PLAY_DAYS FROM TB_CW_PLAY WHERE ID = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			if (rs.next())
				playDays = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return playDays;
	}

	public int insertPlayInfo(PlayDTO dto) {
		connect();

		int row = 0;
		String sql = "INSERT INTO TB_CW_PLAY (ID, NICK_NAME, POSITION, EXPERIENCE, MONEY, HEALTH, ADD_EXPERIENCE, ADD_MONEY, ADD_HEALTH, PLAY_DAYS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getNickName());
			psmt.setInt(3, dto.getPosition());
			psmt.setInt(4, dto.getExperience());
			psmt.setInt(5, dto.getMoney());
			psmt.setInt(6, dto.getHealth());
			psmt.setInt(7, dto.getAddExperience());
			psmt.setInt(8, dto.getAddMoney());
			psmt.setInt(9, dto.getAddHealth());
			psmt.setInt(10, dto.getPlayDays());

			row = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return row;
	}

	public int savePlayInfo(PlayDTO dto) {
		connect();

		int row = 0;
		String sql = "UPDATE TB_CW_PLAY SET POSITION = ?, EXPERIENCE = ?, MONEY = ?, HEALTH = ?, ADD_EXPERIENCE = ?, ADD_MONEY = ?, ADD_HEALTH = ?, PLAY_DAYS = ? WHERE ID = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getPosition());
			psmt.setInt(2, dto.getExperience());
			psmt.setInt(3, dto.getMoney());
			psmt.setInt(4, dto.getHealth());
			psmt.setInt(5, dto.getAddExperience());
			psmt.setInt(6, dto.getAddMoney());
			psmt.setInt(7, dto.getAddHealth());
			psmt.setInt(8, dto.getPlayDays());
			
			psmt.setString(9, dto.getId());

			row = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return row;
	}

	public PlayDTO getPlayInfo(String userId) {
		connect();

		PlayDTO dto = null;
		String sql = "SELECT * FROM TB_CW_PLAY WHERE ID = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			while (rs.next()) {
				dto = new PlayDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return dto;
	}
	
	public int resetPlayerInfo(PlayDTO dto) {
		connect();

		int row = 0;
		String sql = "UPDATE TB_CW_PLAY SET POSITION = ?, EXPERIENCE = ?, MONEY = ?, HEALTH = ?, ADD_EXPERIENCE = ?, ADD_MONEY = ?, ADD_HEALTH = ?, PLAY_DAYS = ? WHERE ID = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getPosition());
			psmt.setInt(2, dto.getExperience());
			psmt.setInt(3, dto.getMoney());
			psmt.setInt(4, dto.getHealth());
			psmt.setInt(5, dto.getAddExperience());
			psmt.setInt(6, dto.getAddMoney());
			psmt.setInt(7, dto.getAddHealth());
			psmt.setInt(8, dto.getPlayDays());
			
			psmt.setString(9, dto.getId());

			row = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return row;
	}
	
	@Override
	public void connect() {
		// TODO Auto-generated method stub
		super.connect();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
	}

	
}
