package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Deck;
import model.DeckManager;
import model.Flashcard;
import view.CreateDeckGui;

public class CreateDeckGuiListener implements ActionListener {
	private CreateDeckGui gui;// view
	private DeckManager deckmanager;// model
	private String cmd;
	private static String _deckname;

	public CreateDeckGuiListener(CreateDeckGui gui, DeckManager model, String cmd) {
		this.gui = gui;
		this.deckmanager = model;
		this.cmd = cmd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}

	private void doCommand(String cmd) {
		if (cmd.equals("ok")) {
			String question = gui.getQuestion().getText();
			String answer = gui.getAnswer().getText();
<<<<<<< HEAD
=======
			int position = deckmanager.getDeck(_deckname).getAmountOfFlashcards();
>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git
			deckmanager.getDeck(_deckname).addFlashcard(new Flashcard(question, answer));
			gui.getQuestion().setText("");
			gui.getAnswer().setText("");
		} else if (cmd.equals("confirm")) {
			gui.dispose();
		} else if (cmd.equals("Deckname")) {
			String deckname = gui.getDeckName().getText();
			_deckname = deckname;
			Deck newDeck = new Deck(deckname);
			deckmanager.addDeck(newDeck);

		}

	}
}