package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
<<<<<<< HEAD

=======
>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
<<<<<<< HEAD
=======

>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import model.Deck;
import model.DeckManager;
import util.NoDeckSelectedExeption;

public class MainGui extends JFrame implements Observer {

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
	private CardLayout contentPanelCardLayout;
	private JPanel startCard;
	private JPanel decksCard;
	private JPanel statistikCard;
	private JPanel einstellungenCard;
	private String fontStyle;

	private DeckManager deckmanager;
	private SettingsGui settingsGui;
	private StartGui startGui;
	private DecksGui decksGui;

	private JComboBox<Deck> chooseDeckComboBox;

	public MainGui(DeckManager manager, String name) {
		this.deckmanager = manager;
		// JFrame erstellen
		mainFrame = new JFrame(name);

		fontStyle = "Helvetica";
		setUIFont(new javax.swing.plaf.FontUIResource(fontStyle, Font.PLAIN, 20));
		mainFrame.setTitle("Lernkarten");
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

	public MainGui() {
		// TODO Auto-generated constructor stub
	}

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
		statistikButton = new JButton("STATISTIK");
		statistikButton.setPreferredSize(new Dimension(160, 40));
		einstellungenButton = new JButton("EINSTELLUNGEN");
		einstellungenButton.setPreferredSize(new Dimension(230, 40));

		topButtonPanel.add(startButton);
		topButtonPanel.add(decksButton);
		topButtonPanel.add(statistikButton);
		topButtonPanel.add(einstellungenButton);


		// ACTIONLISTENER
<<<<<<< HEAD
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPanel.getLayout()).show(contentPanel, "startCard");
				((CardLayout) startCard.getLayout()).show(startCard, "learnHomeCard");

				// Aktuelles Deck Anzeige
//				try {
//					getCurrentDeckLabel().setText(getSelectedDeck());
//				} catch (NoDeckSelectedExeption e1) {
//					getCurrentDeckLabel().setText("kein Deck gewählt");
//				}
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
=======
		startButton.addActionListener(new MainGuiListener(this, "startCard"));
		decksButton.addActionListener(new MainGuiListener(this, "decksCard"));
		statistikButton.addActionListener(new MainGuiListener(this, "statistikCard"));
		einstellungenButton.addActionListener(new MainGuiListener(this, "einstellungenCard"));
>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git

	}

	private void generateContentPanel() {

		// ContentPanel erstellen
		contentPanel = new JPanel();
		contentPanelCardLayout = new CardLayout(0, 0);
		contentPanel.setLayout(contentPanelCardLayout);
		framePanel.add(contentPanel, BorderLayout.CENTER);

		// Start-Karte
		startCard = new JPanel();
		startCard.setLayout(new CardLayout(0, 0));
		startGui = new StartGui(startCard, deckmanager, this);
		contentPanel.add(startCard, "startCard");

		// Decks-Karte
		decksCard = new JPanel();
		decksCard.setLayout(new GridLayout(4, 1, 10, 5));
//		deckListeErstellen();
		decksGui = new DecksGui(decksCard, deckmanager, this);
		contentPanel.add(decksCard, "decksCard");

		// Statistik-Karte
		statistikCard = new JPanel();
		contentPanel.add(statistikCard, "statistikCard");

		// Einstellungen-Karte
		einstellungenCard = new JPanel();
		settingsGui = new SettingsGui(einstellungenCard);
		contentPanel.add(einstellungenCard, "einstellungenCard");

		// Anfangsbildschirm setzten auf "Start"
		((CardLayout) contentPanel.getLayout()).show(contentPanel, "startCard");

	}

	private void deckListeErstellen() {

		chooseDeckComboBox = new JComboBox<Deck>();
		chooseDeckComboBox.setSize(getPreferredSize());

		deckmanager.getData(new File(DeckManager.getPathtoString()));
		update("deckChange");
	}

	public String getSelectedDeck() throws NoDeckSelectedExeption {
		if (chooseDeckComboBox.getSelectedItem() != null) {
			return chooseDeckComboBox.getSelectedItem().toString();
		} else {
			throw new NoDeckSelectedExeption();
		}
	}

	public JComboBox<Deck> getJCombobox() {
		return chooseDeckComboBox;
	}

	@Override
	public void update(String changeType) {

		System.out.println("update");

		if (changeType.equals("deckChange")) {
			// combobox update
			if (!deckmanager.decksisEmpty()) {
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

	public void setDashboardScreen(String screenCard) {

		((CardLayout) contentPanel.getLayout()).show(contentPanel, screenCard);

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
		return statistikCard;
	}

	public JPanel getEinstellungenCard() {
		return einstellungenCard;
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