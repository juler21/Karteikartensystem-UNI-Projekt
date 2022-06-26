package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Deck extends Observable {

	private List<Flashcard> flashcardList = new ArrayList<Flashcard>();

	private String deckname;

	public Deck(String deck) {
		deckname = deck;
	}

	public void addFlashcard(Flashcard flash) {
		flashcardList.add(flash);
		saveFlashcardCSV(flash);
	}

	public void loadFlashcard(Flashcard flash) {
		flashcardList.add(flash);
	}

	public Flashcard getFlashcard(int index) {
		return flashcardList.get(index);
	}

	public void deleteFlashcard(int index) {
		flashcardList.remove(index);
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
		String pathCSVString = pathCSV.toString();

		try (BufferedWriter writeBuffer = new BufferedWriter(new FileWriter(pathCSVString, true))) {

			String row = String.format("%d;%s;%s%n", f.getIndex(), f.getQuestion(), f.getAnswer());

			writeBuffer.append(row);
			writeBuffer.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public int getAmountOfFlashcards() {
		return flashcardList.size();
	}

	public List<Flashcard> getDeckFlashcardlist() {
		return flashcardList;
	}

	public String getDeckname() {
		return deckname;
	}

	public void setDeckname(String deckname) {
		this.deckname = deckname;
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub

	}

}
