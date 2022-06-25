package util;

import model.Deck;
import model.DeckManager;
import model.Flashcard;
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
		int position = deck.getDeckFlashcardlist().size();
		Flashcard newFlashcard = new Flashcard(position, question, answer);
		deck.addFlashcard(newFlashcard);
	}

	public void deleteFlashcard(Deck deck, int position) {
		deck.deleteFlashcard(position);
	}

	public void createDeck(String deckname) {
		Deck newDeck = new Deck(deckname);
		deckmanager.addDeck(newDeck);
	}

	public void deleteDeck(String deckname) {

	}

	public void createDirectory1() {
		deckmanager.createDirectories();
	}

}
