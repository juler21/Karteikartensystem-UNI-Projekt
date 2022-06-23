package util;

import java.io.File;

import model.DeckManager;
import view.Gui;

public class Main {
	public static void main(String[] args) {

		new Gui();
		File folder = new File(DeckManager.getPathtoString());
		DeckManager.getData(folder);
		System.out.println(DeckManager.getDecks().get(0).getDeckname());
		System.out.println(DeckManager.getDecks().get(1).getDeckname());
		System.out.println(DeckManager.getDecks().get(2).getDeckname());
		System.out.println(DeckManager.getDecks().get(2).getDeckname());

	}

}
