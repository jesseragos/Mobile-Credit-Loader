package test_mCL.Classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test_Time {
	
	public static void main(String[] args)
	{
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a | MMM dd, yyyy"); 
		
		System.out.println(sdf.format(d));
	}

}
