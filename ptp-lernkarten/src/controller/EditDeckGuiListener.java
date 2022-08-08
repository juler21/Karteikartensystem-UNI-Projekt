package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DeckManager;
import util.NoFlashcardSelectedExeption;
import view.CreateDeckGui;
import view.EditDeckGui;
import view.ErrorScreen;

public class EditDeckGuiListener implements ActionListener {
	private EditDeckGui gui;// view
	private DeckManager deckmanager;// model
	private String cmd;
	private String selectedDeck;

	public EditDeckGuiListener(EditDeckGui gui, String deck, DeckManager model, String cmd) {
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

				int index = deckmanager.getFlashcardList(selectedDeck).indexOf(gui.getSelectedFlashcard());
				gui.setQuestionText("");
				gui.setAnswerText("");
				deckmanager.deleteFlashcard(selectedDeck, index);

			} else if (cmd.equals("editFlashcard")) {
				deckmanager.setQuestion(gui.getSelectedFlashcard(), gui.getQuestionText());
				deckmanager.setAnswer(gui.getSelectedFlashcard(), gui.getAnswerText());

				deckmanager.updateCSV(selectedDeck);
			} else if (cmd.equals("newFlashcard")) {
				CreateDeckGui DeckGUI = new CreateDeckGui(deckmanager, selectedDeck.toString(), selectedDeck);
				((CardLayout) DeckGUI.getFramePanel().getLayout()).show(DeckGUI.getFramePanel(), "deckKartenCard");

			}
		} catch (NoFlashcardSelectedExeption e) {
			new ErrorScreen(e.getError());
		}

	}
}
