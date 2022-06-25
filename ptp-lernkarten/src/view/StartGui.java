package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.StartGuiListener;
import model.Deck;
import model.DeckManager;

public class StartGui extends JFrame implements Observer {

	// Fenster
	private JFrame mainFrame;
	private JPanel framePanel;

	// TopButton Panel mit Navigationsknöpfen
	private JPanel topButtonPanel;
	// ContentPanel auf dem verschiedene CardLayout Karten liegen
	private JPanel contentPanel;
	private JComboBox<Deck> chooseDeckList;

	private DeckManager deckmanager;

	public StartGui(DeckManager manager, String name) {
		this.deckmanager = manager;

		// JFrame erstellen
		mainFrame = new JFrame(name);
		mainFrame.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		mainFrame.setTitle("Lernkarten");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, 500, 300);

		// frameContentPane erstellen, dass direkt auf dem JFrame liegt
		framePanel = new JPanel();
		framePanel.setBackground(new Color(244, 244, 244));
		framePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		framePanel.setLayout(new BorderLayout(0, 0));
		mainFrame.setContentPane(framePanel);

		// topButtonPanel mit entsprechenden Buttons erstellen
		generateTopButtons();

		// startContentPane mit Karten das auf dem frameContentPane liegt erstellen
		generateContentPanel();

		mainFrame.setVisible(true);

	}

	private void generateTopButtons() {

		// topButtonPanel erstellen und zum frameContentPane hinzufügen
		topButtonPanel = new JPanel();
		framePanel.add(topButtonPanel, BorderLayout.NORTH);
		topButtonPanel.setBackground(new Color(244, 244, 244));

		// START BUTTON
		JButton startButton = new JButton("START");
		startButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		startButton.setForeground(new Color(2, 48, 89));
		startButton.setBackground(Color.GREEN);
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPanel.getLayout()).show(contentPanel, "startCard");
			}
		});
		topButtonPanel.add(startButton);

		// DECKS BUTTON
		JButton decksButton = new JButton("DECKS");
		decksButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		decksButton.setForeground(new Color(2, 48, 89));
		decksButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPanel.getLayout()).show(contentPanel, "decksCard");
			}
		});
		topButtonPanel.add(decksButton);

		// STATISTIK BUTTON
		JButton statistikButton = new JButton("STATISTIK");
		statistikButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		statistikButton.setForeground(new Color(2, 48, 89));

		statistikButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPanel.getLayout()).show(contentPanel, "statistikCard");
			}
		});
		topButtonPanel.add(statistikButton);

		// EINSTELLUNGEN BUTTON
		JButton einstellungenButton = new JButton("EINSTELLUNGEN");
		einstellungenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		einstellungenButton.setForeground(new Color(2, 48, 89));

		einstellungenButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPanel.getLayout()).show(contentPanel, "einstellungenCard");
			}
		});
		topButtonPanel.add(einstellungenButton);

	}

// 
	private void generateContentPanel() {

		contentPanel = new JPanel();
		contentPanel.setLayout(new CardLayout(0, 0));
		framePanel.add(contentPanel, BorderLayout.CENTER);

		// Start-Karte
		JPanel startCard = new JPanel();
		contentPanel.add(startCard, "startCard");
		startCard.setLayout(null);
		JButton lernenBeginnenButton = new JButton("Lernen Beginnen");
		lernenBeginnenButton.setBounds(146, 93, 141, 29);
		startCard.add(lernenBeginnenButton);
		lernenBeginnenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lernenBeginnenButton.setBackground(Color.GREEN);
		lernenBeginnenButton.setForeground(new Color(2, 48, 89));

		// Decks-Karte
		JPanel decksCard = new JPanel();
		decksCard.setLayout(new GridLayout(3, 1, 0, 0));
		contentPanel.add(decksCard, "decksCard");

		deckListeErstellen();
		decksCard.add(chooseDeckList);

		JButton deckErstellenButton = new JButton("Deck Erstellen");
		deckErstellenButton.setBounds(146, 93, 141, 29);
		deckErstellenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		deckErstellenButton.setBackground(Color.GREEN);
		deckErstellenButton.setForeground(new Color(2, 48, 89));
		deckErstellenButton.addActionListener(new StartGuiListener(this, deckmanager, "deckErstellen"));
		decksCard.add(deckErstellenButton);

		JButton deckBearbeitenButton = new JButton("Deck Bearbeiten");
		deckBearbeitenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		deckBearbeitenButton.setForeground(new Color(2, 48, 89));
		deckBearbeitenButton.addActionListener(new StartGuiListener(this, deckmanager, "deckBearbeiten"));
		decksCard.add(deckBearbeitenButton);

		// Statistik-Karte
		JPanel statistikCard = new JPanel();
		contentPanel.add(statistikCard, "statistikCard");

		// Einstellungen-Karte
		JPanel einstellungenCard = new JPanel();
		contentPanel.add(einstellungenCard, "einstellungenCard");

		// Anfangsbildschirm setzten auf "Start"
		((CardLayout) contentPanel.getLayout()).show(contentPanel, "startCard");

	}

	private void deckListeErstellen() {

		Deck[] decksArray = new Deck[10];
		deckmanager.getData(new File(deckmanager.getPathtoString()));
		for (int i = 0; i < deckmanager.getDecks().size(); i++) {
			
			decksArray[i] = deckmanager.getDeck(i);
		}

		chooseDeckList = new JComboBox<Deck>(decksArray);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
