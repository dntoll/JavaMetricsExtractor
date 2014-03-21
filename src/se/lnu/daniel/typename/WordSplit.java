package se.lnu.daniel.typename;

public class WordSplit {

	public WordSplit(String[] input) {
		parts = new Word[input.length];
		int i = 0;
		for (String part : input) {
			parts[i] = new Word(part);
			
			i++;
		}
	}

	public Word[] parts;

}
