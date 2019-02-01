package test_mCL.Classes;

import java.util.Scanner;

public class test_FileRead {

	static Scanner c = new Scanner(System.in);
	public static void main(String[] args)
	{
		String [] da = new String[5];
		da[0] = "AAAAAA";
		da[1] = "BBBBBB";
		da[2] = "CCCXCX";
		
		String [] cas = da;
		
		for(int i = 0; i<cas.length; i++)
			System.out.println(cas[i]);
		
		cas = new String[0];
				
		System.out.println(cas.length);
		
		String h = "World";
		System.out.println("\n" + h.substring(0, 4));
	}
	
}

