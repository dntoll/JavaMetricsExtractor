package se.lnu.daniel.typename;

public class NameTypeMatch {

	private Match bestMatch;
	private NameTypePair pair;

	public NameTypeMatch(NameTypePair ntp, Match bestMatch) {
		this.bestMatch = bestMatch;
		this.pair = ntp;
	}

	
	public boolean isFullMatch() {
		return bestMatch.isFullMatch();
	}

	public boolean isPartMatch() {
		return bestMatch.isPartMatch();
	}


	public NameTypePair getPair() {
		// TODO Auto-generated method stub
		return pair;
	}

}
