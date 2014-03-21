package se.lnu.daniel.typename;


/**
 * Result of a matching between two words.
 */
public class Match  {
	
	
	private WordSplit nameParts;
	private boolean[] matchedNameParts;



	public Match(WordSplit nameSplit) {
		nameParts = nameSplit;
		matchedNameParts = new boolean[nameParts.parts.length];
		
		for (int i = 0; i< matchedNameParts.length; i++)
			matchedNameParts[i] = false;
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
			if (matchedNameParts[i] == false) {
				if (nameParts.parts[i] == namePart) {
					matchedNameParts[i] = true;
				}
			}
		}
	}



	public boolean isFullMatch() {
		for (int i = 0; i< matchedNameParts.length; i++)
			if (matchedNameParts[i] == false) {
				return false;
			}
		
		return true;
	}



	public boolean isPartMatch() {
		for (int i = 0; i< matchedNameParts.length; i++)
			if (matchedNameParts[i] == true) {
				return true;
			}
		
		return false;
	}
}
