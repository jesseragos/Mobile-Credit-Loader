package mCL.mainOperations;

import java.awt.Color;
import java.awt.Font;

import mCL._Domains.Telecom;

public class Telecoms_Philippines {
	
	private static Telecom telcoPref;
	private static final Color[] colorPrefs_Tab = new Color[8];
	private static final Font[] fontPrefs = new Font[1];
	
	public static Telecom verifiedTelecom(String telcoChar) throws Exception
	{
		switch(telcoChar)
		{
		case "G": return setTelcoPref("Globe", getGLOBEColorPrefs(), getGLOBEFontPrefs());
		case "SM": return setTelcoPref("Smart", getSMARTColorPrefs() , getSMARTFontPrefs());
		case "SU": return setTelcoPref("Sun", getSUNColorPrefs(), getSUNFontPrefs());
		default:  return new Telecom("unknown");
		}
	}

	private static Telecom setTelcoPref(String tName, Color[] tColors, Font[] tFonts) throws Exception 
	{
		telcoPref = new Telecom();
		telcoPref.setName(tName);
		telcoPref.setColorPrefs(tColors);
		telcoPref.setFontPrefs(tFonts);
		return telcoPref;
	}
	
	private static Color white = Color.WHITE,
										gray = Color.GRAY,
										black = Color.BLACK,
										orange = Color.ORANGE,
										maroon = new Color(200, 0, 0),
										lightblue = new Color(100, 150, 250);
	private static Color[] getGLOBEColorPrefs() 
	{
		colorPrefs_Tab[0] = new Color(10,50,200);			//		FRAME COLOR BACKGROUND
		colorPrefs_Tab[1] = new Color(245, 0, 250);			//		TAB COLOR
		colorPrefs_Tab[2] = white;								//		TAB FONT COLOR
		
		colorPrefs_Tab[3] = new Color(0, 100, 200);			//		PANEL BACKGROUND
		colorPrefs_Tab[4] = colorPrefs_Tab[2];		//		WELCOME LABEL COLOR
		colorPrefs_Tab[5] = white;		//		LOCAL TEXT COLOR
		colorPrefs_Tab[6] = orange;							//		BUTTON COLOR
		colorPrefs_Tab[7] = black;							//		BUTTON FONT COLOR
		return colorPrefs_Tab;
	}
	
	private static Font[] getGLOBEFontPrefs() 
	{
		fontPrefs[0] = new Font("Khmer UI", Font.PLAIN, 40);		//		WELCOME LABEL
		return fontPrefs;
	}
	
	private static Color[] getSMARTColorPrefs()
	{
		colorPrefs_Tab[0] = gray;								//		FRAME COLOR BACKGROUND
		colorPrefs_Tab[1] = new Color(189, 255, 54);		//		TAB COLOR
		colorPrefs_Tab[2] = lightblue;								//		TAB FONT COLOR
		
		colorPrefs_Tab[3] = new Color(233, 233, 233);		//		PANEL BACKGROUND
		colorPrefs_Tab[4] = colorPrefs_Tab[2];		//		WELCOME LABEL COLOR
		colorPrefs_Tab[5] = black;								//		LOCAL TEXT COLOR
		colorPrefs_Tab[6] = new Color(255, 60, 99);				//		BUTTON COLOR
		colorPrefs_Tab[7] = white;								//		BUTTON COLOR
		return colorPrefs_Tab;
	}
	
	private static Font[] getSMARTFontPrefs() 
	{
		fontPrefs[0] = new Font("Haettenschweiler", Font.PLAIN, 50);		//		WELCOME LABEL
		return fontPrefs;
	}
	
	private static Color[] getSUNColorPrefs()
	{
		colorPrefs_Tab[0] = gray;							//		FRAME COLOR BACKGROUND
		colorPrefs_Tab[1] = new Color(255, 193, 67);						//		TAB COLOR
		colorPrefs_Tab[2] = maroon;				//		TAB FONT COLOR
		
		colorPrefs_Tab[3] = new Color(233, 233, 233);			//		PANEL BACKGROUND
		colorPrefs_Tab[4] = colorPrefs_Tab[2];					//		WELCOME LABEL COLOR
		colorPrefs_Tab[5] = black;								//		LOCAL TEXT COLOR
		colorPrefs_Tab[6] = new Color(210, 0, 0);									//		BUTTON COLOR
		colorPrefs_Tab[7] = white;								//		BUTTON COLOR
		return colorPrefs_Tab;
	}
	
	private static Font[] getSUNFontPrefs() 
	{
		fontPrefs[0] = new Font("MoolBoran", Font.BOLD, 50);		//		WELCOME LABEL
		return fontPrefs;
	}

}
