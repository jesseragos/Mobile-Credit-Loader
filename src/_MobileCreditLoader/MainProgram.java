/*	~	MAIN PROGRAM	~
 * 	Starts main program flow. First is the login window	*/

package _MobileCreditLoader;

import mCL._GUIclasses.SubscriberLogin;

public class MainProgram {
	
	public static void main(String[] args)
	{
				//		Instantiate and show login window
		SubscriberLogin loginWindow = new SubscriberLogin();			//	Instantiating the first interface, the login window, its JFrame instance 
		loginWindow.setVisible(true);														//	Set its visibility true to view it 
	}

}