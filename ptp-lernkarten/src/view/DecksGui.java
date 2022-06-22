package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DecksGui extends StartGui {

	private JPanel DecksContentPane;

	/**
	 * Create the frame.
	 */
	public DecksGui() {
		setFont(new Font("Ubuntu", Font.PLAIN, 12));
		setTitle("Lernkarten");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		super.topButtonsErstellen();
	}

}
