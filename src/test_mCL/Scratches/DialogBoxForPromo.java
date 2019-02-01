package test_mCL.Scratches;

import javax.swing.*;

import mCL.mainOperations.TimeEvaluate;

public class DialogBoxForPromo{
	
	public static void main(String[] args)
	{
		String msg = "\nSurf duration: 5 days\n" +
								"Data Allocation: 500 MB\n" +
								"Start time: " + TimeEvaluate.getCurrentFixedTimeFormat() +
								"\n\n (based on user selection & transactions)";
		
		JOptionPane.showMessageDialog(null, msg, "Call Promo", JOptionPane.DEFAULT_OPTION);
	}
	
}
