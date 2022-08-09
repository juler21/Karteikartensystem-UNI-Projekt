package util;

import javax.swing.UIManager;

import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;

import model.Deck;
import model.DeckManager;
import model.Flashcard;
import view.MainGui;

public class Main {

	private MainGui gui; // view
	private DeckManager deckmanager; // model

	public Main() {
		deckmanager = new DeckManager();
		String name = "Flaschcard System";
		gui = new MainGui(deckmanager, name);

	}

	public static void main(String[] args) {
		// themeSetUp
		System.setProperty("apple.awt.application.appearance", "system");

		FlatLightFlatIJTheme.setup();
//		FlatArcDarkContrastIJTheme.setup();
//		FlatArcIJTheme.setup();
		UIManager.put("Button.arc", 12);
		UIManager.put("Component.arc", 12);
//		UIManager.put( "ComoboBox.arc", 12 );
		UIManager.put("TextComponent.arc", 12);
		UIManager.put("TextArea.arc", 12);
		UIManager.put("ScrollPane.arc", 12);

		new Main();
	}

	// API

	/*
	 * 
	 * @param deckname,
	 * 
	 * @param question
	 * 
	 * @param String answer
	 * 
	 */

	public void addFlashcard(String deckname, String question, String answer) throws UnvalidQAException {
		deckmanager.addFlashcard(deckname, question, answer);
	}

	/*
	 * 
	 */
	public void deleteFlashcard(String deckname, int position) {
		deckmanager.deleteFlashcard(deckname, position);
	}

	public void removeAllFlashcards(String deckname) {
		deckmanager.removeAllFlashcards(deckname);
	}

	/*
	 * 
	 */
	public Flashcard getFlashcard(String deckname, int position) {
		return deckmanager.getFlashcard(deckname, position);
	}

	/*
	 * 
	 */
	public void createDeck(String deckname) throws UnValidDecknameException, DeckIsExistingException {
		deckmanager.addDeck(deckname);
	}

	/*
	 * 
	 */
	public Deck getDeck(String deckname) {
		return deckmanager.getDeck(deckname);
	}

	/*
	 * 
	 */
	public void deleteDeck(String deckname) {
		deckmanager.removeDeck(deckname);
	}

	/*
	 * 
	 */
	public void createDirectory() {
		deckmanager.createDirectories();
	}

	/*
	 * 
	 */
	public DeckManager getDeckmanager() {
		return deckmanager;
	}

	public String getAnswer(String deckname, int position) {
		return deckmanager.getAnswerFlashcard(deckname, position);
	}

	public String getQuestion(String deckname, int position) {
		return deckmanager.getQuestionFlashcard(deckname, position);
	}

	public int getAmountFlashcards(String deckname) {
		return deckmanager.getAmountOfFlashcards(deckname);
	}

	public boolean isDeckExisting(String deckname) {
		return deckmanager.containsDeck(deckname);
	}

	public String getPathDirectory() {
		// TODO Auto-generated method stub
		return DeckManager.getPathtoString();
	}

}
