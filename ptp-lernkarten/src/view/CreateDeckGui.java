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

public class CreateDeckGui {
	// Model
	private DeckManager deckmanager;
//	private CreateDeckGuiListener controller; // controller

	private JFrame createDeckFrame;

	public JFrame getCreateDeckFrame() {
		return createDeckFrame;
	}

	private JPanel framePanel;
	private JPanel setDecknameCard;
	private JPanel createFlashcardsCard;
	private JTextField deckName;
	private JTextArea question;
	private JTextArea answer;
	private JButton confirmDeckname;

	private String fontStyle;
	private JScrollPane questionScrollPane;
	private JScrollPane answerScrollPane;

	public CreateDeckGui(DeckManager deckmanager, String windowname, String d) {
		this.deckmanager = deckmanager;

		// JFrame erstellen
		createDeckFrame = new JFrame(windowname);
		createDeckFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createDeckFrame.setBounds(480, 300, 500, 300);
		fontStyle = "Helvetica";
		setUIFont(new javax.swing.plaf.FontUIResource(fontStyle, Font.PLAIN, 20));
//		mainFrame.setMinimumSize(new Dimension(680,400));

		framePanel = new JPanel();
		framePanel.setLayout(new CardLayout(0, 0));
		createDeckFrame.setContentPane(framePanel);

		// Karten auf dem framePanel erstellen
		// setDeckname Karte
		setDecknameCard = new JPanel();
		setDecknameCard.setLayout(new BorderLayout());
		framePanel.add(setDecknameCard, "decknameCard");

		JLabel label1 = new JLabel("Geben Sie den Name des Decks ein:");
		label1.setPreferredSize(new Dimension(300, 80));
		label1.setFont(new Font(fontStyle, Font.PLAIN, 25));
		deckName = new JTextField("");
		JPanel buttonPanel1 = new JPanel(new FlowLayout());
		buttonPanel1.setBorder(new EmptyBorder(15, 5, 15, 5));

		confirmDeckname = new JButton("Deckname Bestätigen");
		confirmDeckname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) framePanel.getLayout()).show(framePanel, "deckKartenCard");
			}
		});
		confirmDeckname.addActionListener(new CreateDeckGuiListener(this, deckmanager, "Deckname"));
		JButton closeButton = new JButton("Abbrechen");
		closeButton.addActionListener(new CreateDeckGuiListener(this, deckmanager, "close"));

		buttonPanel1.add(confirmDeckname);
		buttonPanel1.add(closeButton);

		deckName.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					confirmDeckname.doClick();
					System.out.println("confrimDeckname");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		deckName.setFocusable(true);
		setDecknameCard.add(label1, BorderLayout.NORTH);
		setDecknameCard.add(deckName, BorderLayout.CENTER);
		setDecknameCard.add(buttonPanel1, BorderLayout.PAGE_END);

		// createFlashcard Karte
		createFlashcardsCard = new JPanel();
		createFlashcardsCard.setLayout(new BorderLayout());
		framePanel.add(createFlashcardsCard, "deckKartenCard");

		String[] labels = { "Frage: ", "Antwort: " };
		int numPairs = labels.length;

		JPanel qaPanel = new JPanel(new SpringLayout());
		JLabel lQuestion = new JLabel("Frage: ", JLabel.TRAILING);
		JLabel lAnswer = new JLabel("Antwort: ", JLabel.TRAILING);
		
		question = new JTextArea(2, 1);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		questionScrollPane = new JScrollPane(question);
		
		answer = new JTextArea(2, 1);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answerScrollPane = new JScrollPane(answer);

		qaPanel.add(lQuestion);
		qaPanel.add(questionScrollPane);
		qaPanel.add(lAnswer);
		qaPanel.add(answerScrollPane);
		createFlashcardsCard.add(qaPanel);

		// Lay out the panel.
		SpringUtilities.makeCompactGrid(qaPanel, numPairs, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new CreateDeckGuiListener(this, deckmanager, "ok", d));
		okButton.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					okButton.doClick();
					System.out.println("ok");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		okButton.setFocusable(true);

		JButton confirmButton = new JButton("Schließen");
		confirmButton.addActionListener(new CreateDeckGuiListener(this, deckmanager, "close"));

		buttonPanel.add(okButton);
		buttonPanel.add(confirmButton);
		createFlashcardsCard.add(qaPanel, BorderLayout.CENTER);
		createFlashcardsCard.add(buttonPanel, BorderLayout.PAGE_END);

		// Start Karte des CardLayout setzten
		((CardLayout) framePanel.getLayout()).show(framePanel, "startCard");

		createDeckFrame.setVisible(true);

	}

	public JTextArea getQuestion() {
		return question;
	}

	public JTextArea getAnswer() {
		return answer;
	}

	public JTextField getDeckName() {
		return deckName;
	}

	public JPanel getFramePanel() {
		return framePanel;
	}

	public void setFramePanel(JPanel framePanel) {
		this.framePanel = framePanel;
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
