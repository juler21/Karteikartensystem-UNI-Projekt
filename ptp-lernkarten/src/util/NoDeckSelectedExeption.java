package util;

public class NoDeckSelectedExeption extends Exception {

	private static final long serialVersionUID = 883439657634690636L;
	private String error;

	public NoDeckSelectedExeption() {
		super("Kein Deck ist ausgewählt. Bitte erstellen Sie erst ein Deck!");
		error = "Kein Deck ist ausgewählt. Bitte erstellen Sie erst ein Deck!";
	}

	public String getError() {
		return error;
	}

}
