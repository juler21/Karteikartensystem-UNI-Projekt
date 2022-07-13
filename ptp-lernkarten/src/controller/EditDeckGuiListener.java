package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Deck;
import model.DeckManager;
import util.NoFlashcardSelectedExeption;
import view.CreateDeckGui;
import view.EditDeckGui;
import view.ErrorScreen;

public class EditDeckGuiListener implements ActionListener {
	private EditDeckGui gui;// view
	private DeckManager deckmanager;// model
	private String cmd;
	private Deck selectedDeck;

	public EditDeckGuiListener(EditDeckGui gui, Deck deck, DeckManager model, String cmd) {
		this.gui = gui;
		this.deckmanager = model;
		this.cmd = cmd;
		this.selectedDeck = deck;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}

	private void doCommand(String cmd) {

		if (cmd.equals("close")) {
			gui.getEditDeckFrame().dispose();
		}
		try {
			if (cmd.equals("deleteFlashcard")) {

				int index = selectedDeck.getDeckFlashcardlist().indexOf(gui.getSelectedFlashcard());
				gui.setQuestionText("");
				gui.setAnswerText("");
				selectedDeck.deleteFlashcard(index);

			} else if (cmd.equals("editFlashcard")) {
				gui.getSelectedFlashcard().setQuestion(gui.getQuestionText());
				gui.getSelectedFlashcard().setAnswer(gui.getAnswerText());

				selectedDeck.deleteDeckCSV();
				selectedDeck.saveDeckCSV();

				System.out.println(gui.getSelectedFlashcard().getAnswer());
				System.out.println(gui.getSelectedFlashcard().getQuestion());
			} else if (cmd.equals("newFlashcard")) {
				CreateDeckGui DeckGUI = new CreateDeckGui(deckmanager, selectedDeck.toString(), selectedDeck);
				((CardLayout) DeckGUI.getFramePanel().getLayout()).show(DeckGUI.getFramePanel(), "deckKartenCard");

			}
		} catch (NoFlashcardSelectedExeption e) {
			new ErrorScreen(e.getError());
		}

	}
}
