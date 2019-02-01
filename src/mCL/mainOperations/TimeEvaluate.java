package mCL.mainOperations;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeEvaluate {
	
	public static String getCurrentFixedTimeFormat()
	{
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a | MMM dd, yyyy"); 
		
		return sdf.format(d);
	}

}
