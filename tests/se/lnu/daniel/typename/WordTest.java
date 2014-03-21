package se.lnu.daniel.typename;

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
		
		actual = Identifier.createFromIdentifier(new Identifier("UPPER_CASE"));
		assertEquals(2, actual.length);
		assertEquals("upper", actual[0].toString());
		assertEquals("case", actual[1].toString());
		
		actual = Identifier.createFromIdentifier(new Identifier("_Fields"));
		assertTrue(actual.length == 1);
		assertEquals("fields", actual[0].toString());
		
		actual = Identifier.createFromIdentifier(new Identifier("CamelCase"));
		assertTrue(actual.length == 2);
		assertEquals("case", actual[1].toString());
		actual = Identifier.createFromIdentifier(new Identifier("camelCase"));
		assertTrue(actual.length == 2);
		assertEquals("case", actual[1].toString());	
		
		actual = Identifier.createFromIdentifier(new Identifier("b64"));
		assertTrue(actual.length == 2);
		assertEquals("b", actual[0].toString());
		assertEquals("64", actual[1].toString());
				
		actual = Identifier.createFromIdentifier(new Identifier("LEFT_AXIOM"));
		assertEquals(2, actual.length);
		assertEquals("left", actual[0].toString());
		assertEquals("axiom", actual[1].toString());
		
		actual = Identifier.createFromIdentifier(new Identifier("left_axiom"));
		assertEquals(2, actual.length);
		assertEquals("left", actual[0].toString());
		assertEquals("axiom", actual[1].toString());
		
		actual = Identifier.createFromIdentifier(new Identifier("_b64_"));
		assertEquals(2, actual.length);
		assertEquals("b", actual[0].toString());
		assertEquals("64", actual[1].toString());
		
	}



}
