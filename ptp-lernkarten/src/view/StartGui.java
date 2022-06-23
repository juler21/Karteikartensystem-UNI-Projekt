package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.GridLayout;

public class StartGui extends JFrame {
	
 public static void main (String[] args) {
		 
		 new StartGui();
	 }

	private JPanel frameContentPane;
	private JPanel startContentPane;
	private JFrame mainFrame;

	/**
	 * Create the frame.
	 */
	public StartGui() {	
		
		mainFrame = new JFrame();
		
		mainFrame.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		mainFrame.setTitle("Lernkarten");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, 450, 300);
		
		topButtonsErstellen();
		
		startContentPane = new JPanel();
		
		//startContentPane zum HauptContent Pain hinzufügen
		frameContentPane.add(startContentPane, BorderLayout.CENTER);
		frameContentPane.setBackground(new Color (244, 244, 244));
		startContentPane.setLayout(new CardLayout(0, 0));
		
		JPanel startCard = new JPanel();
		startContentPane.add(startCard, "name_2836609601500");
		startCard.setLayout(null);
		JButton lernenBeginnenButton = new JButton("Lernen Beginnen");
		lernenBeginnenButton.setBounds(146, 93, 141, 29);
		startCard.add(lernenBeginnenButton);
		lernenBeginnenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lernenBeginnenButton.setBackground(Color.GREEN);
		lernenBeginnenButton.setForeground(new Color (2, 48, 89));
		
		JPanel decksCard = new JPanel();
		startContentPane.add(decksCard, "name_2859201282041");
		decksCard.setLayout(new GridLayout(0, 2, 0, 0));
		JButton deckErstellenButton = new JButton("Deck Erstellen");
		deckErstellenButton.setBounds(146, 93, 141, 29);
		decksCard.add(deckErstellenButton);
		deckErstellenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		deckErstellenButton.setBackground(Color.GREEN);
		deckErstellenButton.setForeground(new Color (2, 48, 89));
		
		JButton deckBearbeitenButton = new JButton("Deck Bearbeiten");
		decksCard.add(deckBearbeitenButton);
		deckBearbeitenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		deckBearbeitenButton.setForeground(new Color (2, 48, 89));
		
		JPanel statistikCard = new JPanel();
		startContentPane.add(statistikCard, "name_2893444114291");
		
		((CardLayout) startContentPane.getLayout()).show(startContentPane,"name_2836609601500");
		mainFrame.setVisible(true);
		
	}
	
	public void topButtonsErstellen() {
		
		frameContentPane = new JPanel();
		frameContentPane.setBackground(new Color (244, 244, 244));
		frameContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainFrame.setContentPane(frameContentPane);
		frameContentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel mainButtonPanel = new JPanel();
		frameContentPane.add(mainButtonPanel, BorderLayout.NORTH);
		mainButtonPanel.setBackground(new Color (244, 244, 244));
		
		JButton startButton = new JButton("START");
		mainButtonPanel.add(startButton);
		startButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		startButton.setForeground(new Color (2, 48, 89));
		startButton.setBackground(Color.GREEN);
		
		startButton.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e)
		    {
		        //Execute when button is pressed
		    	((CardLayout) startContentPane.getLayout()).show(startContentPane,"name_2836609601500");
		    }}
		);
		
		JButton decksButton = new JButton("DECKS");
		mainButtonPanel.add(decksButton);
		decksButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		decksButton.setForeground(new Color (2, 48, 89));
		decksButton.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e)
		    {
		        //Execute when button is pressed
		    	((CardLayout) startContentPane.getLayout()).show(startContentPane,"name_2859201282041");
		    }}
		);
		
		JButton statistikButton = new JButton("STATISTIK");
		mainButtonPanel.add(statistikButton);
		statistikButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		statistikButton.setForeground(new Color (2, 48, 89));
		
		JButton einstellungenButton = new JButton("EINSTELLUNGEN");
		mainButtonPanel.add(einstellungenButton);
		einstellungenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		einstellungenButton.setForeground(new Color (2, 48, 89));
		
	}
	
	public void startContentPaneErstellen() {
			
	}
}
