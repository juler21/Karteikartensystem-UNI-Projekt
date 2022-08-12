package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import util.UnvalidQAException;

/**
 * * PTP 22- Karteikartenlernprogramm Das Deck ist für die Verwaltung und
 * Speicherung von Karteikarten zuständig.
 * 
 * @author J.Dillmann, M. Sterkel
 */
public class Deck {

	private List<Flashcard> flashcardList = new ArrayList<Flashcard>(); // Liste von Karteikarten
	private String deckname; // Deckname

	/**
	 * Im Konstruktor wird dem Deck der Deckname zugeordnet
	 */
	public Deck(String deckname) {
		this.deckname = deckname;
	}

	/**
	 * Fügt dem Deck eine Karteikarte mit Frage und Antwort hinzu. Wirft eine
	 * UnvalidQAException. Löscht die CSV Datei und lädt sie neu.
	 * 
	 * @param question Frage, welche hinzugefügt wird
	 * @param answer   Antwort die der Karteikarte hinzugefügt wird
	 * 
	 * @throws UnvalidQAException wirft eine Exception, wenn mindestens ein Feld
	 *                            null oder leer ist.
	 */
	void addFlashcard(String question, String answer) throws UnvalidQAException {
		if (checkQAText(question) && checkQAText(answer)) {
			Flashcard flash = new Flashcard(question, answer);
			flashcardList.add(flash);
			deleteDeckCSV();
			saveDeckCSV();
		}
	}

	/**
	 * Löscht alle Karteikarten aus dem Deck.
	 */
	void deleteAllFlashcards() {
		flashcardList.removeAll(flashcardList);
	}

	/**
	 * Fügt eine Karteikarte der Liste.
	 * 
	 * @param flashcard Karteikarte, welche hinzugefügt wird
	 */
	void loadFlashcard(Flashcard flashcard) {
		flashcardList.add(flashcard);
	}

	/**
	 * Gibt die Karteikarte aus dem Deck wieder.
	 * 
	 * @param index Position der Karteikarte in der Liste
	 * @return Flashcard die entsprechende Karteikarte
	 */
	Flashcard getFlashcard(int index) {
		return flashcardList.get(index);
	}

	/**
	 * Löscht eine Karteikarte aus dem Deck und löscht die CSV Datei und lädt sie
	 * neu
	 * 
	 * @param index Position der Karteikarte in der Liste
	 */
	void deleteFlashcard(int index) {
		flashcardList.remove(index);
		deleteDeckCSV();
		saveDeckCSV();
	}

	/**
	 * Sichert das Deck als CSV Datei in dem gewählten Ordner. der Deckname ist der
	 * Dateiname von Der CSV Datei. Die einzelnen Karteikarten werden als Zeilen
	 * dargestellt. Die Elemente index, Frage und Antwort werden durch Semikolons
	 * getrennt.
	 */
	void saveDeckCSV() {

		Path pathCSV = Paths.get(DeckOrganizer.getPathtoString(), deckname + ".csv");
		String pathCSVString = pathCSV.toString();

		try {
			BufferedWriter writeBuffer = new BufferedWriter(new FileWriter(pathCSVString, true));
			if (getAmountOfFlashcards() > 0) {
				for (Flashcard f : flashcardList) {
					String questionString = f.getQuestion().replace('\n', '\\');
					String answerString = f.getAnswer().replace('\n', '\\');
					String row = String.format("%d;%s;%s%n", flashcardList.indexOf(f), questionString, answerString);

					writeBuffer.append(row);
				}
			}
			writeBuffer.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Löscht die CSV Datei aus dem Ordner.
	 */
	void deleteDeckCSV() {
		Path pathCSV = Paths.get(DeckOrganizer.getPathtoString(), deckname + ".csv");
		try {
			Files.deleteIfExists(pathCSV);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Gibt die Anzahl der Karteikarten des Decks wieder.
	 * 
	 * @return Karteikartenanzahl
	 */
	int getAmountOfFlashcards() {
		return flashcardList.size();
	}

	/**
	 * Gibt die Liste der Karteikarten wieder.
	 * 
	 * @return Liste Karteikarten
	 */
	List<Flashcard> getDeckFlashcardlist() {
		return flashcardList;
	}

	/**
	 * Gibt den Deckname wieder.
	 * 
	 * @return Deckname
	 */
	String getDeckname() {
		return deckname;
	}

	/**
	 * Setzt den Deckname neu
	 * 
	 * @param deckname neuer Deckname
	 */
	void setDeckname(String deckname) {
		this.deckname = deckname;
	}

	// checkt ob der Text null oder leer ist. Sonst wirft er eine Exeption
	private boolean checkQAText(String text) throws UnvalidQAException {

		if (!text.isBlank() && text != null) {
			return true;
		} else {
			throw new UnvalidQAException();
		}
	}

	@Override
	public String toString() {
		return deckname;
	}

}
