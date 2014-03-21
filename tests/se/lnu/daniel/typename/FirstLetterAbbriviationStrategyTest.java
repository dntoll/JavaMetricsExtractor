package se.lnu.daniel.typename;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FirstLetterAbbriviationStrategyTest extends AbstractMatchingStrategyTest {

	protected AbstractMatchingStrategy sut;

	@Before
	public void setUp() throws Exception {
		sut = new FirstLetterAbbriviationStrategy();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMatch() {
		Match actual = match(sut,new String[]  {"br"}, new String[] {"buffered", "reader"});
		assertTrue(actual.isFullMatch());
	}
	
	@Test
	public void testNoMatch() {
		Match actual = match(sut, new String[] {"sr"}, new String[] {"buffered", "reader"});
		assertFalse(actual.isFullMatch());
	}

	

}
