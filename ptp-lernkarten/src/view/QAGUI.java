package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import controller.ButtonListener;

public class QAGUI extends JFrame{
	private JTextField question;
	private JTextField answer;
	
	public QAGUI() {
		super("Flashcards erstellen");
	
		String[] labels = {"Frage: ", "Antwort: "};
		int numPairs = labels.length;

		//Create and populate the panel.
		JPanel p = new JPanel(new SpringLayout());
		JLabel lQuestion = new JLabel("Frage: ", JLabel.TRAILING);
		JLabel lAnswer = new JLabel("Antwort: ", JLabel.TRAILING);
		question = new JTextField(10);
		answer = new JTextField(10);
		
		p.add(lQuestion);
		p.add(question);
		p.add(lAnswer);
		p.add(answer);

		//Lay out the panel.
		SpringUtilities.makeCompactGrid(p,
		                                numPairs, 2, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad
		
		this.setSize(500, 150);
		this.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton okButton = new JButton("OK");
		JButton confirmButton = new JButton("confirm");
		okButton.addActionListener(new ButtonListener(this, "ok"));
		confirmButton.addActionListener(new ButtonListener(this, "confirm"));
		
		buttonPanel.add(okButton);
		buttonPanel.add(confirmButton);
		this.add(p, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.PAGE_END);
		setVisible(true);
		
	}
	public JTextField getQuestion() {
		return question;
	}
	public JTextField getAnswer() {
		return answer;
	}

}
