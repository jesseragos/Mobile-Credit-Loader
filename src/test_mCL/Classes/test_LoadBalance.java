package test_mCL.Classes;

import mCL._Domains.LoadBalance;

public class test_LoadBalance {
	
	public static void main(String[] args)	
	{
		LoadBalance myLoad = new LoadBalance();
		
		myLoad.setAmount(10.45);
		
		System.out.println(myLoad.getAmount());
		System.out.print(myLoad);
	}

}
