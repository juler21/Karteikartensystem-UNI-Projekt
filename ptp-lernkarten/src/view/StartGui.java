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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import controller.StartGuiListener;
import model.Deck;
import model.DeckManager;
import util.NoDeckSelectedExeption;

public class StartGui extends JFrame implements Observer {

	// Fenster
	private JFrame mainFrame;
	private JPanel framePanel;

	// TopButton Panel mit Navigationsknöpfen
	private JPanel topButtonPanel;
	private JButton startButton;
	private JButton decksButton;
	private JButton statistikButton;
	private JButton einstellungenButton;

	// ContentPanel auf dem verschiedene CardLayout Karten liegen
	private JPanel contentPanel;
	private JPanel startCard;
	private JPanel decksCard;
	private JPanel statistikCard;
	private JPanel einstellungenCard;

	// startCard auf der verschiedene LearnPanel liegen
	private JPanel learnHomeCard;
	private JPanel learnQuestionCard;
	private JLabel onlyQuestionTextLabel;
	private JPanel learnAnswerCard;
	private JLabel questionTextLabel;
	private JLabel answerTextLabel;

	private String fontStyle;

	private JComboBox<Deck> chooseDeckComboBox;

	private DeckManager deckmanager;

	public StartGui(DeckManager manager, String name) {
		this.deckmanager = manager;

		// JFrame erstellen
		mainFrame = new JFrame(name);
		fontStyle = "Ubuntu";
		setUIFont(new javax.swing.plaf.FontUIResource(fontStyle, Font.PLAIN, 13));
		mainFrame.setTitle("Lernkarten");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, 500, 300);

		// frameContentPane erstellen, dass direkt auf dem JFrame liegt
		framePanel = new JPanel();
		framePanel.setLayout(new BorderLayout(0, 0));
		mainFrame.setContentPane(framePanel);

		// topButtonPanel mit entsprechenden Buttons erstellen
		generateTopButtons();

		// startContentPane mit Karten das auf dem frameContentPane liegt erstellen
		generateContentPanel();

		// LearnPanel erstellen, dass auf der StartCard liegt und die Kartenlern
		// "frames" anzeigt
		generateLearnPanel();
		deckmanager.registerObserver(this);
		mainFrame.setVisible(true);

	}

	private void generateTopButtons() {

		// topButtonPanel erstellen und zum frameContentPane hinzufügen
		topButtonPanel = new JPanel();
		framePanel.add(topButtonPanel, BorderLayout.NORTH);
		topButtonPanel.setBackground(new Color(244, 244, 244));

		// BUTTONS
		startButton = new JButton("START");
		decksButton = new JButton("DECKS");
		statistikButton = new JButton("STATISTIK");
		einstellungenButton = new JButton("EINSTELLUNGEN");

		topButtonPanel.add(startButton);
		topButtonPanel.add(decksButton);
		topButtonPanel.add(statistikButton);
		topButtonPanel.add(einstellungenButton);

		// ACTIONLISTENER
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPanel.getLayout()).show(contentPanel, "startCard");
				((CardLayout) startCard.getLayout()).show(startCard, "learnHomeCard");
			}
		});

		decksButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPanel.getLayout()).show(contentPanel, "decksCard");
			}
		});

		statistikButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPanel.getLayout()).show(contentPanel, "statistikCard");
			}
		});

		einstellungenButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPanel.getLayout()).show(contentPanel, "einstellungenCard");
			}
		});

	}

	private void generateContentPanel() {

		// ContentPanel erstellen
		contentPanel = new JPanel();
		contentPanel.setLayout(new CardLayout(0, 0));
		framePanel.add(contentPanel, BorderLayout.CENTER);

		// Start-Karte
		startCard = new JPanel();
		contentPanel.add(startCard, "startCard");
		startCard.setLayout(new CardLayout(0, 0));

		// Decks-Karte
		decksCard = new JPanel();
		decksCard.setLayout(new GridLayout(4, 1, 0, 0));
		contentPanel.add(decksCard, "decksCard");

		deckListeErstellen();
		decksCard.add(chooseDeckComboBox);

		JButton deckErstellenButton = new JButton("Deck Erstellen");
		deckErstellenButton.addActionListener(new StartGuiListener(this, deckmanager, "deckErstellen"));
		decksCard.add(deckErstellenButton);

		JButton deckBearbeitenButton = new JButton("Deck Bearbeiten");
		deckBearbeitenButton.addActionListener(new StartGuiListener(this, deckmanager, "deckBearbeiten"));
		decksCard.add(deckBearbeitenButton);

		JButton deckLöschenButton = new JButton("Deck Löschen");
		deckLöschenButton.addActionListener(new StartGuiListener(this, deckmanager, "deckLöschen"));
		decksCard.add(deckLöschenButton);

		// Statistik-Karte
		statistikCard = new JPanel();
		contentPanel.add(statistikCard, "statistikCard");

		// Einstellungen-Karte
		einstellungenCard = new JPanel();
		contentPanel.add(einstellungenCard, "einstellungenCard");

		// Anfangsbildschirm setzten auf "Start"
		((CardLayout) contentPanel.getLayout()).show(contentPanel, "startCard");

	}

	private void generateLearnPanel() {

		// Karten bilden die Lernoberfläche: Liegen auf der startCard

		// Home-Karte
		learnHomeCard = new JPanel();
		learnHomeCard.setLayout(new BorderLayout());
		startCard.add(learnHomeCard, "learnHomeCard");

		JButton lernenBeginnenButton = new JButton("Lernen Beginnen");
		learnHomeCard.add(lernenBeginnenButton, BorderLayout.CENTER);
		lernenBeginnenButton.addActionListener(new StartGuiListener(this, deckmanager, "lernenBeginnenButton"));
		lernenBeginnenButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) startCard.getLayout()).show(startCard, "learnQuestionCard");
			}
		});

		// Question-Karte
		learnQuestionCard = new JPanel();
		learnQuestionCard.setLayout(new GridLayout(2, 1));
		startCard.add(learnQuestionCard, "learnQuestionCard");
		onlyQuestionTextLabel = new JLabel(""); // default Deck einladen
		onlyQuestionTextLabel.setFont(new Font(fontStyle, Font.PLAIN, 20));

		JButton showAnswerButton = new JButton("Antwort zeigen");
		showAnswerButton.addActionListener(new StartGuiListener(this, deckmanager, "showAnswerButton"));
		showAnswerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) startCard.getLayout()).show(startCard, "learnAnswerCard");
			}
		});

		learnQuestionCard.add(onlyQuestionTextLabel);
		learnQuestionCard.add(showAnswerButton);

		// Answer-Karte
		learnAnswerCard = new JPanel();
		learnAnswerCard.setLayout(new GridLayout(5, 1));
		startCard.add(learnAnswerCard, "learnAnswerCard");

		JLabel questionLabel = new JLabel("Frage:");
		questionTextLabel = new JLabel("");
		questionTextLabel.setFont(new Font(fontStyle, Font.PLAIN, 20));
		JLabel answerLabel = new JLabel("Antwort:");
		answerTextLabel = new JLabel("");
		answerTextLabel.setFont(new Font(fontStyle, Font.PLAIN, 20));

		JButton nextQuestionButton = new JButton("Nächste Frage");
		nextQuestionButton.addActionListener(new StartGuiListener(this, deckmanager, "nextQuestionButton"));
		nextQuestionButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) startCard.getLayout()).show(startCard, "learnQuestionCard");
			}
		});

		learnAnswerCard.add(questionLabel);
		learnAnswerCard.add(questionTextLabel);
		learnAnswerCard.add(answerLabel);
		learnAnswerCard.add(answerTextLabel);
		learnAnswerCard.add(nextQuestionButton);

		// muss hier erstellt werden da sonst qustionLabel... noch nicht erstellt
