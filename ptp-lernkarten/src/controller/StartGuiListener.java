package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DeckManager;
import view.CreateDeckGui;
import view.EditDeckGui;
import view.StartGui;

public class StartGuiListener implements ActionListener {

	// Model
	private DeckManager deckmanager;
	// Startgui
	private StartGui startgui;
	private String cmd;
	private int flashcardLearnIndex;
	

	public StartGuiListener(StartGui startgui, DeckManager deckmanager, String cmd) {

		this.deckmanager = deckmanager;
		this.startgui = startgui;
		this.cmd = cmd;
		flashcardLearnIndex = 0;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}

	private void doCommand(String cmd) {
		if (cmd.equals("deckErstellen")) {

			new CreateDeckGui(deckmanager, "DECK ERSTELLEN");

		} else if (cmd.equals("deckBearbeiten")) {
			String s = startgui.getSelectedDeck().getDeckname();
			new EditDeckGui(deckmanager, startgui, s + "BEARBEITEN");
		} else if (cmd.equals("deckLöschen")) {
			deckmanager.removeDeck(startgui.getSelectedDeck().getDeckname());
		} else if (cmd.equals("nextQuestionButton")) {
			System.out.println(flashcardLearnIndex);
			if(flashcardLearnIndex<startgui.getSelectedDeck().getAmountOfFlashcards()-1) {
			flashcardLearnIndex++;
			startgui.setOnlyQuestionTextLabel(startgui.getSelectedDeck().getFlashcard(flashcardLearnIndex).getQuestion());
			startgui.setQuestionTextLabel(startgui.getSelectedDeck().getFlashcard(flashcardLearnIndex).getQuestion());
			startgui.setAnswerTextLabel(startgui.getSelectedDeck().getFlashcard(flashcardLearnIndex).getAnswer());
			}
		}
	}
}
