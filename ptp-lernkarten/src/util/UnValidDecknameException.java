package util;

public class UnValidDecknameException extends Exception {
	public UnValidDecknameException() {
		super("Der Deckname muss mindestens ein Zeichen beinhalten. Folgende Zeichen sind nicht erlaubt: *, &, %, . ");
	}

	@Override
	public String toString() {
		return "Der Deckname muss mindestens ein Zeichen beinhalten. Folgende Zeichen sind nicht erlaubt: *, &, %, . ";
	}
}
