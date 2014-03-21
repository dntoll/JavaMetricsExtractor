package se.lnu.daniel.typename.parse;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.TokenMgrError;
import japa.parser.ast.CompilationUnit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import se.lnu.daniel.application.JavaFile;
import se.lnu.daniel.typename.CSVSaver;
import se.lnu.daniel.typename.NameTypePair;


public class JavaFilesParser {

	List<NameTypePair> declarations = new ArrayList<NameTypePair>();
	
	HashMap<String, Integer> fileOccurances = new HashMap<String, Integer>();
	
	public static void main(String[] args) throws IOException {
		File folder = new File(args[0]);
		
		if (!folder.exists()) {
			throw new FileNotFoundException(folder.getAbsolutePath());
		}
		if (folder.isDirectory() == false) {
			throw new FileNotFoundException("First argument must be folder path");
		}
		
		JavaFilesParser jfp = new JavaFilesParser();
		
		jfp.parseFolder(folder);
		
		File result = new File("data/extractedVariables.csv");
		CSVSaver saver= new CSVSaver(result);
		
		saver.save(jfp.declarations);
		/*
		TypeNameOccuranceCounter counter = new TypeNameOccuranceCounter(jfp.declarations);
		
		List<TypeNameOccurance> nOccurances = counter.countNameOccurances();
		List<TypeNameOccurance> tOccurances = counter.countTypeOccurances();
		
		PrintStream typeNameAssociations = new PrintStream(new File("typeNameAssociations.txt"));
		
		typeNameAssociations.println("###########################");
		typeNameAssociations.println("####     Types   ##########");
		typeNameAssociations.println("###########################");
		
		for (TypeNameOccurance occ : tOccurances) {
			occ.prettyPrint(typeNameAssociations);
		}
		
		typeNameAssociations.println("###########################");
		typeNameAssociations.println("####     Names   ##########");
		typeNameAssociations.println("###########################");
		
		for (TypeNameOccurance occ : nOccurances) {
			occ.prettyPrint(typeNameAssociations);
		}
		
		typeNameAssociations.close();*/
	}
	
	
		
	public void parseFolder(File folder) throws IOException {
		File[] files = folder.listFiles();
		int numFilesParsedOK = 0;
		
		
		int parsedFilesPercent = 0;
		
		int filesParsed = 0;
		for (File file : files) {
			if (file.getName().endsWith(".java")) {
				numFilesParsedOK = parseFile(numFilesParsedOK, file); 
				
				filesParsed++;
				int percentParced = (100 * filesParsed) / files.length;
				if (parsedFilesPercent < percentParced) {
					parsedFilesPercent = percentParced;
					System.out.println("" + percentParced + " %");
				}
			}
		}
		
		/*List<Integer> fileCounts = new ArrayList< Integer >(fileOccurances.values());
		Collections.sort(fileCounts);
		int filesWithDuplicates = 0;
		int uniqueFiles = fileCounts.size();
		for (Integer fileCount : fileCounts) {
			if (fileCount.intValue() > 1) {
				System.out.println("Duplicates " + fileCount.intValue());
				filesWithDuplicates++;
			}
			
		}
		System.out.println("Files extracted: " +files.length +  " FilesParsedWOErrors: " +numFilesParsedOK+" Unique Files: " + uniqueFiles+ " Files with duplicates: " + filesWithDuplicates);*/
	}



	private int parseFile(int numFilesParsedOK, File file) {
		try {
			JavaFile jf = JavaFile.getFromFile(file);
			List<NameTypePair> nameTypesInFile= getNamePairsFromFile(jf.getContentStream(), file);
			numFilesParsedOK++;
			
			String hashMapKey = jf.getMD5Hash();
			
			if (fileOccurances.containsKey(hashMapKey)) {
				Integer duplicateCount = fileOccurances.get(hashMapKey);
				fileOccurances.put(hashMapKey, new Integer(duplicateCount.intValue() + 1));
			} else {
				declarations.addAll(nameTypesInFile);
				fileOccurances.put(hashMapKey, new Integer(1));
			}
		} catch (ParseException e) {
			System.err.println(file.getName());
		} catch (IOException e) {
			System.err.println(file.getName());
		} catch (TokenMgrError e) {
			System.err.println(file.getName());
		}
		return numFilesParsedOK;
	}

	private List<NameTypePair> getNamePairsFromFile(InputStream in, File current) throws ParseException {
        try
        {
       	 CompilationUnit cu = JavaParser.parse(in);
            MethodVisitor visitor = new MethodVisitor(current);
            visitor.visit(cu, null);

            return visitor.declarations;
        }
        catch(ParseException x)
        {
             throw x;
        }
        
	}

	
	
}
