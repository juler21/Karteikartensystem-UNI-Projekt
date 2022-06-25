package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import model.Deck;
import util.Main;
import view.CreateDeckGui;
import view.EditDeckGui;

public class ButtonListener implements ActionListener {

	private JFrame gui;
	private Main app;
	private String cmd;
	private int index;
	private static Deck deck;

	public ButtonListener(JFrame gui, String cmd) {
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
<<<<<<< HEAD
		if (cmd.equals("deckErstellen")) {
=======
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
>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git

			new CreateDeckGui(app);

		} else if (cmd.equals("deckBearbeiten")) {
			new EditDeckGui(app);
		}
	}
}
