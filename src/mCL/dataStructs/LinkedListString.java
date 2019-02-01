package mCL.dataStructs;

import java.util.LinkedList;

@SuppressWarnings("hiding")
public class LinkedListString<String> extends LinkedList<String>{
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public void sortStringlist(){

		String str1, str2;
		
		for(int i = 0; i<size()-1; i++){
			for(int j = i+1; j<size(); j++){
				
				str1 = (String) get(i);
				str2 = (String) get(j);
				
				if(((Comparable<String>) str1).compareTo(str2) > 0){					
					set(i, str2);
					set(j, str1);
				}
				
			}
		}
	
	}

}
