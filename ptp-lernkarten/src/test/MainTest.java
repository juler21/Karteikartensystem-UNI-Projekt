package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DeckOrganizer;
import util.DeckIsExistingException;
import util.Main;
import util.UnValidDecknameException;
import util.UnvalidQAException;

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
		System.out.println(folder.getPath());
		for (File f : folder.listFiles()) {
			if (f.getPath().equals(app.getPathDirectory())) {
				result = true;
			}
		}
		assertTrue(result);
	}

	@Test
	public void testaddDeck() {
		app.deleteDeck("test1");
		try {
			app.createDeck("test1");
		} catch (UnValidDecknameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DeckIsExistingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File folder = new File(app.getPathDirectory());
		boolean result = false;
		for (String f : folder.list()) {
			System.out.println("Hier:" + f);
		}
		for (String f : folder.list()) {
			if (f.equals("test1.csv")) {
				result = true;
			}
		}
		assertTrue(result);
		assertTrue(app.isDeckExisting("test1"));

		assertThrows(UnValidDecknameException.class, () -> {
			app.createDeck(null);
		});
		assertThrows(UnValidDecknameException.class, () -> {
			app.createDeck("Hallo%");
		});
		assertThrows(DeckIsExistingException.class, () -> {
			app.createDeck("test1");
		});
	}

	@Test
	public void testDeleteDeck() {
		app.deleteDeck("test1");
		File folder = new File(app.getPathDirectory());
		boolean result = false;
		for (String f : folder.list()) {
			System.out.println("Hier:" + f);
		}
		for (String f : folder.list()) {
			if (f.equals("test1.csv")) {
				result = true;
			}
		}
		assertFalse(result);
		assertFalse(app.isDeckExisting("test1"));
	}

	@Test
	public void testaddFlashcard() {
		try {
			app.createDeck("test2");
			app.addFlashcard("test2", "Frage", "Antwort");
		} catch (UnValidDecknameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DeckIsExistingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnvalidQAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals("Frage", app.getQuestion("test2", 0));
	}

	@Test
	public void testdeleteFlashcard() {
		app.deleteDeck("test3");
		try {
			app.createDeck("test3");
		} catch (UnValidDecknameException | DeckIsExistingException e1) {
			e1.printStackTrace();
		}

		try {
			app.addFlashcard("test3", "Hallo", "Tschüss");
			app.addFlashcard("test3", "Moin", "Hello");
		} catch (UnvalidQAException e) {
			e.printStackTrace();
		}
		app.deleteFlashcard("test3", 0);
		// neuer vergleich
		assertEquals(app.getQuestion("test3", 0), "Moin");
		assertEquals(app.getAnswer("test3", 0), "Hello");
		// alter vergleich
		assertEquals(1, app.getAmountFlashcards("test3"));
	}

	@Test
	public void testgetDeck() {
		try {
			app.createDeck("test4");
		} catch (UnValidDecknameException | DeckIsExistingException e) {

			e.printStackTrace();
		}
		assertTrue(app.isDeckExisting("test4"));
	}

	@Test
	public void testgetFlashcard() {
		try {
			app.createDeck("test5");
			app.addFlashcard("test5", "Frage1", "Antwort1");
		} catch (UnvalidQAException | UnValidDecknameException | DeckIsExistingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Frage1", app.getQuestion("test5", 0));
		assertEquals("Antwort1", app.getAnswer("test5", 0));
	}

	@Test
	public void testremoveAllFlashcards() {
		//positiv Test
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
		//Test wenn keine Karteikarten enthalten 
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

	}

	@Test
	public void testgetQuestion() {

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
		//positiv Test
		try {
			app.createDeck("testisDeckExisting");
			assertTrue(app.isDeckExisting("testisDeckExisting"));
			
		} catch (UnValidDecknameException e) {
			e.printStackTrace();
		} catch (DeckIsExistingException e) {
			e.printStackTrace();
		}
		//negativ Test
			assertFalse(app.isDeckExisting("notExistingDeckExisting"));
	}

	@Test
	public void testgetPathDirectory() {

	}
}
