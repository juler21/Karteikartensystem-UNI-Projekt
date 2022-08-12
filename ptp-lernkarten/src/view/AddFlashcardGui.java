package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import controller.AddFlashcardGuiListener;
import model.DeckManager;

public class AddFlashcardGui {

	private DeckManager deckmanager;
	private JFrame addFlashcardFrame;
	private String fontStyle;
	private JPanel addFlashcardCard;
	private JTextArea questionTextArea;
	private JScrollPane questionScrollPane;
	private JTextArea answerTextArea;
	private JScrollPane answerScrollPane;
	private String selectedDeck;

	public AddFlashcardGui(DeckManager deckmanager, String windowname, String selectedDeckString) {

		this.deckmanager = deckmanager;
		this.selectedDeck = selectedDeckString;
		fontStyle = "Helvetica";
		// JFrame erstllen
		addFlashcardFrame = new JFrame(windowname);
		addFlashcardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addFlashcardFrame.setBounds(480, 300, 500, 300);
		setUIFont(new javax.swing.plaf.FontUIResource(fontStyle, Font.PLAIN, 20));

		generateAddFlashCardPanel();
		addFlashcardFrame.setContentPane(addFlashcardCard);
		addFlashcardFrame.setVisible(true);

	}

	private void generateAddFlashCardPanel() {

		addFlashcardCard = new JPanel();
		addFlashcardCard.setLayout(new BorderLayout());

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

		SpringUtilities.makeCompactGrid(qaPanel, numPairs, 2, 6, 6, // initX, initY
				6, 6); // xPad, yPad

		addFlashcardCard.add(qaPanel, BorderLayout.CENTER);
//		createFlashcardCard.add(qaPanel);

		// ButtonPanel erstellen
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new AddFlashcardGuiListener(this, deckmanager, "ok"));
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
		confirmButton.addActionListener(new AddFlashcardGuiListener(this, deckmanager, "close"));

		buttonPanel.add(okButton);
		buttonPanel.add(confirmButton);

		addFlashcardCard.add(buttonPanel, BorderLayout.PAGE_END);

	}

	public JTextArea getAnswerTextArea() {
		return answerTextArea;
	}

	public JTextArea getQuestionTextArea() {
		return questionTextArea;
	}

	public JFrame getAddFlashcardFrame() {
		return addFlashcardFrame;
	}

	public String getSelectedDeck() {
		return selectedDeck;
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