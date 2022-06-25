package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Deck;
import model.DeckManager;
import model.Flashcard;
import view.CreateDeckGui;

public class CreateDeckGuiListener implements ActionListener {
	private CreateDeckGui gui;// view
	private DeckManager model;// model
	private String cmd;
	private int index;

	public CreateDeckGuiListener(CreateDeckGui gui, DeckManager model, String cmd) {
		this.gui = gui;
		this.model = model;
		this.cmd = cmd;
		this.index = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}

	private void doCommand(String cmd) {
		if (cmd.equals("ok")) {
			String question = gui.getQuestion().getText();
			String answer = gui.getAnswer().getText();

			int position = model.getDeck(index).getAmountOfFlashcards();
			model.getDeck(index).addFlashcard(new Flashcard(position, question, answer));
			((CreateDeckGui) gui).getQuestion().setText("");
			((CreateDeckGui) gui).getAnswer().setText("");
		} else if (cmd.equals("confirm")) {
			for (Flashcard f : model.getDeck(index).getDeck()) {
				System.out.println(f.getIndex());
				System.out.println(f.getQuestion());
				System.out.println(f.getAnswer());
			}

		} else if (cmd.equals("confirmDeckname")) {
			String deckname = gui.getDeckName().getText();
			Deck newDeck = new Deck(deckname);
			model.addDeck(newDeck);
			index++;

		}

	}
}