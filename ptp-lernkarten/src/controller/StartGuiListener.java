package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Deck;
import model.DeckManager;
import util.NoDeckSelectedExeption;
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
		try {

			Deck selectedDeck = startgui.getSelectedDeck();
		
		if (cmd.equals("deckErstellen")) {

			new CreateDeckGui(deckmanager, "DECK ERSTELLEN");

		} else if (cmd.equals("deckBearbeiten")) {
			
				new EditDeckGui(deckmanager, startgui, selectedDeck.getDeckname() + "BEARBEITEN");
			
		} else if (cmd.equals("deckLöschen")) {
			
				deckmanager.removeDeck(selectedDeck.getDeckname());
		
		} else if (cmd.equals("nextQuestionButton")) {

			System.out.println(flashcardLearnIndex);
<<<<<<< HEAD
			if(flashcardLearnIndex<startgui.getSelectedDeck().getAmountOfFlashcards()-1) {
			flashcardLearnIndex++;
			startgui.setOnlyQuestionTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getQuestion());
			startgui.setQuestionTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getQuestion());
			startgui.setAnswerTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getAnswer());
=======
			if (flashcardLearnIndex < startgui.getSelectedDeck().getAmountOfFlashcards() - 1) {
				flashcardLearnIndex++;
				startgui.setOnlyQuestionTextLabel(
						startgui.getSelectedDeck().getFlashcard(flashcardLearnIndex).getQuestion());
				startgui.setQuestionTextLabel(
						startgui.getSelectedDeck().getFlashcard(flashcardLearnIndex).getQuestion());
				startgui.setAnswerTextLabel(startgui.getSelectedDeck().getFlashcard(flashcardLearnIndex).getAnswer());
>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git
			}
	
		} else if (cmd.equals("chooseDeckComboBox")) {
<<<<<<< HEAD

				if(startgui.getSelectedDeck().getAmountOfFlashcards() != 0) {
				flashcardLearnIndex= 0;
				System.out.println(flashcardLearnIndex);
				startgui.setOnlyQuestionTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getQuestion());
				startgui.setQuestionTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getQuestion());
				startgui.setAnswerTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getAnswer());
				}
			
		}
		}catch (NoDeckSelectedExeption e) {
		
		new JOptionPane(e.getError());
	}
=======
			flashcardLearnIndex = 0;
			System.out.println(flashcardLearnIndex);
			startgui.setOnlyQuestionTextLabel(
					startgui.getSelectedDeck().getFlashcard(flashcardLearnIndex).getQuestion());
			startgui.setQuestionTextLabel(startgui.getSelectedDeck().getFlashcard(flashcardLearnIndex).getQuestion());
			startgui.setAnswerTextLabel(startgui.getSelectedDeck().getFlashcard(flashcardLearnIndex).getAnswer());
		}
>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git
	}
}
