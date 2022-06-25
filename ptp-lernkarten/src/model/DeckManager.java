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

public class DeckManager {

	private static HashMap<Integer, Deck> decks = new HashMap<Integer, Deck>();
	private static Path pathDirectory;
	private static int anzahlDecks = 0;

	public DeckManager() {

	}

	public static void addDeck(Deck d) {
		anzahlDecks++;
		decks.put(anzahlDecks, d);
	}

	public static HashMap<Integer, Deck> getDecks() {
		return decks;
	}

	public static void setDecks(HashMap<Integer, Deck> decks) {
		DeckManager.decks = decks;
	}

	public static String[] findAllFilesInFolder(File folder) {
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

	public static Path[] findAllFilesInFolderPath(File folder) {
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

	public static String pathToFileName(Path path) {
		int length = path.getFileName().toString().length() - 4;
		String name = path.getFileName().toString().substring(0, length);
		return name;
	}

//TODO nehme eine Methode
	public static void getData(File folder) {
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

	public static void createDirectories() {

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
