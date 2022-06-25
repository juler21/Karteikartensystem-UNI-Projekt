package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import model.Deck;
import util.Main;
import view.CreateDeckGui;
import view.EditDeckGui;

public class ButtonListener implements ActionListener {

	private JFrame gui;
	private Main app;
	private String cmd;
	private int index;
	private static Deck deck;

	public ButtonListener(JFrame gui, String cmd) {
		this.app = app;
		this.gui = gui;
		this.cmd = cmd;

		index = 1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doCommand(cmd);
	}

	private void doCommand(String cmd) {
		if (cmd.equals("deckErstellen")) {

			new CreateDeckGui(app);

		} else if (cmd.equals("deckBearbeiten")) {
			new EditDeckGui(app);
		}
	}
}
