package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UnsupportedLookAndFeelException;

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
	private static int flashcardLearnIndex = 0;
	private String theme;

	public StartGuiListener(StartGui startgui, DeckManager deckmanager, String cmd) {

		this.deckmanager = deckmanager;
		this.startgui = startgui;
		this.cmd = cmd;
		this.theme = "light";

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
		if (cmd.equals("switchThemeButton")) {
			System.out.println("switchThemeButton");
			try {
				if (theme.equals("light")) {
					javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme());
					com.formdev.flatlaf.FlatLaf.updateUI();
					startgui.getSwitchThemeButton().setText("Light Mode");
					theme = "dark";
				} else if (theme.equals("dark")) {
					javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme());
					com.formdev.flatlaf.FlatLaf.updateUI();
					startgui.getSwitchThemeButton().setText("Dark Mode");
					theme = "light";
				}
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {

				String selectedDeck = startgui.getSelectedDeck();
				System.out.println(selectedDeck);

				if (cmd.equals("deckBearbeiten")) {

					new EditDeckGui(deckmanager, startgui, selectedDeck, "Deck: \"" + selectedDeck + "\" Bearbeiten");

				} else if (cmd.equals("deckLöschen")) {

					deckmanager.removeDeck(selectedDeck);

				} else if (cmd.equals("lernenBeginnenButton")) {
					System.out.println(flashcardLearnIndex);
					flashcardLearnIndex = 0;
					if (deckmanager.getAmountOfFlashcards(selectedDeck) != 0) {
						startgui.setOnlyQuestionTextLabel(
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

						startgui.setOnlyQuestionTextLabel(
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
					startgui.setDashboardScreen("decksCard");
				}
			} catch (NoDeckSelectedExeption e) {
				new ErrorScreen(e.getError());
			}
		}
	}
}
