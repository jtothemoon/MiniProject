package Controller;

import Model.PlayDTO;
import Model.QuizDTO;

public class QuizDAO extends UserDAO {
	public QuizDTO makeQuiz(PlayDTO dto, int random) {
		connect();

		QuizDTO qDto = null;
		String sql = "SELECT QUIZ_SQ, QUIZ_PART, QUESTION, ANSWER FROM TB_CW_QUIZ WHERE QUIZ_SQ = ?";
		
		try {
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, random);

			rs = psmt.executeQuery();
			if (rs.next() == true) {
				qDto = new QuizDTO(rs.getInt("QUIZ_SQ"), "", rs.getString("QUIZ_PART"), rs.getString("QUESTION"), rs.getString("ANSWER"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();

		}
		return qDto;
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
