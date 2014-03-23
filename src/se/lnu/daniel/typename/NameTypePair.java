package se.lnu.daniel.typename;

import java.io.File;

public class NameTypePair {
	public enum Origin {
		Any,
		Field, Parameter, Local
	}
	private VariableName name;
	private Type type;
	private Origin scope;
	private File sourceFile;
	
	public NameTypePair(VariableName id, Type t, Origin origin, File sourceFile) {
		this.name = id;
		this.type = t;
		this.scope = origin;
		this.sourceFile = sourceFile;
	}
	
	public NameTypePair(String id, String type, Origin origin ) {
		this(new VariableName(id), new Type(type), origin, null);
	}
	
	

	public Origin getScope() {
		return scope;
	}

	public Type getType() {
		return type;
	}
	
	public VariableName getName() {
		return name;
	}
	
	
	
	
	


	

	
	
	
	
		
	
	

	

/*
	public boolean hasHungarianNotationForType() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean hasClassMemberNotation() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isConditionVariable() {
		// TODO Auto-generated method stub
		return false;
	}
*/	
	

	
	public boolean equalsIgnorePluralis() {
		
		return type.equalsIgnorePluralis(name);
	}
	
	public boolean nameIndicatesNumberType() {
		
		final String[] wordsIndicatesNumber = { "count", 
												"num",
												"size",
												"number",
												"length"};
		
		for (String word : wordsIndicatesNumber) {
			if ( name.containsIgnoreCase(word) )
				return true;
		}
		return false;
	}

	public boolean typeIsArray() {
		return type.isArrayType();
	}

	public boolean nameIsPluralis() {
		return name.isPluralis();
	}
	public boolean typeCanBeCollection() {
		if (type.isArrayType()) {
			return true;
		}
		return type.indicateCollection();
	}

	public boolean nameIndicatesCollection() {
		return name.indicateCollection();
	}

	public File getSourceFile() {
		return sourceFile;
	}
}
