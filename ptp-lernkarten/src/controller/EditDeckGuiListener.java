package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DeckManager;
import model.Flashcard;
import util.NoFlashcardSelectedExeption;
import view.AddFlashcardGui;
import view.CreateDeckGui;
import view.EditDeckGui;
import view.ErrorScreen;

public class EditDeckGuiListener implements ActionListener {
	private EditDeckGui gui;// view
	private DeckManager deckmanager;// model
	private String cmd;
	private String selectedDeck;

	public EditDeckGuiListener(EditDeckGui gui, String selectedDeck, DeckManager model, String cmd) {
		this.gui = gui;
		this.deckmanager = model;
		this.cmd = cmd;
		this.selectedDeck = selectedDeck;
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
				new AddFlashcardGui(deckmanager, selectedDeck.toString(), selectedDeck);
//				((CardLayout) DeckGUI.getCreateDeckFramePanel().getLayout()).show(DeckGUI.getCreateDeckFramePanel(), "createFlashcardCard");
			} else if (cmd.equals("comboBoxChange")) {
				if (gui.getFlashcardList().getSelectedItem() != null) {
					Flashcard f = (Flashcard) (gui.getFlashcardList().getSelectedItem());
					gui.setQuestionText(deckmanager.getQuestion(f));
					gui.setAnswerText(deckmanager.getAnswer(f));
				}	
			}
		} catch (NoFlashcardSelectedExeption e) {
			new ErrorScreen(e.getError());
		}

	}
}
