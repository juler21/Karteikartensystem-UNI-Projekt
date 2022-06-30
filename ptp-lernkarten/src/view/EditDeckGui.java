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

import controller.EditDeckGuiListener;
import model.DeckManager;
import model.Flashcard;

public class EditDeckGui extends JFrame implements Observer {

	// Model
	private DeckManager deckmanager;

	private StartGui startgui;

	private JFrame editDeckFrame;
	private JPanel editDeckPanel;
	private JPanel lowerButtonPanel;
	private JPanel listPanel;
	private JPanel qaPanel;
	private JComboBox<Flashcard> flashcardList;
	private JLabel questionLabel;
	private JTextArea questionText;
	private JLabel answerLabel;
	private JTextArea answerText;
	private JButton deleteFlashcardButton;
	private JButton saveFlashcardButton;

	public EditDeckGui(DeckManager deckmanager, StartGui startgui, String windowname) {

		this.deckmanager = deckmanager;
		this.startgui = startgui;

		System.out.println("Hier" + startgui.getSelectedDeck());

		// JFrame erstellen
		editDeckFrame = new JFrame(windowname);
		editDeckFrame.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		editDeckFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		editDeckFrame.setBounds(100, 100, 500, 300);

		//
		editDeckPanel = new JPanel();
		editDeckPanel.setBackground(new Color(244, 244, 244));
		editDeckPanel.setLayout(new BorderLayout(0, 0));
		editDeckFrame.setContentPane(editDeckPanel);

		Flashcard[] flashcardArray = new Flashcard[startgui.getSelectedDeck().getDeckFlashcardlist().size()];
		for (int i = 0; i < startgui.getSelectedDeck().getAmountOfFlashcards(); i++) {
			flashcardArray[i] = startgui.getSelectedDeck().getFlashcard(i);
		}
//		
		flashcardList = new JComboBox<Flashcard>(flashcardArray);
		editDeckPanel.add(flashcardList, BorderLayout.PAGE_START);
		flashcardList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Flashcard f = (Flashcard) (flashcardList.getSelectedItem());
				questionText.setText(f.getQuestion());
				answerText.setText(f.getAnswer());

			}
		});

		qaPanel = new JPanel();
		qaPanel.setLayout(new GridLayout(4, 1));
//		qaPanel.setAlignmentX(CENTER_ALIGNMENT);
//		qaPanel.setAlignmentY(CENTER_ALIGNMENT);
		System.out.println((Flashcard) (flashcardList.getSelectedItem()));
		Flashcard f = (Flashcard) (flashcardList.getSelectedItem());
		questionLabel = new JLabel("FRAGE");
//		questionLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		questionText = new JTextArea(3, 10);
		questionText.setText(f.getQuestion());
		answerLabel = new JLabel("ANTWORT");
//		answerLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		answerText = new JTextArea(3, 10);

		answerText.setText(f.getAnswer());

		qaPanel.add(questionLabel);
		qaPanel.add(questionText);
		qaPanel.add(answerLabel);
		qaPanel.add(answerText);

		editDeckPanel.add(qaPanel, BorderLayout.CENTER);

		lowerButtonPanel = new JPanel();
		deleteFlashcardButton = new JButton("LERNKARTE LÖSCHEN");
		deleteFlashcardButton
				.addActionListener(new EditDeckGuiListener(this, startgui, deckmanager, "deleteFlashcard"));
		saveFlashcardButton = new JButton("LERNKARTE SPEICHERN");
		saveFlashcardButton.addActionListener((new EditDeckGuiListener(this, startgui, deckmanager, "editFlashcard")));
		lowerButtonPanel.add(deleteFlashcardButton);
		lowerButtonPanel.add(saveFlashcardButton);
		editDeckPanel.add(lowerButtonPanel, BorderLayout.PAGE_END);

		startgui.getSelectedDeck().registerObserver(this);
		editDeckFrame.setVisible(true);

	}

	public JComboBox<Flashcard> getFlashcardList() {
		return flashcardList;
	}

	public Flashcard getSelectedFlashcard() {
		return (Flashcard) flashcardList.getSelectedItem();
	}

	@Override
	public void update() {
		System.out.println("update");
		flashcardList.removeAllItems();
		for (Flashcard f : startgui.getSelectedDeck().getDeckFlashcardlist()) {
			flashcardList.addItem(f);
		}
		flashcardList.setSelectedIndex(0);

	}

	public String getQuestionText() {
		return questionText.getText();
	}

	public void setQuestionText(String questionText) {
		this.questionText.setText(questionText);
		;
	}

	public String getAnswerText() {
		return answerText.getText();
	}

	public void setAnswerText(String answerText) {
		this.answerText.setText(answerText);
	}

}
