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

import util.DeckIsExistingException;
import util.UnValidDecknameException;

public class DeckOrganizer {

	private HashMap<String, Deck> decks;
	private static Path pathDirectory; // aktuelles deck

	public DeckOrganizer() {
		decks = new HashMap<String, Deck>();
		createDirectories();
	}

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

	private static boolean decknameIsNotExisting(String deckname) throws DeckIsExistingException {
		boolean result = true;
		for (File f : pathDirectory.toFile().listFiles()) {
			if (f.getName().equals(deckname + ".csv")) {
				throw new DeckIsExistingException();
			}
		}
		System.out.println(result);
		return result;
	}

	void addDeck(String deckname) throws UnValidDecknameException, DeckIsExistingException {

		if (decknameIsValidRegex(deckname) && decknameIsNotExisting(deckname)) {
			Deck newDeck = new Deck(deckname);
			decks.put(newDeck.getDeckname(), newDeck);
			newDeck.saveDeckCSV();
		}

	}

	void loadDeck(Deck d) {
		decks.put(d.getDeckname(), d);
	}

	boolean isExisting(String deckname) {
		return decks.containsKey(deckname) && deckname != null;
	}

	Deck getDeck(String deckname) {
		return decks.get(deckname);
	}

	HashMap<String, Deck> getDecks() {
		return decks;
	}

	void setDecks(HashMap<String, Deck> decks) {
		this.decks = decks;
	}

	void removeDeck(String deckname) {
		if (isExisting(deckname)) {
			getDeck(deckname).deleteDeckCSV();
			decks.remove(deckname);
		}
	}

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

	String pathToFileName(Path path) {
		int length = path.getFileName().toString().length() - 4;
		String name = path.getFileName().toString().substring(0, length);
		return name;
	}

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
					newdeck.loadFlashcard(new Flashcard(values[1], values[2]));
				}
				loadDeck(newdeck);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	void createDirectories() {

		String path = System.getProperty("user.home");
		pathDirectory = Paths.get(path, "decks");
		try {
			Files.createDirectories(pathDirectory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static String getPathtoString() {
		String path = System.getProperty("user.home");
		pathDirectory = Paths.get(path, "decks");
		return pathDirectory.toString();
	}

	static Path getPathDirectory() {
		return pathDirectory;
	}

	static void setPathDirectory(Path pathDirectory) {
		DeckOrganizer.pathDirectory = pathDirectory;
	}

	boolean containsDeck(String deckname) {
		return decks.containsKey(deckname);
	}

	boolean isEmpty() {
		return decks.isEmpty();
	}

}
