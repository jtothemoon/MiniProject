package Controller;

import java.util.ArrayList;

import Model.RankDTO;

public class RankDAO extends UserDAO {
	
	public int insertPlayRankInfo(RankDTO rDto) {
		connect();

		int row = 0;
		String sql = "INSERT INTO TB_CW_RANK (ID, NICK_NAME, PLAY_DAYS, MONEY) VALUES (?, ?, ?, ?)";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, rDto.getId());
			psmt.setString(2, rDto.getNickName());
			psmt.setInt(3, rDto.getPlayDays());
			psmt.setInt(4, rDto.getMoney());

			row = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return row;
	}
	
	public ArrayList<RankDTO> selectRankAll() {
		connect();
		
		ArrayList<RankDTO> list = new ArrayList<RankDTO>();
		String sql = "SELECT RANK() OVER(ORDER BY PLAY_DAYS ASC, MONEY DESC), ID, NICK_NAME, PLAY_DAYS, MONEY FROM TB_CW_RANK";
		
		try {
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			
			while (rs.next()) {
				list.add(new RankDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
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
