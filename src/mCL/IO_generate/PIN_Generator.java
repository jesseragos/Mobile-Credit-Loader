package mCL.IO_generate;

import mCL._Domains.PINnumber;
import mCL.dataStructs.LinkedListString;
import mCL.dataStructs.PINLinkedList;

public class PIN_Generator {
	
	private static final int RVALUE = 10, CHARS = 14, TIMES = 15;
	private static PINLinkedList<PINnumber> PINlist; 
	private static PINnumber PINnums;
	
	public static PINnumber getGeneratedPIN() throws Exception
	{	
		PINnums = new PINnumber();
		String newPIN = "";
		int PINchar;
			
		for(int Pnum = 0; Pnum<CHARS; Pnum++){
			PINchar = (int)(Math.random() * RVALUE);				
			newPIN += PINchar;
		}
		
		PINnums.setPIN(newPIN);
		
		return PINnums;
	}
	
	public static PINLinkedList<PINnumber> getGeneratedPINlist() throws Exception
	{
		PINlist = new PINLinkedList<>();
		
		for(int ctr = 0; ctr<TIMES; ctr++){
			PINlist.add(getGeneratedPIN());
		}
				
		return PINlist;
	}
	
	public static void updatePINList(LinkedListString<String> telcoPINList, String[] PINdata, String chosenfile) throws Exception
	{
		telcoPINList.remove(PINdata[0] + "," + PINdata[1]);
		
		telcoPINList.add(getGeneratedPIN().getPIN() + "," + PINdata[1]);
				
		telcoPINList.sortStringlist();
		
		FileIO.updateFile(telcoPINList, chosenfile);
	}

}
