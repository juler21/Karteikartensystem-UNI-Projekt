package util;

import javax.swing.UIManager;

import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;

import model.Deck;
import model.DeckManager;
import model.Flashcard;
import view.MainGui;

/**
 * * PTP 22 - Karteikartenlernprogramm Applikation zum Erstellen und Lernen von
 * Karteikarten.
 * 
 * Application Programming Interface
 * 
 * @author J. Dillmann, M. Sterkel
 * @version 7.0 03.03.08 mod. AH 25.03.08
 */
public class Main {

	private DeckManager deckmanager; // model

	/**
	 * Der Konstruktor erstellt den Deckmanager (model) und die MainGUI (view). Der
	 * DeckManager wird der GUI übergeben
	 */
	public Main() {
		deckmanager = new DeckManager();
		String name = "Karteikarten einfach lernen!";
		new MainGui(deckmanager, name);

	}

	/**
	 * Hier wird das Programm gestartet, indem man den Konstruktor der MAIN aufruft.
	 * Es wird durch Look and Feel ein Theme von FlatLaf geladen und die Ecken der
	 * Elemente abgerundet. MacOS spezifisch: Passt das Colortheme des Frames an den
	 * gewählten ColorModus des Systems an. Erscheinungsbild "dunkel" -> setzt
	 * Framefarbe auf dunkel Erscheinungsbild "hell" -> setzt Framefarbe auf hell
	 */
	public static void main(String[] args) {
		// themeSetUp
		System.setProperty("apple.awt.application.appearance", "system");
		// FlatLaf setzen Light Theme und Ecken abrunden
		FlatLightFlatIJTheme.setup();
		UIManager.put("Button.arc", 12);
		UIManager.put("Component.arc", 12);
		UIManager.put("TextComponent.arc", 12);
		UIManager.put("TextArea.arc", 12);
		UIManager.put("ScrollPane.arc", 12);

		new Main();
	}

	/*
	 * Fügt einem Deck eine Karteikarte mit Frage und Antwort hinzu. Wirft eine
	 * UnvalidQAException. Fügt eine Zeile in der CSV Datei mit den Daten hinzu.
	 * 
	 * @param deckname Deckname vom dem Deck, wo eine Karteikarte hinzugefügt werden
	 * soll
	 * 
	 * @param question Frage, welche hinzugefügt wird
	 * 
	 * @param answer Antwort die der Karteikarte hinzugefügt wird
	 *
	 * @throws UnvalidQAException wirft eine Exception, wenn mindestens ein Feld
	 * null oder leer ist.
	 */
	public void addFlashcard(String deckname, String question, String answer) throws UnvalidQAException {
		deckmanager.addFlashcard(deckname, question, answer);
	}

	/*
	 * Löscht eine Karteikarte aus dem gewählten Deck und löscht die entsprechende
	 * Zeile.
	 * 
	 * @param deckname Deckname vom dem Deck, wo eine Karteikarte gelöscht werden
	 * soll
	 * 
	 * @param position Position der Karteikarte in der Liste
	 */
	public void deleteFlashcard(String deckname, int position) {
		deckmanager.deleteFlashcard(deckname, position);
	}

	/*
	 * Löscht alle Karteikarten aus einem Deck
	 * 
	 * @param deckname Deckname vom dem Deck, wo alle Karteikarte gelöscht werden
	 * sollen
	 */
	public void removeAllFlashcards(String deckname) {
		deckmanager.removeAllFlashcards(deckname);
	}

	/*
	 * Gibt die Karteikarte aus dem gewählten Deck wieder.
	 * 
	 * @param deckname Deckname vom dem Deck
	 * 
	 * @param position Position der Karteikarte in der Liste
	 * 
	 * @return Flashcard die entsprechende Karteikarte
	 */
	public Flashcard getFlashcard(String deckname, int position) {
		return deckmanager.getFlashcard(deckname, position);
	}

	/*
	 * Erstellt ein neues Deck mit Decknamen. Es wird eine CSV Datei erstellt und in
	 * den Ordner hinzugefügt.
	 * 
	 * @param deckname Deckname vom dem neuen Deck
	 *
	 * @throws UnValidDecknameException deckname darf nicht null oder leer sein.
	 * Folgende Zeichen sind nicht erlaubt: *, &, %, ., Leerzeichen
	 * 
	 * @throws DeckIsExistingException deckname darf nicht schon einmal gewählt
	 * worden sein.
	 */
	public void createDeck(String deckname) throws UnValidDecknameException, DeckIsExistingException {
		deckmanager.addDeck(deckname);
	}

	/*
	 * Gibt das Deck wieder.
	 * 
	 * @param deckname Deckname vom dem Deck
	 * 
	 * @return Deck, das gewählte Deck
	 */
	public Deck getDeck(String deckname) {
		return deckmanager.getDeck(deckname);
	}

	/*
	 * Löscht das gewählte Deck und löscht die CSV Datei.
	 * 
	 * @param deckname Deckname vom dem neuen Deck
	 */
	public void deleteDeck(String deckname) {
		deckmanager.removeDeck(deckname);
	}

	/*
	 * Ändert den Ordnername, wo die DeckCSV Dateien gespeichert werden.
	 * 
	 * @param foldername der neue Ordername
	 */
	public void changeFoldername(String foldername) {
		deckmanager.changeDirectory(foldername);
	}

	/*
	 * Gibt die Antwort einer Karteikarte wieder.
	 * 
	 * @param deckname Deckname vom dem Deck
	 * 
	 * @param position Position der Karteikarte
	 * 
	 * @return Antwort
	 */
	public String getAnswer(String deckname, int position) {
		return deckmanager.getAnswerFlashcard(deckname, position);
	}

	/*
	 * Gibt die Frage einer Karteikarte wieder.
	 * 
	 * @param deckname Deckname vom dem Deck
	 * 
	 * @param position Position der Karteikarte
	 * 
	 * @return Frage
	 */
	public String getQuestion(String deckname, int position) {
		return deckmanager.getQuestionFlashcard(deckname, position);
	}

	/*
	 * Gibt die Anzahl der Karteikarten in einem Deck wieder.
	 * 
	 * @param deckname Deckname vom dem Deck
	 * 
	 * @return Karteikartenanzahl
	 */
	public int getAmountFlashcards(String deckname) {
		return deckmanager.getAmountOfFlashcards(deckname);
	}

	/*
	 * Gibt wieder, ob ein Deck Existiert oder nicht.
	 * 
	 * @param deckname Deckname vom dem Deck, welches geprüft werden soll
	 * 
	 * @return Ergebnis true -> Gleiches Deck ist vorhanden false -> Deck ist noch
	 * nicht vorhanden
	 */
	public boolean isDeckExisting(String deckname) {
		return deckmanager.containsDeck(deckname);
	}

	/*
	 * Gibt den Dateipfad als String des Ordners wieder, wo die Decks als CSV
	 * Dateien gespeichert werden.
	 *
	 * @return Dateipfad
	 */
	public String getPathDirectory() {
		return DeckManager.getPathtoString();
	}

}
