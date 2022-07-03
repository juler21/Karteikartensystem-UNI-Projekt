package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import controller.CreateDeckGuiListener;
import model.Deck;
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
	private JTextField question;
	private JTextField answer;
	private JButton confirmDeckname;

	public CreateDeckGui(DeckManager deckmanager, String windowname, Deck d) {
		this.deckmanager = deckmanager;

		// JFrame erstellen
		createDeckFrame = new JFrame(windowname);
		createDeckFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createDeckFrame.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		createDeckFrame.setBounds(100, 100, 500, 300);

		framePanel = new JPanel();
		framePanel.setLayout(new CardLayout(0, 0));
		createDeckFrame.setContentPane(framePanel);

		// Karten auf dem framePanel erstellen
		// setDeckname Karte
		setDecknameCard = new JPanel();
		setDecknameCard.setLayout(new BorderLayout());
		framePanel.add(setDecknameCard, "decknameCard");

		JLabel label1 = new JLabel("Geben Sie den Name des Decks ein:");
		deckName = new JTextField("");
		JPanel buttonPanel1 = new JPanel(new FlowLayout());

		confirmDeckname = new JButton("Deckname Bestätigen");
		confirmDeckname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) framePanel.getLayout()).show(framePanel, "deckKartenCard");
			}
		});
		confirmDeckname.addActionListener(new CreateDeckGuiListener(this, deckmanager, "Deckname"));
		JButton closeButton = new JButton("ABBRECHEN");
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
		question = new JTextField(10);
		answer = new JTextField(10);

		qaPanel.add(lQuestion);
		qaPanel.add(question);
		qaPanel.add(lAnswer);
		qaPanel.add(answer);
		createFlashcardsCard.add(qaPanel);

		// Lay out the panel.
		SpringUtilities.makeCompactGrid(qaPanel, numPairs, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new CreateDeckGuiListener(this, deckmanager, "ok", d));
		answer.addKeyListener(new KeyListener() {

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
		answer.setFocusable(true);

		JButton confirmButton = new JButton("SCHLIEßEN");
		confirmButton.addActionListener(new CreateDeckGuiListener(this, deckmanager, "close"));

		buttonPanel.add(okButton);
		buttonPanel.add(confirmButton);
		createFlashcardsCard.add(qaPanel, BorderLayout.CENTER);
		createFlashcardsCard.add(buttonPanel, BorderLayout.PAGE_END);

		// Start Karte des CardLayout setzten
		((CardLayout) framePanel.getLayout()).show(framePanel, "startCard");

		createDeckFrame.setVisible(true);

	}

	public JTextField getQuestion() {
		return question;
	}

	public JTextField getAnswer() {
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

}
