package se.lnu.daniel.typename;

public class AbstractMatchingStrategyTest {
	
	
	
	protected Match match(AbstractMatchingStrategy sut2, String[] inputName,
			String[] inputType) {
		WordSplit nameSplit = new WordSplit(inputName);
		
		WordSplit typeSplit =  new WordSplit(inputType);
		Match actual = sut2.match(nameSplit, typeSplit);
		return actual;
	}
}
