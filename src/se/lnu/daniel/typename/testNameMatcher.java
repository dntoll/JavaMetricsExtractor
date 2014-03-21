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

public class testNameMatcher {

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
		
		NameTypeMatch actual = getActual("bufferedReader", "BufferedReader");
		
		assertTrue(actual.isFullMatch());
		assertFalse(actual.doesNotMatch());
	}

	private NameTypeMatch getActual(String variable, String type) {
		NameTypePair ntp = new NameTypePair(variable, type, Origin.Any);
		List<NameTypePair> input = new ArrayList<NameTypePair>();
		input.add(ntp);
		
		NameMatcher sut = new NameMatcher(input);
		
		NameTypeMatch actual = sut.getBestMatches().get(0);
		return actual;
	}
	
	@Test
	public void shouldNotMatch() throws Exception {
		
		NameTypeMatch actual = getActual("out", "BufferedReader");
		
		assertFalse(actual.isFullMatch());
		assertTrue(actual.doesNotMatch());
	}

}
