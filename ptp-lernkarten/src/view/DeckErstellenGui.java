package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.ButtonListener;

public class DeckErstellenGui extends JFrame {

	private JTextField deckName;

	public DeckErstellenGui() {
		super("Deck erstellen");
		setLayout(new BorderLayout());

		JLabel label1 = new JLabel("Geben Sie den Name des Decks ein:");
		deckName = new JTextField("");
		JButton confirm = new JButton("confirm");
		confirm.addActionListener(new ButtonListener(this, "confirmDeck"));

		add(label1, BorderLayout.NORTH);
		add(deckName, BorderLayout.CENTER);
		add(confirm, BorderLayout.PAGE_END);
		pack();
		setVisible(true);

	}

	public JTextField getDeckName() {
		return deckName;
	}

}
