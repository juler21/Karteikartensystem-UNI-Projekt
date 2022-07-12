package util;

import javax.swing.UIManager;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkContrastIJTheme;
import com.formdev.flatlaf.util.SystemInfo;

import model.Deck;
import model.DeckManager;
import model.Flashcard;
import model.UnvalidQAException;
import view.StartGui;

public class Main {

	private StartGui gui; // view
	private DeckManager deckmanager; // model

	public Main() {
		deckmanager = new DeckManager();
		String name = "Flaschcard System";
		gui = new StartGui(deckmanager, name);

	}

	public static void main(String[] args) {
		// themeSetUp
		FlatLightFlatIJTheme.setup();
//		FlatArcDarkContrastIJTheme.setup();
//		FlatArcIJTheme.setup();
		UIManager.put( "Button.arc", 12 );
		UIManager.put( "Component.arc", 12 );
//		UIManager.put( "ComoboBox.arc", 12 );
		UIManager.put( "TextComponent.arc", 12 );
		
		//soll Fenster dunkel machen 
		if( SystemInfo.isMacOS ) {
		System.setProperty( "apple.awt.application.appearance", "system" );
		 }
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
		getDeck(deckname).addFlashcard(question, answer);
	}

	/*
	 * 
	 */
	public void deleteFlashcard(String deckname, int position) {
		getDeck(deckname).deleteFlashcard(position);
	}

	public void removeAllFlashcards(String deckname) {
		getDeck(deckname).deleteAllFlashcards();
	}

	/*
	 * 
	 */
	public Flashcard getFlashcard(String deckname, int position) {
		return getDeck(deckname).getFlashcard(position);
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
	public String getPathDirectory() {
		return DeckManager.getPathtoString();
	}

	/*
	 * 
	 */
	public DeckManager getDeckmanager() {
		return deckmanager;
	}

}
