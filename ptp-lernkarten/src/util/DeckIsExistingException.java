package util;

public class DeckIsExistingException extends Exception {
	public DeckIsExistingException() {
		super("Der Deckname ist schon vorhanden. Wählen sie einen anderen Decknamen.");
	}

	@Override
	public String toString() {
		return "Der Deckname ist schon vorhanden. Wählen sie einen anderen Decknamen.";
	}
}
