package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DeckManager;
import util.DeckIsExistingException;
import util.UnValidDecknameException;
import util.UnvalidQAException;
import view.CreateDeckGui;
import view.ErrorScreen;

/**
 * PTP 22 - Controllerklasse: Realisiert Interaktionen mit der CreateDeckGui 
 * 
 * @author J.Dillmann, M. Sterkel
 */
public class CreateDeckGuiListener implements ActionListener {
	private CreateDeckGui createDeckGui;// view
	private DeckManager deckmanager;// model
	private String cmd;
	private static String deckName;

	/**
	 * Im Konstruktor wird eine Instanz der Klasse CreateDeckGuiListener erstellt.
	 */
	public CreateDeckGuiListener(CreateDeckGui gui, DeckManager model, String cmd) {
		this.createDeckGui = gui;
		this.deckmanager = model;
		this.cmd = cmd;
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
			String question = createDeckGui.getQuestion().getText();
			System.out.println(question);
			String answer = createDeckGui.getAnswer().getText();
			System.out.println(answer);
			try {
				deckmanager.addFlashcard(deckName, question, answer);
				createDeckGui.getQuestion().setText("");
				createDeckGui.getAnswer().setText("");
			} catch (UnvalidQAException e) {
				new ErrorScreen(e.toString());
			}

		} else if (cmd.equals("close")) {
			createDeckGui.getCreateDeckFrame().dispose();

		} else if (cmd.equals("confirmDeckname")) {
			deckName = createDeckGui.getDeckName().getText();
			try {
				deckmanager.addDeck(deckName);
			} catch (UnValidDecknameException e) {
				new ErrorScreen(e.toString());
				createDeckGui.getCreateDeckFrame().dispose();

			} catch (DeckIsExistingException e) {
				new ErrorScreen(e.toString());
				createDeckGui.getCreateDeckFrame().dispose();
			}
			createDeckGui.getCreateDeckFrame().setTitle("Lernkarte zu: \"" + deckName + "\" hinzufügen");
			// CardLayout weiterschalten
			CardLayout cardLayout = (CardLayout) createDeckGui.getCreateDeckFramePanel().getLayout();
			cardLayout.show(createDeckGui.getCreateDeckFramePanel(), "createFlashcardCard");
		}
	}
}