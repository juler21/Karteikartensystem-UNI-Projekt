package util;

public class UnvalidQAException extends Exception {

	private static final long serialVersionUID = 6088678616924629042L;

	public UnvalidQAException() {
		super("Die Frage und Antwort müssen jeweils mindestens ein Zeichen beinhalten");
	}

}
