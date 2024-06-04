package Model;

public class QuizDTO {
	private int quizSq;
	private String quizCd;
	private String quizPart;
	private String question;
	private String answer;
	
	public QuizDTO(int quizSq, String quizCd, String quizPart, String question, String answer) {
		this.quizSq = quizSq;
		this.quizCd = quizCd;
		this.quizPart = quizPart;
		this.question = question;
		this.answer = answer;
	}

	public int getQuizSq() {
		return quizSq;
	}

	public void setQuizSq(int quizSq) {
		this.quizSq = quizSq;
	}

	public String getQuizCd() {
		return quizCd;
	}

	public void setQuizCd(String quizCd) {
		this.quizCd = quizCd;
	}

	public String getQuizPart() {
		return quizPart;
	}

	public void setQuizPart(String quizPart) {
		this.quizPart = quizPart;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	
}
