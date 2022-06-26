package test;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.Main;

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
		app.createDeck("test123");
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
	}
}
