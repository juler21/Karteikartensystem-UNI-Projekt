package util;

import model.DeckManager;
import view.StartGui;

public class Main {

//	StartGui gui; // view
//	DeckManager manager; // model

	public Main() {

	}

	public static void main(String[] args) {

		DeckManager manager = new DeckManager();
		String name = "Flaschcard System";
		new StartGui(manager, name);
	}

	// API

<<<<<<< HEAD
//	public void addFlashcard(Deck deck, String question, String answer) {
//		int position = deck.getDeck().size();
//		Flashcard newFlashcard = new Flashcard(position, question, answer);
//		deck.addFlashcard(newFlashcard);
//	}
//
//	public void deleteFlashcard(Deck deck, int position) {
//		deck.getDeck().remove(position);
//	}
//
//	public void createDeck(String deckname) {
//		Deck newDeck = new Deck(deckname);
//		DeckManager.addDeck(newDeck);
//	}
//
//	public void createDirectory1() {
//		DeckManager.createDirectories();
//	}
=======
	public void addFlashcard(Deck deck, String question, String answer) {
		int position = deck.getDeckFlashcardlist().size();
		Flashcard newFlashcard = new Flashcard(position, question, answer);
		deck.addFlashcard(newFlashcard);
	}

	public void deleteFlashcard(Deck deck, int position) {
		deck.getDeckFlashcardlist().remove(position);
	}

	public void createDeck(String deckname) {
		Deck newDeck = new Deck(deckname);
		DeckManager.addDeck(newDeck);
	}

	public void createDirectory1() {
		DeckManager.createDirectories();
	}
>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git

}
