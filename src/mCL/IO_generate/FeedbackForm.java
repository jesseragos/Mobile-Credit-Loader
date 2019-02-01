package mCL.IO_generate;

import java.io.IOException;

import mCL._Domains.PhoneNumber;

public class FeedbackForm {
	
	public static void generateFormToFile(String[] formData, PhoneNumber pNo) throws IOException
	{
		String dataStr = "Name: "  + formData[0] + "\tPhone Number: " + pNo.getNum() +  "\tTelecom: " + pNo.getTelecom() + "\nAge: " + formData[1] +
									"\tGender: " + formData[2] + "\tProfession: " + formData[3] + "\tRating: " + formData[4] +
									"\nComments:" + formData[5] + "\n" + getLineBorder();
		
		FileIO.appendToFile("feedbackForms DB", dataStr);
	}

	private static String getLineBorder() 
	{
		String dash = "";
		for(int i = 100; 0<i; i--)
			dash += "-";
		
		return dash;
	}

}
