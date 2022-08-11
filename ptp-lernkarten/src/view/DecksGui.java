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

/** PTP 22 
* Hauptguiklasse: Startpunkt der Anwendung
* @author Mark Sterkel Julian Dillmann
* @version 
*/
public class DecksGui {
	
	private MainGui mainGui;
	private String fontStyle;
	
	private JComboBox<Deck> chooseDeckComboBox;
	private DeckManager deckmanager;
	
	/**
	* Der DecksGui Konstruktor erstellt die DecksGui mit ihren Komponenten 
	* und entsprechenden Listenern.
	*/
	DecksGui(JPanel decksCard, DeckManager deckmanager, String fontstyle, MainGui maingui) {
		
		this.mainGui = maingui;
		this.chooseDeckComboBox = mainGui.getJCombobox();
		this.deckmanager = deckmanager;
		fontStyle = fontstyle;
		
		JPanel comboBoxPanel = new JPanel();
		chooseDeckComboBox.setPreferredSize(new Dimension(700, 40));
		comboBoxPanel.add(chooseDeckComboBox);
		decksCard.add(comboBoxPanel);

		JButton deckErstellenButton = new JButton("Deck Erstellen");
		deckErstellenButton.setFont(new Font(fontStyle, Font.PLAIN, 27));
		deckErstellenButton.addActionListener(new DecksGuiListener(this, deckmanager, fontStyle,  "deckErstellen"));
		decksCard.add(deckErstellenButton);
		
		JButton deckBearbeitenButton = new JButton("Deck Bearbeiten");
		deckBearbeitenButton.setFont(new Font(fontStyle, Font.PLAIN, 27));
		deckBearbeitenButton.addActionListener(new DecksGuiListener(this, deckmanager, fontStyle, "deckBearbeiten"));
		decksCard.add(deckBearbeitenButton);
		
		JButton deckLöschenButton = new JButton("Deck Löschen");
		deckLöschenButton.setFont(new Font(fontStyle, Font.PLAIN, 27));
		deckLöschenButton.addActionListener(new DecksGuiListener(this, deckmanager, fontStyle, "deckLöschen"));
		decksCard.add(deckLöschenButton);
		
	}
	
	/**
	* gibt das aktuell in der Combobox gewählte Deck zurück 
	* 
	* @return das aktuell gewählte Deck als String 
	* @exception NoDeckSelectedExeption wenn kein Deck gewählt ist 
	* 
	*/
	public String getSelectedDeck() throws NoDeckSelectedExeption {
		return mainGui.getSelectedDeck();
	}
}
