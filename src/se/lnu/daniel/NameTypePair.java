package se.lnu.daniel;

public class NameTypePair {
	private String[] nameWords;
	private String name;
	private String type;
	private String[] typeWords;

	public NameTypePair(String id, String t) {
		this.name = id.replace("[]", "");
		this.type = t.replace("[]", "");
		
		nameWords = name.split(splitCamelIntoWords);
		typeWords = type.split(splitCamelIntoWords);
	}
	
	
	public boolean isMatching() {
		
		return name.equalsIgnoreCase(type);
	}
	
	public String toString() {
		return type + " " + name;
	}

	//http://stackoverflow.com/questions/7593969/regex-to-split-camelcase-or-titlecase-advanced
	String splitCamelIntoWords = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";
	public boolean isMatchingOneWordOfType() {
		
		for(String typeWord : typeWords) {
			if (name.equalsIgnoreCase(typeWord)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean typeIsMatchingOneWordOfName() {
		for(String nameWord : nameWords) {
			if (type.equalsIgnoreCase(nameWord)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean nameIsShortForType() {
		for(String typeWord : typeWords) {
			String lowerTypeWord = typeWord.toLowerCase();
			if (lowerTypeWord.startsWith(name)) {
				return true;
			}
		}
		return false;
	}
}
