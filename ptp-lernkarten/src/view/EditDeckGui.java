package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.EditDeckGuiListener;
import model.Deck;
import model.DeckManager;
import model.Flashcard;
import util.NoDeckSelectedExeption;
import util.NoFlashcardSelectedExeption;

public class EditDeckGui extends JFrame implements Observer {

	// Model
	private DeckManager deckmanager;

	private StartGui startgui;
	private Deck selectedDeck;

	private JFrame editDeckFrame;
	private JPanel editDeckPanel;
	private JPanel lowerButtonPanel;
	private JPanel listPanel;
	private JPanel qaPanel;
	private JComboBox<Flashcard> flashcardComboBox;
	private JLabel questionLabel;
	private JTextArea questionText;
	private JLabel answerLabel;
	private JTextArea answerText;
	private JButton deleteFlashcardButton;
	private JButton saveFlashcardButton;
	private JButton quitButton;
	private JButton newFlashcardButton;

	private String fontStyle;

	public EditDeckGui(DeckManager deckmanager, StartGui startgui, Deck selectedDeck, String windowname) {

		this.deckmanager = deckmanager;
		this.startgui = startgui;
		this.selectedDeck = selectedDeck;

		// JFrame erstellen
		editDeckFrame = new JFrame(windowname);
		editDeckFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		editDeckFrame.setBounds(550, 300, 600, 400);
		fontStyle = "Helvetica";
		setUIFont(new javax.swing.plaf.FontUIResource(fontStyle, Font.PLAIN, 20));

		//
		editDeckPanel = new JPanel();
		editDeckPanel.setBackground(new Color(244, 244, 244));
		editDeckPanel.setLayout(new BorderLayout(0, 0));
		editDeckFrame.setContentPane(editDeckPanel);

		Flashcard[] flashcardArray = new Flashcard[0];

		flashcardArray = new Flashcard[selectedDeck.getDeckFlashcardlist().size()];
		for (int i = 0; i < selectedDeck.getAmountOfFlashcards(); i++) {
			flashcardArray[i] = selectedDeck.getFlashcard(i);
		}


		flashcardComboBox = new JComboBox<Flashcard>(flashcardArray);
//		flashcardComboBox.setBorder(new EmptyBorder(15,5,15,5));
		editDeckPanel.add(flashcardComboBox, BorderLayout.PAGE_START);
		flashcardComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (flashcardComboBox.getSelectedItem() != null) {
					Flashcard f = (Flashcard) (flashcardComboBox.getSelectedItem());
					questionText.setText(f.getQuestion());
					answerText.setText(f.getAnswer());
				}

			}
		});

		qaPanel = new JPanel();
		qaPanel.setLayout(new GridLayout(4, 1));
		System.out.println((Flashcard) (flashcardComboBox.getSelectedItem()));
		Flashcard f = (Flashcard) (flashcardComboBox.getSelectedItem());
		questionLabel = new JLabel("Frage");
		questionLabel.setFont(new Font(fontStyle, Font.PLAIN, 25));
		questionText = new JTextArea(3, 10);
		
		answerLabel = new JLabel("Antwort");
		answerLabel.setFont(new Font(fontStyle, Font.PLAIN, 25));
		answerText = new JTextArea(3, 10);

		if (!selectedDeck.getDeckFlashcardlist().isEmpty()) {
			questionText.setText(f.getQuestion());
			answerText.setText(f.getAnswer());
		}

		qaPanel.add(questionLabel);
		qaPanel.add(questionText);
		qaPanel.add(answerLabel);
		qaPanel.add(answerText);

		editDeckPanel.add(qaPanel, BorderLayout.CENTER);

		lowerButtonPanel = new JPanel();
		deleteFlashcardButton = new JButton("Löschen");
		deleteFlashcardButton
				.addActionListener(new EditDeckGuiListener(this, selectedDeck, deckmanager, "deleteFlashcard"));
		saveFlashcardButton = new JButton("Speichern");
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

		try {
			startgui.getSelectedDeck().registerObserver(this);
		} catch (NoDeckSelectedExeption e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		editDeckFrame.setVisible(true);

	}

	public JComboBox<Flashcard> getFlashcardList() {
		return flashcardComboBox;
	}

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
			System.out.println("update");
			flashcardComboBox.removeAllItems();
			for (Flashcard f : selectedDeck.getDeckFlashcardlist()) {
				flashcardComboBox.addItem(f);
			}
			// setzt flashcard combobox auf erstes objekt
//			flashcardList.setSelectedIndex(0);
		}
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
}
