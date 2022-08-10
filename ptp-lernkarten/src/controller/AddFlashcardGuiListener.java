package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DeckManager;
import util.DeckIsExistingException;
import util.UnValidDecknameException;
import util.UnvalidQAException;
import view.AddFlashcardGui;
import view.CreateDeckGui;
import view.ErrorScreen;

public class AddFlashcardGuiListener implements ActionListener {
	
	private AddFlashcardGui addFlashcardGui;// view
	private DeckManager deckmanager;// model
	private String cmd;
	private String deckName;

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
