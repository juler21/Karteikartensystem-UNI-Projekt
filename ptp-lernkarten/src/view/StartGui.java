package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.StartGuiListener;
import model.DeckManager;
import util.NoDeckSelectedExeption;


public class StartGui {
	
	//Panel auf dem alles liegt
	private JPanel startCard;
	//Font
	private String fontStyle;
	
	private DeckManager deckmanager;
	
	//4 verscheidenen Karten die angezeigt werden können 
	private JPanel learnHomeCard;
	private JPanel learnQuestionCard;
	private JPanel learnAnswerCard;
	private JPanel learnEndCard;
	
	
	private JLabel currentDeckLabel;
	private JTextArea onlyQuestionTextArea;
	private JTextArea questionTextArea;
	private JScrollPane questionScrollPane;
	private JScrollPane answerScrollPane;
	private JTextArea answerTextArea;
	private MainGui mainGui;

	
	public StartGui(JPanel startCard, DeckManager deckmanager, MainGui maingui) {
		
		this.startCard = startCard;
		this.deckmanager = deckmanager;
		this.mainGui =maingui;
		fontStyle = "Helvetica";
		
		createLearnHomeCard();
		
		createLearnQuestionCard();
		
		createlearnAnswerCard();
		
		createlearnEndCard();
		
		startCard.add(learnQuestionCard, "learnQuestionCard");
		startCard.add(learnAnswerCard, "learnAnswerCard");
		startCard.add(learnHomeCard, "learnHomeCard");
		startCard.add(learnEndCard, "learnEndCard");
		
		//Anfangscreen auf Home setzten	
		setLearnScreen("learnHomeCard");
		
	}

	private void createLearnHomeCard() {
		
		learnHomeCard = new JPanel();
		learnHomeCard.setLayout(null);


		JLabel startLearningLabel = new JLabel();
		startLearningLabel.setText("<html><body>Karteikarten einfach<br>lernen!</body></html>");
		startLearningLabel.setFont(new Font(fontStyle, Font.PLAIN, 60));
		startLearningLabel.setBounds(50, -70, 700, 300);

		JButton lernenBeginnenButton = new JButton("Lernen Beginnen");
		lernenBeginnenButton.setFont(new Font(fontStyle, Font.PLAIN, 27));
		lernenBeginnenButton.setBounds(50, 165, 300, 60);

		JLabel currentDeckInfoLabel = new JLabel("<html><body>Aktuelles Deck:</body></html>");
		currentDeckInfoLabel.setFont(new Font(fontStyle, Font.PLAIN, 27));
		currentDeckInfoLabel.setBounds(50, 235, 300, 60);
		currentDeckLabel = new JLabel("noch kein Deck gewählt");
		currentDeckLabel.setFont(new Font(fontStyle, Font.PLAIN, 27));
		currentDeckLabel.setBounds(250, 235, 300, 60);

		learnHomeCard.add(startLearningLabel);
		learnHomeCard.add(lernenBeginnenButton);
		learnHomeCard.add(currentDeckInfoLabel);
		learnHomeCard.add(currentDeckLabel);

		lernenBeginnenButton.addActionListener(new StartGuiListener(this, deckmanager, "lernenBeginnenButton"));

	}

	private void createLearnQuestionCard() {
			
		// Question-Karte
		learnQuestionCard = new JPanel();
		learnQuestionCard.setLayout(new GridLayout(2, 1, 10, 5));
		onlyQuestionTextArea = new JTextArea(3,1);
		onlyQuestionTextArea.setFont(new Font(fontStyle, Font.PLAIN, 27));
		onlyQuestionTextArea.setLineWrap(true);
		onlyQuestionTextArea.setWrapStyleWord(true);
		onlyQuestionTextArea.setEditable(false);
		

		JButton showAnswerButton = new JButton("Antwort zeigen");
		showAnswerButton.setFont(new Font(fontStyle, Font.PLAIN, 27));
		showAnswerButton.addActionListener(new StartGuiListener(this, deckmanager, "showAnswerButton"));
		
		learnQuestionCard.add(onlyQuestionTextArea);
		learnQuestionCard.add(showAnswerButton);

		
	}
	
	private void createlearnAnswerCard() {
		// Answer-Karte
		learnAnswerCard = new JPanel();
		learnAnswerCard.setLayout(new GridLayout(5, 1, 10, 5));

		JLabel questionLabel = new JLabel("Frage:");
		questionLabel.setFont(new Font(fontStyle, Font.BOLD, 27));
		questionTextArea = new JTextArea(2,1);
		questionTextArea.setFont(new Font(fontStyle, Font.PLAIN, 27));
		questionTextArea.setLineWrap(true);
		questionTextArea.setWrapStyleWord(true);
		questionTextArea.setEditable(false);
		questionScrollPane = new JScrollPane(questionTextArea);

		
		JLabel answerLabel = new JLabel("Antwort:");
		answerLabel.setFont(new Font(fontStyle, Font.BOLD, 27));

		answerTextArea = new JTextArea(2,1);
		answerTextArea.setFont(new Font(fontStyle, Font.PLAIN, 27));
		answerTextArea.setLineWrap(true);
		answerTextArea.setWrapStyleWord(true);
		answerTextArea.setEditable(false);
		answerScrollPane = new JScrollPane(answerTextArea);
//						answerScrollPane.setBounds(3,3,400,400);

		JButton nextQuestionButton = new JButton("Nächste Frage");
		nextQuestionButton.setFont(new Font(fontStyle, Font.PLAIN, 27));
		nextQuestionButton.addActionListener(new StartGuiListener(this, deckmanager, "nextQuestionButton"));

		learnAnswerCard.add(questionLabel);
		learnAnswerCard.add(questionScrollPane);
		learnAnswerCard.add(answerLabel);
		learnAnswerCard.add(answerScrollPane);
		learnAnswerCard.add(nextQuestionButton);
		
	}
	

	private void createlearnEndCard() {
		
		// learnEnd-Karte
		learnEndCard = new JPanel();
		learnEndCard.setLayout(new GridLayout(3, 1, 10, 5));
		JLabel deckEndLabel = new JLabel("Deck-Ende erreicht");
		deckEndLabel.setFont(new Font(fontStyle, Font.PLAIN, 27));
		JButton restartDeckButton = new JButton("Erneut starten");
		restartDeckButton.setFont(new Font(fontStyle, Font.PLAIN, 27));
		JButton switchDeckButton = new JButton("neues Deck wählen");
		switchDeckButton.setFont(new Font(fontStyle, Font.PLAIN, 27));

		learnEndCard.add(deckEndLabel);
		learnEndCard.add(restartDeckButton);
		learnEndCard.add(switchDeckButton);

		restartDeckButton.addActionListener(new StartGuiListener(this, deckmanager, "restartDeckButton"));
		switchDeckButton.addActionListener(new StartGuiListener(this, deckmanager, "switchDeckButton"));

		
	}

	public String getSelectedDeck() throws NoDeckSelectedExeption {
		return mainGui.getSelectedDeck();
	}
	

	public void setOnlyQuestionTextArea(String text) {
		this.onlyQuestionTextArea.setText(text);
	}

	public void setQuestionTextArea(String text) {
		this.questionTextArea.setText(text);
	}

	public void setAnswerTextArea(String text) {
		this.answerTextArea.setText(text);
	}
	
	public JTextArea getAnswerTextArea() {
		return answerTextArea;
	
	}
	
	public void setLearnScreen(String screenCard) {

		System.out.println("methode wird aufgerufen");

		((CardLayout) startCard.getLayout()).show(startCard, screenCard);

	}
	
}


