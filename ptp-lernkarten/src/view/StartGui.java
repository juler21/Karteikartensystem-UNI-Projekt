package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ButtonListener;
<<<<<<< HEAD
import util.Main;

import java.awt.SystemColor;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.GridLayout;
=======
import model.DeckManager;
>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git

public class StartGui extends JFrame {
<<<<<<< HEAD
	
 	private Main app;
=======

	public static void main(String[] args) {

		new StartGui();
	}

>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git
	private JFrame mainFrame;
	private JPanel frameContentPane;
	private JPanel startContentPane;
	private JPanel topButtonPanel;
	private JList<String> deckAuswahlList;

	/**
	 * Create the frame.
	 */
<<<<<<< HEAD
	public StartGui(Main app) {	
		
		this.app = app;
		//JFrame erstellen
=======
	public StartGui() {

		// JFrame erstellen
>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git
		mainFrame = new JFrame();
		mainFrame.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		mainFrame.setTitle("Lernkarten");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, 500, 300);

		// frameContentPane erstellen, dass direkt auf dem JFrame liegt
		frameContentPane = new JPanel();
		frameContentPane.setBackground(new Color(244, 244, 244));
		frameContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainFrame.setContentPane(frameContentPane);
		frameContentPane.setLayout(new BorderLayout(0, 0));

		// topButtonPanel mit entsprechenden Buttons erstellen
		topButtonsErstellen();

		// startContentPane mit Karten das auf dem frameContentPane liegt erstellen
		startContentPaneErstellen();

	}

	private void topButtonsErstellen() {

		// topButtonPanel erstellen und zum frameContentPane hinzufügen
		topButtonPanel = new JPanel();
		frameContentPane.add(topButtonPanel, BorderLayout.NORTH);
		topButtonPanel.setBackground(new Color(244, 244, 244));

		JButton startButton = new JButton("START");
		topButtonPanel.add(startButton);
		startButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		startButton.setForeground(new Color(2, 48, 89));
		startButton.setBackground(Color.GREEN);

		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) startContentPane.getLayout()).show(startContentPane, "startCard");
			}
		});

		JButton decksButton = new JButton("DECKS");
		topButtonPanel.add(decksButton);
		decksButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		decksButton.setForeground(new Color(2, 48, 89));
		decksButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) startContentPane.getLayout()).show(startContentPane, "decksCard");
			}
		});

		JButton statistikButton = new JButton("STATISTIK");
		topButtonPanel.add(statistikButton);
		statistikButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		statistikButton.setForeground(new Color(2, 48, 89));

		statistikButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) startContentPane.getLayout()).show(startContentPane, "statistikCard");
			}
		});

		JButton einstellungenButton = new JButton("EINSTELLUNGEN");
		topButtonPanel.add(einstellungenButton);
		einstellungenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		einstellungenButton.setForeground(new Color(2, 48, 89));

		einstellungenButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) startContentPane.getLayout()).show(startContentPane, "einstellungenCard");
			}
		});

	}

	private void startContentPaneErstellen() {

		startContentPane = new JPanel();
		frameContentPane.add(startContentPane, BorderLayout.CENTER);
		frameContentPane.setBackground(new Color(244, 244, 244));
		startContentPane.setLayout(new CardLayout(0, 0));

		// Start-Karte
		JPanel startCard = new JPanel();
		startContentPane.add(startCard, "startCard");
		startCard.setLayout(null);
		JButton lernenBeginnenButton = new JButton("Lernen Beginnen");
		lernenBeginnenButton.setBounds(146, 93, 141, 29);
		startCard.add(lernenBeginnenButton);
		lernenBeginnenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lernenBeginnenButton.setBackground(Color.GREEN);
		lernenBeginnenButton.setForeground(new Color(2, 48, 89));

		// Decks-Karte
		JPanel decksCard = new JPanel();
		startContentPane.add(decksCard, "decksCard");
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
<<<<<<< HEAD
		deckErstellenButton.setForeground(new Color (2, 48, 89));
		deckErstellenButton.addActionListener(new ButtonListener(app, this, "deckErstellen"));
		
=======
		deckErstellenButton.setForeground(new Color(2, 48, 89));
		deckErstellenButton.addActionListener(new ButtonListener(this, "deckErstellen"));

>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git
		JButton deckBearbeitenButton = new JButton("Deck Bearbeiten");
		decksCard.add(deckBearbeitenButton);
		deckBearbeitenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		deckBearbeitenButton.setForeground(new Color(2, 48, 89));
		deckBearbeitenButton.addActionListener(new ButtonListener(this, "deckBearbeiten"));

		// Statistik-Karte
		JPanel statistikCard = new JPanel();
		startContentPane.add(statistikCard, "statistikCard");

		// Statistik-Karte
		JPanel einstellungenCard = new JPanel();
		startContentPane.add(einstellungenCard, "einstellungenCard");

		// Anfangsbildschirm setzten auf "Start"
		((CardLayout) startContentPane.getLayout()).show(startContentPane, "startCard");
		mainFrame.setVisible(true);

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
