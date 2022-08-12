package model;

/**
 * * PTP 22- Karteikartenlernprogramm
 * 
 * Die Klasse Flashcard ist für die Speicherung der Werte Frage und Antwort
 * einer Karteikarte zuständig.
 * 
 * @author J.Dillmann, M. Sterkel
 */
public class Flashcard {

	private String question; // Frage
	private String answer; // Antwort

	/**
	 * Im Konstruktor werden Frage und Antwort initialisiert
	 */
	public Flashcard(String q, String a) {
		this.question = q;
		this.answer = a;

	}

	/**
	 * Gibt die Frage wieder.
	 * 
	 * @return Frage
	 */
	String getQuestion() {
		return question;
	}

	/**
	 * Setzt die Frage neu.
	 * 
	 * @param question Frage
	 */
	void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * Gibt die Antwort wieder.
	 * 
	 * @return Antwort
	 */
	String getAnswer() {
		return answer;
	}

	/**
	 * Setzt die Antwort neu.
	 * 
	 * @param answer Antwort
	 */
	void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return question;
	}

}
