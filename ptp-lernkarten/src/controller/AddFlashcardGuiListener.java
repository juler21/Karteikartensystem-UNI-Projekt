package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DeckManager;
import util.UnvalidQAException;
import view.AddFlashcardGui;
import view.ErrorScreen;

/**
 * PTP 22 - Controllerklasse: Realisiert Interaktionen mit der AddFlashcardGui
 * 
 * @author J.Dillmann, M. Sterkel
 */
public class AddFlashcardGuiListener implements ActionListener {

	private AddFlashcardGui addFlashcardGui;
	private DeckManager deckmanager;
	private String cmd;
	private String deckName;

	/**
	 * Im Konstruktor wird eine Instanz der Klasse AddFlashcardGuiListener erstellt.
	 */
	public AddFlashcardGuiListener(AddFlashcardGui addflashcardgui, DeckManager deckmanager, String cmd) {
		this.addFlashcardGui = addflashcardgui;
		this.deckmanager = deckmanager;
		this.cmd = cmd;
		this.deckName = addFlashcardGui.getSelectedDeck();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}

	/**
	 * Methode führt je nach Interaktionstyp (zum Beispiel Knopfdruck) die entsprechende Aktion aus 
	 */
	private void doCommand(String cmd) {
		if (cmd.equals("ok")) {

			String question = addFlashcardGui.getQuestionTextArea().getText();
			String answer = addFlashcardGui.getAnswerTextArea().getText();
			try {
				deckmanager.addFlashcard(deckName, question, answer);
				addFlashcardGui.getQuestionTextArea().setText("");
				addFlashcardGui.getAnswerTextArea().setText("");
			} catch (UnvalidQAException e) {
				new ErrorScreen(e.toString());
			}
		} else if (cmd.equals("close")) {
			addFlashcardGui.getAddFlashcardFrame().dispose();
		}
	}
}
