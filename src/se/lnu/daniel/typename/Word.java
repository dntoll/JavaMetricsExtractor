package se.lnu.daniel.typename;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class Word  implements Comparable<Word> {

	static final String splitCamelIntoWords = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";
	private String text;
	private String original;
	
	
	public Word(String word) {
		original = word;
		
		text = word.toLowerCase();
		text = text.trim();
		text = text.replace("[]", "");
		text = text.replace("<", "");
		text = text.replace(">", "");
		text = text.replace(",", "");
		
	}

	public static Word[] createFromIdentifier(Identifier identifier) {
		
		
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
		
		ret.add(identifier.fullName);
		
		return ret.toArray(new Word[ret.size()]);
	}
	
	public String toString() {
		return text;
	}
	
	public int hashCode(){
        return text.hashCode();
    }

	public boolean containsIgnoreCase(String word) {
		String lower = word.toLowerCase();
		return text.contains(lower);
	}

	public boolean equals(String numberType) {
		return text.equals(numberType);
	}
	
	public boolean equals(Object other) {
		return text.equals(((Word)other).text);
	}
	
	public boolean equalsIgnorePluralis(Word other) {
		String noPluralText =  removePluralis();
		String otherNoPlural =  other.removePluralis();
		
		if (text.equalsIgnoreCase(other.text)) {
			return true;
		} else if (noPluralText.equalsIgnoreCase(other.text)) {
			return true;
		} else if (text.equalsIgnoreCase(otherNoPlural)) {
			return true;
		} else if (noPluralText.equalsIgnoreCase(otherNoPlural)) {
			return true;
		}
		
		return false;
	}
	
	public boolean containsIgnorePluralis(Word other) {
		String noPluralText =  removePluralis();
		String otherNoPlural =  other.removePluralis();
		
		if (text.contains(other.text)) {
			return true;
		} else if (noPluralText.contains(other.text)) {
			return true;
		} else if (text.contains(otherNoPlural)) {
			return true;
		} else if (noPluralText.contains(otherNoPlural)) {
			return true;
		}
		
		return false;
	}
	
	private String removePluralis() {
		
		//http://en.wikipedia.org/wiki/English_plurals
		
		String compName = text;

		if (text.length() > 3 && text.endsWith("ies")) {
			compName = text.substring(0, text.length()-3) + "y";
		} else if (text.length() > 3 && text.endsWith("oes")) {
			compName = text.substring(0, text.length()-2);
		} else if (text.length() > 1 && text.endsWith("s")) {
			compName = text.substring(0, text.length()-1);
		}

		if (compName.length() > 0)
			return compName;
		return text;
	}

	public boolean isPluralis() {
		String noPluralText =  removePluralis();
		return noPluralText.equalsIgnoreCase(text) == false; 
	}

	public boolean hasVokalInMiddle() {
		
		if (text.length() == 1)
			return false;
		
		byte[] textBytes = text.getBytes();
		String vowels = "aeiouyåäö";
		byte[] vowelBytes = vowels.getBytes();
		for (int i = 1; i< text.length(); i++) {
			for (int v = 1; v < vowels.length(); v++) {
				if (textBytes[i] == vowelBytes[v])
					return true;
			}
		}
			
		return false;
	}

	public String getWithoutPluralis() {
		return removePluralis();
	}

	@Override
	public int compareTo(Word other) {
		return text.compareTo(other.text);
	}
	

}
