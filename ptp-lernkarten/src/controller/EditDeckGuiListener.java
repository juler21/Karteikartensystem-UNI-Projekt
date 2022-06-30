package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DeckManager;
import view.EditDeckGui;
import view.StartGui;

public class EditDeckGuiListener implements ActionListener {
	private EditDeckGui gui;// view
	private DeckManager deckmanager;// model
	private String cmd;
	private StartGui startGui;

	public EditDeckGuiListener(EditDeckGui gui, StartGui startGui, DeckManager model, String cmd) {
		this.gui = gui;
		this.deckmanager = model;
		this.cmd = cmd;
		this.startGui = startGui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}

	private void doCommand(String cmd) {
		if (cmd.equals("deleteFlashcard")) {
			int index = startGui.getSelectedDeck().getDeckFlashcardlist().indexOf(gui.getSelectedFlashcard());
			startGui.getSelectedDeck().deleteFlashcard(index);
		} else if (cmd.equals("editFlashcard")) {
			gui.getSelectedFlashcard().setQuestion(gui.getQuestionText());
			gui.getSelectedFlashcard().setAnswer(gui.getAnswerText());
			startGui.getSelectedDeck().deleteDeckCSV();
			startGui.getSelectedDeck().saveDeckCSV();

			System.out.println(gui.getSelectedFlashcard().getAnswer());
			System.out.println(gui.getSelectedFlashcard().getQuestion());
		}
	}
}
