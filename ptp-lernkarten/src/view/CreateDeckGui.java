package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.CreateDeckGuiListener;
import model.DeckManager;

/** PTP 22 
* Extra Fenster: Zuständig für die Erstellung von neuen Kartendecks
* @author Mark Sterkel & Julian Dillmann
* @version 
*/
public class CreateDeckGui {
	
	private DeckManager deckmanager;
	private String fontStyle;

	private JFrame createDeckFrame;
	private JPanel createDeckFramePanel;
	private JPanel setDecknameCard;
	private JPanel createFlashcardCard;

	private JTextField deckNameTextField;
	private JTextArea questionTextArea;
	private JScrollPane questionScrollPane;
	private JTextArea answerTextArea;
	private JScrollPane answerScrollPane;
	private JButton confirmDecknameButton;

	/**
	* Der CreateDeckGui-Konstruktor erstellt die CreateDeckGui mit ihren Komponenten 
	* und entsprechenden Listenern.
	*/
	public CreateDeckGui(DeckManager deckmanager, String fontstyle) {
		
		this.deckmanager = deckmanager;
		fontStyle = fontstyle;
		
		// JFrame erstllen
		createDeckFrame = new JFrame("Deck Erstellen");
		createDeckFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createDeckFrame.setBounds(480, 300, 500, 300);
		setUIFont(new javax.swing.plaf.FontUIResource(fontStyle, Font.PLAIN, 20));

		// FramePanel mit CardLayout erstellen
		createDeckFramePanel = new JPanel();
		createDeckFramePanel.setLayout(new CardLayout(0, 0));
		createDeckFrame.setContentPane(createDeckFramePanel);

		generateSetDecknameCard();
		generateCreateFlashcardCard();
		
		createDeckFrame.setVisible(true);
	}
	
	private void generateSetDecknameCard() {
		
		setDecknameCard = new JPanel();
		setDecknameCard.setLayout(new BorderLayout());
		createDeckFramePanel.add(setDecknameCard, "setDecknameCard");
		
		JLabel chooseNameLabel = new JLabel("Geben Sie den Name des Decks ein:");
		chooseNameLabel.setPreferredSize(new Dimension(300, 80));
		chooseNameLabel.setFont(new Font(fontStyle, Font.PLAIN, 25));
		deckNameTextField = new JTextField("");
		deckNameTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					confirmDecknameButton.doClick();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		deckNameTextField.setFocusable(true);
		
		//Buttonpanel erstellen 
		JPanel confirmNameButtonPanel = new JPanel(new FlowLayout());
		confirmNameButtonPanel.setBorder(new EmptyBorder(15, 5, 15, 5));
		
		//confirmDeckButton
		confirmDecknameButton = new JButton("Deckname Bestätigen");
		confirmDecknameButton.addActionListener(new CreateDeckGuiListener(this, deckmanager, "confirmDeckname"));
		confirmNameButtonPanel.add(confirmDecknameButton);
		//closeButton
		JButton closeButton = new JButton("Abbrechen");
		closeButton.addActionListener(new CreateDeckGuiListener(this, deckmanager, "close"));
		confirmNameButtonPanel.add(closeButton);


		setDecknameCard.add(chooseNameLabel, BorderLayout.NORTH);
		setDecknameCard.add(deckNameTextField, BorderLayout.CENTER);
		setDecknameCard.add(confirmNameButtonPanel, BorderLayout.PAGE_END);

		
	}
	
	private void generateCreateFlashcardCard() {
		
		createFlashcardCard = new JPanel();
		createFlashcardCard.setLayout(new BorderLayout());
		createDeckFramePanel.add(createFlashcardCard, "createFlashcardCard");

		
		String[] labels = { "Frage: ", "Antwort: " };
		int numPairs = labels.length;

		JPanel qaPanel = new JPanel(new SpringLayout());
		JLabel questionSpringLabel = new JLabel("Frage: ", JLabel.TRAILING);
		JLabel answerSpringLabel = new JLabel("Antwort: ", JLabel.TRAILING);
		
		
		questionTextArea = new JTextArea(2, 1);
		questionTextArea.setLineWrap(true);
		questionTextArea.setWrapStyleWord(true);
		questionScrollPane = new JScrollPane(questionTextArea);
		
		answerTextArea = new JTextArea(2, 1);
		answerTextArea.setLineWrap(true);
		answerTextArea.setWrapStyleWord(true);
		answerScrollPane = new JScrollPane(answerTextArea);

		qaPanel.add(questionSpringLabel);
		qaPanel.add(questionScrollPane);
		qaPanel.add(answerSpringLabel);
		qaPanel.add(answerScrollPane);
		
		SpringUtilities.makeCompactGrid(qaPanel, numPairs, 2, 
				6, 6, // initX, initY
				6, 6); // xPad, yPad
		
		createFlashcardCard.add(qaPanel, BorderLayout.CENTER);

		//ButtonPanel erstellen	
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new CreateDeckGuiListener(this, deckmanager, "ok"));
		answerTextArea.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ALT) {
					okButton.doClick();
					System.out.println("ok");
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		answerTextArea.setFocusable(true);
		JButton confirmButton = new JButton("Schließen");
		confirmButton.addActionListener(new CreateDeckGuiListener(this, deckmanager, "close"));

		buttonPanel.add(okButton);
		buttonPanel.add(confirmButton);
		
		createFlashcardCard.add(buttonPanel, BorderLayout.PAGE_END);	
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
	public JTextArea getQuestion() {
		return questionTextArea;
	}
	
	public JTextArea getAnswer() {
		return answerTextArea;
	}
	
	public JTextField getDeckName() {
		return deckNameTextField;
	}
	
	public JPanel getCreateDeckFramePanel() {
		return createDeckFramePanel;
	}
	
	public JFrame getCreateDeckFrame() {
		return createDeckFrame;
	}
}
