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
		
		assertTwoParts("oneTwo", "onetwo", "one", "two");
	}
	
	@Test
	public void shouldSplitOnUnderscore() {
		assertTwoParts("one_two", "one_two", "one", "two");
		assertTwoParts("ONE_TWO", "one_two", "one", "two");
	}
	
	@Test
	public void shouldSplitOnNumber() {
		assertTwoParts("_b64_", "b64", "b", "64");
	}


	public void assertTwoParts(String input, String expectedFull,
			String expectedPart1, String expectedPart2) {
		
		Identifier sut = new Identifier(input);
		WordSplit[] actual = sut.getSplits();
		
		assertNotNull(actual);
		assertEquals(2, actual.length);
		
		assertEquals(expectedPart1, actual[1].parts[0].text);
		assertEquals(expectedPart2, actual[1].parts[1].text);
		assertEquals(expectedFull, actual[0].parts[0].text);
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
