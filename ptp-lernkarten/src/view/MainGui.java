package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.MainGuiListener;
import model.Deck;
import model.DeckManager;
import util.NoDeckSelectedExeption;

/**
 * PTP 22 - HauptGui-Klasse: Startpunkt der Anwendung von hier aus werden die
 * anderen Gui KLassen erstellt
 * 
 * @author J.Dillmann, M. Sterkel
 */
public class MainGui extends JFrame implements Observer {

	private static final long serialVersionUID = 531951800455880798L;

	// Fenster
	private JFrame mainFrame;
	private JPanel framePanel;

	// TopButton Panel mit Navigationsknöpfen
	private JPanel topButtonPanel;
	private JButton startButton;
	private JButton decksButton;
	private JButton statisticButton;
	private JButton settingsButton;

	// ContentPanel auf dem verschiedene CardLayout Karten liegen
	private JPanel contentPanel;
	private CardLayout contentPanelCardLayout;
	private JPanel startCard;
	private JPanel decksCard;
	private JPanel statisticCard;
	private JPanel settingsCard;
	private String fontStyle;

	private DeckManager deckmanager;
	private StartGui startGui;
	private JComboBox<Deck> chooseDeckComboBox;

	/**
	 * Der MainGui Konstruktor erstellt die MainGui mit ihren Komponenten und
	 * entsprechenden Listenern.
	 */
	public MainGui(DeckManager manager, String name) {

		this.deckmanager = manager;
		// JFrame erstellen
		mainFrame = new JFrame(name);

		fontStyle = "Helvetica";
		setUIFont(new javax.swing.plaf.FontUIResource(fontStyle, Font.PLAIN, 20));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(270, 170, 900, 550);
		mainFrame.setMinimumSize(new Dimension(680, 400));

		// frameContentPane erstellen, dass direkt auf dem JFrame liegt
		framePanel = new JPanel();
		framePanel.setLayout(new BorderLayout(0, 0));
		mainFrame.setContentPane(framePanel);

		deckListeErstellen();

		// topButtonPanel mit entsprechenden Buttons erstellen
		generateTopButtons();

		// startContentPane mit Karten das auf dem frameContentPane liegt erstellen
		generateContentPanel();

		deckmanager.registerObserver(this);
		mainFrame.setVisible(true);
	}

	/**
	 * erstellt das topButtonPanel zur Navigation
	 */
	private void generateTopButtons() {
		// topButtonPanel erstellen und zum frameContentPane hinzufügen
		topButtonPanel = new JPanel();
		topButtonPanel.setLayout(new FlowLayout());
		topButtonPanel.setBorder(new EmptyBorder(15, 5, 15, 5));
		framePanel.add(topButtonPanel, BorderLayout.NORTH);

		// BUTTONS
		startButton = new JButton("START");
		startButton.setPreferredSize(new Dimension(130, 40));
		decksButton = new JButton("DECKS");
		decksButton.setPreferredSize(new Dimension(130, 40));
		statisticButton = new JButton("STATISTIK");
		statisticButton.setPreferredSize(new Dimension(160, 40));
		settingsButton = new JButton("EINSTELLUNGEN");
		settingsButton.setPreferredSize(new Dimension(230, 40));

		topButtonPanel.add(startButton);
		topButtonPanel.add(decksButton);
		topButtonPanel.add(statisticButton);
		topButtonPanel.add(settingsButton);

		// ACTIONLISTENER
		startButton.addActionListener(new MainGuiListener(this, deckmanager, "startCard"));
		decksButton.addActionListener(new MainGuiListener(this, deckmanager, "decksCard"));
		statisticButton.addActionListener(new MainGuiListener(this, deckmanager, "statistikCard"));
		settingsButton.addActionListener(new MainGuiListener(this, deckmanager, "einstellungenCard"));
	}

