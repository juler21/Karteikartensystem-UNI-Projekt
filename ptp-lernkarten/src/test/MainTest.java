package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.DeckIsExistingException;
import util.Main;
import util.UnValidDecknameException;
import util.UnvalidQAException;

/**
 * 
 * Testmethoden testen intern und extern.
 * 
 * @author J.Dillmann, Mark Sterkel
 *
 */
public class MainTest {
	private Main app;

	@BeforeEach
	public void newMain() {
		app = new Main();
		app.changeFoldername("test");
		app.getData(new File(app.getPathtoString()));
		app.removeAllDecks();

	}

	@Test
	public void testremoveAllDecks() {
		try {
			app.createDeck("testremoveAllDecks");
			app.createDeck("testremoveAllDecks2");
			app.createDeck("testremoveAllDecks3");
			app.createDeck("testremoveAllDecks4");
			app.removeAllDecks();
		} catch (UnValidDecknameException | DeckIsExistingException e) {
			e.printStackTrace();
		}
		assertEquals(0, app.getAmountOfDecks());
	}

	@Test
	public void testgetAmountofDecks() {
		try {
			app.createDeck("testgetAmountofDecks");
			app.createDeck("testgetAmountofDecks2");
			app.createDeck("testgetAmountofDecks3");
			app.createDeck("testgetAmountofDecks4");
		} catch (UnValidDecknameException | DeckIsExistingException e) {
			e.printStackTrace();
		}
		assertEquals(4, app.getAmountOfDecks());
	}

	@Test
	public void testCreateDirectory() {
		boolean result = false;
		String path = System.getProperty("user.home");
		File folder = new File(path);
		for (File f : folder.listFiles()) {
			if (f.getPath().equals(app.getPathDirectory())) {
				result = true;
			}
		}
		assertTrue(result);
	}

	@Test
	public void testaddDeck() {
		try {
			app.createDeck("testaddDeck");
		} catch (UnValidDecknameException e) {
			e.printStackTrace();
		} catch (DeckIsExistingException e) {
			e.printStackTrace();
		}
		File folder = new File(app.getPathDirectory());
		boolean result = false;
		for (String f : folder.list()) {
			if (f.equals("testaddDeck.csv")) {
				result = true;
			}
		}
		assertTrue(result);
		assertTrue(app.isDeckExisting("testaddDeck"));

		assertThrows(UnValidDecknameException.class, () -> {
			app.createDeck(null);
		});
		assertThrows(UnValidDecknameException.class, () -> {
			app.createDeck("Hallo%");
		});
		assertThrows(DeckIsExistingException.class, () -> {
			app.createDeck("testaddDeck");
		});
	}

	@Test
	public void testDeleteDeck() {
		app.deleteDeck("testDeleteDeck");
		File folder = new File(app.getPathDirectory());
		boolean result = false;
		for (String f : folder.list()) {
			if (f.equals("testDeleteDeck.csv")) {
				result = true;
			}
		}
		assertFalse(result);
		assertFalse(app.isDeckExisting("testDeleteDeck"));
	}

	@Test
	public void testaddFlashcard() {
		try {
			app.createDeck("testaddFlashcard");
			app.addFlashcard("testaddFlashcard", "Frage", "Antwort");
		} catch (UnValidDecknameException e) {
			e.printStackTrace();
		} catch (DeckIsExistingException e) {
			e.printStackTrace();
		} catch (UnvalidQAException e) {
			e.printStackTrace();
		}

		assertEquals("Frage", app.getQuestion("testaddFlashcard", 0));

		assertThrows(UnvalidQAException.class, () -> {
			app.addFlashcard("testaddFlashcard", "", "Antwort");
		});
		assertThrows(UnvalidQAException.class, () -> {
			app.addFlashcard("testaddFlashcard", null, "Antwort");
		});
		assertThrows(UnvalidQAException.class, () -> {
			app.addFlashcard("testaddFlashcard", "Frage", "");
		});
		assertThrows(UnvalidQAException.class, () -> {
			app.addFlashcard("testaddFlashcard", "Frage", null);
		});
	}

	@Test
	public void testdeleteFlashcard() {
		try {
			app.createDeck("testdeleteFlashcard");
		} catch (UnValidDecknameException | DeckIsExistingException e1) {
			e1.printStackTrace();
		}

		try {
			app.addFlashcard("testdeleteFlashcard", "Hallo", "Tschüss");
			app.addFlashcard("testdeleteFlashcard", "Moin", "Hello");
		} catch (UnvalidQAException e) {
			e.printStackTrace();
		}
		app.deleteFlashcard("testdeleteFlashcard", 0);
		// neuer vergleich
		assertEquals(app.getQuestion("testdeleteFlashcard", 0), "Moin");
		assertEquals(app.getAnswer("testdeleteFlashcard", 0), "Hello");
		// alter vergleich
		assertEquals(1, app.getAmountFlashcards("testdeleteFlashcard"));
	}

