package test_mCL.Classes;

import java.util.Stack;

import mCL._Domains.PINnumber;

public class test_StackElements {
	
	public static void main(String[] args) throws Exception
	{
		Stack<PINnumber> strs = new Stack<>();
		
		PINnumber pin = new PINnumber();
		
		String p = "";
		int chars = 0;
		
		for(int i = 0; i<10; i++){
			p = "";
			pin = new PINnumber();
			
			for(int j = 0; j<14; j++){
				chars = (int)(Math.random() * 10);
				p += chars;
			}

				pin.setPIN(p);
				strs.push(pin);
		}
		
		System.out.print(strs);
	}

}
