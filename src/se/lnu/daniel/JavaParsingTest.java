package se.lnu.daniel;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JavaParsingTest {

	int numMethods = 0;
	int numParameters = 0;
	
	
	
	List<NameTypePair> declarations = new ArrayList<NameTypePair>();
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		JavaParsingTest app = new JavaParsingTest();
		
		File file = new File("src\\se\\lnu\\daniel\\SyntaxChecker.java");
        FileInputStream in = new FileInputStream(file);
		app.parse(in);
		in.close();
         
         
         
	}
	
	public class MethodVisitor extends VoidVisitorAdapter<Object>
	{
        

		public void visit(MethodDeclaration n, Object arg)
        {
        	
        	//System.out.println(n);
        	numMethods++;
        	if (n.getParameters() != null) {
	        	numParameters = n.getParameters().size();
	        	
	        	for (Parameter p : n.getParameters()) {
	        		declarations.add(new NameTypePair(p.getId().toString(), p.getType().toString()));
	        	}
        	}
        	super.visit(n, arg);
        }
		
		@Override
		public void visit(VariableDeclarationExpr n, Object arg)
		{      
			
		    List <VariableDeclarator> myVars = n.getVars();
	        for (VariableDeclarator vars: myVars){
	        	declarations.add(new NameTypePair(vars.getId().toString(), n.getType().toString()));
            }
	        super.visit(n, arg);
		}
		
		@Override
		public void visit(FieldDeclaration n, Object arg) {
		    
		    for (VariableDeclarator vars: n.getVariables() ){
		    	declarations.add(new NameTypePair(vars.getId().toString(), n.getType().toString()));
		    }
		    
		    super.visit(n, arg);
		}
	}
	
	private void parse(FileInputStream in) throws FileNotFoundException, IOException {
         CompilationUnit cu = null;
         try
         {
        	 
             cu = JavaParser.parse(in);
             
             MethodVisitor visitor = new MethodVisitor();
             
             visitor.visit(cu, null);
             
             for(NameTypePair d  : declarations)  {
            	 if (d.isMatching()) {
            		 System.out.println("IS matching [" + d + "]");
            	 } else if (d.isMatchingOneWordOfType()) {
            		 System.out.println("Name is part matching Type [" + d + "]");
            	 } else if (d.typeIsMatchingOneWordOfName()) {
            		 System.out.println("Type is part matching Name [" + d + "]");
            	 } else if (d.nameIsShortForType()) {
            		 System.out.println("Name is short for Type [" + d + "]");
            	 } else {
            		 System.out.println("NOT matching [" + d + "]");
            	 }
             }
             
         }
         catch(ParseException x)
         {
              System.err.println(x);
         }
         
	}

}
