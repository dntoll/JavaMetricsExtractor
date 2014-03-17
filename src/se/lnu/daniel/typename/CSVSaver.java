package se.lnu.daniel.typename;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVSaver {
	
	char delimiter = ';';
	private File result;
	CSVSaver(File result) {
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
	
	private static void writeHeader(FileWriter fw, char delimiter)	throws IOException {
		fw.write("FileName" + delimiter + 
		 "scope" + delimiter +
		 "Type" + delimiter + 
		 "Name" + delimiter +
		 "nameWOType" + delimiter +
		 "matchedType" + delimiter +
		 "equalsIgnorePluralis" + delimiter + 
		 "isMatchingOneWordOfType" + delimiter + 
		 "nameIsShortForType" + delimiter + 
		 "nameIsFirstLetterAbbriviationOfType" + delimiter + 
		 "nameIndicateNumber" + delimiter +
		 "nameIsPluralis" + delimiter +
		 "nameIndicatesCollection" + delimiter +
		 "typeCanBeCollection" + delimiter +
		 "typeIsArray" + delimiter + 
		 "\n");
	}
	
	
	
	private static void writePairs(FileWriter fw, char delimiter, List<NameTypePair> nameTypesInFile) throws IOException {
		for (NameTypePair pair : nameTypesInFile) {
			String nameWithoutType = "";
			String typeMatched = "";
			
			Word[] names = pair.getRemainsAfterRemovingType();
			for (Word name : names) {
				nameWithoutType += "[" +name.toString() + "]";
			}
			Match[] matches = pair.getMatchedWords();
			for (Match name : matches) {
				typeMatched += "[" +name + "]";
			}
			
			fw.write(String.format("%s" + delimiter + 
					   "%s" + delimiter +
					   "%s" + delimiter + 
					   "%s" + delimiter +
					   "%s" + delimiter +
					   "%s" + delimiter +
					   "%b" + delimiter +
					   "%b" + delimiter +
					   "%b" + delimiter +
					   "%b" + delimiter +
					   "%b" + delimiter +
					   "%b" + delimiter +
					   "%b" + delimiter +
					   "%b" + delimiter +
					   "%b" + delimiter +
					   "\n", 
					   pair.getSourceFile().getName(),
					   pair.getScope(),
					   pair.getType(), 
					   pair.getName(),
					   nameWithoutType,
					   typeMatched,
					   pair.equalsIgnorePluralis(),
					   pair.isMatchingOneWordOfType(),
					   pair.nameIsShortForType(),
					   pair.nameIsFirstLetterAbbriviationOfType(),
					   pair.nameIndicatesNumberType(),
					   pair.nameIsPluralis(),
					   pair.nameIndicatesCollection(),
					   pair.typeCanBeCollection(),
					   pair.typeIsArray()
					   ));
		}
	}


}