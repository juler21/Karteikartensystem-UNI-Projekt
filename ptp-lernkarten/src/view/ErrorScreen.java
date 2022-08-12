package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * PTP 22 - JOptionPane das bei falschen User Eingaben angezeigt wird
 * 
 * @author J.Dillmann, M. Sterkel
 */
public class ErrorScreen {

	private JFrame errorFrame;

	/**
	 * Im Konstruktor wird eine Instanz der Klasse ErrorScreen erstellt.
	 * @param error Message die Anzuzeigende Error-Nachricht
	 */
	public ErrorScreen(String errorMessage) {
		errorFrame = new JFrame();
		JOptionPane.showMessageDialog(errorFrame, errorMessage, "Warning", JOptionPane.WARNING_MESSAGE);
	}
}
