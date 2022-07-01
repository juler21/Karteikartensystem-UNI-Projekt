package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Deck;
import model.DeckManager;
import util.NoDeckSelectedExeption;
import view.CreateDeckGui;
import view.EditDeckGui;
import view.ErrorScreen;
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
			return;

		}
		try {

			Deck selectedDeck = startgui.getSelectedDeck();

			if (cmd.equals("deckBearbeiten")) {

				new EditDeckGui(deckmanager, startgui, selectedDeck, selectedDeck.getDeckname() + "BEARBEITEN");

			} else if (cmd.equals("deckLöschen")) {

				deckmanager.removeDeck(selectedDeck.getDeckname());

			} else if (cmd.equals("lernenBeginnenButton")) {
				System.out.println(flashcardLearnIndex);
				flashcardLearnIndex = 0;
				if (selectedDeck.getAmountOfFlashcards() != 0) {
					startgui.setOnlyQuestionTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getQuestion());
					startgui.setQuestionTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getQuestion());
					startgui.setAnswerTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getAnswer());
				}

			} else if (cmd.equals("nextQuestionButton")) {

				if (flashcardLearnIndex < selectedDeck.getAmountOfFlashcards() - 1) {
					flashcardLearnIndex++;
					startgui.setOnlyQuestionTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getQuestion());
					startgui.setQuestionTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getQuestion());
					startgui.setAnswerTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getAnswer());
				}
			} else if (cmd.equals("chooseDeckComboBox")) {

				if (selectedDeck.getAmountOfFlashcards() != 0) {
					flashcardLearnIndex = 0;
					System.out.println(flashcardLearnIndex);
					startgui.setOnlyQuestionTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getQuestion());
					startgui.setQuestionTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getQuestion());
					startgui.setAnswerTextLabel(selectedDeck.getFlashcard(flashcardLearnIndex).getAnswer());
				}

			}
		} catch (NoDeckSelectedExeption e) {
			new ErrorScreen(e.getError());
		}
	}
}
