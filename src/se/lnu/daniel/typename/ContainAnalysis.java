package se.lnu.daniel.typename;

import java.util.ArrayList;
import java.util.LinkedList;

import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;
import name.fraser.neil.plaintext.diff_match_patch.Operation;

public class ContainAnalysis {
	
	
	ArrayList<Word> remains = new ArrayList<Word>();
	ArrayList<Match> matched = new ArrayList<Match>();
	private VariableName name;
	private Type type;
	
	ContainAnalysis(VariableName name, Type type) {
		this.name = name;
		this.type = type;
		match();
	}
	

	public Word[] getRemainsAfterRemovingType() {
		return remains.toArray(new Word[remains.size()]);
	}
	public Match[] getMatchedWords() {
		return matched.toArray(new Match[matched.size()]);
	}
	
	private void match() {
		
		diff_match_patch dmp = new diff_match_patch();
		for(Word nameWord : name.getWords()) {
			
			
			int longestMatch =0;
			int longestWordMatchSize = 0;
			int matchesInBeginningOfWords = 0;
			for(Word typeWord : type.getWords()) {
				LinkedList<Diff> overlaps = dmp.diff_main(nameWord.toString(), typeWord.toString());
				int wordMatchSize = 0;
				boolean matchesBeginningOfThisWord = false;
				
				for(Diff part : overlaps) {
					if (part.operation == Operation.EQUAL) {
						wordMatchSize += part.text.length();
						if (part.text.length() > longestMatch)
							longestMatch =part.text.length();
						
						if (typeWord.toString().startsWith(part.text)) {
							matchesInBeginningOfWords+= part.text.length();
							matchesBeginningOfThisWord = true;
						}
					} 
				}
				
				if (wordMatchSize > longestWordMatchSize && matchesBeginningOfThisWord)
					longestWordMatchSize = wordMatchSize;
			}
			
			boolean isAccronym = matchesInBeginningOfWords >= nameWord.toString().length() || 
								 nameIsFirstLetterAbbriviationOfType();
			boolean matchInOneTypeWord = longestWordMatchSize >= 3 && longestMatch > 2;
			boolean abbriviationIsEntireName = longestWordMatchSize == nameWord.toString().length();
			boolean abbriviationHasVokalInMiddle = nameWord.hasVokalInMiddle();
			
			if (matchInOneTypeWord || 
					isAccronym || 
					(abbriviationIsEntireName && !abbriviationHasVokalInMiddle)) {
				matched.add(new Match(nameWord, matchInOneTypeWord, isAccronym, abbriviationIsEntireName));
			} else {
				
				if (nameWord.toString().equalsIgnoreCase("_") == false)
					remains.add(nameWord);
			}
			
		}
				
		
	}
	
	boolean nameIsFirstLetterAbbriviationOfType() {
		String lowerTypeWord = "";
		
		Word[] words = type.getWords();
		for(int i = 0; i < words.length; i++) {
			try {
				lowerTypeWord = "" + words[i].toString().charAt(0);
				
				if(name.equalsIgnorePluralis(new Identifier(lowerTypeWord))) {
					return true;
				}
				
				for(int j = i+1; j < words.length; j++) {
					lowerTypeWord += words[j].toString().charAt(0);
					if(name.equalsIgnorePluralis(new Identifier(lowerTypeWord))) {
						return true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(" " + name + " " + type + " "+ i + " " + words[i]);
				
				for(Word w : words) {
					System.out.println("w: " + w.toString() + " ");
				}
			}
		}
		return false;
	}
	
	/*public String toString() {
	String ret = getType() +" " + name + "\n";
	if (equalsIgnorePluralis()) {
		 	ret += "equalsIgnorePluralis\n";
   	} else if (isMatchingOneWordOfType()) {
   		ret += "Name is matching one word of Type\n";
   	} else if (nameIsShortForType()) {
   		ret += "Name is short for Type\n";
   	} else if (nameIsFirstLetterAbbriviationOfType()) {
   		ret += "Name is abbriviation fo first letters for Type\n";
   	}
	if (type.isNumberType()) {
		
		if (nameIndicatesNumberType()) {
			ret += "GOOD! Type is number and name indicates that\n";	
		} else {
			ret += "BAD! Type is number and name does not indicate that\n";
		}
	} else {
		
	}

	if (isArrayType()) {
		if (name.IndicatesPluralis())
			ret += "GOOD! Name indicates explicite type pluralis\n";
		else
			ret += "BAD! Name DOES NOT show explicite type Pluralis\n";
   	} else {
   		if (typeIndicatesPluralis()) {
   			if (nameIndicatesPluralis())
				ret += "OK, Name shows implicit type pluralis\n";
			else
				ret += "BAD! Name DOES NOT show implicit type Pluralis\n";
   		} else {
   			if (nameIndicatesPluralis())
				ret += "BAD! Name shows implicit type Pluralis\n";
			else
				ret += "GOOD, Name shows implicit type Singularis\n";
   		}
   	}
	
	
	return ret;
	return "";
	}*/
	
	public boolean nameIsShortForType() {
		diff_match_patch dmp = new diff_match_patch();
		LinkedList<Diff> overlaps = dmp.diff_main(name.toString().toLowerCase(), type.toString().toLowerCase());
		
		int matchingParts = 0;
		for(Diff part : overlaps) {
			if (part.operation == Operation.EQUAL) {
				if (part.text.length() > 1)
					return true;
				else
					matchingParts++;
			}
		}
		if (matchingParts == name.toString().toLowerCase().length()) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean isMatchingOneWordOfType() {
		
		
		for(Word nameWord : name.getWords()) {
			for(Word typeWord : type.getWords()) {
				if (typeWord.equalsIgnorePluralis(nameWord)) {
					return true;
				}
			}
			if(type.fullName.containsIgnorePluralis(nameWord))
				return true;
		}
		
		
		
		return false;
	}


	public boolean isFullyMatched() {
		
		if (isPartlyMatched()) {
			String letters = "";
			for (Word w : remains ) {
				letters += w.toString();
			}
			
			return letters.length() < 2;
			//return remains.size() == 0;
		} 
		return false;
	}


	public boolean isPartlyMatched() {
		return matched.size() > 0;
	}
}
