package se.lnu.daniel.typename;

import java.util.LinkedHashSet;

public class Identifier {

	private boolean isArray;
	String asWrittenInCode; //this contains [] and other modifiers
	
	protected Word fullName; //this is without [] 
	//protected Word[] words;
	
	

	public Identifier(String fromCode) {
		asWrittenInCode = fromCode;
		if (fromCode.endsWith("[]")) {
			isArray = true;
		}
		fullName = new Word(fromCode);
		//words = createFromIdentifier(this);
	}
	
	final static String splitCamelIntoWords = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";
	
	WordSplit[] getSplits() {
		WordSplit fullWord = new WordSplit(new String[] {fullName.text});
		
		String camelNotationWords[] = fullName.original.split(splitCamelIntoWords);
		String underscoreWords[] = fullName.text.split("_");
		underscoreWords = removeEmpty(underscoreWords);
		
		String numberWords[] = fullName.text.split("(?<=\\d)(?=\\D)|(?=\\d)(?<=\\D)");
		
		
		This should be done recursively somehow...
		if (camelNotationWords.length > 1) {
			WordSplit camelSplit = new WordSplit(camelNotationWords);
			return new WordSplit[] { fullWord, camelSplit };
		} 
		if (underscoreWords.length > 1) {
			WordSplit underScoreSplit= new WordSplit(underscoreWords);
			return new WordSplit[] { fullWord, underScoreSplit };
		}
			
			
		}
		
		return new WordSplit[] { fullWord };
	}
	
	private String[] removeEmpty(String[] underscoreWords) {
		int num = 0; 
		for (String word : underscoreWords) {
			if (word.equals("") == false) {
				num++;
			}
		}
		String[] ret = new String[num];
		int index = 0;
		for (String word : underscoreWords) {
			if (word.equals("") == false) {
				ret[index] = word;
				index++;
			}
		}
		return ret;
		
	}

	static Word[] createFromIdentifier(Identifier identifier) {
		
		
		String camelNotationWords[] = identifier.fullName.original.split(splitCamelIntoWords);
		//String underscoreWords[] = identifier.fullName.original.split("_");
		
		
		String words[] = camelNotationWords;
		
		LinkedHashSet<Word> ret = new LinkedHashSet<Word>(); 
		
		for(String stringToSplit : words) {
			//Strings...
			String[] tokens = stringToSplit .split("(?<=\\d)(?=\\D)|(?=\\d)(?<=\\D)");
			
			for (String word : tokens) {
			
			
				if (word.equals("_")) {
					ret.add(new Word("_"));
				} else if (word.startsWith("_")) {
					ret.add(new Word("_"));
					ret.add(new Word(word.replace("_", "")));
				} else if (word.endsWith("_")) {
					ret.add(new Word(word.replace("_", "")));
					ret.add(new Word("_"));
				} else if (word.contains("_")) {
					String underscoreWords[] = word.split("_");
					for(int i = 0; i <  underscoreWords.length; i++) {
						ret.add(new Word(underscoreWords[i]));
						if (i+1 < underscoreWords.length)
							ret.add(new Word("_"));
					}
				} else {
					ret.add(new Word(word));
				}
			}
		}
		
		//ret.add(identifier.fullName);
		
		//Remove blank words
		LinkedHashSet<Word> noBlanks = new LinkedHashSet<Word>(); 
		for (Word r : ret) {
			if (r.text.equals("") == false) {
				noBlanks.add(r);
			}
		}
		
		return noBlanks.toArray(new Word[noBlanks.size()]);
	}

	public boolean isArrayType() {
		return isArray;
	}
	
	public String toString() {
		return asWrittenInCode;
	}

	/*public Word[] getWords() {
		return words;
	}*/
	
	public boolean isPluralis() {
		return fullName.isPluralis();
	}
	
	boolean indicateCollection() {
		final String[] wordsIndicatePluralis = {"array", 
												"buffer",
												"buff", 
												"list",
												"map",
												"collection",
												"collector",
												"set"};
		
		for (String word : wordsIndicatePluralis) {
			if ( fullName.equalsIgnorePluralis(new Word(word)) )
				return true;
		}
										 
		return false;
	}
	
	public boolean containsIgnoreCase(String word) {
		return fullName.containsIgnoreCase(word);
	}
	
	
	public boolean equalsIgnorePluralis(Identifier other)  {
		return fullName.equalsIgnorePluralis(other.fullName);
	}
	
	
	
}
