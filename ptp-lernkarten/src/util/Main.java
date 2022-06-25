package util;
import java.io.File;

import model.Deck;
import model.DeckManager;
import view.StartGui;


public class Main {

	StartGui gui;
	
	public Main () {
		
		new StartGui(this);
	}

	public static void main(String[] args) {

		new Main();
		File folder = new File(DeckManager.getPathtoString());
		DeckManager.getData(folder);
//		System.out.println(DeckManager.getDecks().get(0).getDeckname());
//		System.out.println(DeckManager.getDecks().get(1).getDeckname());
//		System.out.println(DeckManager.getDecks().get(2).getDeckname());
//		System.out.println(DeckManager.getDecks().get(2).getDeckname());

	}
	
	//API
	
	public void lernkarteHinzufügen(Deck deck, String question, String answer){
		
		
	}
	
	public void lernkartelöschen(Deck deck, int position){
		
		
	}
	
	public void deckErstellen(String deckname){
		Deck newDeck = new Deck(deckname);
		DeckManager.addDeck(newDeck);
		
	}
}
