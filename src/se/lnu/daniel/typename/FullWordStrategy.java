package se.lnu.daniel.typename;

public class FullWordStrategy extends AbstractMatchingStrategy {

	@Override
	public Match match(WordSplit nameSplit, WordSplit typeSplit) {
		
		Match ret = new Match(nameSplit);
		
		for (Word namePart :nameSplit.parts ) {
			for (Word typePart :typeSplit.parts ) {
				if (namePart.equalsIgnorePluralis(typePart)) {
					ret.addMatch(namePart, typePart);
				}
			}
		}
		return ret;
	}
	 
}
