package util;

public class NoFlashcardSelectedExeption extends Exception {
	private String error;

	public NoFlashcardSelectedExeption() {
		super("In dem Deck befinden sich keine gespeicherten Karteikarten. Bitte fügen sie Karteikarten hinzu");
		error = "In dem Deck befinden sich keine gespeicherten Karteikarten. Bitte fügen sie Karteikarten hinzu";
	}

	public String getError() {
		return error;
	}
}
