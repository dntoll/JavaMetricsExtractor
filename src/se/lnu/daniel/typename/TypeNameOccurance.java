package se.lnu.daniel.typename;

import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TypeNameOccurance {
	private Word word;
	private HashMap<String, Integer> associatedWords;
	private int count;
	
	public TypeNameOccurance(Word w) {
		word = w;
		
		associatedWords = new HashMap<String, Integer>();
		count = 0; //must be 0 since add methods add the associations
	}

	public void addNames(NameTypePair pair) {
		//Word[] nonTypeWords = pair.getRemainsAfterRemovingType();		
		Word[] words = pair.getName().getWords();
		
		count(words);
	}
	
	public void addTypes(NameTypePair pair) {
		Word[] words = pair.getType().getWords();
		
		
		count(words);
	}

	private void count(Word[] words) {
		for(Word word : words) {
			String key = word.getWithoutPluralis(); 
			if (associatedWords.containsKey(key)) {
				associatedWords.put(key, new Integer(associatedWords.get(key).intValue()+1));
			} else {
				associatedWords.put(key, new Integer(1));
			}
		}
		count++;
	}
	
	

	public int compareTo(TypeNameOccurance other) {
		if (count == other.count)
			return 0;
		
		if (count > other.count)
			return 1;
		
		return -1;
	}

	public void prettyPrint(PrintStream out) {
		List<Map.Entry<String, Integer>> list =  new LinkedList<Map.Entry<String, Integer>>( associatedWords.entrySet() );
		Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return -(o1.getValue()).compareTo( o2.getValue() );
            }
        } );
		
		
		String ass = "";
		int num = 0;
		if (count > 100) 
		{ 
			out.println();
			out.println(word.toString() + "(" + count + ")");
			out.print("\t");
			for (Entry<String, Integer> entry : list) {
				if (entry.getValue() > 10 || num < 9) 
				{
					ass = "[" + entry.getKey() + "(" + entry.getValue().toString() + ")]";
					out.print(ass);
					num++;
					if (num % 10 == 9) {
						out.println();
						out.print("\t");
					}
				}
			}
		}
		  
	}

}
