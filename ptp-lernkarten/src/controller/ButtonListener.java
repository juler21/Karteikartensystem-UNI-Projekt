package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import model.Deck;
import model.DeckManager;
import model.Flashcard;
import view.Gui;
import view.QAGUI;

public class ButtonListener implements ActionListener {
	
	private JFrame gui;
	private String cmd;
	private int index;
	private static Deck deck;
	
	public ButtonListener(JFrame gui, String cmd) {
		this.gui = gui;
		this.cmd = cmd;
		
		index = 1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}
	private void doCommand(String cmd) {
		if(cmd.equals("ok"))
		{
			String question = ((QAGUI) gui).getQuestion().getText();
			String answer = ((QAGUI) gui).getAnswer().getText();
			
			
			deck.addFlashcard(new Flashcard(index, question, answer));
			index++;
			((QAGUI) gui).getQuestion().setText("");
			((QAGUI) gui).getAnswer().setText("");
		}
		else if(cmd.equals("confirm")) {
			System.out.println(DeckManager.getDecks().get(1).getDeckname());
			for(Flashcard f: deck.getDeck()) {
				System.out.println(f.getIndex());
				System.out.println(f.getQuestion());
				System.out.println(f.getAnswer());
			}
			DeckManager.createDirectories();
			System.out.println(DeckManager.createDirectories().getAbsolutePath());
			
//			deck.loadInCSV();
			
		}
		else if(cmd.equals("confirmDeck")) {
			Deck newDeck = new Deck(((Gui) (gui)).getDeckName().getText());
			deck = newDeck;
			DeckManager.addDeck(index, newDeck);
			new QAGUI();
		}
	}
}
