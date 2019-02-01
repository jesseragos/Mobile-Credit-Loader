package test_mCL.Classes;

import mCL.dataStructs.LinkedListString;

public class test_LinkedListString {
	
	public static void main(String[] args)
	{
		LinkedListString<String> strList = new LinkedListString<>();
		
		strList.add("V");
		strList.add("D");
		strList.add("A");
		strList.add("W");
		strList.add("H");
		strList.add("F");
		strList.add("D");
		strList.add("J");
		strList.add("N");
		
		strList.sortStringlist();
		
		System.out.print(strList);
	}

}
