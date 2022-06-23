package model;

public class Flashcard {

	private String question;
	private String answer;
	private int index;

	public Flashcard(int index, String q, String a) {
		this.index = index;
		this.question = q;
		this.answer = a;

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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
