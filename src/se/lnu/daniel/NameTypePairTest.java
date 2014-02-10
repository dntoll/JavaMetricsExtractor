package se.lnu.daniel;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NameTypePairTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsMatching() {
		NameTypePair sut;
		sut = new NameTypePair("inputStream", "InputStream[]");
		assertTrue(sut.isMatching());
		
		sut = new NameTypePair("inputStream[]", "InputStream");
		assertTrue(sut.isMatching());
		
		sut = new NameTypePair("inputStream", "InputStream");
		assertTrue(sut.isMatching());
		
		sut = new NameTypePair("stream", "InputStream");
		assertFalse(sut.isMatching());
	}

	@Test
	public void testIsMatchingOneWordOfType() {
		NameTypePair sut;
		sut = new NameTypePair("input", "InputStream");
		assertTrue(sut.isMatchingOneWordOfType());
		
		sut = new NameTypePair("stream", "InputStream");
		assertTrue(sut.isMatchingOneWordOfType());
		
		sut = new NameTypePair("streams", "InputStream");
		assertFalse(sut.isMatchingOneWordOfType());
	}

	@Test
	public void testTypeIsMatchingOneWordOfName() {
		NameTypePair sut;
		sut = new NameTypePair("InputStream", "Input");
		assertTrue(sut.typeIsMatchingOneWordOfName());
		
		sut = new NameTypePair("InputStream", "Stream");
		assertTrue(sut.typeIsMatchingOneWordOfName());
		
		sut = new NameTypePair("InputStream", "Inputs");
		assertFalse(sut.typeIsMatchingOneWordOfName());
	}

	@Test
	public void testNameIsShortForType() {
		NameTypePair sut;
		sut = new NameTypePair("in", "InputStream");
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("str", "InputStream");
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("ins", "InputStream");
		assertFalse(sut.nameIsShortForType());
	}

}
