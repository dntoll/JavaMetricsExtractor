package se.lnu.daniel.typename;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import se.lnu.daniel.typename.NameTypePair.Origin;

public class CSVSaver {
	
	char delimiter = ';';
	private File result;
	public CSVSaver(File result) {
		this.result = result;
	}
	
	public void save(List<NameTypePair> declarations) {
		try {
			result.createNewFile();
		
			FileWriter fw = new FileWriter(result);
			
			writeHeader(fw, delimiter);
			writePairs(fw, delimiter, declarations);
			fw.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveExtra(List<NameTypePair> declarations) {
		try {
			result.createNewFile();
		
			FileWriter fw = new FileWriter(result);
			
			writeExtraHeader(fw, delimiter);
			writeExtraPairs(fw, delimiter, declarations);
			fw.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<NameTypePair> load() throws IOException {
		List<NameTypePair> ret = new ArrayList<NameTypePair>();
		
		
		BufferedReader br = new BufferedReader(new FileReader(result));
		
		br.readLine(); //skip header
		
		String line;
		while((line = br.readLine()) != null) {
			String[] parts = line.split(";");
			String filename = parts[0];
			String scope = parts[1];
			String type = parts[2];
			String name = parts[3];
			
			NameTypePair.Origin origin = Origin.valueOf(scope);
			
			ret.add(new NameTypePair(	new VariableName(name), 
								new Type(type), 
								origin, 
								new File(filename)));
								
		}
		
		return ret;
		
		
		
	}
	
	private static void writeHeader(FileWriter fw, char delimiter)	throws IOException {
		fw.write("FileName" + delimiter + 
		 "scope" + delimiter +
		 "Type" + delimiter + 
		 "Name" + delimiter + "\n");
	}
	
	private static void writeExtraHeader(FileWriter fw, char delimiter)	throws IOException {
		fw.write("FileName" + delimiter + 
		 "scope" + delimiter +
		 "Type" + delimiter + 
		 "Name" + delimiter +
		 "Match" + delimiter +"\n");
	}
	
	
	
	private static void writePairs(FileWriter fw, char delimiter, List<NameTypePair> nameTypesInFile) throws IOException {
		for (NameTypePair pair : nameTypesInFile) {
			fw.write(String.format("%s" + delimiter + 
					   "%s" + delimiter +
					   "%s" + delimiter + 
					   "%s" + "\n", 
					   pair.getSourceFile().getName(),
					   pair.getScope().toString(),
					   pair.getType(), 
					   pair.getName()));
		}
	}

	public void writeExtraPairs(FileWriter fw, char delimiter, List<NameTypePair> nameTypesInFile) throws IOException {
		
		NameMatcher nm = new NameMatcher(nameTypesInFile);
		
		List<NameTypeMatch> matches = nm.getBestMatches();
		
		for (NameTypeMatch match : matches) {
			NameTypePair pair = match.getPair();
			
			String matchString = "not matched";
			if (match.isFullMatch()) {
				matchString = "fully matched";
			} else if (match.isPartMatch()) {
				matchString = "partly matched";
			}
				
			
			fw.write(String.format("%s" + delimiter + 
					   "%s" + delimiter +
					   "%s" + delimiter + 
					   "%s" + delimiter + "%s" + "\n", 
					   pair.getSourceFile().getName(),
					   pair.getScope().toString(),
					   pair.getType(), 
					   pair.getName(),
					   matchString
					  ));
		}
	}

	


}
