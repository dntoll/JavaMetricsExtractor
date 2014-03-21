package se.lnu.daniel.typename;

public class NameTypeMatch {

	private Match bestMatch;

	public NameTypeMatch(NameTypePair ntp, Match bestMatch) {
		this.bestMatch = bestMatch;
		
	}

	
	public boolean isFullMatch() {
		return bestMatch.isFullMatch();
	}

	public boolean isPartMatch() {
		return bestMatch.isPartMatch();
	}

}
