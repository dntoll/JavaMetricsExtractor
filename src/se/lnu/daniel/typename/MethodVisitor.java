package se.lnu.daniel.typename;

import japa.parser.ast.LineComment;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.JavadocComment;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import se.lnu.daniel.typename.NameTypePair.Origin;

public class MethodVisitor extends VoidVisitorAdapter<Object>
{
	List<NameTypePair> declarations = new ArrayList<NameTypePair>();
	File currentFile;
	int numMethods = 0;
	int numParameters = 0;
    
    public MethodVisitor(File currentFile) {
		this.currentFile = currentFile;
    }

	public void visit(MethodDeclaration n, Object arg)
    {
    	
    	//System.out.println(n);
    	numMethods++;
    	if (n.getParameters() != null) {
        	numParameters = n.getParameters().size();
        	
        	for (Parameter p : n.getParameters()) {
        		declarations.add(new NameTypePair(new VariableName(p.getId().toString()), 
        										  new Type(p.getType().toString()), Origin.Parameter, currentFile));
        	}
    	}
    	super.visit(n, arg);
    }
	
	@Override
	public void visit(VariableDeclarationExpr n, Object arg)
	{      
		
	    List <VariableDeclarator> myVars = n.getVars();
        for (VariableDeclarator vars: myVars){
        	declarations.add(new NameTypePair(	new VariableName(vars.getId().toString()), 
        										new Type(n.getType().toString()), Origin.Local, currentFile));
        }
        super.visit(n, arg);
	}
	
	@Override
	public void visit(FieldDeclaration n, Object arg) {
	    
	    for (VariableDeclarator vars: n.getVariables() ){
	    	declarations.add(new NameTypePair(	new VariableName(vars.getId().toString()), 
	    										new Type(n.getType().toString()), Origin.Field, currentFile));
	    }
	    
	    super.visit(n, arg);
	}
	
	@Override
	public void visit(JavadocComment n, Object arg) {
		
		super.visit(n, arg);
    }
	
	@Override
	public void visit(LineComment n, Object arg) {
		super.visit(n, arg);
    }
}