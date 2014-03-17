package se.lnu.daniel.typename;

import static org.junit.Assert.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WordTest {

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
	public void testCreateFromIdentifier() {
		Word[] actual;
		
		actual = Word.createFromIdentifier(new Identifier("UPPER_CASE"));
		assertTrue(actual.length == 3);
		assertEquals("upper", actual[0].toString());
		assertEquals("_", actual[1].toString());
		assertEquals("case", actual[2].toString());
		
		actual = Word.createFromIdentifier(new Identifier("_Fields"));
		assertTrue(actual.length == 2);
		assertEquals("_", actual[0].toString());
		assertEquals("fields", actual[1].toString());
		
		actual = Word.createFromIdentifier(new Identifier("CamelCase"));
		assertTrue(actual.length == 2);
		assertEquals("case", actual[1].toString());
		actual = Word.createFromIdentifier(new Identifier("camelCase"));
		assertTrue(actual.length == 2);
		assertEquals("case", actual[1].toString());	
		
		
		
		actual = Word.createFromIdentifier(new Identifier("b64"));
		assertTrue(actual.length == 2);
		assertEquals("b", actual[0].toString());
		assertEquals("64", actual[1].toString());
		
		
		
		actual = Word.createFromIdentifier(new Identifier("LEFT_AXIOM"));
		assertEquals(3, actual.length);
		assertEquals("left", actual[0].toString());
		assertEquals("_", actual[1].toString());
		assertEquals("axiom", actual[2].toString());
		
		actual = Word.createFromIdentifier(new Identifier("left_axiom"));
		assertEquals(3, actual.length);
		assertEquals("left", actual[0].toString());
		assertEquals("_", actual[1].toString());
		assertEquals("axiom", actual[2].toString());
		
		actual = Word.createFromIdentifier(new Identifier("_b64_"));
		assertTrue(actual.length == 4);
		assertEquals("_", actual[0].toString());
		assertEquals("b", actual[1].toString());
		assertEquals("64", actual[2].toString());
		assertEquals("_", actual[3].toString());
		
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsIgnoreCase() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsString() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsIgnorePluralis() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsIgnorePluralis() {
		fail("Not yet implemented");
	}

}
