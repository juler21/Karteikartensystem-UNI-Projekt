package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.DeckIsExistingException;
import util.Main;
import util.UnValidDecknameException;

public class MainTest {
	private Main app;

	@BeforeEach
	public void newMain() {
		app = new Main();
	}

	@Test
	public void testCreateDirectory() {
//		app.createDirectory();
		boolean result = false;
		String path = System.getProperty("user.home");
		File folder = new File(path);
		System.out.println(folder.getPath());
//		for (String f : folder.list()) {
//			System.out.println(f);
//		}
		for (File f : folder.listFiles()) {
			if (f.getPath().equals(app.getPathDirectory())) {
				result = true;
			}
		}
		assertTrue(result);
	}

	@Test
	public void testaddDeck() {
		app.deleteDeck("test123");
		try {
			app.createDeck("test123");
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
			if (f.equals("test123.csv")) {
				result = true;
			}
		}
		assertTrue(result);
		assertEquals(app.getDeckmanager().getDecks().get("test123").getDeckname(), "test123");

		assertThrows(UnValidDecknameException.class, () -> {
			app.createDeck(null);
		});
		assertThrows(UnValidDecknameException.class, () -> {
			app.createDeck("Hallo%");
		});
		assertThrows(DeckIsExistingException.class, () -> {
			app.createDeck("test123");
		});
	}

	@Test
	public void testDeleteDeck() {
		app.deleteDeck("test123");
		File folder = new File(app.getPathDirectory());
		boolean result = false;
		for (String f : folder.list()) {
			System.out.println("Hier:" + f);
		}
		for (String f : folder.list()) {
			if (f.equals("test123.csv")) {
				result = true;
			}
		}
		assertFalse(result);
		assertEquals(app.getDeckmanager().getDecks().get("test123"), null);
	}
}
