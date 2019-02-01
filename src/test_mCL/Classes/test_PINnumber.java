package test_mCL.Classes;

import mCL._Domains.PINnumber;

public class test_PINnumber {
	
	public static void main(String[] args) throws Exception	
	{
		PINnumber myPIN = new PINnumber();
		PINnumber urPIN = new PINnumber("1234567890");
		
		myPIN.setPIN("12345678901234");
		
		System.out.println(myPIN.getPIN());
		System.out.println(myPIN);
		System.out.println();
		System.out.println(urPIN.getPIN());
		System.out.println(urPIN);
	}

}
