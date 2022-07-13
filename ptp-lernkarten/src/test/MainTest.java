package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Flashcard;
import util.DeckIsExistingException;
import util.Main;
import util.UnValidDecknameException;
import util.UnvalidQAException;

public class MainTest {
	private Main app;

	@BeforeEach
	public void newMain() {
		app = new Main();
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
		assertEquals(app.getDeckmanager().getDecks().get("test1").getDeckname(), "test1");

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
		assertEquals(app.getDeckmanager().getDecks().get("test1"), null);
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

		assertEquals("Frage", app.getFlashcard("test2", 0).getQuestion());
	}

	@Test
	public void testdeleteFlashcard() {
		app.deleteDeck("test3");
		try {
			app.createDeck("test3");
			System.out.println(app.getDeck("test3").getAmountOfFlashcards());
		} catch (UnValidDecknameException | DeckIsExistingException e1) {
			e1.printStackTrace();
		}
//
		try {
			app.addFlashcard("test3", "Hallo", "Tschüss");
			app.addFlashcard("test3", "Moin", "Hello");
		} catch (UnvalidQAException e) {
			e.printStackTrace();
		}
		app.deleteFlashcard("test3", 0);
		//neuer vergleich
		assertEquals(app.getFlashcard("test3", 0).getQuestion(), "Moin");
		assertEquals(app.getFlashcard("test3", 0).getAnswer(), "Hello");
		//alter vergleich
		assertEquals(1, app.getDeck("testDeck3").getAmountOfFlashcards());
	}

	@Test
	public void testgetDeck() {
		try {
			app.createDeck("test4");
		} catch (UnValidDecknameException | DeckIsExistingException e) {

			e.printStackTrace();
		}
		assertEquals("test4", app.getDeck("test4").getDeckname());
	}

	public void testgetFlashcard() {
		try {
			app.createDeck("test5");
			app.addFlashcard("test5", "Frage1", "Antwort1");
		} catch (UnvalidQAException | UnValidDecknameException | DeckIsExistingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Frage1", app.getFlashcard("test5", 0).getQuestion());
		assertEquals("Antwort1", app.getFlashcard("test5", 0).getAnswer());
	}
}
