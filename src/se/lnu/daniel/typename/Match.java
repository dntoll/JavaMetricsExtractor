package se.lnu.daniel.typename;


/**
 * Result of a matching between two words.
 */
public class Match  {
	enum NameMatch {
		unmatched,
		partlyMatched,
		fullyMatched
	}
	
	private WordSplit nameParts;
	private NameMatch[] matchedNameParts;



	public Match(WordSplit nameSplit) {
		nameParts = nameSplit;
		matchedNameParts = new NameMatch[nameParts.parts.length];
		
		for (int i = 0; i< matchedNameParts.length; i++)
			matchedNameParts[i] = NameMatch.unmatched;
	}

	

	public boolean isBetter(Match bestMatch) {
		if (bestMatch == null)
			return true;
		
		//an earlier strategy is better
		if (bestMatch.isFullMatch()) {
			return false;
		}
		
		if (bestMatch.isPartMatch() && this.isFullMatch()) {
			return true;
		}
		
		if (bestMatch.isPartMatch() == false && this.isPartMatch()) {
			return true;
		}
		
		return false;
	}



	public void addMatch(Word namePart, Word typePart) {
		
		for (int i = 0; i< nameParts.parts.length; i++) {
			if (matchedNameParts[i] == NameMatch.unmatched) {
				if (nameParts.parts[i] == namePart) {
					matchedNameParts[i] = NameMatch.fullyMatched;
				}
			}
		}
	}
	
	public void addPartlyMatch(Word nameWord, Word typeWord) {
		for (int i = 0; i< nameParts.parts.length; i++) {
			if (matchedNameParts[i] == NameMatch.unmatched) {
				if (nameParts.parts[i] == nameWord) {
					matchedNameParts[i] = NameMatch.partlyMatched;
				}
			}
		}
	}



	public boolean isFullMatch() {
		for (int i = 0; i< matchedNameParts.length; i++) {
			if (matchedNameParts[i] != NameMatch.fullyMatched) {
				try {
					Integer.parseInt(nameParts.parts[i].text );
					
				} catch (NumberFormatException e) {
					return false;
				}
				
			}
		}
		
		return true;
	}



	public boolean isPartMatch() {
		for (int i = 0; i< matchedNameParts.length; i++)
			if (matchedNameParts[i] != NameMatch.unmatched) {
				return true;
			}
		
		return false;
	}



	
}
