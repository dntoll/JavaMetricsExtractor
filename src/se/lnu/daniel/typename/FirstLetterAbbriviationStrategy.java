package se.lnu.daniel.typename;

public class FirstLetterAbbriviationStrategy extends AbstractMatchingStrategy {

	@Override
	public Match match(WordSplit nameSplit, WordSplit typeSplit) {
		
		Match ret = new Match(nameSplit);
		
		String firstLetterAbbriviation = "";
		for (Word typePart : typeSplit.parts ) {
			if (typePart.text.length() > 0)
				firstLetterAbbriviation += typePart.text.charAt(0);
		}
		
		for (Word namePart :nameSplit.parts ) {
			
			if (namePart.equalsIgnorePluralis(new Word(firstLetterAbbriviation))) {
				for (Word typePart :typeSplit.parts ) {
					ret.addMatch(namePart, typePart);
				}
			}
			
		}
		return ret;
	}

}
