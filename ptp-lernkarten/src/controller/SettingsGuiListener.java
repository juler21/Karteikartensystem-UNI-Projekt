package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UnsupportedLookAndFeelException;

import model.DeckManager;
import view.CreateDeckGui;
import view.MainGui;
import view.SettingsGui;

public class SettingsGuiListener implements ActionListener{
	private String cmd;
	private String theme;
	private SettingsGui settingsGui;
	
	public SettingsGuiListener(SettingsGui settingsgui, String cmd) {
		this.theme = "light";
		this.settingsGui = settingsgui;
		this.cmd = cmd;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
		
	}
	private void doCommand(String cmd) {
		if (cmd.equals("switchThemeButton")) {
			try {
				if (theme.equals("light")) {
					javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme());
					com.formdev.flatlaf.FlatLaf.updateUI();
					settingsGui.getSwitchThemeButton().setText("Light Mode");
					theme = "dark";
				} else if (theme.equals("dark")) {
					javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme());
					com.formdev.flatlaf.FlatLaf.updateUI();
					settingsGui.getSwitchThemeButton().setText("Dark Mode");
					theme = "light";
				}
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
}