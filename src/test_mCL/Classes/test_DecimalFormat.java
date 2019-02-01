package test_mCL.Classes;

import java.text.DecimalFormat;

public class test_DecimalFormat {
	
	public static void main(String[] args)
	{
		DecimalFormat op = new DecimalFormat("0.00");
		
		double d = 0.5;
		
		System.out.print(op.format(d));
	}

}
