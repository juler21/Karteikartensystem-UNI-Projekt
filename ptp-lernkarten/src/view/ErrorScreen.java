package view;

import javax.swing.JOptionPane;

public class ErrorScreen extends JOptionPane {

	private String errorMessage;

	public ErrorScreen(String errorMessage) {
		super(errorMessage, WARNING_MESSAGE);
		this.errorMessage = errorMessage;
		this.showMessageDialog(null, errorMessage);
	}
}
