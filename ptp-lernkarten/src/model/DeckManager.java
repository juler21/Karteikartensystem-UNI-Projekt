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

public class DeckManager extends Observable {

	private HashMap<String, Deck> decks;
	private static Path pathDirectory;
	private int anzahlDecks;

	public DeckManager() {
		decks = new HashMap<String, Deck>();
		anzahlDecks = 0;
		createDirectories();

	}

	private static boolean decknameisValidRegex(String deckname) throws UnValidDecknameException {

		String pattern = "^[^*&%\s]+$";
		if (deckname.matches(pattern)) {
			return true;
		} else {
			throw new UnValidDecknameException();
		}
	}

//TODO Hier muss die instanz von einem Deckerstellt werden. 
	public void addDeck(String deckname) throws UnValidDecknameException {

		if (decknameisValidRegex(deckname)) {
			Deck newDeck = new Deck(deckname);
			anzahlDecks++;
			decks.put(newDeck.getDeckname(), newDeck);
			newDeck.saveDeckCSV();
			notifyObserver("deckChange");
		}

	}

	public void loadDeck(Deck d) {
		anzahlDecks++;
		decks.put(d.getDeckname(), d);
		notifyObserver("deckChange");
	}

	public boolean isExisting(String deckname) {
		return decks.containsKey(deckname);
	}

	public Deck getDeck(String deckname) {
		return decks.get(deckname);
	}

	public HashMap<String, Deck> getDecks() {
		return decks;
	}

	public void setDecks(HashMap<String, Deck> decks) {
		this.decks = decks;
		notifyObserver("deckChange");
	}

	public void removeDeck(String deckname) {
		getDeck(deckname).deleteDeckCSV();
		decks.remove(deckname);
		notifyObserver("deckChange");

	}

	public List<String> findAllFilesInFolder(File folder) {

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

	public List<Path> findAllFilesInFolderPath(File folder) {
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

	public String pathToFileName(Path path) {
		int length = path.getFileName().toString().length() - 4;
		String name = path.getFileName().toString().substring(0, length);
		return name;
	}

//TODO nehme eine Methode
	public void getData(File folder) {
		List<String> paths = findAllFilesInFolder(folder);
		List<Path> paths1 = findAllFilesInFolderPath(folder);
		String line = "";
		for (int i = 0; i < paths.size(); i++) {

			try {
				Deck newdeck = new Deck(pathToFileName(paths1.get(i)));
				BufferedReader br = new BufferedReader(new FileReader(paths.get(i)));
				if (br.readLine() != "") {
					while ((line = br.readLine()) != null) {
						String[] values = line.split(";");
						newdeck.loadFlashcard(new Flashcard(values[1], values[2]));
					}
				}
				loadDeck(newdeck);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void createDirectories() {

		String path = System.getProperty("user.home");
		pathDirectory = Paths.get(path, "decks");
		try {
			Files.createDirectories(pathDirectory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getPathtoString() {
		String path = System.getProperty("user.home");
		pathDirectory = Paths.get(path, "decks");
		return pathDirectory.toString();
	}

}
