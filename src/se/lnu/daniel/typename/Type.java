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
			"char", "boolean", "string", "object"
	};
	
	final String[] mostcommonjavatypes = {
			"bufferedreader", 
			"stringbuffer", 
			"stringbuilder", 
			"object", 
			"file",
			"inputstream",
			"view",
			"bundle",
			"intent",
			"context",
			"textview",
			"button", 
			"node", 
			"date", 
			"irubyobject",
			"irubyobject", 
			"liststring", 
			"actionevent", 
			"java.awt.event.actionevent", 
			"arrayliststring"
			
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
		
		if (isNumberType())
			return true;
		
		for (String primType : primitives) {
			if ( fullName.equals(primType) )
				return true;
		}
		
		/*for (String primType : mostcommonjavatypes) {
			if ( fullName.equals(primType) )
				return true;
		}*/
		
		
		return false;
	}

/*	public boolean isPrimitivePlus() {
		return isPrimitive() || isJavaType();
	}

	private boolean isJavaType() {
		for (String type : javatypes) {
			if ( fullName.equals(type) )
				return true;
		}
		return false;
	}

	
	
	*/

}
