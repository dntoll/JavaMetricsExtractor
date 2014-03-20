package se.lnu.daniel.typename;

public class Type extends Identifier {
	final String[] numberTypes = {
			"int", 
			"Integer",
			"float",
			"Float",
			"double",
			"Double",
			"short",
			"Short",
			"long",
			"Long",
			"byte",
			"Byte",
			"AtomicInteger", 
			"AtomicLong", 
			"BigDecimal", 
			"BigInteger", 
			"DoubleAccumulator", 
			"DoubleAdder", 
			"LongAccumulator", 
			"LongAdder"
			};
	
	//
	final String[] primitives = {
			"char", "boolean", "String", "StringBuilder"
	};
	public Type(String fromCode) {
		super(fromCode);
	}

	public boolean isNumberType() {
		if (isArrayType())
			return false;
		
		for (String numberType : numberTypes) {
			if ( fullName.equals(numberType) )
				return true;
		}
		
		return false;
	}
	
	public boolean isPrimitive() {
		if (isArrayType())
			return false;
		
		for (String numberType : numberTypes) {
			if ( fullName.equals(numberType) )
				return true;
		}
		
		for (String primType : primitives) {
			if ( fullName.equals(primType) )
				return true;
		}
		return false;
	}
	
	

}
