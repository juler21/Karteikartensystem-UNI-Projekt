package util;

import java.io.File;

import model.Deck;
import model.DeckManager;
import model.Flashcard;
import view.StartGui;

public class Main {

	StartGui gui;

	public Main() {

		new StartGui(this);
	}

	public static void main(String[] args) {

		new Main();
		File folder = new File(DeckManager.getPathtoString());
		DeckManager.getData(folder);
		System.out.println(DeckManager.getDecks().get(0).getDeckname());
		System.out.println(DeckManager.getDecks().get(1).getDeckname());
		System.out.println(DeckManager.getDecks().get(2).getDeckname());
		System.out.println(DeckManager.getDecks().get(2).getDeckname());
	}

	// API

	public void addFlashcard(Deck deck, String question, String answer) {
		int position = deck.getDeck().size();
		Flashcard newFlashcard = new Flashcard(position, question, answer);
		deck.addFlashcard(newFlashcard);
	}

	public void deleteFlashcard(Deck deck, int position) {
		deck.getDeck().remove(position);
	}

	public void createDeck(String deckname) {
		Deck newDeck = new Deck(deckname);
		DeckManager.addDeck(newDeck);
	}

	public void createDirectory1() {
		DeckManager.createDirectories();
	}

}
