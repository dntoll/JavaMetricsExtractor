package se.lnu.daniel.typename;



import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.lnu.daniel.typename.NameTypePair.Origin;

public class NameMatcherTest {

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
	public void shouldMatchFullName() throws Exception {
		
		assertFullyMatched("BufferedReader", "bufferedReader");
		assertFullyMatched("buffered", "BufferedReader");
		assertFullyMatched("br", "BufferedReader");
		assertFullyMatched("br", "buffered_reader");

	}
	
	@Test
	public void shouldNotMatch() throws Exception {
		assertUnMatched("out", "BufferedReader");
	}
	
	@Test
	public void shouldMatchPartly() throws Exception {
		assertPartlyMatched("bufferedOne", "BufferedReader");
		assertPartlyMatched("bufferedOne", "ReaderOne");
	}

	private void assertFullyMatched(String type, String variable) {
		NameTypeMatch actual = getActual(type, variable);
		
		assertTrue(actual.isFullMatch());
		assertTrue(actual.isPartMatch());
	}
	
	private void assertPartlyMatched(String type, String variable) {
		NameTypeMatch actual = getActual(type, variable);
		
		assertFalse(actual.isFullMatch());
		assertTrue(actual.isPartMatch());
	}
	private void assertUnMatched(String type, String variable) {
		NameTypeMatch actual = getActual(type, variable);
		
		assertFalse(actual.isFullMatch());
		assertFalse(actual.isPartMatch());
	}
	
	

	private NameTypeMatch getActual(String variable, String type) {
		NameTypePair ntp = new NameTypePair(variable, type, Origin.Any);
		List<NameTypePair> input = new ArrayList<NameTypePair>();
		input.add(ntp);
		
		NameMatcher sut = new NameMatcher(input);
		
		NameTypeMatch actual = sut.getBestMatches().get(0);
		return actual;
	}
	
	

}
