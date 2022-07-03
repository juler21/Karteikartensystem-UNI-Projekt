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

	public Deck(String deck) throws UnValidDecknameException {
		if (decknameisValid(deck)) {
			deckname = deck;
		}

	}

	private static boolean decknameisValid(String deckname) throws UnValidDecknameException {

		String pattern = "^[^*&%\s]+$";
		if (deckname.matches(pattern)) {
			return true;
		} else {
			throw new UnValidDecknameException();
		}
	}

	public void addFlashcard(Flashcard flash) {
		flashcardList.add(flash);
		deleteDeckCSV();
		saveDeckCSV();
		notifyObserver("flashcardChange");
	}

	public void loadFlashcard(Flashcard flash) {
		flashcardList.add(flash);
	}

	public Flashcard getFlashcard(int index) {
		return flashcardList.get(index);
	}

	public void deleteFlashcard(int index) {
		flashcardList.remove(index);
		deleteDeckCSV();
		saveDeckCSV();
		notifyObserver("flashcardChange");
	}

	public void saveDeckCSV() {

		Path pathCSV = Paths.get(DeckManager.getPathtoString(), deckname + ".csv");
		String pathCSVString = pathCSV.toString();

		try {
			BufferedWriter writeBuffer = new BufferedWriter(new FileWriter(pathCSVString, true));
			if (getAmountOfFlashcards() > 0) {
				for (Flashcard f : flashcardList) {
					String row = String.format("%d;%s;%s%n", flashcardList.indexOf(f), f.getQuestion(), f.getAnswer());

					writeBuffer.append(row);
				}
			}
			writeBuffer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	public void saveDeckCSV() {
//
//		Path pathCSV = Paths.get(DeckManager.getPathtoString(), deckname + ".csv");
//		String pathCSVString = pathCSV.toString();
//
//		try {
//			BufferedWriter writeBuffer = new BufferedWriter(new FileWriter(pathCSVString, true));
//			if (getAmountOfFlashcards() > 0) {
//				for (Flashcard f : flashcardList) {
//					String row = String.format("%d;%s;%s%n", f.getIndex(), f.getQuestion(), f.getAnswer());
//
//					writeBuffer.append(row);
//				}
//			}
//			writeBuffer.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public void deleteDeckCSV() {
		Path pathCSV = Paths.get(DeckManager.getPathtoString(), deckname + ".csv");
		try {
			Files.deleteIfExists(pathCSV);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	public String toString() {
		return deckname;
	}

}
