package util;

public class NoDeckSelectedExeption extends Exception {
	String error;
	public NoDeckSelectedExeption() {
		super("Kein Deck ist ausgewählt");
		error = "Kein Deck ist ausgewählt";
	}
	public String getError() {
		return error;
	}
	
}
