package util;

/**
 * PTP 22 - Exception: wird bei unzulässigen Fragen und Antworten geworfen 
 * unzulässig sind leere oder nur aus leerzeichen bestehende Fragen/Antworten 
 * 
 * @author J.Dillmann, M. Sterkel
 */
public class UnvalidQAException extends Exception {

	private static final long serialVersionUID = 6088678616924629042L;

	public UnvalidQAException() {
		super("Die Frage und Antwort müssen jeweils mindestens ein Zeichen beinhalten");
	}

}
