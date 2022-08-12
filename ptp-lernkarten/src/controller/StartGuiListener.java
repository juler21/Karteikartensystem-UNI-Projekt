package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DeckManager;
import util.NoDeckSelectedExeption;
import view.ErrorScreen;
import view.MainGui;
import view.StartGui;

/**
 * PTP 22 - Controllerklasse: Realisiert Interaktionen mit der StartGui 
 * 
 * @author J.Dillmann, M. Sterkel
 */
public class StartGuiListener implements ActionListener {
	private String cmd;
	private StartGui startgui;
	private MainGui mainGui;
	private DeckManager deckmanager;
	private static int flashcardLearnIndex = 0;
	
	
	/**
	 * Im Konstruktor wird eine Instanz der Klasse StartGuiListener erstellt.
	 */
	public StartGuiListener(StartGui startgui, MainGui maingui, DeckManager deckmanager, String cmd) {
		this.startgui = startgui;
		this.deckmanager = deckmanager;
		this.cmd = cmd;
		this.mainGui = maingui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
		
	}
	
	/**
	 * Methode führt je nach Interaktionstyp (zum Beispiel Knopfdruck) die entsprechende Aktion aus 
	 */
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
					setLearnScreen("learnQuestionCard");
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
					setLearnScreen("learnQuestionCard");
				} else {
					System.out.println("ende");
					setLearnScreen("learnEndCard");
				}
				
			} else if (cmd.equals("showAnswerButton")) {
				setLearnScreen("learnAnswerCard");
				
			} else if (cmd.equals("restartDeckButton")) {
				setLearnScreen("learnHomeCard");
				
			} else if (cmd.equals("switchDeckButton")) {
				CardLayout cardLayout = mainGui.getContentPanelCardLayout();
				cardLayout.show(mainGui.getContentPanel(), "decksCard");
			}
		
		} catch (NoDeckSelectedExeption e) {
			new ErrorScreen(e.getError());
		}
	}
	
	/**
	 * Schaltet auf eine gewüschte Karte des StartCard CardLayout
	 * 
	 * @param screenCard die gewünschte Karte
	 */
	private void setLearnScreen(String screenCard) {
		CardLayout cardLayout = (CardLayout) startgui.getStartCard().getLayout();
		cardLayout.show(startgui.getStartCard(), screenCard);

	}
}
