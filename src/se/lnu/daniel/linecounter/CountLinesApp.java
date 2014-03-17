package se.lnu.daniel.linecounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;


import se.lnu.daniel.application.JavaFile;

public class CountLinesApp {
	public static void main(String args[]) throws Exception {
		File folder = new File(args[0]);
		
		if (!folder.exists()) {
			throw new FileNotFoundException(folder.getAbsolutePath());
		}
		if (folder.isDirectory() == false) {
			throw new Exception("First argument must be folder path");
		}
		File result = new File("result.csv");
		
		result.createNewFile();
		
		FileWriter fw = new FileWriter(result);
		
		char delimiter = '\t';	
		fw.write("FileName" + delimiter + 
				 "LinesWithCode" + delimiter + 
				 "LinesWithComments" + delimiter + 
				 "EmptyLines" + delimiter +
				 "TotalLines" + delimiter +
				 "Characters\n");
		
		File[] files = folder.listFiles();
		
		for (File f : files) {
			if (f.getName().endsWith(".java")) {
			
				JavaFile file = JavaFile.getFromFile(f);
				LineCounter counter = new LineCounter(file.getContent());
				
				fw.write(String.format("%s" + delimiter + 
									   "%d" + delimiter + 
									   "%d" + delimiter + 
									   "%d" + delimiter + 
									   "%d" + delimiter + 
									   "%d\n", 
										f.getName(), 
										counter.getLines(), 
										counter.getComments(), 
										counter.getEmptyLines(), 
										counter.getTotalLines(), 
										counter.getNumberOfCharacters()));
			}
		}
		fw.close();
	}
}
