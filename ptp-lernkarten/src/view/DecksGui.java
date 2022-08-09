package view;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.DecksGuiListener;
import model.Deck;
import model.DeckManager;
import util.NoDeckSelectedExeption;

public class DecksGui {
	
	private String fontStyle;
	private MainGui mainGui;
	
	private JComboBox<Deck> chooseDeckComboBox;
	
	DecksGui(JPanel decksCard, DeckManager deckmanager, MainGui maingui) {
		
		this.mainGui = maingui;
		this.chooseDeckComboBox = mainGui.getJCombobox();
		fontStyle = "Helvetica";
		
		JPanel comboBoxPanel = new JPanel();
		chooseDeckComboBox.setPreferredSize(new Dimension(700, 40));
		comboBoxPanel.add(chooseDeckComboBox);
		decksCard.add(comboBoxPanel);

		JButton deckErstellenButton = new JButton("Deck Erstellen");
		deckErstellenButton.setFont(new Font(fontStyle, Font.PLAIN, 27));
		deckErstellenButton.addActionListener(new DecksGuiListener(this, deckmanager, "deckErstellen"));
		decksCard.add(deckErstellenButton);
		
		JButton deckBearbeitenButton = new JButton("Deck Bearbeiten");
		deckBearbeitenButton.setFont(new Font(fontStyle, Font.PLAIN, 27));
		deckBearbeitenButton.addActionListener(new DecksGuiListener(this, deckmanager, "deckBearbeiten"));
		decksCard.add(deckBearbeitenButton);
		
		JButton deckLöschenButton = new JButton("Deck Löschen");
		deckLöschenButton.setFont(new Font(fontStyle, Font.PLAIN, 27));
		deckLöschenButton.addActionListener(new DecksGuiListener(this, deckmanager, "deckLöschen"));
		decksCard.add(deckLöschenButton);
		
	}
	
	public String getSelectedDeck() throws NoDeckSelectedExeption {
		return mainGui.getSelectedDeck();
	}
	


}
