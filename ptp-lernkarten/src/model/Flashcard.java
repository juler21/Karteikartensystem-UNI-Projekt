package model;

public class Flashcard {

	private String question;
	private String answer;

	public Flashcard(String q, String a) {
//		this.index = index;
		this.question = q;
		this.answer = a;

	}

	String getQuestion() {
		return question;
	}

	void setQuestion(String question) {
		this.question = question;
	}

	String getAnswer() {
		return answer;
	}

	void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return question;
	}

}
