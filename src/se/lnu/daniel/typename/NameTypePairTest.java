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
		
		sut = new NameTypePair("ActiveEvent", "active_event", Origin.Any);
		assertTrue(sut.equalsIgnorePluralis());
	}
	
	@Test
	public void testFirstLetterAbbriviations() {
		
		sut = new NameTypePair("fw", "FileWriter", Origin.Any);
		assertTrue(sut.nameIsFirstLetterAbbriviationOfType());
		
		sut = new NameTypePair("fp", "FileWriter", Origin.Any);
		assertFalse(sut.nameIsFirstLetterAbbriviationOfType());
		
	}

	@Test
	public void testIsMatchingOneWordOfType() {
		
		sut = new NameTypePair("input", "InputStream", Origin.Any);
		assertTrue(sut.isMatchingOneWordOfType());
		
		sut = new NameTypePair("stream", "InputStream", Origin.Any);
		assertTrue(sut.isMatchingOneWordOfType());
		
		sut = new NameTypePair("streams", "InputStream", Origin.Any);
		assertTrue(sut.isMatchingOneWordOfType());
		
		
		
		sut = new NameTypePair("textFontNativeMetrics", "FontMetrics", Origin.Any);
		assertTrue(sut.isMatchingOneWordOfType());
		
		sut = new NameTypePair("streamo", "InputStream", Origin.Any);
		assertFalse(sut.isMatchingOneWordOfType());
		
		sut = new NameTypePair("textFont", "PFont", Origin.Any);
		assertTrue(sut.isMatchingOneWordOfType());
		
		sut = new NameTypePair("InputStream", "Input", Origin.Any);
		assertTrue(sut.isMatchingOneWordOfType());
		
		sut = new NameTypePair("InputStream", "Stream", Origin.Any);
		assertTrue(sut.isMatchingOneWordOfType());
		
		sut = new NameTypePair("InputStream", "Inputs", Origin.Any);
		assertTrue(sut.isMatchingOneWordOfType());
		
		sut = new NameTypePair("_adapter", "ArrayAdapterString", Origin.Any);
		assertTrue(sut.isMatchingOneWordOfType());
		
		sut = new NameTypePair("adj1", "AdjMatrix", Origin.Any);
		assertTrue(sut.isMatchingOneWordOfType());
		
		sut = new NameTypePair("aluno1", "Aluno", Origin.Any);
		assertTrue(sut.isMatchingOneWordOfType());
		
		sut = new NameTypePair("left_axiom", "AddAxiom", Origin.Any);
		assertTrue(sut.isMatchingOneWordOfType());
		
		sut = new NameTypePair("resetbutton", "Button", Origin.Any);
		assertTrue(sut.isMatchingOneWordOfType());
		
	}

	@Test
	public void testNameIsShortForType() {
		
		 
		
		
		sut = new NameTypePair("in", "InputStream", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("str", "InputStream", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("fileManager", "StandardJavaFileManager", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("diagnostics", "DiagnosticCollector<JavaFileObject>", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("inst", "InputStream", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		
		// this is quite hard to decide, this is an first letter abbriviation
		sut = new NameTypePair("f", "File", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("methodStr", "String", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("Account", "acc1", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		
		sut = new NameTypePair("evt", "ActionEvent", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("file_ar", "ArrayListFile", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("class_mon", "AEMonitor", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("testAct", "Account", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		
		sut = new NameTypePair("b64", "Base64", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("img", "BufferedImage", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("bmp", "Bitmap", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("bm", "Bitmap", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		
		
		sut = new NameTypePair("btn", "Button", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("amp", "AnimationPlayer", Origin.Any);
		assertTrue(sut.nameIsShortForType());
		
		sut = new NameTypePair("background", "BGSprite", Origin.Any);
		assertTrue(sut.nameIsShortForType());
	}
	
	//http://en.wikipedia.org/wiki/Hungarian_notation
	@Test
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
	
	@Test
	public void shouldRemoveTypeInformation() {
		sut = new NameTypePair("timeString", "String", Origin.Any);
		Word[] remains = sut.getRemainsAfterRemovingType();
		assertEquals(1, remains.length);
		assertEquals("time", remains[0].toString());
		
		sut = new NameTypePair("timeStr", "String", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(1, remains.length);
		assertEquals("time", remains[0]);
		
		sut = new NameTypePair("bezierBasis", "PMatrix", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(2, remains.length);
		assertEquals("bezier", remains[0]);
		assertEquals("basis", remains[1]);
		
		sut = new NameTypePair("currProduct", "Panda", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(2, remains.length);
		assertEquals("curr", remains[0]);
		assertEquals("product", remains[1]);
		
		sut = new NameTypePair("animations", "Map<String, Animation>", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(0, remains.length);
		
		sut = new NameTypePair("mediaFile", "File", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(1, remains.length);
		assertEquals("media", remains[0]);
		
		sut = new NameTypePair("jScrollPane1", "javax.swing.JScrollPane", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(1, remains.length);
		assertEquals("1", remains[0]);
		
		
		
		

		sut = new NameTypePair("nocollapse", "boolean", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(1, remains.length);
		assertEquals("nocollapse", remains[0]);
		
		sut = new NameTypePair("sb", "StringBuffer", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(0, remains.length);
		
		sut = new NameTypePair("PARTNER_ENDPOINT_SENTINEL", "Endpoint", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(2, remains.length);
		assertEquals("partner", remains[0]);
		assertEquals("sentinel", remains[1]);
		
		
		
		
		
		sut = new NameTypePair("opcoes", "RadioGroup", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(1, remains.length);
		assertEquals("opcoes", remains[0]);
		
		sut = new NameTypePair("serviceName", "ComponentName", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(1, remains.length);
		assertEquals("service", remains[0]);

		sut = new NameTypePair("rnd", "Random", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(0, remains.length);
		
		sut = new NameTypePair("str", "String", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(0, remains.length);
		
		sut = new NameTypePair("sig", "String", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(1, remains.length);
		assertEquals("sig", remains[0]);
		
		
		sut = new NameTypePair("sigRequest", "String", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(2, remains.length);
		assertEquals("sig", remains[0]);
		assertEquals("request", remains[1]);
		
		sut = new NameTypePair("ec", "EventableChannel", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(0, remains.length);
		
		sut = new NameTypePair("iwConfig", "IndexWriterConfig", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(0, remains.length);

		
		sut = new NameTypePair("newContentPane", "JComponent", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(3, remains.length);
		assertEquals("new", remains[0]);
		assertEquals("content", remains[1]);
		assertEquals("pane", remains[2]);
		
		DocumentBuilderFactory	dbFactory	[db]	[factory]

		
		

	}
}
