package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
	private JLabel currentDeckLabel;
	private JPanel learnQuestionCard;
	private JLabel onlyQuestionTextLabel;
	private JPanel learnAnswerCard;
	private JLabel questionTextLabel;
	private JLabel answerTextLabel;
	private JPanel learnEndCard;

	private String fontStyle;

	private JComboBox<Deck> chooseDeckComboBox;

	private DeckManager deckmanager;

	public StartGui(DeckManager manager, String name) {
		this.deckmanager = manager;
		// JFrame erstellen
		mainFrame = new JFrame(name);

		fontStyle = "Helvetica";
		setUIFont(new javax.swing.plaf.FontUIResource(fontStyle, Font.PLAIN, 13));
		mainFrame.setTitle("Lernkarten");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, 500, 300);
		mainFrame.setPreferredSize(new Dimension(1000, 500));

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
//		startButton.addActionListener(new StartGuiListener(this, deckmanager, "startButtonPressed"));
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPanel.getLayout()).show(contentPanel, "startCard");
				((CardLayout) startCard.getLayout()).show(startCard, "learnHomeCard");
				try {
					getCurrentDeckLabel().setText(getSelectedDeck().getDeckname());
				} catch (NoDeckSelectedExeption e1) {
					getCurrentDeckLabel().setText("kein Deck gewählt");
				}
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
		// Combobox für Path, wo man die Carteikarten abspeichert
		JPanel directoryPanel = new JPanel(new FlowLayout());
		JComboBox<Path> comboboxdirectory = new JComboBox();
		comboboxdirectory.setPreferredSize(new Dimension(350, 30));
		comboboxdirectory.addItem(deckmanager.getPathDirectory());
		comboboxdirectory.setEditable(true);
		JButton browseButton = new JButton("Browse");
		browseButton.addActionListener(new StartGuiListener(this, deckmanager, "browse"));
		directoryPanel.add(comboboxdirectory, FlowLayout.LEFT);
		directoryPanel.add(browseButton);

		einstellungenCard.add(directoryPanel);
		contentPanel.add(einstellungenCard, "einstellungenCard");

		// Anfangsbildschirm setzten auf "Start"
		((CardLayout) contentPanel.getLayout()).show(contentPanel, "startCard");

	}

	private void generateLearnPanel() {

		// Karten bilden die Lernoberfläche: Liegen auf der startCard

		// Home-Karte
		learnHomeCard = new JPanel();
		learnHomeCard.setLayout(new BorderLayout());
		JPanel westPanel = new JPanel();

		startCard.add(learnHomeCard, "learnHomeCard");
		JButton lernenBeginnenButton = new JButton("Lernen Beginnen");
		lernenBeginnenButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel currentDeckInfoLabel = new JLabel("Aktuelles Deck:");
		JLabel startLearningLabel = new JLabel();
		startLearningLabel.setText("<html><body>Karteikarten einfach<br>lernen!</body></html>");
		startLearningLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		startLearningLabel.setFont(new Font(fontStyle, Font.PLAIN, 45));

		currentDeckInfoLabel.setFont(new Font(fontStyle, Font.PLAIN, 15));
		currentDeckLabel = new JLabel("noch kein Deck gewählt");
		currentDeckLabel.setFont(new Font(fontStyle, Font.PLAIN, 15));

		JPanel currentDeckPanel = new JPanel(new FlowLayout());
		currentDeckPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		westPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		westPanel.add(startLearningLabel, BorderLayout.WEST);
		westPanel.add(Box.createRigidArea(new Dimension(0, 10)));

//		learnHomeCard.add(Box.createGlue());
		westPanel.add(lernenBeginnenButton);
		currentDeckPanel.add(currentDeckInfoLabel);
		currentDeckPanel.add(currentDeckLabel);
		westPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		westPanel.add(currentDeckPanel);
		learnHomeCard.add(westPanel, BorderLayout.WEST);
		lernenBeginnenButton.addActionListener(new StartGuiListener(this, deckmanager, "lernenBeginnenButton"));
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		// learnHomeCard.add(westPanel, BorderLayout.WEST);

//		lernenBeginnenButton.addActionListener(new ActionListener() {
////
//			public void actionPerformed(ActionEvent e) {
//				((CardLayout) startCard.getLayout()).show(startCard, "learnQuestionCard");
//			}
//		});

		// Question-Karte
		learnQuestionCard = new JPanel();
		learnQuestionCard.setLayout(new GridLayout(2, 1));
		startCard.add(learnQuestionCard, "learnQuestionCard");
		onlyQuestionTextLabel = new JLabel(""); // default Deck einladen
		onlyQuestionTextLabel.setFont(new Font(fontStyle, Font.PLAIN, 20));

		JButton showAnswerButton = new JButton("Antwort zeigen");
		showAnswerButton.addActionListener(new StartGuiListener(this, deckmanager, "showAnswerButton"));
//		showAnswerButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				((CardLayout) startCard.getLayout()).show(startCard, "learnAnswerCard");
//			}
//		});

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
//		nextQuestionButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				((CardLayout) startCard.getLayout()).show(startCard, "learnQuestionCard");
//			}
//		});

		learnAnswerCard.add(questionLabel);
		learnAnswerCard.add(questionTextLabel);
		learnAnswerCard.add(answerLabel);
		learnAnswerCard.add(answerTextLabel);
		learnAnswerCard.add(nextQuestionButton);

		// learnEnd-Karte
		learnEndCard = new JPanel();
		startCard.add(learnEndCard, "learnEndCard");
		learnEndCard.setLayout(new GridLayout(3, 1));
		JLabel deckEndLabel = new JLabel("Deck-Ende erreicht");
		JButton restartDeckButton = new JButton("Erneut starten");
		JButton switchDeckButton = new JButton("neues Deck wählen");

		learnEndCard.add(deckEndLabel);
		learnEndCard.add(restartDeckButton);
		learnEndCard.add(switchDeckButton);

		restartDeckButton.addActionListener(new StartGuiListener(this, deckmanager, "restartDeckButton"));
		switchDeckButton.addActionListener(new StartGuiListener(this, deckmanager, "switchDeckButton"));

	}

	private void deckListeErstellen() {

		chooseDeckComboBox = new JComboBox<Deck>();

		// Was wird da gemacht (Julian)
		deckmanager.getData(new File(DeckManager.getPathtoString()));
		update("deckChange");
	}

	public JComboBox<Deck> getJCombobox() {
		return chooseDeckComboBox;
	}

	public Deck getSelectedDeck() throws NoDeckSelectedExeption {
		if (chooseDeckComboBox.getSelectedItem() != null) {
			return (Deck) (chooseDeckComboBox.getSelectedItem());
		} else {
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

		if (changeType.equals("deckChange")) {
			// combobox update
			if (!deckmanager.getDecks().isEmpty()) {
				chooseDeckComboBox.removeAllItems();
				deckmanager.getDecks().forEach((key, value) -> {
					chooseDeckComboBox.addItem(deckmanager.getDeck(key));
				});
				// setzt default Item in Combobox
				chooseDeckComboBox.setSelectedIndex(0);
			} else {
				chooseDeckComboBox.removeAllItems();
			}

		}

	}

	public void setLearnScreen(String screenCard) {

		System.out.println("methode wird aufgerufen");

		((CardLayout) startCard.getLayout()).show(startCard, screenCard);

	}

	public void setDashboardScreen(String screenCard) {

		System.out.println("methode wird aufgerufen");

		((CardLayout) contentPanel.getLayout()).show(contentPanel, screenCard);

	}

	public JLabel getCurrentDeckLabel() {
		return currentDeckLabel;
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

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public JPanel getFramePanel() {
		return framePanel;
	}

	public JPanel getTopButtonPanel() {
		return topButtonPanel;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public JButton getDecksButton() {
		return decksButton;
	}

	public JButton getStatistikButton() {
		return statistikButton;
	}

	public JButton getEinstellungenButton() {
		return einstellungenButton;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JPanel getStartCard() {
		return startCard;
	}

	public JPanel getDecksCard() {
		return decksCard;
	}

	public JPanel getStatistikCard() {
		return statistikCard;
	}

	public JPanel getEinstellungenCard() {
		return einstellungenCard;
	}

	public JPanel getLearnHomeCard() {
		return learnHomeCard;
	}

	public JPanel getLearnQuestionCard() {
		return learnQuestionCard;
	}

	public JPanel getLearnAnswerCard() {
		return learnAnswerCard;
	}

	public String getFontStyle() {
		return fontStyle;
	}

	public JComboBox<Deck> getChooseDeckComboBox() {
		return chooseDeckComboBox;
	}

	public DeckManager getDeckmanager() {
		return deckmanager;
	}
}
