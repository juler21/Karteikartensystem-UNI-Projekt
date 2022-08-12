package util;

/**
 * PTP 22 - Exception: wird bei geworfen wenn keine Karteikarte in der ComboBox gewählt ist 
 * 
 * @author J.Dillmann, M. Sterkel
 */
public class NoFlashcardSelectedExeption extends Exception {
	private static final long serialVersionUID = 2065042652578480369L;
	private String error;

	public NoFlashcardSelectedExeption() {
		super("In dem Deck befinden sich keine gespeicherten Karteikarten. Bitte fügen sie Karteikarten hinzu");
		error = "In dem Deck befinden sich keine gespeicherten Karteikarten. Bitte fügen sie Karteikarten hinzu";
	}

	public String getError() {
		return error;
	}
}
