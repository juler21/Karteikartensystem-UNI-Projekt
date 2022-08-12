package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import controller.EditDeckGuiListener;
import model.DeckManager;
import model.Flashcard;
import util.NoDeckSelectedExeption;
import util.NoFlashcardSelectedExeption;

/** PTP 22 
* Extra Fenster: Zuständig für das Bearbeiten,Erstellen,Löschen und Hinzufügen von Karteikarten
* @author Mark Sterkel & Julian Dillmann
* @version 
*/

public class EditDeckGui extends JFrame implements Observer {

	private DeckManager deckmanager;
	private DecksGui decksgui;
	private String selectedDeck;
	private String fontStyle;

	private JFrame editDeckFrame;
	private JPanel editDeckPanel;
	private JComboBox<Flashcard> flashcardComboBox;
	private JPanel lowerButtonPanel;
	private JPanel listPanel;
	private JPanel qaPanel;
	private JLabel questionLabel;
	private JTextArea questionText;
	private JLabel answerLabel;
	private JTextArea answerText;
	private JButton deleteFlashcardButton;
	private JButton saveFlashcardButton;
	private JButton quitButton;
	private JButton newFlashcardButton;
	private JScrollPane questionScrollPane;
	private JScrollPane answerScrollPane;

	/**
	* Der EditDeckGui-Konstruktor erstellt die EditDeckGui mit ihren Komponenten 
	* und entsprechenden Listenern.
	*/
	public EditDeckGui(DeckManager deckmanager, DecksGui decksgui, String selectedDeck, String windowname,
			String fontstyle) {

		this.deckmanager = deckmanager;
		this.decksgui = decksgui;
		this.selectedDeck = selectedDeck;
		this.fontStyle = fontstyle;

		// JFrame erstellen
		editDeckFrame = new JFrame(windowname);
		editDeckFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		editDeckFrame.setBounds(420, 270, 650, 400);
		setUIFont(new javax.swing.plaf.FontUIResource(fontStyle, Font.PLAIN, 20));

		// Panel erstellen
		editDeckPanel = new JPanel();
		editDeckPanel.setBackground(new Color(244, 244, 244));
		editDeckPanel.setLayout(new BorderLayout(0, 0));
		editDeckFrame.setContentPane(editDeckPanel);

		generateFlashcardComboBox();
		generateQAPanel();
		generateButtonPanel();
	
		//EditDeckGui beobachtet den Deckmanager um Änderungen innerhalb der Decks zu registrieren
		deckmanager.registerObserver(this);
		editDeckFrame.setVisible(true);
	}

	private void generateQAPanel() {
		
		qaPanel = new JPanel();
		qaPanel.setLayout(new GridLayout(4, 1));
		System.out.println((Flashcard) (flashcardComboBox.getSelectedItem()));
		Flashcard f = (Flashcard) (flashcardComboBox.getSelectedItem());
		questionLabel = new JLabel("Frage");
		questionLabel.setFont(new Font(fontStyle, Font.PLAIN, 25));
		questionText = new JTextArea(2, 1);
		questionText.setLineWrap(true);
		questionText.setWrapStyleWord(true);
		questionScrollPane = new JScrollPane(questionText);

		answerLabel = new JLabel("Antwort");
		answerLabel.setFont(new Font(fontStyle, Font.PLAIN, 25));
		answerText = new JTextArea(2, 1);
		answerText.setLineWrap(true);
		answerText.setWrapStyleWord(true);
		answerScrollPane = new JScrollPane(answerText);

		//initiales füllen 
		if (!deckmanager.getFlashcardList(selectedDeck).isEmpty()) {
			questionText.setText(deckmanager.getQuestion(f));
			answerText.setText(deckmanager.getAnswer(f));
		}

		qaPanel.add(questionLabel);
		qaPanel.add(questionScrollPane);
		qaPanel.add(answerLabel);
		qaPanel.add(answerScrollPane);

		editDeckPanel.add(qaPanel, BorderLayout.CENTER);
	}

	
	private void generateButtonPanel() {
		
		lowerButtonPanel = new JPanel();
		deleteFlashcardButton = new JButton("Löschen");
		deleteFlashcardButton
		.addActionListener(new EditDeckGuiListener(this, selectedDeck, deckmanager, "deleteFlashcard"));
		saveFlashcardButton = new JButton("Ändern");
		saveFlashcardButton
		.addActionListener((new EditDeckGuiListener(this, selectedDeck, deckmanager, "editFlashcard")));
		quitButton = new JButton("Schließen");
		quitButton.addActionListener((new EditDeckGuiListener(this, selectedDeck, deckmanager, "close")));
		newFlashcardButton = new JButton("Hinzufügen");
		newFlashcardButton.addActionListener(new EditDeckGuiListener(this, selectedDeck, deckmanager, "newFlashcard"));
		lowerButtonPanel.add(deleteFlashcardButton);
		lowerButtonPanel.add(saveFlashcardButton);
		lowerButtonPanel.add(newFlashcardButton);
		lowerButtonPanel.add(quitButton);
		editDeckPanel.add(lowerButtonPanel, BorderLayout.PAGE_END);
		
	}


	/**
	* erstellt die FlashcardComboBox
	* 
	*/
	private void generateFlashcardComboBox() {

		Flashcard[] flashcardArray = new Flashcard[0];
		flashcardArray = new Flashcard[deckmanager.getFlashcardList(selectedDeck).size()];
		for (int i = 0; i < deckmanager.getAmountOfFlashcards(selectedDeck); i++) {
			flashcardArray[i] = deckmanager.getFlashcard(selectedDeck, i);
		}

		flashcardComboBox = new JComboBox<Flashcard>(flashcardArray);
		flashcardComboBox.addActionListener(new EditDeckGuiListener(this, selectedDeck, deckmanager, "comboBoxChange"));
		editDeckPanel.add(flashcardComboBox, BorderLayout.PAGE_START);
	}
	
	/**
	* gibt das aktuell in der Combobox gewählte Deck zurück 
	* 
	* @return das aktuell gewählte Deck als String 
	* @exception NoDeckSelectedExeption wenn kein Deck gewählt ist 
	* 
	*/
	public Flashcard getSelectedFlashcard() throws NoFlashcardSelectedExeption {
		if (flashcardComboBox.getSelectedItem() != null) {
			return (Flashcard) flashcardComboBox.getSelectedItem();
		} else {
			throw new NoFlashcardSelectedExeption();
		}

	}

	@Override
	public void update(String changeType) {
		if (changeType.equals("flashcardChange")) {
			System.out.println("flashcardUpdate");
			flashcardComboBox.removeAllItems();
			for (Flashcard f : deckmanager.getFlashcardList(selectedDeck)) {
				flashcardComboBox.addItem(f);
			}
		}
	}

	/*
	 * Setzt den default für alle UI Fonts auf die übergebene Font
	 * @param f die gewünschte FontUIResource 
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
	
	// Getter + Setter 
	public JComboBox<Flashcard> getFlashcardComboBox() {
		return flashcardComboBox;
	}
	
	public JFrame getEditDeckFrame() {
		return editDeckFrame;
	}

	public String getQuestionText() {
		return questionText.getText();
	}

	public void setQuestionText(String questionText) {
		this.questionText.setText(questionText);
	}

	public String getAnswerText() {
		return answerText.getText();
	}

	public void setAnswerText(String answerText) {
		this.answerText.setText(answerText);
	}

}
