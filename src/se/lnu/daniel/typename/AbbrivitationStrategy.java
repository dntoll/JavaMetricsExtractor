package se.lnu.daniel.typename;

import java.util.LinkedList;

import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;
import name.fraser.neil.plaintext.diff_match_patch.Operation;

public class AbbrivitationStrategy extends AbstractMatchingStrategy {

	@Override
	public Match match(WordSplit nameSplit, WordSplit typeSplit) {
		Match ret = new Match(nameSplit);
		
		
		diff_match_patch dmp = new diff_match_patch();
		
		for(Word nameWord : nameSplit.parts) {
			int longestWordMatchSize = 0;
			for(Word typeWord : typeSplit.parts) {
				LinkedList<Diff> overlaps = dmp.diff_main(nameWord.toString(), typeWord.toString());
				int wordMatchSize = 0;
				boolean matchesBeginningOfThisWord = false;
				boolean doesNotMatchEntireName = false;
				boolean typeIsFullyMatchedInName = false;
				
				for(Diff part : overlaps) {
					if (part.operation == Operation.EQUAL) {
						wordMatchSize += part.text.length();
					
						if (typeWord.toString().startsWith(part.text)) {
							matchesBeginningOfThisWord = true;
						}
						
						if (typeWord.toString().equalsIgnoreCase(part.text)) {
							typeIsFullyMatchedInName = true;
						}
					}
					if (part.operation == Operation.DELETE) {
						doesNotMatchEntireName = true;
					}
				}
				
				if (wordMatchSize > longestWordMatchSize && matchesBeginningOfThisWord)
					longestWordMatchSize = wordMatchSize;
				
				boolean matchInOneTypeWord = longestWordMatchSize >= 2;
				boolean abbriviationIsEntireName = longestWordMatchSize == nameWord.toString().length();
				if ((!doesNotMatchEntireName && matchInOneTypeWord) || 
					(abbriviationIsEntireName && !nameWord.hasVokalInMiddle())) {
					ret.addMatch(nameWord, typeWord);
				} 
				
				if (typeIsFullyMatchedInName) {
					ret.addPartlyMatch(nameWord, typeWord);
				}
			}
			
		}
		return ret;
	}

	
	
}
