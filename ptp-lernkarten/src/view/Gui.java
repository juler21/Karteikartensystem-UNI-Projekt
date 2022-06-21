package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import controller.ButtonListener;

public class Gui extends JFrame {
	
	private JTextField deckName;
	
	public Gui() {
		super("Deck erstellen");
		setLayout(new BorderLayout());
		
		JLabel label1 = new JLabel("Geben Sie den Name des Decks ein:");
		deckName = new JTextField("Deckname");
		deckName.setForeground(Color.gray);
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
