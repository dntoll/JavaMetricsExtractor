package se.lnu.daniel.linecounter;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;

public class LineCounterTest {

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
	public void noInput() {
		String input = "";
		int expectedLOC = 0;
		int expectedLCOM = 0;
		int expectedEmpty = 0;
		
		assertDataDriven(input, expectedLOC, expectedLCOM, expectedEmpty, 0);
	}
	
	@Test
	public void countCharacters() {
		LineCounter sut = new LineCounter("123");
		
		int actualCharacters = sut.getNumberOfCharacters();
		assertEquals("NumberOfCharacters", 3, actualCharacters);
	}
	
	@Test
	public void countEmpty() {
		String input = "\n";
		int expectedLOC = 0;
		int expectedLCOM = 0;
		int expectedEmpty = 1;
		
		assertDataDriven(input, expectedLOC, expectedLCOM, expectedEmpty, 1);
	}
	@Test
	public void count2Empty() {
		String input = "\n\n";
		int expectedLOC = 0;
		int expectedLCOM = 0;
		int expectedEmpty = 2;
		
		assertDataDriven(input, expectedLOC, expectedLCOM, expectedEmpty, 2);
	}
	
	@Test
	public void count1Comment() {
		String input = "//hello"; //hello
		int expectedLOC = 0;
		int expectedLCOM = 1;
		int expectedEmpty = 0;
		
		assertDataDriven(input, expectedLOC, expectedLCOM, expectedEmpty, 1);
	}
	
	@Test
	public void count2Comments() {
		String input = "	//hello \n" +
					   "	//smello"; //hello
		int expectedLOC = 0;
		int expectedLCOM = 2;
		int expectedEmpty = 0;
		
		assertDataDriven(input, expectedLOC, expectedLCOM, expectedEmpty, 2);
	}
	
	@Test
	public void count2EmptyComments() {
		String input = "	// \n" +
					   "	// "; //hello
		int expectedLOC = 0;
		int expectedLCOM = 0;
		int expectedEmpty = 2;
		
		assertDataDriven(input, expectedLOC, expectedLCOM, expectedEmpty, 2);
	}
	
	@Test
	public void countMultilineComment() {
		String input = "	/*hello \n" +
					   "	smello */"; //hello
		int expectedLOC = 0;
		int expectedLCOM = 2;
		int expectedEmpty = 0;
		
		assertDataDriven(input, expectedLOC, expectedLCOM, expectedEmpty, 2);
	}
	@Test
	public void countMultilineCommentWithEmptyLine() {
		String input = " /*hello \n\t\n" +
					   " smello */"; //hello
		int expectedLOC = 0;
		int expectedLCOM = 2;
		int expectedEmpty = 1;
		
		assertDataDriven(input, expectedLOC, expectedLCOM, expectedEmpty, 3);
	}
	
	@Test
	public void e1() {
		String input =  "/*h \nt\n" +
		   				"s */"; //hello
		int expectedLOC = 0;
		int expectedLCOM = 3;
		int expectedEmpty = 0;
		
		assertDataDriven(input, expectedLOC, expectedLCOM, expectedEmpty, 3);
		
	}
		
	@Test
	public void dontCountCommentsInStringsDouble() {
		String input = "one = \"//hello\";";
		int expectedLOC = 1;
		int expectedLCOM = 0;
		int expectedEmpty = 0;
		
		assertDataDriven(input, expectedLOC, expectedLCOM, expectedEmpty, 1);
	}
	
	@Test
	public void dontCountCommentsInStringsSingle() {
		String input = "one = '//hello';";
		int expectedLOC = 1;
		int expectedLCOM = 0;
		int expectedEmpty = 0;
		
		assertDataDriven(input, expectedLOC, expectedLCOM, expectedEmpty, 1);
	}
	
	@Test
	public void countCommentsWithStrings() {
		String input = "one = 1;//'//hello';";
		int expectedLOC = 1;
		int expectedLCOM = 1;
		int expectedEmpty = 0;
		
		assertDataDriven(input, expectedLOC, expectedLCOM, expectedEmpty, 1);
	}
	
	@Test
	public void combo() {
		String input = "\tone/*hello \n" +
					   "\t\n" +
					   "\tsmello */ two"; //hello
		int expectedLOC = 2;
		int expectedLCOM = 2;
		int expectedEmpty = 1;
		
		assertDataDriven(input, expectedLOC, expectedLCOM, expectedEmpty, 3);
	}

	private void assertDataDriven(String input, int expectedLOC,
			int expectedLCOM, int expectedEmpty, int expectedNumLines) {
		LineCounter sut = new LineCounter(input);
		
		int actualLOC = sut.getLines();
		int actualLCOM = sut.getComments();
		int actualLE = sut.getEmptyLines();
		int actualNumLines = sut.getTotalLines();
		
		assertEquals("Lines of Code", expectedLOC, actualLOC);
		assertEquals("Lines of Comments", expectedLCOM, actualLCOM);
		assertEquals("Lines of Empty", expectedEmpty, actualLE);
		assertEquals("Number of lines", expectedNumLines, actualNumLines);
	}

}