	/**
	 * erstellt das contenPanel mit CardLayout und die jeweiligen Karten
	 */
	private void generateContentPanel() {
		// ContentPanel erstellen
		contentPanel = new JPanel();
		contentPanelCardLayout = new CardLayout(0, 0);
		contentPanel.setLayout(contentPanelCardLayout);
		framePanel.add(contentPanel, BorderLayout.CENTER);

		// Start-Karte
		startCard = new JPanel();
		startCard.setLayout(new CardLayout(0, 0));
		startGui = new StartGui(startCard, deckmanager, fontStyle, this);
		contentPanel.add(startCard, "startCard");

		// Decks-Karte
		decksCard = new JPanel();
		decksCard.setLayout(new GridLayout(4, 1, 10, 5));
		new DecksGui(decksCard, deckmanager, fontStyle, this);
		contentPanel.add(decksCard, "decksCard");

		// Statistik-Karte
		statisticCard = new JPanel();
		contentPanel.add(statisticCard, "statistikCard");

		// Einstellungen-Karte
		settingsCard = new JPanel();
		new SettingsGui(settingsCard, fontStyle, deckmanager);
		contentPanel.add(settingsCard, "einstellungenCard");
	}

	/**
	 * erstellt die ComboBox, die in der DecksGui angezeigt wird und alle Decks
	 * enthält
	 */
	private void deckListeErstellen() {
		chooseDeckComboBox = new JComboBox<Deck>();
		chooseDeckComboBox.setSize(getPreferredSize());
		update("deckChange");
	}

	/**
	 * gibt das aktuell in der Combobox gewählte Deck zurück
	 * 
	 * @return das aktuell gewählte Deck als String
	 * @exception NoDeckSelectedExeption wenn kein Deck gewählt ist
	 * 
	 */
	public String getSelectedDeck() throws NoDeckSelectedExeption {
		if (chooseDeckComboBox.getSelectedItem() != null) {
			return chooseDeckComboBox.getSelectedItem().toString();
		} else {
			throw new NoDeckSelectedExeption();
		}
	}

	/*
	 * Update Methode des ObserverPattern: Updated die ComboBox sofern es eine
	 * Änderung in der DeckListe gab
	 * 
	 * @param changeType über den Parameter können DeckListen und Deck Änderungen
	 * unterschieden werden
	 *
	 */
	@Override
	public void update(String changeType) {
		if (changeType.equals("deckChange")) {
			System.out.println("Deckupdate");
			// combobox update
			if (!deckmanager.decksisEmpty()) {
				chooseDeckComboBox.removeAllItems();
				deckmanager.getDecks().forEach((key, value) -> {
					chooseDeckComboBox.addItem(deckmanager.getDeck(key));
				});
				// selektiert ein default Item in der Combobox
				chooseDeckComboBox.setSelectedIndex(0);

			} else {
				chooseDeckComboBox.removeAllItems();
			}
		}
	}

	/*
	 * Setzt den default für alle UI Fonts auf die übergebene Font
	 * 
	 * @param f die gewünschte FontUIResource
	 *
	 */
	private void setUIFont(javax.swing.plaf.FontUIResource f) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource)
				UIManager.put(key, f);
		}
	}

	// Getter + Setter

	public JComboBox<Deck> getJCombobox() {
		return chooseDeckComboBox;
	}

	public CardLayout getContentPanelCardLayout() {
		return contentPanelCardLayout;
	}

	public Container getContentPanel() {
		return contentPanel;
	}

	public JPanel getStartCard() {
		return startCard;
	}

	public JPanel getDecksCard() {
		return decksCard;
	}

	public JPanel getStatistikCard() {
		return statisticCard;
	}

	public JPanel getEinstellungenCard() {
		return settingsCard;
	}

	public String getFontStyle() {
		return fontStyle;
	}

	public DeckManager getDeckmanager() {
		return deckmanager;
	}

	public StartGui getStartGui() {
		return startGui;
	}
}
