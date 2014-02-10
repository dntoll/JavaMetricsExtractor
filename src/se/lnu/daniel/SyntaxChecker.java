package se.lnu.daniel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import se.lnu.daniel.application.JavaFile;

public class SyntaxChecker {
	
	/**
     * A file object used to represent source coming from a string.
     */
    class JavaSourceFromString extends SimpleJavaFileObject {
        /**
         * The source code of this "file".
         */
        final String code;

        /**
         * Constructs a new JavaSourceFromString.
         * @param name the name of the compilation unit represented by this file object
         * @param code the source code for the compilation unit represented by this file object
         */
        public JavaSourceFromString(JavaFile javaFile) {
        	super(URI.create("string:///" + javaFile.getURL().substring(javaFile.getURL().lastIndexOf('/')+1 )),
	                  Kind.SOURCE);
			
			
			this.code = javaFile.getContent();
			
			//System.out.println(this.getName());
		}

		@Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }
    
	public static void main(String args[]) throws Exception {
		File folder = new File(args[0]);
		SyntaxChecker checker = new SyntaxChecker();
		checker.checkSyntax(folder);
		
		
	}

	private void checkSyntax(File folder) throws Exception {

		if (!folder.exists()) {
			throw new FileNotFoundException(folder.getAbsolutePath());
		}
		if (folder.isDirectory() == false) {
			throw new Exception("First argument must be folder path");
		}
		File result = new File("error.csv");
		
		result.createNewFile();
		
		FileWriter fw = new FileWriter(result);
		
		fw.write("FileName\tOK\tCount\n");
		
		File[] files = folder.listFiles();
		
		for (File f : files) {
			if (f.getName().endsWith(".java")) {
			
				JavaFile javaFile = JavaFile.getFromFile(f);
				
				
				
				JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
				
				if (compiler == null)
					throw new Exception("you must use the correct JDK... not eclipse own");
				StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
				
				
				
				ArrayList<JavaFileObject> compilationUnits = new ArrayList<JavaFileObject>();
				
				compilationUnits.add(new JavaSourceFromString(javaFile) );
		                //fileManager.getJavaFileObjectsFromStrings(Arrays.asList(file));

		        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		        compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits).call();

		        List<String> messages = new ArrayList<String>();
		        Formatter formatter = new Formatter();
		        for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
		        	if (diagnostic.getMessage(Locale.ROOT).contains("does not exist")) {
		        		
		        	} else if (diagnostic.getMessage(Locale.ROOT).contains("cannot find symbol")) {
		        		
		        	} else if (diagnostic.getMessage(Locale.ROOT).contains("method does not override or implement a method from a supertype")) {
		        		
		        	} else if (diagnostic.getKind() == javax.tools.Diagnostic.Kind.NOTE) {
		        		
		        	} else {
		        		String error = diagnostic.getKind() + ":\t Line [" + diagnostic.getLineNumber() + "] \t Position [" + diagnostic.getPosition() + "]\t" + diagnostic.getMessage(Locale.ROOT) + "\n";
		        		messages.add(error);
		        		
		        		System.err.println(javaFile.getURL());
		        		System.err.println(error);
		        	}
		        }
		        
		        if (messages.size() > 0) {
		        	fw.write(f.getName() + "\tFail\t"+messages.size()+"\t");
		        	for(String message : messages) {
		        		message = message.replace('\n', ',');
		        		message = message.replace('\r', ',');
		        		fw.write(message + ",");
		        	}
		        	fw.write("\n");
		        } else {
		        	
		        	fw.write(f.getName() + "\tOK\t"+messages.size()+"\n");
		        }

		       // System.exit(-1);
			}
		}
		fw.close();
	}
}
