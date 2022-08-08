package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.MainGuiListener;
import controller.SettingsGuiListener;
import model.DeckManager;

public class SettingsGui {
	
	private JPanel einstellungenCard;
	private JButton switchThemeButton;
	
	
	public SettingsGui(JPanel einstellungenCard) {
		
		this.einstellungenCard = einstellungenCard;

		// Combobox für Path, wo man die Carteikarten abspeichert
		JPanel directoryPanel = new JPanel(new FlowLayout());

		JComboBox<Path> comboboxdirectory = new JComboBox();
		comboboxdirectory.setPreferredSize(new Dimension(500, 40));
//		comboboxdirectory.addItem(new Path());
		comboboxdirectory.setEditable(true);
		JButton browseButton = new JButton("Browse");
		directoryPanel.add(comboboxdirectory, FlowLayout.LEFT);
		directoryPanel.add(browseButton);
		einstellungenCard.add(directoryPanel);

		switchThemeButton = new JButton("Dark Mode");
		switchThemeButton.addActionListener(new SettingsGuiListener(this, "switchThemeButton"));
		einstellungenCard.add(switchThemeButton);


	}
	
	public JButton getSwitchThemeButton() {
		return switchThemeButton;
	}

}
