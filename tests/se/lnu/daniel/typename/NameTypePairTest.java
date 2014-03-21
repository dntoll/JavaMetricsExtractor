package se.lnu.daniel.typename;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.lnu.daniel.typename.NameTypePair.Origin;

public class NameTypePairTest {
	NameTypePair sut;
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
		
		sut = new NameTypePair("inputStream", "InputStream[]", Origin.Any);
		assertTrue(sut.equalsIgnorePluralis());
		
		sut = new NameTypePair("inputStream[]", "InputStream", Origin.Any);
		assertTrue(sut.equalsIgnorePluralis());
		
		sut = new NameTypePair("inputStream", "InputStream", Origin.Any);
		assertTrue(sut.equalsIgnorePluralis());
		
		sut = new NameTypePair("files", "File[]", Origin.Any);
		assertTrue(sut.equalsIgnorePluralis());
		
		sut = new NameTypePair("files[]", "File", Origin.Any);
		assertTrue(sut.equalsIgnorePluralis());
		
		sut = new NameTypePair("files", "File", Origin.Any);
		assertTrue(sut.equalsIgnorePluralis());
		
		sut = new NameTypePair("cherries", "Cherry", Origin.Any);
		assertTrue(sut.equalsIgnorePluralis());
		
		sut = new NameTypePair("heroes", "Hero[]", Origin.Any);
		assertTrue(sut.equalsIgnorePluralis());
		
		sut = new NameTypePair("stream", "InputStream", Origin.Any);
		assertFalse(sut.equalsIgnorePluralis());
		
		sut = new NameTypePair("_access", "Access", Origin.Any);
		assertTrue(sut.equalsIgnorePluralis());
	}
	
	


	
	
	//http://en.wikipedia.org/wiki/Hungarian_notation
