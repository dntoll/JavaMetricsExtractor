package se.lnu.daniel.typename;


/**
 * Result of a matching between two words.
 */
public class Match  {
	Word nameWord;
	boolean matchInOneTypeWord, isAcronym, abbriviationIsEntireName;
	
	public Match(Word nameWord, boolean matchInOneTypeWord, boolean isAcronym,
			boolean abbriviationIsEntireName) {
		this.nameWord = nameWord;
		this.matchInOneTypeWord = matchInOneTypeWord;
		this.isAcronym = isAcronym;
		this.abbriviationIsEntireName = abbriviationIsEntireName;
	}

	public String toString() {
		
		String typeOfMatch = "";
		/*
		 if (matchInOneTypeWord) {
			typeOfMatch += "(inT)";
		} else if (abbriviationIsEntireName) {
			typeOfMatch += "(abb)";
		} else if (isAcronym) {
			typeOfMatch += "(acr)";
		} */
		
		return nameWord + typeOfMatch;
	}
}
