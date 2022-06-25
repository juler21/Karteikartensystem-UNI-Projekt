package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import model.Deck;
import model.DeckManager;
import model.Flashcard;
import util.Main;
import view.EditDeckGui;
import view.CreateDeckGui;

public class ButtonListener implements ActionListener {

	private JFrame gui;
	private Main app;
	private String cmd;
	private int index;
	private static Deck deck;

	public ButtonListener(Main app, JFrame gui, String cmd) {
		this.app = app;
		this.gui = gui;
		this.cmd = cmd;

		index = 1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}

	private void doCommand(String cmd) {
		if (cmd.equals("ok")) {
			String question = ((CreateDeckGui) gui).getQuestion().getText();
			String answer = ((CreateDeckGui) gui).getAnswer().getText();

			deck.addFlashcard(new Flashcard(index, question, answer));
			index++;
			((CreateDeckGui) gui).getQuestion().setText("");
			((CreateDeckGui) gui).getAnswer().setText("");
		} else if (cmd.equals("confirm")) {
			System.out.println(DeckManager.getDecks().get(1).getDeckname());
			for (Flashcard f : deck.getDeckFlashcardlist()) {
				System.out.println(f.getIndex());
				System.out.println(f.getQuestion());
				System.out.println(f.getAnswer());
			}

		} else if (cmd.equals("Deckname Bestätigen")) {
			String deckname = ((CreateDeckGui) gui).getDeckName().getText();
			app.createDeck(deckname);
		} else if (cmd.equals("deckErstellen")) {

			new CreateDeckGui(app);

		} else if (cmd.equals("deckBearbeiten")) {
			new EditDeckGui(app);
		}
	}
}
