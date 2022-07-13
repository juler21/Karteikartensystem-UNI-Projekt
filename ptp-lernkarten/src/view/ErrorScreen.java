package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorScreen {

	private JFrame errorFrame;

	public ErrorScreen(String errorMessage) {
		errorFrame = new JFrame();

		JOptionPane.showMessageDialog(errorFrame, errorMessage, "Warning", JOptionPane.WARNING_MESSAGE);
	}
}
