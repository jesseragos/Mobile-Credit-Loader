package _MobileCreditLoader;

import java.io.IOException;

import mCL._Domains.PhoneNumber;
import mCL._GUIclasses.MobileCreditLoader;

public class test_mcl {
	
	public static void main(String[] args) throws IOException
	{
		MobileCreditLoader mcl = new MobileCreditLoader(new PhoneNumber());
		mcl.setVisible(true);
	}

}
