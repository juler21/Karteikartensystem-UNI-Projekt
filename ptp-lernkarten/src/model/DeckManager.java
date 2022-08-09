package model;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import util.DeckIsExistingException;
import util.UnValidDecknameException;
import util.UnvalidQAException;

public class DeckManager extends Observable {

	DeckOrganizer deckorganizer;

	public DeckManager() {
		deckorganizer = new DeckOrganizer();
	}

	public void addDeck(String deckname) throws UnValidDecknameException, DeckIsExistingException {
		deckorganizer.addDeck(deckname);
	}

	public Deck getDeck(String deckname) {
		return deckorganizer.getDeck(deckname);
	}

	public void removeDeck(String deckname) {
		deckorganizer.removeDeck(deckname);
	}

	public void getData(File folder) {
		deckorganizer.getData(folder);
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
		return deckorganizer.containsDeck(deckname);

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

	public void createDirectories() {
		deckorganizer.createDirectories();

	}

	public static String getPathtoString() {
		return DeckOrganizer.getPathtoString();
	}

	public boolean decksisEmpty() {
		return deckorganizer.isEmpty();
	}

	public HashMap<String, Deck> getDecks() {
		return deckorganizer.getDecks();
	}

}
