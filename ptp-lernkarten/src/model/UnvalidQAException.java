package model;

public class UnvalidQAException extends Exception {

	public UnvalidQAException() {
		super("Sie Frage und Antwort muss jeweils ein Zeichen beinhalten");
	}

}
