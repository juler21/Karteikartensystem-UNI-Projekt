package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.SettingsGuiListener;
import model.DeckManager;

/** 
* PTP 22 - SettingsGuiklasse: Zuständig für die Einstellungen-Seite auf der das Theme geändert
* 
* @author J.Dillmann, M. Sterkel
*/
public class SettingsGui {

	private String fontStyle;
	private JButton switchThemeButton;

	/**
	* Der SettingsGui Konstruktor erstellt die SettingsGui mit ihren Komponenten 
	* und entsprechenden Listenern.
	*/
	public SettingsGui(JPanel einstellungenCard, String fontstyle, DeckManager deckmanager) {

		this.fontStyle = fontstyle;

		einstellungenCard.setLayout(new GridLayout(2, 1));
		// Combobox für Path, wo die Karteikarten abspeichert werden
		JPanel directoryPanel = new JPanel(new FlowLayout());
		JComboBox<String> comboboxdirectory = new JComboBox<String>();
		comboboxdirectory.addItem(DeckManager.getPathtoString());
		comboboxdirectory.setPreferredSize(new Dimension(500, 40));
		comboboxdirectory.setEditable(true);
		JButton browseButton = new JButton("Browse");
		directoryPanel.add(comboboxdirectory, FlowLayout.LEFT);
		directoryPanel.add(browseButton);
		einstellungenCard.add(directoryPanel);

		switchThemeButton = new JButton("Dark Mode");
		switchThemeButton.setFont(new Font(fontStyle, Font.PLAIN, 20));
		switchThemeButton.addActionListener(new SettingsGuiListener(this, "switchThemeButton"));
		directoryPanel.add(switchThemeButton);
	}

	public JButton getSwitchThemeButton() {
		return switchThemeButton;
	}

}
