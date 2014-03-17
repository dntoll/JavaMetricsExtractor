package se.lnu.daniel.typename;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TypeNameOccuranceCounter {

	
	List<NameTypePair> declarations;
	public TypeNameOccuranceCounter(List<NameTypePair> declarations) {
		this.declarations = declarations;
	}
	
	public List<TypeNameOccurance> countNameOccurances() {
		
		HashMap<String, TypeNameOccurance> nameOccurances = new HashMap<String, TypeNameOccurance>();
		
		
		for (NameTypePair pair : declarations) {
			VariableName name = pair.getName();
			
			for(Word w : name.getWords()) {
				getOccurance(nameOccurances, w).addTypes(pair);
			}
		}
		
		return sort(nameOccurances);
	}
	
	public List<TypeNameOccurance> countTypeOccurances() {
		HashMap<String, TypeNameOccurance> occurances = new HashMap<String, TypeNameOccurance>();
		for (NameTypePair pair : declarations) {
			Type type = pair.getType();
			
			for(Word w : type.getWords()) {
				getOccurance(occurances, w).addNames(pair);
			}
		}
		
		return sort(occurances);
	}
	
	private TypeNameOccurance getOccurance(HashMap<String, TypeNameOccurance> occurances, Word w) {
		String hashMapKey = w.getWithoutPluralis();//.toString();
		TypeNameOccurance byWord;
		
		if (occurances.containsKey(hashMapKey)) {
			byWord = occurances.get(hashMapKey);
		} else {
			byWord = new TypeNameOccurance(w);
			occurances.put(hashMapKey, byWord);
		}
		//byWord.add(pair);
		return byWord;
	}

	private List<TypeNameOccurance> sort(HashMap<String, TypeNameOccurance> occurances) {
		List<TypeNameOccurance> result = new ArrayList<TypeNameOccurance>();
		
		List<Map.Entry<String, TypeNameOccurance>> list =  new LinkedList<Map.Entry<String, TypeNameOccurance>>( occurances.entrySet() );
		Collections.sort( list, new Comparator<Map.Entry<String, TypeNameOccurance>>()
        {
            public int compare( Map.Entry<String, TypeNameOccurance> o1, Map.Entry<String, TypeNameOccurance> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );
		for (Entry<String, TypeNameOccurance> entry : list)
        {
            result.add( entry.getValue() );
        }
		
		return result;
	}

	

	

}
