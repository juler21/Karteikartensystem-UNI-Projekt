package model;

import java.io.File;
import java.nio.file.Path;
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

//TODO Hier muss die instanz von einem Deckerstellt werden. 
	public void addDeck(String deckname) throws UnValidDecknameException, DeckIsExistingException {
		deckorganizer.addDeck(deckname);
	}

	public void loadDeck(Deck d) {
		deckorganizer.loadDeck(d);
	}

	public boolean isExisting(String deckname) {
		return deckorganizer.isExisting(deckname);
	}

	public Deck getDeck(String deckname) {
		return deckorganizer.getDeck(deckname);
	}

	public void setDecks(HashMap<String, Deck> decks) {
		deckorganizer.setDecks(decks);
	}

	public void removeDeck(String deckname) {
		deckorganizer.removeDeck(deckname);
	}

	public List<String> findAllFilesInFolder(File folder) {
		return deckorganizer.findAllFilesInFolder(folder);
	}

	public List<Path> findAllFilesInFolderPath(File folder) {
		return deckorganizer.findAllFilesInFolderPath(folder);
	}

	public String pathToFileName(Path path) {
		return deckorganizer.pathToFileName(path);
	}

//TODO nehme eine Methode
	public void getData(File folder) {
		deckorganizer.getData(folder);
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
