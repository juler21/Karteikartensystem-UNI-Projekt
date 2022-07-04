package util;

import model.Deck;
import model.DeckManager;
import model.Flashcard;
import model.UnValidDecknameException;
import view.StartGui;

public class Main {

	private StartGui gui; // view
	private DeckManager deckmanager; // model

	public Main() {
		deckmanager = new DeckManager();
		String name = "Flaschcard System";
		gui = new StartGui(deckmanager, name);

	}

	public static void main(String[] args) {
		new Main();
	}

	// API

	public void addFlashcard(Deck deck, String question, String answer) {
//		int position = deck.getDeckFlashcardlist().size();
		Flashcard newFlashcard = new Flashcard(question, answer);
//		Flashcard newFlashcard = new Flashcard(position, question, answer);
		deck.addFlashcard(newFlashcard);
	}

	public void deleteFlashcard(Deck deck, int position) {
		deck.deleteFlashcard(position);
	}

	public Flashcard getFlashcard(Deck deck, int position) {
		return deck.getFlashcard(position);
	}

	public void createDeck(String deckname) throws UnValidDecknameException {
		deckmanager.addDeck(deckname);
	}

	public Deck getDeck(String deckname) {
		return deckmanager.getDeck(deckname);
	}

	public void deleteDeck(String deckname) {
		deckmanager.removeDeck(deckname);
	}

	public void createDirectory() {
		deckmanager.createDirectories();
	}

	public String getPathDirectory() {
		return DeckManager.getPathtoString();
	}

	public StartGui getGui() {
		return gui;
	}

	public DeckManager getDeckmanager() {
		return deckmanager;
	}

}
