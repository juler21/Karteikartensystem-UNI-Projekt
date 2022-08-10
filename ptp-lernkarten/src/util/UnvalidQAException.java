package util;

public class UnvalidQAException extends Exception {

	public UnvalidQAException() {
		super("Die Frage und Antwort müssen jeweils mindestens ein Zeichen beinhalten");
	}

}
