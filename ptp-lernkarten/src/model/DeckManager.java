package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class DeckManager extends Observable {

	private HashMap<String, Deck> decks;
	private static Path pathDirectory;
	private int anzahlDecks;

	public DeckManager() {
		decks = new HashMap<String, Deck>();
		anzahlDecks = 0;
		createDirectories();

	}

	public void addDeck(Deck d) {
		anzahlDecks++;
		decks.put(d.getDeckname(), d);

		notifyObserver();
	}

	public Deck getDeck(String deckname) {
		return decks.get(deckname);
	}

	public HashMap<String, Deck> getDecks() {
		return decks;
	}

	public void setDecks(HashMap<String, Deck> decks) {
		this.decks = decks;
		notifyObserver();
	}

	public void removeDeck(String deckname) {
		decks.remove(deckname);
	}

	public String[] findAllFilesInFolder(File folder) {
		String[] paths = new String[folder.listFiles().length];
		for (int i = 0; i < folder.listFiles().length; i++) {
			paths[i] = folder.listFiles()[i].toString();
		}
		for (String s : paths) {
			System.out.println(s);
		}
		System.out.println("--------------------------------------------------");
		return paths;
	}

	public Path[] findAllFilesInFolderPath(File folder) {
		Path[] paths = new Path[folder.listFiles().length];
		for (int i = 0; i < folder.listFiles().length; i++) {
			paths[i] = folder.listFiles()[i].toPath();
		}
		for (Path s : paths) {
			System.out.println(s);
		}
		System.out.println("--------------------------------------------------");
		return paths;
	}

	public String pathToFileName(Path path) {
		int length = path.getFileName().toString().length() - 4;
		String name = path.getFileName().toString().substring(0, length);
		return name;
	}

//TODO nehme eine Methode
	public void getData(File folder) {
		String[] paths = findAllFilesInFolder(folder);
		Path[] paths1 = findAllFilesInFolderPath(folder);
		String line = "";
		for (int i = 0; i < paths.length; i++) {
			Deck newdeck = new Deck(pathToFileName(paths1[i]));
			try {
				BufferedReader br = new BufferedReader(new FileReader(paths[i]));
				while ((line = br.readLine()) != null) {
					String[] values = line.split(";");
					newdeck.addFlashcard(new Flashcard(Integer.parseInt(values[0]), values[1], values[2]));
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			addDeck(newdeck);
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
