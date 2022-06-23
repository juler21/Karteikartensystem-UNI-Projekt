package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Deck {
	private List<Flashcard> deck = new ArrayList<Flashcard>();
	private String deckname;

	public Deck(String deck) {
		deckname = deck;

	}

	public void addFlashcard(Flashcard flash) {
		deck.add(flash);
	}

	public Flashcard getFlashcard(int index) {
		return deck.get(index);
	}

	public void loadInCSV() {
		DeckManager.createDirectories();

		Path pathCSV = Paths.get(DeckManager.createDirectories(), deckname + ".csv");

		try (BufferedWriter writeBuffer = Files.newBufferedWriter(pathCSV);) {
			for (Flashcard f : deck) {
				System.out.println(f.getIndex());
				String row = String.format("%d;%s;%s%n", f.getIndex(), f.getQuestion(), f.getAnswer());
				writeBuffer.write(row);
			}

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public List<Flashcard> getDeck() {
		return deck;
	}

	public String getDeckname() {
		return deckname;
	}

	public void setDeckname(String deckname) {
		this.deckname = deckname;
	}

}
