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

public class DeckErstellenGui extends JFrame {

	private Main app;
	private JFrame decksErstellenFrame;
	private JPanel framePane;
	private JPanel decknameCard;
	private JPanel deckKartenCard;
	private JTextField deckName;
	private JTextField question;
	private JTextField answer;

<<<<<<< HEAD
	public DeckErstellenGui(Main app) {
		
		this.app =app;
		
		//JFrame erstellen
=======
	public DeckErstellenGui() {

		// JFrame erstellen
>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git
		decksErstellenFrame = new JFrame();
		decksErstellenFrame.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		decksErstellenFrame.setTitle("Deck Erstellen");
		decksErstellenFrame.setBounds(100, 100, 500, 300);

		framePane = new JPanel();
		decksErstellenFrame.setContentPane(framePane);
		framePane.setLayout(new CardLayout(0, 0));

		decknameCard = new JPanel();
		framePane.add(decknameCard, "decknameCard");
		decknameCard.setLayout(new BorderLayout());

		deckKartenCard = new JPanel();
		framePane.add(deckKartenCard, "deckKartenCard");

		JLabel label1 = new JLabel("Geben Sie den Name des Decks ein:");
		deckName = new JTextField("");
		JButton confirm = new JButton("Deckname Bestätigen");
		confirm.addActionListener(new ActionListener() {
<<<<<<< HEAD
			
		    public void actionPerformed(ActionEvent e)
		    {
		    	((CardLayout) framePane.getLayout()).show(framePane,"deckKartenCard");
		    	new ButtonListener(app, decksErstellenFrame, "Deckname Bestätigen");
		    }}
		);
=======

			public void actionPerformed(ActionEvent e) {
				((CardLayout) framePane.getLayout()).show(framePane, "deckKartenCard");
				new ButtonListener(decksErstellenFrame, "Deckname Bestätigen");
			}
		});
>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git

		decknameCard.add(label1, BorderLayout.NORTH);
		decknameCard.add(deckName, BorderLayout.CENTER);
		decknameCard.add(confirm, BorderLayout.PAGE_END);

		// zweite Card erstellen (Fragen Antwort eingabe)

		String[] labels = { "Frage: ", "Antwort: " };
		int numPairs = labels.length;

		// Create and populate the panel.
		JPanel p = new JPanel(new SpringLayout());

		deckKartenCard.add(p);
		deckKartenCard.setLayout(new BorderLayout());
		JLabel lQuestion = new JLabel("Frage: ", JLabel.TRAILING);
		JLabel lAnswer = new JLabel("Antwort: ", JLabel.TRAILING);
		question = new JTextField(10);
		answer = new JTextField(10);

		p.add(lQuestion);
		p.add(question);
		p.add(lAnswer);
		p.add(answer);

		// Lay out the panel.
		SpringUtilities.makeCompactGrid(p, numPairs, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton okButton = new JButton("OK");
		JButton confirmButton = new JButton("confirm");
<<<<<<< HEAD
		okButton.addActionListener(new ButtonListener(app, this, "ok"));
		confirmButton.addActionListener(new ButtonListener(app, this, "confirm"));
		
=======
		okButton.addActionListener(new ButtonListener(this, "ok"));
		confirmButton.addActionListener(new ButtonListener(this, "confirm"));

>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git
		buttonPanel.add(okButton);
		buttonPanel.add(confirmButton);
		deckKartenCard.add(p, BorderLayout.CENTER);
		deckKartenCard.add(buttonPanel, BorderLayout.PAGE_END);

		((CardLayout) framePane.getLayout()).show(framePane, "startCard");
		pack();
		decksErstellenFrame.setVisible(true);

	}

	public JTextField getQuestion() {
		return question;
	}

	public JTextField getAnswer() {
		return answer;
	}
<<<<<<< HEAD
	
=======

>>>>>>> branch 'main' of https://git.informatik.uni-hamburg.de/0sterkel/ptp22-do08-lernkarten.git
	public JTextField getDeckName() {
		return deckName;
	}

}
