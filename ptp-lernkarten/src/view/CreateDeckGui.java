package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import controller.ButtonListener;
import util.Main;

public class CreateDeckGui extends JFrame {

	private Main app;
	private JFrame createDeckFrame;
	private JPanel framePanel;
	private JPanel setDecknameCard;
	private JPanel createFlashcardsCard;
	private JTextField deckName;
	private JTextField question;
	private JTextField answer;


	public CreateDeckGui(Main app) {
		
		this.app =app;
		
		// JFrame erstellen
		createDeckFrame = new JFrame();
		createDeckFrame.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		createDeckFrame.setTitle("Deck Erstellen");
		createDeckFrame.setBounds(100, 100, 500, 300);

		framePanel = new JPanel();
		framePanel.setLayout(new CardLayout(0, 0));
		createDeckFrame.setContentPane(framePanel);

		//Karten auf dem framePanel erstellen
		//setDeckname Karte
		setDecknameCard = new JPanel();
		setDecknameCard.setLayout(new BorderLayout());
		framePanel.add(setDecknameCard, "decknameCard");

		JLabel label1 = new JLabel("Geben Sie den Name des Decks ein:");
		deckName = new JTextField("");
		JButton confirm = new JButton("Deckname Bestätigen");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) framePanel.getLayout()).show(framePanel, "deckKartenCard");
				new ButtonListener(app, createDeckFrame, "Deckname Bestätigen");
			}
		});
		
		setDecknameCard.add(label1, BorderLayout.NORTH);
		setDecknameCard.add(deckName, BorderLayout.CENTER);
		setDecknameCard.add(confirm, BorderLayout.PAGE_END);
		
		
		//createFlashcard Karte
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
		okButton.addActionListener(new ButtonListener(app, this, "ok"));
		
		JButton confirmButton = new JButton("confirm");
		confirmButton.addActionListener(new ButtonListener(app, this, "confirm"));

		buttonPanel.add(okButton);
		buttonPanel.add(confirmButton);
		createFlashcardsCard.add(qaPanel, BorderLayout.CENTER);
		createFlashcardsCard.add(buttonPanel, BorderLayout.PAGE_END);

		//Start Karte des CardLayout setzten 
		((CardLayout) framePanel.getLayout()).show(framePanel, "startCard");
		pack();
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

}
