package se.lnu.daniel.typename;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IdentifierTest {

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
	public void shouldSplitCamelCaseGetSplits() {
		String expectedFull = "one_two";
		String expectedPart1 = "one";
		String expectedPart2 = "two";
		
		assertTwoParts(expectedFull, expectedPart1, expectedPart2);
	}
	
	@Test
	public void shouldSplitOnUnderscore() {
		String expectedFull = "one_two";
		String expectedPart1 = "one";
		String expectedPart2 = "two";
		
		assertTwoParts(expectedFull, expectedPart1, expectedPart2);
	}
	
	@Test
	public void shouldSplitOnNumber() {
		String expectedFull = "_b64_";
		String expectedPart1 = "b";
		String expectedPart2 = "64";
		
		assertTwoParts(expectedFull, expectedPart1, expectedPart2);
	}


	public void assertTwoParts(String expectedFull,
			String expectedPart1, String expectedPart2) {
		
		Identifier sut = new Identifier(expectedFull);
		WordSplit[] actual = sut.getSplits();
		
		assertNotNull(actual);
		assertEquals(2, actual.length);
		assertEquals(expectedFull, actual[0].parts[0].text);
		
		assertEquals(expectedPart1, actual[1].parts[0].text);
		assertEquals(expectedPart2, actual[1].parts[1].text);
	}
	
	
	
	@Test
	public void shouldSplitOnUnderscoreSingleWord() {
		Identifier sut = new Identifier("_access");
		
		WordSplit[] actual = sut.getSplits();
		
		assertNotNull(actual);
		assertEquals(1, actual.length);
		assertEquals("access", actual[0].parts[0].text);

	}

}
