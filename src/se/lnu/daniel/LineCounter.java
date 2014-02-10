package se.lnu.daniel;

public class LineCounter {

	private int empty = 0;
	private int comments = 0;
	private int lines = 0;
	private int characters = 0;
	private int numLines = 0;

	/*
	 * Temporary variables
	 */
	int numCharsInLine = 0;
	boolean inMultiLineComment = false;
	boolean inSingleLineComment = false;
	boolean inString = false;
	char stringType = '"';
	

	public LineCounter(String input) {
		if (input.isEmpty())
			return;
		count(input);
	}

	
	
	public int getLines() {
		return lines;
	}

	public int getComments() {
		return comments;
	}

	public int getEmptyLines() {
		return empty;
	}
	
	public int getNumberOfCharacters() {
		return characters;
	}
	
	public int getTotalLines() {
		return numLines;
	}
	
	private void count(String input) {
		char[] charBuff = new char[input.length()];
		input.getChars(0, input.length(), charBuff, 0);
		characters = input.length();
		

		for (int i = 0; i < input.length(); i++) {
			
			if (inMultiLineComment == false && inSingleLineComment == false) {
				i = countNotInComment(input, charBuff, i);
			} else {
				i = countInComment(input, charBuff, i);
			}
			

		}
	}

	private int countInComment(String input, char[] charBuff, int i) {
		if (isExitingMultilineComment(input, inMultiLineComment, charBuff, i)) {
			inMultiLineComment = false;
			countComment();
			i++;
			
			if (isLastCharacterInFile(input, i)) {
				numLines++;
			}
		} else if (isEndOfLine(input, charBuff, i)) {
			countCommentCanBeEmpty();
			inSingleLineComment = false;
			numLines++;
		} else if (Character.isWhitespace(charBuff[i]) == false) {
			numCharsInLine++;
		}
		return i;
	}

	private int countNotInComment(String input, char[] charBuff, int i) {
		if (!inString && isStartingMultilineComment(input, charBuff, i)) {
			inMultiLineComment = true;
			countLine();
			i++;
		} else if (!inString && isStartingSingleLineComment(input, charBuff, i)) {
			inSingleLineComment = true;
			countLine();
			i++;
		} else if (isEndOfLine(input, charBuff, i)) {
			countLineCanBeEmpty();
			numLines ++;
		} else if (startsString(charBuff, i)) {
			numCharsInLine++;
			inString = true;
		} else if (inString && endsString(charBuff, i)) {
			numCharsInLine++;
			inString = false;
		} else if (Character.isWhitespace(charBuff[i]) == false) {
			numCharsInLine++;
		}
		return i;
	}

	private boolean startsString(char[] charBuff, int i) {
		if (charBuff[i] == '"' || charBuff[i] == '\'') {
			stringType =charBuff[i]; 
			return true;
		}
		
		return false;
	}

	private boolean endsString(char[] charBuff, int i) {
		if (charBuff[i] == stringType) {
			return true;
		}
		return false;
	}

	private boolean isEndOfLine(String input, char[] charBuff, int i) {
		//Note that end of file is counted as end of line...
		return charBuff[i] == '\n' || isLastCharacterInFile(input, i);
	}



	private boolean isLastCharacterInFile(String input, int i) {
		return i == input.length() - 1;
	}

	private boolean isExitingMultilineComment(String input,
			boolean inMultiLineComment, char[] charBuff, int i) {
		return inMultiLineComment && i < input.length() - 1
				&& charBuff[i] == '*' && charBuff[i + 1] == '/';
	}

	private boolean isStartingSingleLineComment(String input, char[] charBuff,
			int i) {
		return i < input.length() - 1 && charBuff[i] == '/'
				&& charBuff[i + 1] == '/';
	}

	private boolean isStartingMultilineComment(String input, char[] charBuff,
			int i) {
		return i < input.length() - 1 && charBuff[i] == '/'
				&& charBuff[i + 1] == '*';
	}

	private void countCommentCanBeEmpty() {
		if (numCharsInLine > 0) {
			comments++;
			numCharsInLine = 0;
		} else {
			empty++;
		}
	}

	private void countComment() {
		if (numCharsInLine > 0) {
			comments++;
			numCharsInLine = 0;
		}
	}

	private void countLineCanBeEmpty() {
		if (numCharsInLine > 0) {
			lines++;
			numCharsInLine = 0;
		} else {
			empty++;
		}
	}

	private void countLine() {
		if (numCharsInLine > 0) {
			lines++;
			numCharsInLine = 0;
		}

	}



	

	
}
