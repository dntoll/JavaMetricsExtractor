package se.lnu.daniel.typename;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import se.lnu.daniel.typename.NameTypePair.Origin;

public class ContainAnalysisTest {

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testFirstLetterAbbriviations() {
		
		NameTypePair ntp = new NameTypePair("fw", "FileWriter", Origin.Any);
		ContainAnalysis sut = new ContainAnalysis(ntp.getName(), ntp.getType());
		assertTrue(sut.nameIsFirstLetterAbbriviationOfType());
		
		ntp = new NameTypePair("fp", "FileWriter", Origin.Any);
		sut = new ContainAnalysis(ntp.getName(), ntp.getType());
		assertFalse(sut.nameIsFirstLetterAbbriviationOfType());
		
	}
	
	void shouldRemoveTypeInformationDD(String variableName, String typeName, String[] result) {
		NameTypePair ntp = new NameTypePair(variableName, typeName, Origin.Any);
		ContainAnalysis sut = new ContainAnalysis(ntp.getName(), ntp.getType());
		Word[] remains = sut.getRemainsAfterRemovingType();
		assertEquals(result.length, remains.length);
		
		for (int i = 0; i < result.length; i++) {
			assertEquals(result[i], remains[i].toString());
		}
	}
	
	@Test
	public void shouldRemoveTypeInformation() {
		shouldRemoveTypeInformationDD("timeString", "String", new String[] {"time"});
		shouldRemoveTypeInformationDD("bezierBasis", "PMatrix", new String[] {"bezier", "basis"});
		shouldRemoveTypeInformationDD("currProduct", "Panda", new String[] {"curr", "product"});
		/*
		 * TODO Add these tests also, but the test allready breaks so wait until we fix the main issue...
		 */
		ntp = new NameTypePair("currProduct", "Panda", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(2, remains.length);
		assertEquals("curr", remains[0]);
		assertEquals("product", remains[1]);
		
		ntp = new NameTypePair("animations", "Map<String, Animation>", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(0, remains.length);
		
		ntp = new NameTypePair("mediaFile", "File", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(1, remains.length);
		assertEquals("media", remains[0]);
		
		ntp = new NameTypePair("jScrollPane1", "javax.swing.JScrollPane", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(1, remains.length);
		assertEquals("1", remains[0]);
		
		
		
		

		ntp = new NameTypePair("nocollapse", "boolean", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(1, remains.length);
		assertEquals("nocollapse", remains[0]);
		
		ntp = new NameTypePair("sb", "StringBuffer", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(0, remains.length);
		
		ntp = new NameTypePair("PARTNER_ENDPOINT_SENTINEL", "Endpoint", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(2, remains.length);
		assertEquals("partner", remains[0]);
		assertEquals("sentinel", remains[1]);
		
		
		
		
		
		ntp = new NameTypePair("opcoes", "RadioGroup", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(1, remains.length);
		assertEquals("opcoes", remains[0]);
		
		ntp = new NameTypePair("serviceName", "ComponentName", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(1, remains.length);
		assertEquals("service", remains[0]);

		ntp = new NameTypePair("rnd", "Random", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(0, remains.length);
		
		ntp = new NameTypePair("str", "String", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(0, remains.length);
		
		ntp = new NameTypePair("sig", "String", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(1, remains.length);
		assertEquals("sig", remains[0]);
		
		
		ntp = new NameTypePair("sigRequest", "String", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(2, remains.length);
		assertEquals("sig", remains[0]);
		assertEquals("request", remains[1]);
		
		ntp = new NameTypePair("ec", "EventableChannel", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(0, remains.length);
		
		ntp = new NameTypePair("iwConfig", "IndexWriterConfig", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(0, remains.length);

		
		ntp = new NameTypePair("newContentPane", "JComponent", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(3, remains.length);
		assertEquals("new", remains[0]);
		assertEquals("content", remains[1]);
		assertEquals("pane", remains[2]);
		
		ntp = new NameTypePair("dbFactory", "DocumentBuilderFactory", Origin.Any);
		remains = sut.getRemainsAfterRemovingType();
		assertEquals(0, remains.length);*/
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
}
