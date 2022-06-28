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
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import controller.StartGuiListener;
import model.Deck;
import model.DeckManager;

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
	
	private JComboBox<Deck> chooseDeckList;

	private DeckManager deckmanager;

	public StartGui(DeckManager manager, String name) {
		this.deckmanager = manager;

		// JFrame erstellen
		mainFrame = new JFrame(name);
		setUIFont(new javax.swing.plaf.FontUIResource("Ubuntu",Font.PLAIN,13));
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
		
		// LearnPanel erstellen, dass auf der StartCard liegt und die Kartenlern "frames" anzeigt
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
			decksCard.add(chooseDeckList);
	
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
		
		//Karten die auf der StartKarte liegen 
		
		//homeKarte
		JPanel learnHomeCard = new JPanel();
		learnHomeCard.setLayout(new BorderLayout());
		
		JButton lernenBeginnenButton = new JButton("Lernen Beginnen");
		learnHomeCard.add(lernenBeginnenButton,BorderLayout.CENTER);
		lernenBeginnenButton.setFont(new Font("Ubuntu", Font.PLAIN, 30));
		lernenBeginnenButton.setBackground(Color.GREEN);
		lernenBeginnenButton.setForeground(new Color(2, 48, 89));
		startCard.add(learnHomeCard, "learnHomeCard");
		
		
		lernenBeginnenButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) startCard.getLayout()).show(startCard, "learnQuestionCard");
			}
		});
		
		//questionKarte
		JPanel learnQuestionCard = new JPanel();
		learnQuestionCard.setLayout(new GridLayout(4, 1));
		JLabel onlyQuestionLabel = new JLabel("FRAGE:");
		JLabel onlyQuestionTextLabel = new JLabel("Hier steht die Frage...");
		JButton showAnswerButton = new JButton("Antwort zeigen");
		
		showAnswerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) startCard.getLayout()).show(startCard, "learnAnswerCard");
			}
		});
		
		
		learnQuestionCard.add(onlyQuestionLabel);
		learnQuestionCard.add(onlyQuestionTextLabel);
		learnQuestionCard.add(showAnswerButton);
		startCard.add(learnQuestionCard, "learnQuestionCard");
		//answerKarte
		JPanel learnAnswerCard = new JPanel();
		learnAnswerCard.setLayout(new GridLayout(5, 1));
		JLabel questionLabel = new JLabel("FRAGE:");
		JLabel questionTextLabel = new JLabel("Hier steht die Frage...");
		JLabel answerLabel = new JLabel("ANTWORT:");
		JLabel answerTextLabel = new JLabel("Hier steht die Antwort...");
		JButton nextQuestionButton = new JButton("Nächste Frage");
		
		learnAnswerCard.add(questionLabel);
		learnAnswerCard.add(questionTextLabel);
		learnAnswerCard.add(answerLabel);
		learnAnswerCard.add(answerTextLabel);
		learnAnswerCard.add(nextQuestionButton);
		
		startCard.add(learnAnswerCard, "learnAnswerCard");

		
	}
	private void deckListeErstellen() {

		chooseDeckList = new JComboBox<Deck>();

		deckmanager.getData(new File(DeckManager.getPathtoString()));
		update();
	}

	public JComboBox<Deck> getJCombobox() {
		return chooseDeckList;
	}

	public Deck getSelectedDeck() {
		return (Deck) (chooseDeckList.getSelectedItem());
	}
	
	private void setUIFont (javax.swing.plaf.FontUIResource f){
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	      }
	    } 

	@Override
	public void update() {

		System.out.println("update");
		// combobox update
		chooseDeckList.removeAllItems();
		deckmanager.getDecks().forEach((key, value) -> {
			chooseDeckList.addItem(deckmanager.getDeck(key));
		});

	}
}
