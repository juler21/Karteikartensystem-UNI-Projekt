package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DeckManager;
import util.NoDeckSelectedExeption;
import view.CreateDeckGui;
import view.DecksGui;
import view.EditDeckGui;
import view.ErrorScreen;

/**
 * PTP 22 - Controllerklasse: Realisiert Interaktionen mit der DecksGui 
 * 
 * @author J.Dillmann, M. Sterkel
 */
public class DecksGuiListener implements ActionListener {

	private DeckManager deckmanager;
	private DecksGui decksgui;
	private String cmd;
	private String fontStyle;

	/**
	 * Im Konstruktor wird eine Instanz der Klasse DecksGuiListener erstellt.
	 */
	public DecksGuiListener(DecksGui decksgui, DeckManager deckmanager, String fontstyle, String cmd) {

		this.deckmanager = deckmanager;
		this.decksgui = decksgui;
		this.fontStyle = fontstyle;
		this.cmd = cmd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}
	
	/**
	 * Methode führt je nach Interaktionstyp (zum Beispiel Knopfdruck) die entsprechende Aktion aus 
	 */
	private void doCommand(String cmd) {
		if (cmd.equals("deckErstellen")) {

			new CreateDeckGui(deckmanager, fontStyle);
			return;
		}
		try {

			String selectedDeck = decksgui.getSelectedDeck();
			System.out.println(selectedDeck);

		if (cmd.equals("deckBearbeiten")) {

				new EditDeckGui(deckmanager, decksgui, selectedDeck, "Deck: \"" + selectedDeck + "\" Bearbeiten", fontStyle);

		} else if (cmd.equals("deckLöschen")) {

				deckmanager.removeDeck(selectedDeck);
		} 
		}
		catch (NoDeckSelectedExeption e) {
			new ErrorScreen(e.getError());
		}
		}
}
