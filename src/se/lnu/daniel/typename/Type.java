package se.lnu.daniel.typename;

public class Type extends Identifier {

	public Type(String fromCode) {
		super(fromCode);
	}

	public boolean isNumberType() {
		if (isArrayType())
			return false;
		
		final String[] numberTypes = {
				"int", 
				"float",
				"double", 
				"byte",
				"Integer",
				"Float",
				"Double"};
		for (String numberType : numberTypes) {
			if ( fullName.equals(numberType) )
				return true;
		}
		
		return false;
	}
	
	

}
