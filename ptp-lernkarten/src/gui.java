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

public class gui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public gui() {
		setFont(new Font("Ubuntu", Font.PLAIN, 12));
		setTitle("Lernkarten");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton lernenBeginnenButton = new JButton("Lernen Beginnen");
		lernenBeginnenButton.setBounds(18, 147, 191, 53);
		lernenBeginnenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		lernenBeginnenButton.setBackground(Color.GREEN);
		lernenBeginnenButton.setForeground(new Color (2, 48, 89));
		contentPane.add(lernenBeginnenButton);
		
		JButton startButton = new JButton("START");
		startButton.setBounds(6, 6, 84, 22);
		startButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		startButton.setForeground(new Color (2, 48, 89));
		contentPane.add(startButton);
		
		JButton decksButton = new JButton("DECKS");
		decksButton.setBounds(102, 6, 84, 22);
		decksButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		decksButton.setForeground(new Color (2, 48, 89));
		contentPane.add(decksButton);
		
		JButton statistikButton = new JButton("STATISTIK");
		statistikButton.setBounds(205, 6, 84, 22);
		statistikButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		statistikButton.setForeground(new Color (2, 48, 89));
		contentPane.add(statistikButton);
		
		JButton einstellungenButton = new JButton("EINSTELLUNGEN");
		einstellungenButton.setBounds(310, 6, 134, 22);
		einstellungenButton.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		einstellungenButton.setForeground(new Color (2, 48, 89));
		contentPane.add(einstellungenButton);
	}
}