//		chooseDeckComboBox.setSelectedIndex(0);
		chooseDeckComboBox.addActionListener(new StartGuiListener(this, deckmanager, "chooseDeckComboBox"));
	}

	public JLabel getOnlyQuestionTextLabel() {
		return onlyQuestionTextLabel;
	}

	public void setOnlyQuestionTextLabel(String text) {
		this.onlyQuestionTextLabel.setText(text);
	}

	public JLabel getQuestionTextLabel() {
		return questionTextLabel;
	}

	public void setQuestionTextLabel(String text) {
		this.questionTextLabel.setText(text);
	}

	public JLabel getAnswerTextLabel() {
		return answerTextLabel;
	}

	public void setAnswerTextLabel(String text) {
		this.answerTextLabel.setText(text);
	}

	private void deckListeErstellen() {

		chooseDeckComboBox = new JComboBox<Deck>();

		deckmanager.getData(new File(DeckManager.getPathtoString()));
		update("deckChange");
	}

	public JComboBox<Deck> getJCombobox() {
		return chooseDeckComboBox;
	}

	public Deck getSelectedDeck() throws NoDeckSelectedExeption {
		if (chooseDeckComboBox.getSelectedItem()!= null) {
			return (Deck) (chooseDeckComboBox.getSelectedItem());	
		}
		else {
			throw new NoDeckSelectedExeption();
		}
	}

	/*
	 * Setzt alle Default UI Fonts auf die übergebene Font
	 *
	 */
	private void setUIFont(javax.swing.plaf.FontUIResource f) {
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource)
				UIManager.put(key, f);
		}
	}

	@Override
	public void update(String changeType) {

		System.out.println("update");
		
		if(changeType.equals("deckChange")) {
		// combobox update
			if (!deckmanager.getDecks().isEmpty()) {
				chooseDeckComboBox.removeAllItems();
				deckmanager.getDecks().forEach((key, value) -> {
					chooseDeckComboBox.addItem(deckmanager.getDeck(key));
				});
				// setzt default Item in Combobox
//				chooseDeckComboBox.setSelectedIndex(0);
			}
		}

	}
}
