package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DeckManager;
import util.NoDeckSelectedExeption;
import view.MainGui;

public class MainGuiListener implements ActionListener {

	private MainGui mainGui;
	private String cmd;
	private DeckManager deckManager;

	public MainGuiListener(MainGui maingui, DeckManager deckmanager,  String cmd) {

		this.mainGui = maingui;
		this.deckManager = deckmanager;
		this.cmd = cmd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}

	private void doCommand(String cmd) {
		
		if (cmd.equals("startCard")) {

			CardLayout cardLayout = mainGui.getContentPanelCardLayout();
			cardLayout.show(mainGui.getContentPanel(), cmd);
			
			//Bildschirm der StartKarte auf homescreen setzen 
			((CardLayout) mainGui.getStartCard().getLayout()).show(mainGui.getStartCard(), "learnHomeCard");
			
			//CurrentDecklabel setzten 
			try {
				mainGui.getStartGui().setCurrentDeckLabel(mainGui.getSelectedDeck());
				
				if (deckManager.getAmountOfFlashcards(mainGui.getSelectedDeck())==0) {
					mainGui.getStartGui().enableLernenBeginnenButton(false);
				}
				else {
					mainGui.getStartGui().enableLernenBeginnenButton(true);	
				}
			} catch (NoDeckSelectedExeption e1) {
				mainGui.getStartGui().setCurrentDeckLabel("kein Deck vorhanden");
				mainGui.getStartGui().enableLernenBeginnenButton(false);
			}
			

		} else {
	
			CardLayout cardLayout = mainGui.getContentPanelCardLayout();
			cardLayout.show(mainGui.getContentPanel(), cmd);

	}
	}
		
}


