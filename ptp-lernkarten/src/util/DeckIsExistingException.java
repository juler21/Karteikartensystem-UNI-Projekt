package util;

public class DeckIsExistingException extends Exception {

	private static final long serialVersionUID = -3909281602067006704L;

	public DeckIsExistingException() {
		super("Der Deckname ist schon vorhanden. Wählen sie einen anderen Decknamen.");
	}

	@Override
	public String toString() {
		return "Der Deckname ist schon vorhanden. Wählen sie einen anderen Decknamen.";
	}
}
