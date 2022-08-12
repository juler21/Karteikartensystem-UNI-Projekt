package model;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import util.DeckIsExistingException;
import util.UnValidDecknameException;
import util.UnvalidQAException;

/**
 * * PTP 22- Karteikartenlernprogramm API model Der DeckManager bildet die
 * Schnittstelle vom model im MVC-Ansatz. Die Klasse erbt von Observable und
 * benachrichtigt bei Änderungen alle Observer.
 * 
 * @author J.Dillmann, M. Sterkel
 */
public class DeckManager extends Observable {

	DeckOrganizer deckorganizer;

	/**
	 * Im Konstruktor wird eine Instanz der Klasse DeckOrganizer erstellt.
	 */
	public DeckManager() {
		deckorganizer = new DeckOrganizer();
	}

	/**
	 * Erstellt ein neues Deck mit Decknamen. Es wird eine CSV Datei erstellt und in
	 * den Ordner hinzugefügt.
	 * 
	 * @param deckname Deckname vom dem neuen Deck
	 *
	 * @throws UnValidDecknameException deckname darf nicht null oder leer sein.
	 *                                  Folgende Zeichen sind nicht erlaubt: *, &,
	 *                                  %, ., Leerzeichen
	 * 
	 * @throws DeckIsExistingException  deckname darf nicht schon einmal gewählt
	 *                                  worden sein.
	 */
	public void addDeck(String deckname) throws UnValidDecknameException, DeckIsExistingException {
		deckorganizer.addDeck(deckname);
		notifyObserver("deckChange");
	}

	/**
	 * Gibt das Deck wieder.
	 * 
	 * @param deckname Deckname vom dem Deck
	 * 
	 * @return Deck, das gewählte Deck
	 */
	public Deck getDeck(String deckname) {
		return deckorganizer.getDeck(deckname);
	}

	/**
	 * Löscht das gewählte Deck und löscht die CSV Datei.
	 * 
	 * @param deckname Deckname vom dem neuen Deck
	 */
	public void removeDeck(String deckname) {
		deckorganizer.removeDeck(deckname);
		notifyObserver("deckChange");
	}

	/**
	 * Holt sich die Daten (CSV) aus dem Ordner und lädt sie in die HashMap.
	 * 
	 * @param folder Ordener, wo die Daten liegen
	 */
	public void getData(File folder) {
		deckorganizer.getData(folder);
	}

	/**
	 * Gibt die Liste der Karteikarten eines Decks wieder.
	 * 
	 * @param deckname Deckname vom dem neuen Deck
	 * 
	 * @return List<Flashcard> Karteikartenliste
	 */
	public List<Flashcard> getFlashcardList(String deckname) {
		return getDeck(deckname).getDeckFlashcardlist();
	}

	/**
	 * Gibt die Antwort einer Karteikarte wieder.
	 * 
	 * @param deckname Deckname vom dem Deck
	 * 
	 * @param position Position der Karteikarte
	 * 
	 * @return Antwort
	 */
	public String getAnswerFlashcard(String deckname, int position) {
		return getDeck(deckname).getFlashcard(position).getAnswer();
	}

	/**
	 * Gibt die Frage einer Karteikarte wieder.
	 * 
	 * @param deckname Deckname vom dem Deck
	 * 
	 * @param position Position der Karteikarte
	 * 
	 * @return Frage
	 */
	public String getQuestionFlashcard(String deckname, int position) {
		return getDeck(deckname).getFlashcard(position).getQuestion();
	}

	/**
	 * Gibt die Karteikarte aus dem gewählten Deck wieder.
	 * 
	 * @param deckname Deckname vom dem Deck
	 * 
	 * @param position Position der Karteikarte in der Liste
	 * 
	 * @return Flashcard die entsprechende Karteikarte
	 */
	public Flashcard getFlashcard(String deckname, int position) {
		return getDeck(deckname).getFlashcard(position);
	}

	/**
	 * Löscht eine Karteikarte aus dem gewählten Deck und löscht die entsprechende
	 * Zeile.
	 * 
	 * @param deckname Deckname vom dem Deck, wo eine Karteikarte gelöscht werden
	 *                 soll
	 * 
	 * @param position Position der Karteikarte in der Liste
	 */
	public void deleteFlashcard(String deckname, int position) {
		getDeck(deckname).deleteFlashcard(position);
		notifyObserver("flashcardChange");
	}

