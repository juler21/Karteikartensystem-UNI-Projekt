package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import view.Observer;

public class Deck extends Observable {
	private List<Flashcard> deck = new ArrayList<Flashcard>();
	private String deckname;
	private int fragenIndex;

	public Deck(String deck) {
		deckname = deck;
		saveDeckCSV();
	}

	public void addFlashcard(Flashcard flash) {
		deck.add(flash);
		saveFlashcardCSV(flash);
	}

	public Flashcard getFlashcard(int index) {
		return deck.get(index);
	}

	public void saveDeckCSV() {

		Path pathCSV = Paths.get(DeckManager.getPathtoString(), deckname + ".csv");

		try {
			BufferedWriter writeBuffer = Files.newBufferedWriter(pathCSV);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void saveFlashcardCSV(Flashcard f) {

		Path pathCSV = Paths.get(DeckManager.getPathtoString(), deckname + ".csv");

		try (BufferedWriter writeBuffer = Files.newBufferedWriter(pathCSV);) {

			String row = String.format("%d;%s;%s%n", f.getIndex(), f.getQuestion(), f.getAnswer());
			writeBuffer.write(row);

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public int getAmountOfFlashcards() {
		return deck.size();
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

	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteObserver(Observer o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub

	}

}
