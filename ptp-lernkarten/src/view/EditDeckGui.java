package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controller.ButtonListener;
import model.DeckManager;
import util.Main;

public class EditDeckGui extends JFrame {

	private Main app;
	private JFrame editDeckFrame;
	private JPanel editDeckPanel;
	private JPanel lowerButtonPanel;
	private JPanel listPanel;
	private JPanel qaPanel;
	private JComboBox<String> flashcardList;
	private JLabel questionLabel;
	private JTextArea questionText;
	private JLabel answerLabel;
	private JTextArea answerText;
	private JButton deleteFlashcardButton;
	private JButton saveFlashcardButton;

	public EditDeckGui(Main app) {
		
		this.app = app;
		
		
		// JFrame erstellen
		editDeckFrame = new JFrame();
		editDeckFrame.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		editDeckFrame.setTitle("Lernkarten");
		editDeckFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		editDeckFrame.setBounds(100, 100, 500, 300);

		//
		editDeckPanel = new JPanel();
		editDeckPanel.setBackground(new Color(244, 244, 244));
		editDeckPanel.setLayout(new BorderLayout(0, 0));
		editDeckFrame.setContentPane(editDeckPanel);
		
		String[] flashcardArray = new String[10];
		flashcardArray[1]= "Karte 1";
		flashcardArray[2]= "Karte 2";
		flashcardArray[3]= "Karte 3";
		flashcardList = new  JComboBox<String>(flashcardArray);
		editDeckPanel.add(flashcardList,BorderLayout.PAGE_START);
		
		qaPanel = new JPanel();
		qaPanel.setLayout(new GridLayout(4,1));
//		qaPanel.setAlignmentX(CENTER_ALIGNMENT);
//		qaPanel.setAlignmentY(CENTER_ALIGNMENT);
		questionLabel = new JLabel("FRAGE");
//		questionLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		questionText = new JTextArea(3,10);
		answerLabel = new JLabel("ANTWORT");
//		answerLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		answerText = new JTextArea(3,10);
		
		qaPanel.add(questionLabel);
		qaPanel.add(questionText);
		qaPanel.add(answerLabel);
		qaPanel.add(answerText);
		
		editDeckPanel.add(qaPanel,BorderLayout.CENTER);
		
		lowerButtonPanel = new JPanel();
		deleteFlashcardButton = new JButton("LERNKARTE LÖSCHEN");
		saveFlashcardButton = new JButton("LERNKARTE SPEICHERN");
		lowerButtonPanel.add(deleteFlashcardButton);
		lowerButtonPanel.add(saveFlashcardButton);
		editDeckPanel.add(lowerButtonPanel,BorderLayout.PAGE_END);
		
		
		editDeckFrame.setVisible(true);
			
		
		
	}

		
}