	@Test
	public void testgetDeck() {
		try {
			app.createDeck("testgetDeck");
		} catch (UnValidDecknameException | DeckIsExistingException e) {

			e.printStackTrace();
		}
		assertTrue(app.isDeckExisting("testgetDeck"));
		assertEquals("testgetDeck", app.getDeck("testgetDeck").toString());
	}

	@Test
	public void testgetFlashcard() {
		try {
			app.createDeck("test5");
			app.addFlashcard("test5", "Frage1", "Antwort1");
		} catch (UnvalidQAException | UnValidDecknameException | DeckIsExistingException e) {
			e.printStackTrace();
		}
		assertEquals("Frage1", app.getQuestion("test5", 0));
		assertEquals("Antwort1", app.getAnswer("test5", 0));
	}

	@Test
	public void testremoveAllFlashcards() {
		// positiv Test
		try {
			app.createDeck("testremoveAllFlashcards");
			app.addFlashcard("testremoveAllFlashcards", "frage1", "antwort1");
			app.addFlashcard("testremoveAllFlashcards", "frage2", "antwort2");
			app.addFlashcard("testremoveAllFlashcards", "frage3", "antwort3");
			app.removeAllFlashcards("testremoveAllFlashcards");

			assertEquals(0, app.getAmountFlashcards("testremoveAllFlashcards"));

		} catch (UnValidDecknameException e) {
			e.printStackTrace();
		} catch (DeckIsExistingException e) {
			e.printStackTrace();
		} catch (UnvalidQAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Test wenn keine Karteikarten enthalten
		try {
			app.createDeck("testremoveAllFlashcards2");
			app.removeAllFlashcards("testremoveAllFlashcards2");
		} catch (UnValidDecknameException | DeckIsExistingException e) {
			e.printStackTrace();
		}
		assertEquals(0, app.getAmountFlashcards("testremoveAllFlashcards2"));
	}

	@Test
	public void testgetAnswer() {
		try {
			app.createDeck("test9");
			app.addFlashcard("test9", "testFrage2", "testAntwort2");
		} catch (UnValidDecknameException | DeckIsExistingException | UnvalidQAException e) {
			e.printStackTrace();
		}
		assertEquals("testAntwort2", app.getAnswer("test9", 0));

	}

	@Test
	public void testgetQuestion() {
		try {
			app.createDeck("test10");
			app.addFlashcard("test10", "testFrage3", "testAntwort3");
		} catch (UnValidDecknameException | DeckIsExistingException | UnvalidQAException e) {
			e.printStackTrace();
		}
		assertEquals("testFrage3", app.getQuestion("test10", 0));
	}

	@Test
	public void testgetAmountFlashcards() {
		try {
			app.createDeck("testgetAmountFlashcards");
			app.addFlashcard("testgetAmountFlashcards", "frage1", "antwort1");
			app.addFlashcard("testgetAmountFlashcards", "frage2", "antwort2");
			app.addFlashcard("testgetAmountFlashcards", "frage3", "antwort3");
		} catch (UnValidDecknameException | DeckIsExistingException e) {
			e.printStackTrace();
		} catch (UnvalidQAException e) {
			e.printStackTrace();
		}
		assertEquals(3, app.getAmountFlashcards("testgetAmountFlashcards"));
	}

	@Test
	public void testisDeckExisting() {
		// positiv Test
		try {
			app.createDeck("testisDeckExisting");
			assertTrue(app.isDeckExisting("testisDeckExisting"));

		} catch (UnValidDecknameException e) {
			e.printStackTrace();
		} catch (DeckIsExistingException e) {
			e.printStackTrace();
		}
		// negativ Test
		assertFalse(app.isDeckExisting("notExistingDeckExisting"));
	}

	@Test
	public void testgetPathDirectory() {
		String testPath = app.getPathDirectory();
		String comparePathPositiv = System.getProperty("user.home") + "/test";
		String comparePathNegativ = System.getProperty("user.home") + "/decks";
		assertEquals(comparePathPositiv, testPath);
		assertNotEquals(comparePathNegativ, testPath);
	}
}
