package se.lnu.daniel.typename;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbbrivitationStrategyTest extends FirstLetterAbbriviationStrategyTest{
	protected AbstractMatchingStrategy sut;
	@Before
	public void setUp() throws Exception {
		sut = new AbbrivitationStrategy();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMatch() {
		assertFullMatch(new String[]  {"in"}, new String[] {"input"});
		
		
		
	}
	
	@Test
	public void testPartMatch() {
		assertPartMatch( new String[]  {"in", "For"}, new String[] {"input"});
		assertPartMatch(new String[]  {"instring"}, new String[] {"String"});
	}
	
	@Test
	public void testNoMatch() {
		assertNoMatch(new String[]  {"out"}, new String[] {"input"});
	}
	
	void assertNoMatch(String[] name, String[] type) {
		Match actual = match(sut, name, type);
		assertFalse(actual.isFullMatch());
	}
	
	void assertFullMatch(String[] name, String[] type) {
		Match actual = match(sut, name, type);
		assertTrue(actual.isFullMatch());
	}
	
	void assertPartMatch(String[] name, String[] type) {
		Match actual = match(sut, name, type);
		assertFalse(actual.isFullMatch());
		assertTrue(actual.isPartMatch());
	}

}
