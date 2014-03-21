package se.lnu.daniel.typename;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NameMatcher {

	
	private List<NameTypePair> declarations;

	public NameMatcher(List<NameTypePair> declarations) {
		
		this.declarations = declarations;
		
		
	}

	public static void main(String args[]) throws IOException {
		System.out.println("reading file...");
		CSVSaver loader = new CSVSaver(new File("data/extractedVariables.csv"));
		
		Map<String, Object> files = new HashMap<String, Object>();
		
		
		List<NameTypePair> declarations = loader.load();
		
		
		NameMatcher nm = new NameMatcher(declarations);
		
		List<NameTypeMatch> matches = nm.getBestMatches();
	}

	List<NameTypeMatch> getBestMatches() {
		List<NameTypeMatch> ret = new ArrayList<NameTypeMatch>();
		
		
		for (NameTypePair ntp : declarations) {
			VariableName name = ntp.getName();
			Type type = ntp.getType();
			Match bestMatch = null;
			WordSplit[] nameSplits = name.getSplits();
			WordSplit[] typeSplits = type.getSplits();
			
			for(WordSplit nameSplit : nameSplits) {
				for(WordSplit typeSplit : typeSplits) {
					MatchingStrategy ms = new MatchingStrategy();
					
					Match ntm = ms.matchFullName(nameSplit, typeSplit);
					
					if (ntm.isBetter(bestMatch)) {
						bestMatch = ntm;
					}
				}
			}
			
			ret.add(new NameTypeMatch(ntp, bestMatch));
		}
		
		return ret;
		
		
	}
}
