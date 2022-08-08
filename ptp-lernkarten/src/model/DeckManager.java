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
import util.UnvalidQAException;

public class DeckManager extends Observable {

	private HashMap<String, Deck> decks;
	private static Path pathDirectory; // aktuelles deck
	private int anzahlDecks;

	public DeckManager() {
		decks = new HashMap<String, Deck>();
		anzahlDecks = 0;
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

//TODO Hier muss die instanz von einem Deckerstellt werden. 
	public void addDeck(String deckname) throws UnValidDecknameException, DeckIsExistingException {

		if (decknameIsValidRegex(deckname) && decknameIsNotExisting(deckname)) {
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
		return decks.containsKey(deckname) && deckname != null;
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
		if (isExisting(deckname)) {
			getDeck(deckname).deleteDeckCSV();
			decks.remove(deckname);
			notifyObserver("deckChange");
		}
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
//				if (br.readLine() != "") {
				while ((line = br.readLine()) != null) {
					String[] values = line.split(";");
					newdeck.loadFlashcard(new Flashcard(values[1], values[2]));
				}
//				}
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

	public static Path getPathDirectory() {
		return pathDirectory;
	}

	public static void setPathDirectory(Path pathDirectory) {
		DeckManager.pathDirectory = pathDirectory;
	}

	public String getDeckname(Deck d) {
		return d.getDeckname();
	}

	public List<Flashcard> getFlashcardList(String deckname) {
		return getDeck(deckname).getDeckFlashcardlist();
	}

	public String getAnswerFlashcard(String deckname, int position) {
		return getDeck(deckname).getFlashcard(position).getAnswer();
	}

	public String getQuestionFlashcard(String deckname, int position) {
		return getDeck(deckname).getFlashcard(position).getQuestion();
	}

	public Flashcard getFlashcard(String deckname, int position) {
		return getDeck(deckname).getFlashcard(position);
	}

	public void deleteFlashcard(String deckname, int position) {
		getDeck(deckname).deleteFlashcard(position);
	}

	public void removeAllFlashcards(String deckname) {
		getDeck(deckname).deleteAllFlashcards();
	}

	public void addFlashcard(String deckname, String question, String answer) throws UnvalidQAException {
		getDeck(deckname).addFlashcard(question, answer);
	}

	public int getAmountOfFlashcards(String deckname) {
		return getDeck(deckname).getAmountOfFlashcards();
	}

	public boolean containsDeck(String deckname) {
		return decks.containsKey(deckname);
	}

	public void setQuestion(Flashcard flashcard, String question) {
		flashcard.setQuestion(question);
	}

	public void setAnswer(Flashcard flashcard, String answer) {
		flashcard.setAnswer(answer);
	}

	public void updateCSV(String deckname) {
		getDeck(deckname).deleteDeckCSV();
		getDeck(deckname).saveDeckCSV();
	}

	public String getQuestion(Flashcard f) {
		return f.getQuestion();
	}

	public String getAnswer(Flashcard f) {
		return f.getAnswer();
	}

}
