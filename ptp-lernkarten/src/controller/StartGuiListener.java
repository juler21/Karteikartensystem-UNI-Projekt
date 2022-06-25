package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import model.Deck;
import model.DeckManager;
import model.Flashcard;
import util.Main;
import view.CreateDeckGui;
import view.EditDeckGui;
import view.StartGui;

public class StartGuiListener implements ActionListener {

	//Model
	private DeckManager deckmanager;
	//Startgui
	private StartGui startgui;
	
	private String cmd;


	public StartGuiListener(StartGui startgui, DeckManager deckmanager, String cmd) {
		
		this.deckmanager = deckmanager;
		this.startgui = startgui;
		this.cmd = cmd;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}

	private void doCommand(String cmd) {
		if (cmd.equals("deckErstellen")) {

			new CreateDeckGui(deckmanager, "DECK ERSTELLEN");

		} else if (cmd.equals("deckBearbeiten")) {
			new EditDeckGui(deckmanager, "DECK BEARBEITEN");
		}
	}
}
