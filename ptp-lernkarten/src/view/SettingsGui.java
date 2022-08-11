package view;

import java.awt.CardLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.MainGuiListener;
import controller.SettingsGuiListener;
import model.DeckManager;

/** PTP 22 
* Zuständig für die Einstellungen-Seite auf der das Theme geändert werden kann
* @author Mark Sterkel & Julian Dillmann
* @version 
*/
public class SettingsGui {
	
	private JPanel einstellungenCard;
	private String fontStyle;
	private JButton switchThemeButton;
	
	public SettingsGui(JPanel einstellungenCard, String fontstyle) {
		
		this.einstellungenCard = einstellungenCard;
		this.fontStyle = fontstyle;

		einstellungenCard.setLayout(new GridLayout(2,1));
		// Combobox für Path, wo die Karteikarten abspeichert werden
		JPanel directoryPanel = new JPanel(new FlowLayout());
		JComboBox<Path> comboboxdirectory = new JComboBox();
		comboboxdirectory.setPreferredSize(new Dimension(500, 40));
		comboboxdirectory.setEditable(true);
		JButton browseButton = new JButton("Browse");
		directoryPanel.add(comboboxdirectory, FlowLayout.LEFT);
		directoryPanel.add(browseButton);
		einstellungenCard.add(directoryPanel);

		switchThemeButton = new JButton("Dark Mode");
		switchThemeButton.setFont(new Font(fontStyle, Font.PLAIN, 27));
		switchThemeButton.addActionListener(new SettingsGuiListener(this, "switchThemeButton"));
		einstellungenCard.add(switchThemeButton);


	}
	
	public JButton getSwitchThemeButton() {
		return switchThemeButton;
	}

}
