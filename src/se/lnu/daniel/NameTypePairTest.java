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
		
		sut = new NameTypePair("files", "File[]");
		assertTrue(sut.isMatching());
		
		sut = new NameTypePair("files[]", "File");
		assertTrue(sut.isMatching());
		
		sut = new NameTypePair("stream", "InputStream");
		assertFalse(sut.isMatching());
	}
	
	@Test
	public void testFirstLetterAbbriviations() {
		NameTypePair sut;
		sut = new NameTypePair("fw", "FileWriter");
		assertTrue(sut.nameIsAbbriviationOfType());
		
		sut = new NameTypePair("fp", "FileWriter");
		assertFalse(sut.nameIsAbbriviationOfType());
		
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
		
		sut = new NameTypePair("fileManager", "StandardJavaFileManager");
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("diagnostics", "DiagnosticCollector<JavaFileObject>");
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("ins", "InputStream");
		assertFalse(sut.nameIsShortForType());
		
		sut = new NameTypePair("f", "File");
		assertFalse(sut.nameIsShortForType());
	}
	
	@Test
	public void testHasHungarianNotationForType() {
		NameTypePair sut;
		sut = new NameTypePair("nTimes", "int");
		assertTrue(sut.hasHungarianNotationForType());
		
		sut = new NameTypePair("times", "int");
		assertFalse(sut.hasHungarianNotationForType());
	}
	
	@Test
	public void testHasMemberNotation() {
		NameTypePair sut;
		sut = new NameTypePair("myTimes", "int", Type.Member);
		assertTrue(sut.hasClassMemberNotation());
		
		sut = new NameTypePair("m_Times", "int", Type.Member);
		assertTrue(sut.hasClassMemberNotation());
		
		sut = new NameTypePair("_times", "int", Type.Member);
		assertTrue(sut.hasClassMemberNotation());
		
		sut = new NameTypePair("times", "int", Type.Member);
		assertFalse(sut.hasClassMemberNotation());
		
		http://www.cs.umd.edu/~clin/MoreJava/Intro/var-descr.html
	}
	
	@Test
	public void testShowsPluralis() {
		NameTypePair sut;
		sut = new NameTypePair("myTimes", "int[]", Type.Any);
		assertTrue(sut.isNameIsShowingTypePluralis());
		
		sut = new NameTypePair("myTimeBuffer", "int[]", Type.Any);
		assertTrue(sut.isNameIsShowingTypePluralis());
		
		sut = new NameTypePair("myTimeArray", "int[]", Type.Any);
		assertTrue(sut.isNameIsShowingTypePluralis());
		
		sut = new NameTypePair("myTimeArray", "int", Type.Any);
		assertFalse(sut.isNameIsShowingTypePluralis());
		
		sut = new NameTypePair("myTime", "int[]", Type.Any);
		assertFalse(sut.isShowingTypePluralis());
	}
}
