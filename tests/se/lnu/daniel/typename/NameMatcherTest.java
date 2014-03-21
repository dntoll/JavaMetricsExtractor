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

		assertFullyMatched("bufferedReader", "BufferedReader");
		assertFullyMatched("buffered", "BufferedReader");
		assertFullyMatched("br", "BufferedReader");
		assertFullyMatched("br", "buffered_reader");
		assertFullyMatched("ActiveEvent", "active_event");
		assertFullyMatched("cherries", "Cherry");
		assertFullyMatched("in", "inputStream[]");
		assertFullyMatched("animations", "Map<String, Animation>");
		
		
		
		assertFullyMatched("sb", "StringBuffer");
		assertFullyMatched("rnd", "Random");
		assertFullyMatched("str", "String");
		assertFullyMatched("ec", "EventableChannel");
		assertFullyMatched("iwConfig", "IndexWriterConfig");
		assertFullyMatched("dbFactory", "DocumentBuilderFactory");
		assertFullyMatched("in", "InputStream");
		assertFullyMatched("str", "InputStream");
		assertFullyMatched("fileManager", "StandardJavaFileManager");
		assertFullyMatched("diagnostics", "DiagnosticCollector<JavaFileObject>");
		assertFullyMatched("inst", "InputStream");
		assertFullyMatched("f", "File");
		assertFullyMatched("evt", "ActionEvent");
		assertFullyMatched("file_ar", "ArrayListFile");
		assertFullyMatched("b64", "Base64");
		assertFullyMatched("img", "BufferedImage");
		assertFullyMatched("bmp", "Bitmap");
		assertFullyMatched("bm", "Bitmap");
		assertFullyMatched("btn", "Button");
		assertFullyMatched("amp", "AnimationPlayer");
		
		assertFullyMatched("input", "InputStream");
		assertFullyMatched("stream", "InputStream");
		assertFullyMatched("streams", "InputStream");
		
		assertFullyMatched("_adapter", "ArrayAdapterString");
		
		

	}

	@Test
	public void shouldNotMatch() throws Exception {
		assertUnMatched("out", "BufferedReader");
		assertUnMatched("currProduct", "Panda");
		assertUnMatched("bezierBasis", "PMatrix");
		assertUnMatched("nocollapse", "boolean");
		assertUnMatched("opcoes", "RadioGroup");
		assertUnMatched("newContentPane", "JComponent");
	}

	@Test
	public void shouldMatchPartly() throws Exception {
		assertPartlyMatched("class_mon", "AEMonitor");
		assertPartlyMatched("bufferedOne", "BufferedReader");
		assertPartlyMatched("bufferedOne", "ReaderOne");

		assertPartlyMatched("timeString", "String");
		
		assertPartlyMatched("mediaFile", "File");
		assertPartlyMatched("PARTNER_ENDPOINT_SENTINEL", "Endpoint");
		assertPartlyMatched("serviceName", "ComponentName");
		assertPartlyMatched("methodStr", "String");
		
		assertPartlyMatched("testAct", "Account");
		assertPartlyMatched("textFontNativeMetrics", "FontMetrics");
		assertPartlyMatched("textFont", "PFont");
		assertPartlyMatched("left_axiom", "AddAxiom");
		assertPartlyMatched("resetbutton", "Button");
	}
	
	@Test
	public void shouldBeFullMatchEvenIfNumberIsThere() throws Exception {
		assertFullyMatched("jScrollPane1", "javax.swing.JScrollPane");
		assertFullyMatched("acc1", "Account");
		assertFullyMatched("adj1", "AdjMatrix");
		assertFullyMatched("aluno1", "Aluno");
	}
	@Test
	public void problematic() throws Exception {
		assertUnMatched("sigRequest", "String");
		assertFullyMatched("streamo", "InputStream");
		assertFullyMatched("background", "BGSprite");
		
		assertUnMatched("sig", "String");
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
