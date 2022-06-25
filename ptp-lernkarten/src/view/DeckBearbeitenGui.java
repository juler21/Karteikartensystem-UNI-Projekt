package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import controller.ButtonListener;
import model.DeckManager;

public class DeckBearbeitenGui extends JFrame {

	private JList<String> deckAuswahlList;

	public DeckBearbeitenGui() {
		JPanel decksCard = new JPanel();
		add(decksCard, "decksCard");
		decksCard.setLayout(new GridLayout(1, 2, 0, 0));

		deckListeErstellen();

		decksCard.add(deckAuswahlList);

		JButton deckErstellenButton = new JButton("Deck Erstellen");

		Icon i = new ImageIcon("/ptp-lernkarten/the-button-859346_1280.png");
		deckErstellenButton.setIcon(i);

		deckErstellenButton.setBounds(146, 93, 141, 29);
		decksCard.add(deckErstellenButton);
		deckErstellenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		deckErstellenButton.setBackground(Color.GREEN);
		deckErstellenButton.setForeground(new Color(2, 48, 89));
		deckErstellenButton.addActionListener(new ButtonListener(app, this, "deckErstellen"));

		JButton deckBearbeitenButton = new JButton("Deck Bearbeiten");
		decksCard.add(deckBearbeitenButton);
		deckBearbeitenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		deckBearbeitenButton.setForeground(new Color(2, 48, 89));
	}

	private void deckListeErstellen() {

		// DummY Items für die Liste
		String[] decksArray = new String[10];
		DeckManager.getData(new File(DeckManager.getPathtoString()));
		DeckManager.getDecks().forEach((key, value) -> {
			decksArray[key] = value.getDeckname();
		});

		deckAuswahlList = new JList<>(decksArray);

	}
}
