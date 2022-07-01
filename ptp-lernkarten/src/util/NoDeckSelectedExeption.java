package util;

public class NoDeckSelectedExeption extends Exception {
	private String error;

	public NoDeckSelectedExeption() {
		super("Kein Deck ist ausgewählt. Bitte erstellen Sie erst ein Deck!");
		error = "Kein Deck ist ausgewählt. Bitte erstellen Sie erst ein Deck!";
	}

	public String getError() {
		return error;
	}

}