/*	@Test
	public void testHasHungarianNotationForType() {
		
		sut = new NameTypePair("nTimes", "int", Origin.Any);
		assertTrue(sut.hasHungarianNotationForType());
		
		sut = new NameTypePair("times", "int", Origin.Any);
		assertFalse(sut.hasHungarianNotationForType());
	}
	
	@Test
	public void testHasMemberNotation() {
		
		sut = new NameTypePair("myTimes", "int", Origin.Field);
		assertTrue(sut.hasClassMemberNotation());
		
		sut = new NameTypePair("m_Times", "int", Origin.Field);
		assertTrue(sut.hasClassMemberNotation());
		
		sut = new NameTypePair("_times", "int", Origin.Field);
		assertTrue(sut.hasClassMemberNotation());
		
		sut = new NameTypePair("times", "int", Origin.Field);
		assertFalse(sut.hasClassMemberNotation());
		
		
	}
	
	@Test
	public void testConditionVariables() {
		//http://www.cs.umd.edu/~clin/MoreJava/Intro/var-descr.html
		
		sut = new NameTypePair("isNotNull", "boolean", Origin.Any);
		assertTrue(sut.isConditionVariable());
		sut = new NameTypePair("hasPassedExam", "boolean", Origin.Any);
		assertTrue(sut.isConditionVariable());
		sut = new NameTypePair("containsIntegers", "boolean", Origin.Any);
		assertTrue(sut.isConditionVariable());
		
		//inMultiLineComment
		//boolean curve_inited
	}
	*/
	@Test
	public void testShowsPluralis() {
		
		sut = new NameTypePair("myTimes", "int[]", Origin.Any);
		assertTrue(sut.nameIsPluralis());
		assertTrue(sut.typeIsArray());
		
		
		
		sut = new NameTypePair("myTimeBuffer", "int[]", Origin.Any);
		assertTrue(sut.nameIndicatesCollection());
		assertTrue(sut.typeIsArray());
		
		sut = new NameTypePair("myTimeArray", "int[]", Origin.Any);
		assertTrue(sut.nameIndicatesCollection());
		assertTrue(sut.typeIsArray());
		
		sut = new NameTypePair("myTimeArray", "intBuffer", Origin.Any);
		assertTrue(sut.nameIndicatesCollection());
		assertTrue(sut.typeCanBeCollection());
		
		sut = new NameTypePair("myTime", "int[]", Origin.Any);
		assertFalse(sut.nameIndicatesCollection());
		assertTrue(sut.typeCanBeCollection());
		
		sut = new NameTypePair("diagnostics", "DiagnosticCollectorJavaFileObject", Origin.Any);
		assertTrue(sut.nameIsPluralis());
		assertTrue(sut.typeCanBeCollection());
		
		sut = new NameTypePair("charBuff", "char[]", Origin.Any);
		assertTrue(sut.nameIndicatesCollection());
		assertTrue(sut.typeCanBeCollection());
		
		//numbers can be pluralis
		sut = new NameTypePair("comments", "int", Origin.Any);
		assertFalse(sut.nameIndicatesCollection());
		assertFalse(sut.typeCanBeCollection());
		
		sut = new NameTypePair("lines", "int", Origin.Any);
		assertFalse(sut.nameIndicatesCollection());
		assertFalse(sut.typeIsArray());
		
		sut = new NameTypePair("numLines", "int", Origin.Any);
		assertFalse(sut.nameIndicatesCollection());
		assertFalse(sut.typeCanBeCollection());
		
		//also float can be pluralis
		sut = new NameTypePair("curveTightness", "float", Origin.Any);
		assertFalse(sut.nameIndicatesCollection());
		assertFalse(sut.typeCanBeCollection());
		 
		
		
	}
	@Test
	public void testNameIndicatesNumber() {
		
		sut = new NameTypePair("textSize", "float", Origin.Any);
		assertTrue(sut.nameIndicatesNumberType());
		
		sut = new NameTypePair("lightCount", "int", Origin.Any);
		assertTrue(sut.nameIndicatesNumberType());
		
		sut = new NameTypePair("SINCOS_LENGTH", "int", Origin.Any);
		assertTrue(sut.nameIndicatesNumberType());
		
		
	}
	
	@Test
	public void testShowsPluralisHardCases() {	
		sut = new NameTypePair("ignoreEncodingErrors", "boolean", Origin.Any);
		assertFalse(sut.nameIndicatesCollection());
		assertFalse(sut.typeCanBeCollection());
		
		sut = new NameTypePair("typeIsPluralis", "boolean", Origin.Any);
		assertFalse(sut.nameIndicatesCollection());
		assertFalse(sut.typeCanBeCollection());
		
		sut = new NameTypePair("myTimeArray", "int", Origin.Any);
		assertTrue(sut.nameIndicatesCollection());
		assertFalse(sut.typeCanBeCollection());

		sut = new NameTypePair("raw", "PGraphics", Origin.Any);
		assertFalse(sut.nameIndicatesCollection());
		assertFalse(sut.typeCanBeCollection());
		
		sut = new NameTypePair("rawGraphics", "PGraphics", Origin.Any);
		assertFalse(sut.nameIndicatesCollection());
		assertFalse(sut.typeCanBeCollection());
		 
		//String splitCamelIntoWords //this is a regexp
		
		//float[] cacheHsbValue
		sut = new NameTypePair("cacheHsbValue", "float[]", Origin.Any);
		assertFalse(sut.nameIndicatesCollection());
		assertTrue(sut.typeCanBeCollection());
		
		//Matrix indicates pluralis
		//PMatrix bezierBasis
		//float[] bezierForwardMatrix
		//float[] bezierDrawMatrix
		sut = new NameTypePair("bezierBasis", "PMatrix", Origin.Any);
		assertFalse(sut.nameIndicatesCollection());
		assertFalse(sut.typeCanBeCollection());
		
		
		//Cosinus
		sut = new NameTypePair("lightSpotAngleCos", "float[]", Origin.Any);
		assertFalse(sut.nameIndicatesCollection());
		assertTrue(sut.typeCanBeCollection());
		
		//cases where 
		//int[] stencil
		//float[] curve_forward
		//int[] lightType
		//float[] lightPosition
		//int[] alpha
	}
	
	
}
