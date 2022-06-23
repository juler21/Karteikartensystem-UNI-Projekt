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

public DeckManager() {
	
}
public static void addDeck(int index, Deck d) {
	decks.put(index, d);
}

public static HashMap<Integer, Deck> getDecks() {
	return decks;
}
public static void setDecks(HashMap<Integer, Deck> decks) {
	DeckManager.decks = decks;
}

public void getData(String path) {
	String line = "";
	HashMap<Integer, Deck> newdecks = new HashMap<Integer, Deck>();
	Deck newdeck = new Deck("path");
	try {
		BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null) {
				String[] values = line.split(",");
				newdeck.addFlashcard(new Flashcard(Integer.parseInt(values[0]),values[1], values[2]));
			}
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public static String createDirectories() {

	String path = System.getProperty("user.home");
	Path pathDirectory = Paths.get(path, "decks");
	try {
		Files.createDirectories(pathDirectory);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
System.out.println(pathDirectory);	

return pathDirectory.toString();	
}
 
}

