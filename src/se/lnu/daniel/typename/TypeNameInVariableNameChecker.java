package se.lnu.daniel.typename;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeNameInVariableNameChecker {
	class Matches {
		int fullyMatched = 0;
		int partlyMatched = 0;
		int unmatched = 0;
		
		void match(NameTypeMatch match) {
			if (match.isFullMatch()) {
				fullyMatched++;
			} else if (match.isPartMatch()) {
				partlyMatched++;
			} else {
				unmatched++;
			}
		}

		public void show(String name) {
			int total = fullyMatched + partlyMatched + unmatched;
			System.out.printf("%s in Java, fully matched: (%d)%d%% Part matched: (%d)%d%% unmatched: (%d)%d%%\n",
					name,
					fullyMatched,
					(int)((100.0f * fullyMatched) / total), 
					partlyMatched,
					(int)((100.0f * partlyMatched) / total), 
					unmatched,
					(int)((100.0f * unmatched) / total ));
		}
	}
	
	public TypeNameInVariableNameChecker() throws IOException {
		String file = "wordpress-3.8.1"; 
		System.out.println("reading file...");
		CSVSaver loader = new CSVSaver(new File("data/"+file+".csv"));
		
		Map<String, Object> files = new HashMap<String, Object>();
		
		
		List<NameTypePair> declarations = loader.load();
		Matches primitive = new Matches();
		Matches nonPrimitive = new Matches();
		Matches locals = new Matches();
		Matches fields = new Matches();
		Matches parameters = new Matches();
		
		
		
		System.out.println("saving analysis to file...");
		File result = new File("data/"+file+"Analysed.csv");
		CSVSaver saver= new CSVSaver(result);
		saver.saveExtra(declarations);
		
		
		System.out.println("starting analysis...");
		int progress = 0;
		int parsedPercent = 0;
		
		
		
		NameMatcher nm = new NameMatcher(declarations);
		
		List<NameTypeMatch> matches = nm.getBestMatches();
		
		for (NameTypeMatch match : matches) {
			//ContainAnalysis ca = new ContainAnalysis(ntp.getName(), ntp.getType());
			NameTypePair ntp = match.getPair();
			
			if (ntp.getType().isPrimitive() == false) {
				nonPrimitive.match(match);
				if (ntp.getScope() == NameTypePair.Origin.Parameter) {
					parameters.match(match);
				} else if (ntp.getScope() == NameTypePair.Origin.Field) {
					fields.match(match);
				} else {
					locals.match(match);
				}
			} else {
				primitive.match(match);
			}
			
			
			
			files.put(ntp.getSourceFile().toString(), ntp);
			
			int percentParced = (100 * progress) / declarations.size();
			if (parsedPercent < percentParced) {
				parsedPercent = percentParced;
				System.out.println("" + percentParced + " %");
			}
			progress++;
			
		}
		
		primitive.show("Primitives ");
		nonPrimitive.show("Non-primitives ");
		parameters.show("Non-primitive parameters ");
		fields.show("Non-primitive fields ");
		locals.show("Non-primitive locals ");
		
		
		System.out.println("Based on " + declarations.size() + " declarations in " + files.size() + " files");
		
		
		
		
	}

	
	public static void main(String args[]) throws IOException {
		new TypeNameInVariableNameChecker();
		
		
	}
}
