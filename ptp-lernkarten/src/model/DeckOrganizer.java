package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import util.DeckIsExistingException;
import util.UnValidDecknameException;

/**
 * * PTP 22- Karteikartenlernprogramm Der DeckOrganizer ist für die Verwaltung
 * und Speicherung der Decks zuständig.
 * 
 * @author J.Dillmann, M. Sterkel
 */
public class DeckOrganizer {

	private HashMap<String, Deck> decks;
	private static Path pathDirectory;

	/**
	 * Im Konstruktor die HashMap erstellt. Außerdem wird der Ordner decks erstellt,
	 * wo die Decks als CSV Datei gespeichert werden mit getData werden alle CSV
	 * Dateien eingelesen.
	 */
	public DeckOrganizer() {
		decks = new HashMap<String, Deck>();
		createDirectories("decks");
		getData(new File(getPathtoString()));
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
	void addDeck(String deckname) throws UnValidDecknameException, DeckIsExistingException {
		if (decknameIsValidRegex(deckname) && decknameIsNotExisting(deckname)) {
			Deck newDeck = new Deck(deckname);
			decks.put(newDeck.getDeckname(), newDeck);
			newDeck.saveDeckCSV();
		}

	}

	/**
	 * Lädt ein Deck in die Map
	 * 
	 * @param deck Deck, welches geladen wird
	 */
	void loadDeck(Deck deck) {
		decks.put(deck.getDeckname(), deck);
	}

	/**
	 * Gibt wieder, ob ein Deck in der Map vorhanden ist oder nicht.
	 * 
	 * @param deckname Deckname vom dem Deck, welches geprüft werden soll
	 * 
	 * @return Ergebnis true -> Gleiches Deck ist vorhanden, false -> Deck ist noch
	 *         nicht vorhanden
	 */
	boolean isExisting(String deckname) {
		return decks.containsKey(deckname) && deckname != null;
	}

	/**
	 * Gibt das Deck wieder.
	 * 
	 * @param deckname Deckname vom dem Deck
	 * 
	 * @return Deck, das gewählte Deck
	 */
	Deck getDeck(String deckname) {
		return decks.get(deckname);
	}

	/**
	 * Gibt die HashMap wieder.
	 * 
	 * @return HashMap<String, Deck> String -> Deckname
	 */
	HashMap<String, Deck> getDecks() {
		return decks;
	}

	/**
	 * Setzt eine neue Map
	 * 
	 * @param decks neue HashMap
	 */
	void setDecks(HashMap<String, Deck> decks) {
		this.decks = decks;
	}

	/**
	 * Löscht das gewählte Deck und löscht die CSV Datei.
	 * 
	 * @param deckname Deckname vom dem neuen Deck
	 */
	void removeDeck(String deckname) {
		if (isExisting(deckname)) {
			getDeck(deckname).deleteDeckCSV();
			decks.remove(deckname);
		}
	}
	
	/**
	 * Löscht alle Decks aus dem aktuellen Verzeichnis.
	 * 
	 */
	void removeAllDecks() {
		List<String> decknames = new ArrayList<>();

		for(Entry<String, Deck> e: decks.entrySet()) {
			decknames.add((String) e.getKey());
		}
		
		for(String g: decknames) {
			removeDeck(g);
		}
	}

	/**
	 * Findet alle Dateien in einem Ordner und gibt die Dateipfade als String
	 * wieder.
	 * 
	 * @param folder Ordner
	 * @return List<String> Liste von String
	 */
	List<String> findAllFilesInFolder(File folder) {

		// Änderung noch nicht komplett NICHT LÖSCHEN geheime DS_STORE Dateien bei MAC
		List<String> paths = new ArrayList<String>();

		for (File p : folder.listFiles()) {
			System.out.println(p.getPath());
		}
		if (folder.listFiles() != null) {
			for (File p : folder.listFiles()) {
				if (p.getPath().endsWith(".csv")) {
					paths.add(p.toString());
				}
			}
		}
		return paths;
	}

	/**
	 * Findet alle Dateien in einem Ordner und gibt die Dateipfade als Path wieder.
	 * 
	 * @param folder Ordner
	 * @return List<Path> Liste von String
	 */
	List<Path> findAllFilesInFolderPath(File folder) {
		List<Path> paths = new ArrayList<Path>();
		if (folder.listFiles() != null) {
			for (File p : folder.listFiles()) {
				if (p.getPath().endsWith(".csv")) {
					paths.add(p.toPath());
				}
			}
		}
		return paths;
	}

	/**
	 * Konvertiert einen Path zu seinem Dateinamen.
	 * 
	 * @param Path Pfad
	 * @return Fateinamen als String
	 */
	String pathToFileName(Path path) {
		int length = path.getFileName().toString().length() - 4;
		String name = path.getFileName().toString().substring(0, length);
		return name;
	}

	/**
	 * Holt sich die Daten (CSV) aus dem Ordner und lädt sie in die HashMap.
	 * 
	 * @param folder Ordener, wo die Daten liegen
	 */
	void getData(File folder) {
		List<String> paths = findAllFilesInFolder(folder);
		List<Path> paths1 = findAllFilesInFolderPath(folder);
		String line = "";
		for (int i = 0; i < paths.size(); i++) {
			try {
				Deck newdeck = new Deck(pathToFileName(paths1.get(i)));
				BufferedReader br = new BufferedReader(new FileReader(paths.get(i)));
				while ((line = br.readLine()) != null) {
					String[] values = line.split(";");
					String question = values[1].replace('\\', '\n');
					String answer = values[2].replace('\\', '\n');
					newdeck.loadFlashcard(new Flashcard(question, answer));
				}
				loadDeck(newdeck);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Erstellt einen Ordner mit einem Namen im Home-Verzeichnis des Computers.
	 *
	 * @param foldername neuer Ordnername
	 */
	void createDirectories(String foldername) {

		String path = System.getProperty("user.home");
		pathDirectory = Paths.get(path, foldername);
		try {
			Files.createDirectories(pathDirectory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * gibt die aktuelle Anzahl an Decks zurück
	 * @return 
	 * 
	 * @return anzahl Decks
	 */
	public int getAmountOfDecks() {
		return decks.size();
	}

	/**
	 * Gibt den Dateipfad als String wieder.
	 * 
	 * @return Dateipfad als String
	 */
	static String getPathtoString() {
		return pathDirectory.toString();
	}

	// Getter
	static Path getPathDirectory() {
		return pathDirectory;
	}

	/**
	 * Überprüft ob die Map decks leer ist
	 * 
	 * @return Ergebnis Map ist leer -> true, Map hat mindestens ein Element ->
	 *         false
	 */
	boolean isEmpty() {
		return decks.isEmpty();
	}

	// Check Regex von deckname
	private static boolean decknameIsValidRegex(String deckname) throws UnValidDecknameException {
		if (deckname != null) {
			String pattern = "^[^*&%\s]+$";
			if (deckname.matches(pattern)) {
				return true;
			} else {
				throw new UnValidDecknameException();
			}
		}
		throw new UnValidDecknameException();
	}

	// check ob Deck schon vorhanden
	private static boolean decknameIsNotExisting(String deckname) throws DeckIsExistingException {
		boolean result = true;
		for (File f : pathDirectory.toFile().listFiles()) {
			if (f.getName().equals(deckname + ".csv")) {
				throw new DeckIsExistingException();
			}
		}
		return result;
	}

}
