package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UnsupportedLookAndFeelException;

import model.DeckManager;
import util.NoDeckSelectedExeption;
import view.CreateDeckGui;
import view.EditDeckGui;
import view.ErrorScreen;
import view.MainGui;

public class MainGuiListener implements ActionListener {

	private MainGui mainGui;
	private String cmd;

	public MainGuiListener(MainGui maingui, String cmd) {

		this.mainGui = maingui;
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
			} catch (NoDeckSelectedExeption e1) {
				mainGui.getStartGui().setCurrentDeckLabel("kein Deck gewählt");
			}

		} else {
	
			CardLayout cardLayout = mainGui.getContentPanelCardLayout();
			cardLayout.show(mainGui.getContentPanel(), cmd);

	}
	}
		
}


