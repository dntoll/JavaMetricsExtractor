package se.lnu.daniel.typename;

public class MatchingStrategy {

	public Match matchFullName(WordSplit nameSplit, WordSplit typeSplit) {
		
		Match ret = new Match()
		
		for (Word namePart :nameSplit.parts ) {
			for (Word typePart :typeSplit.parts ) {
				if (namePart.equalsIgnorePluralis(typePart)) {
					ret.addMatch(namePart, typePart);
				}
			}
		}
		return null;
	}

}
