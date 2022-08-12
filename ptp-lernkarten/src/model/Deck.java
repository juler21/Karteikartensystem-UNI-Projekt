package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import util.UnvalidQAException;

public class Deck {

	private List<Flashcard> flashcardList = new ArrayList<Flashcard>();

	private String deckname;

	public Deck(String deck) {
		deckname = deck;
	}

	private boolean checkQAText(String text) throws UnvalidQAException {

		if (!text.isBlank() && text != null) {
			return true;
		} else {
			throw new UnvalidQAException();
		}
	}

	void addFlashcard(String question, String answer) throws UnvalidQAException {
		if (checkQAText(question) && checkQAText(answer)) {
			Flashcard flash = new Flashcard(question, answer);
			flashcardList.add(flash);
			deleteDeckCSV();
			saveDeckCSV();
		}
	}

	void deleteAllFlashcards() {
		flashcardList.removeAll(flashcardList);
	}

	void loadFlashcard(Flashcard flash) {
		flashcardList.add(flash);
	}

	Flashcard getFlashcard(int index) {
		return flashcardList.get(index);
	}

	void deleteFlashcard(int index) {
		flashcardList.remove(index);
		deleteDeckCSV();
		saveDeckCSV();
	}

	void saveDeckCSV() {

		Path pathCSV = Paths.get(DeckOrganizer.getPathtoString(), deckname + ".csv");
		String pathCSVString = pathCSV.toString();

		try {
			BufferedWriter writeBuffer = new BufferedWriter(new FileWriter(pathCSVString, true));
			if (getAmountOfFlashcards() > 0) {
				for (Flashcard f : flashcardList) {
					String questionString = f.getQuestion().replace('\n', '\\');
					System.out.println("HIER:" + questionString);
					String answerString = f.getAnswer().replace('\n', '\\');
					String row = String.format("%d;%s;%s%n", flashcardList.indexOf(f), questionString, answerString);

					writeBuffer.append(row);
				}
			}
			writeBuffer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void deleteDeckCSV() {
		Path pathCSV = Paths.get(DeckOrganizer.getPathtoString(), deckname + ".csv");
		try {
			Files.deleteIfExists(pathCSV);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	int getAmountOfFlashcards() {
		return flashcardList.size();
	}

	List<Flashcard> getDeckFlashcardlist() {
		return flashcardList;
	}

	String getDeckname() {
		return deckname;
	}

	void setDeckname(String deckname) {
		this.deckname = deckname;
	}

	@Override
	public String toString() {
		return deckname;
	}

}
