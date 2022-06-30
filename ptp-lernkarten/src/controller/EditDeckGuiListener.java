package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DeckManager;
import util.NoDeckSelectedExeption;
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
			
			int index;
			try {
				index = startGui.getSelectedDeck().getDeckFlashcardlist().indexOf(gui.getSelectedFlashcard());
				startGui.getSelectedDeck().deleteFlashcard(index);
			} catch (NoDeckSelectedExeption e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
