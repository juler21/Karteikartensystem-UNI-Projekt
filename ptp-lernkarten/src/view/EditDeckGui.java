package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
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
	private JList<String> flashcardList;
	private JLabel questionLabel;
	private JLabel answerLabel;
	private JButton deleteFlashcardButton;

	public EditDeckGui(Main app) {
		
		this.app = app;
		
		
		// JFrame erstellen
		editDeckFrame = new JFrame();
		editDeckFrame.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		editDeckFrame.setTitle("Lernkarten");
		editDeckFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editDeckFrame.setBounds(100, 100, 500, 300);

		//
		editDeckPanel = new JPanel();
		editDeckPanel.setBackground(new Color(244, 244, 244));
		editDeckPanel.setLayout(new BoxLayout(editDeckPanel, BoxLayout.PAGE_AXIS));
		editDeckFrame.setContentPane(editDeckPanel);
		
		String[] flashcardArray = new String[10];
		flashcardArray[1]= "Karte 1";
		flashcardArray[2]= "Karte 2";
		flashcardArray[3]= "Karte 3";
		flashcardList = new JList(flashcardArray);
		editDeckPanel.add(flashcardList);
		
		
		editDeckFrame.setVisible(true);
			
		
		
	}

		
}
