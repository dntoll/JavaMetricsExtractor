package se.lnu.daniel.typename;

public class Identifier {

	private boolean isArray;
	String asWrittenInCode; //this contains [] and other modifiers
	
	protected Word fullName; //this is without [] 
	protected Word[] words;
	
	

	public Identifier(String fromCode) {
		asWrittenInCode = fromCode;
		if (fromCode.endsWith("[]")) {
			isArray = true;
		}
		fullName = new Word(fromCode);
		words = Word.createFromIdentifier(this);
	}

	public boolean isArrayType() {
		return isArray;
	}
	
	public String toString() {
		return asWrittenInCode;
	}

	public Word[] getWords() {
		return words;
	}
	
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
