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

public class CreateDeckGuiListener implements ActionListener {
	private CreateDeckGui createDeckGui;// view
	private DeckManager deckmanager;// model
	private String cmd;
	private static String deckName;

	public CreateDeckGuiListener(CreateDeckGui gui, DeckManager model, String cmd) {
		this.createDeckGui = gui;
		this.deckmanager = model;
		this.cmd = cmd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}

	private void doCommand(String cmd) {
		if (cmd.equals("ok")) {
			String question = createDeckGui.getQuestion().getText();
			String answer = createDeckGui.getAnswer().getText();
			try {
				deckmanager.addFlashcard(deckName, question, answer);
			} catch (UnvalidQAException e) {
				// TODO Auto-generated catch block
				new ErrorScreen(e.toString());
			}
			createDeckGui.getQuestion().setText("");
			createDeckGui.getAnswer().setText("");
			
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