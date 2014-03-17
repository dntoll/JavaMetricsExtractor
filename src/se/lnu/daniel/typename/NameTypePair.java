package se.lnu.daniel.typename;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;
import name.fraser.neil.plaintext.diff_match_patch.Operation;
import se.lnu.daniel.typename.NameTypePair.Origin;

public class NameTypePair {
	public enum Origin {
		Any,
		Field, Parameter, Local
	}
	private VariableName name;
	private Type type;
	
	ArrayList<Word> remains = new ArrayList<Word>();
	ArrayList<Match> matched = new ArrayList<Match>();
	private Origin scope;
	private File sourceFile;
	
	public NameTypePair(VariableName id, Type t, Origin origin, File sourceFile) {
		this.name = id;
		this.type = t;
		this.scope = origin;
		this.sourceFile = sourceFile;
		match();
		
	}
	
	public NameTypePair(String id, String type, Origin origin ) {
		this(new VariableName(id), new Type(type), origin, null);
	}
	
	

	public String getScope() {
		return scope.name();
	}

	public Type getType() {
		//String ret = type + (type.isArrayType() ? "[]":"");
		return type;
	}
	
	public VariableName getName() {
		return name;
	}
	
	
	public boolean equalsIgnorePluralis() {
		
		return type.equalsIgnorePluralis(name);
	}
	
	public String toString() {
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
	/*	
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
		*/
		
		return ret;
	}


	//http://stackoverflow.com/questions/7593969/regex-to-split-camelcase-or-titlecase-advanced
	
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
			
			boolean isAccronym = matchesInBeginningOfWords >= nameWord.toString().length() || nameIsFirstLetterAbbriviationOfType();
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
	

	public boolean nameIsFirstLetterAbbriviationOfType() {
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
				System.err.println(e.toString());
			}
		}
		return false;
	}


	public boolean hasHungarianNotationForType() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean hasClassMemberNotation() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean typeCanBeCollection() {
		if (type.isArrayType()) {
			return true;
		}
		
		
		
		return type.indicateCollection();
	}

	public boolean nameIndicatesCollection() {
		//int nu
		/*if (!type.isArrayType() && type.isNumberType() && ) {
			if (!type.isArrayType())
				return false;
			if (nameIndicatesNumberType())
				return false;
		} else {
			
		}
		*/
		
		
		return name.indicateCollection();
	}


	


	public boolean isConditionVariable() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	

	
	public boolean nameIndicatesNumberType() {
		
		final String[] wordsIndicatesNumber = { "count", 
												"num",
												"size",
												"number",
												"length"};
		
		for (String word : wordsIndicatesNumber) {
			if ( name.containsIgnoreCase(word) )
				return true;
		}
		return false;
	}

	public boolean typeIsArray() {
		return type.isArrayType();
	}

	public boolean nameIsPluralis() {
		return name.isPluralis();
	}

	public File getSourceFile() {
		return sourceFile;
	}

	

	


	
}
