package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DeckManager;
import util.NoDeckSelectedExeption;
import view.ErrorScreen;
import view.StartGui;

public class StartGuiListener implements ActionListener {
	private String cmd;
	private String theme;
	private StartGui startgui;
	private DeckManager deckmanager;
	private static int flashcardLearnIndex = 0;
	
	public StartGuiListener(StartGui startgui, DeckManager deckmanager, String cmd) {
		this.theme = "light";
		this.startgui = startgui;
		this.deckmanager = deckmanager;
		this.cmd = cmd;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
		
	}
	private void doCommand(String cmd) {
		try {

			String selectedDeck = startgui.getSelectedDeck();

			if (cmd.equals("lernenBeginnenButton")) {
				System.out.println(flashcardLearnIndex);
				flashcardLearnIndex = 0;
				if (deckmanager.getAmountOfFlashcards(selectedDeck) != 0) {
					startgui.setOnlyQuestionTextArea(
							deckmanager.getQuestionFlashcard(selectedDeck, flashcardLearnIndex));
					startgui.setQuestionTextArea(
							deckmanager.getQuestionFlashcard(selectedDeck, flashcardLearnIndex));
					startgui.setAnswerTextArea(deckmanager.getAnswerFlashcard(selectedDeck, flashcardLearnIndex));

					startgui.getAnswerTextArea().setCaretPosition(0);
					System.out.println("lernenBeginnenButton");
					startgui.setLearnScreen("learnQuestionCard");
				}
			
			} else if (cmd.equals("nextQuestionButton")) {
				if (flashcardLearnIndex < deckmanager.getAmountOfFlashcards(selectedDeck) - 1) {
					flashcardLearnIndex++;

					startgui.setOnlyQuestionTextArea(
							deckmanager.getQuestionFlashcard(selectedDeck, flashcardLearnIndex));
					startgui.setQuestionTextArea(
							deckmanager.getQuestionFlashcard(selectedDeck, flashcardLearnIndex));
					startgui.setAnswerTextArea(deckmanager.getAnswerFlashcard(selectedDeck, flashcardLearnIndex));

					startgui.getAnswerTextArea().setCaretPosition(0);
					startgui.setLearnScreen("learnQuestionCard");
				} else {
					System.out.println("ende");
					startgui.setLearnScreen("learnEndCard");
				}
				
			} else if (cmd.equals("showAnswerButton")) {
				startgui.setLearnScreen("learnAnswerCard");
				
			} else if (cmd.equals("restartDeckButton")) {
				startgui.setLearnScreen("learnHomeCard");
				
			} else if (cmd.equals("switchDeckButton")) {
//				startgui.setDashboardScreen("decksCard");
			}
		
		} catch (NoDeckSelectedExeption e) {
			new ErrorScreen(e.getError());
		}

	}
}
