package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Deck;
import model.DeckManager;
import view.EditDeckGui;

public class EditDeckGuiListener implements ActionListener {
	private EditDeckGui gui;// view
	private DeckManager deckmanager;// model
	private String cmd;
	private Deck selectedDeck;

	public EditDeckGuiListener(EditDeckGui gui, Deck selectedDeck, DeckManager model, String cmd) {
		this.gui = gui;
		this.deckmanager = model;
		this.cmd = cmd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}

	private void doCommand(String cmd) {
		if (cmd.equals("deleteFlashcard")) {
			selectedDeck.deleteFlashcard(gui.getSelectedFlashcard().getIndex());
		}
	}
}
