package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DeckManager;
import util.NoDeckSelectedExeption;
import view.CreateDeckGui;
import view.DecksGui;
import view.EditDeckGui;
import view.ErrorScreen;
import view.MainGui;

public class DecksGuiListener implements ActionListener {

	// Model
	private DeckManager deckmanager;
	// Startgui
	private DecksGui decksgui;
	private String cmd;

	public DecksGuiListener(DecksGui decksgui, DeckManager deckmanager, String cmd) {

		this.deckmanager = deckmanager;
		this.decksgui = decksgui;
		this.cmd = cmd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}

	private void doCommand(String cmd) {
		if (cmd.equals("deckErstellen")) {

			new CreateDeckGui(deckmanager, "Deck Erstellen", null);
			return;

		}
		try {

			String selectedDeck = decksgui.getSelectedDeck();
			System.out.println(selectedDeck);

		if (cmd.equals("deckBearbeiten")) {

				new EditDeckGui(deckmanager, decksgui, selectedDeck, "Deck: \"" + selectedDeck + "\" Bearbeiten");

		} else if (cmd.equals("deckLöschen")) {

				deckmanager.removeDeck(selectedDeck);

		} 
		}
		catch (NoDeckSelectedExeption e) {
			new ErrorScreen(e.getError());
		}
		}

}