	/**
	 * Löscht alle Karteikarten aus einem Deck
	 * 
	 * @param deckname Deckname vom dem Deck, wo alle Karteikarte gelöscht werden
	 *                 sollen
	 */
	public void removeAllFlashcards(String deckname) {
		getDeck(deckname).deleteAllFlashcards();
		notifyObserver("flashcardChange");
	}

	/**
	 * Fügt einem Deck eine Karteikarte mit Frage und Antwort hinzu. Wirft eine
	 * UnvalidQAException. Fügt eine Zeile in der CSV Datei mit den Daten hinzu.
	 * 
	 * @param deckname Deckname vom dem Deck, wo eine Karteikarte hinzugefügt werden
	 *                 soll
	 * 
	 * @param question Frage, welche hinzugefügt wird
	 * 
	 * @param answer   Antwort die der Karteikarte hinzugefügt wird
	 * 
	 * @throws UnvalidQAException wirft eine Exception, wenn mindestens ein Feld
	 *                            null oder leer ist.
	 */
	public void addFlashcard(String deckname, String question, String answer) throws UnvalidQAException {
		getDeck(deckname).addFlashcard(question, answer);
		notifyObserver("flashcardChange");

	}

	/**
	 * Gibt die Anzahl der Karteikarten in einem Deck wieder.
	 * 
	 * @param deckname Deckname vom dem Deck
	 * 
	 * @return Karteikartenanzahl
	 */
	public int getAmountOfFlashcards(String deckname) {
		return getDeck(deckname).getAmountOfFlashcards();
	}

	/**
	 * Gibt wieder, ob ein Deck in der Map vorhanden ist oder nicht.
	 * 
	 * @param deckname Deckname vom dem Deck, welches geprüft werden soll
	 * 
	 * @return Ergebnis true -> Gleiches Deck ist vorhanden, false -> Deck ist noch
	 *         nicht vorhanden
	 */
	public boolean containsDeck(String deckname) {
		return deckorganizer.isExisting(deckname);

	}

	/**
	 * Setzt die Frage einer Karteikarte neu.
	 * 
	 * @param flashcard Karteikarte
	 * 
	 * @param question  neue Frage
	 */
	public void setQuestion(Flashcard flashcard, String question) {
		flashcard.setQuestion(question);
		notifyObserver("deckChange");
	}

	/**
	 * Setzt die Antwort einer Karteikarte neu.
	 * 
	 * @param flashcard Karteikarte
	 * 
	 * @param answer    neue Antwort
	 */
	public void setAnswer(Flashcard flashcard, String answer) {
		flashcard.setAnswer(answer);
		notifyObserver("deckChange");
	}

	/**
	 * Löscht die CSV Datein von dem gewählten Deck und läft eine neue CSV Datei.
	 * 
	 * @param deckname Deckname des Decks
	 */
	public void updateCSV(String deckname) {
		getDeck(deckname).deleteDeckCSV();
		getDeck(deckname).saveDeckCSV();
	}

	/**
	 * Gibt die Frage einer Karteikarte wieder.
	 * 
	 * @param flashcard Karteikarte
	 * 
	 * @return Frage
	 */
	public String getQuestion(Flashcard flashcard) {
		return flashcard.getQuestion();
	}

	/**
	 * Gibt die Antwort einer Karteikarte wieder.
	 * 
	 * @param flashcard Karteikarte
	 * 
	 * @return Antwort
	 */
	public String getAnswer(Flashcard flashcard) {
		return flashcard.getAnswer();
	}

	/**
	 * Gibt den Dateipfad als String des Ordners wieder, wo die Decks als CSV
	 * Dateien gespeichert werden.
	 *
	 * @return Dateipfad
	 */
	public static String getPathtoString() {
		return DeckOrganizer.getPathtoString();
	}

	/**
	 * Ändert den Ordnername des Ordners, wo die Decks gespeichert werden.
	 *
	 * @param foldername neuer Ordnername
	 */
	public void changeDirectory(String foldername) {
		deckorganizer.createDirectories(foldername);
	}

	/**
	 * Überprüft ob mindestens ein Deck abgespeichert ist, also ob die Map Elemente
	 * enthält.
	 * 
	 *
	 * @return Ergebnis kein Deck vorhanden -> true, mindestens ein Deck vorhanden
	 *         -> false
	 */
	public boolean decksisEmpty() {
		return deckorganizer.isEmpty();
	}

	/**
	 * Gibt die HashMap wieder.
	 * 
	 * @return HashMap<String, Deck> String -> Deckname
	 */
	public HashMap<String, Deck> getDecks() {
		return deckorganizer.getDecks();
	}

}
