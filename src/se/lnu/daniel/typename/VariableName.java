package se.lnu.daniel.typename;

public class VariableName extends Identifier {

	public VariableName(String fromCode) {
		super(fromCode);

	}

	public boolean isShortFor(Type type) {
		
		if (this.fullName.toString().length() > 1)
			return type.fullName.containsIgnorePluralis(this.fullName);
		return false;
			
	}

	

	

	/*private boolean contains(String nameWord) {
		if (nameWord.length() < 2) {
			return false;
		}
		String compName = removePluralis(nameWord).toLowerCase();
		String lowerTypeWord = type.toLowerCase();
		
		if (lowerTypeWord.contains(compName))
			return true;
		if (lowerTypeWord.contains(nameWord))
			return true;
		return false;
	}*/
}
