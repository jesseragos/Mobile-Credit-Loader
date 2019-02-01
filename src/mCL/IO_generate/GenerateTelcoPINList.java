package mCL.IO_generate;

import mCL._Domains.PINnumber;
import mCL.dataStructs.LinkedListString;
import mCL.dataStructs.PINLinkedList;

public class GenerateTelcoPINList{
	
	private static void main(String[] args) throws Exception
	{
		/*int[] deno = {100, 300, 500};
		
		for(int fileCtr = 3; fileCtr>0; fileCtr--){
			
			PINLinkedList<PINnumber> PINList;
			LinkedListString<String> PINinfo = new LinkedListString<String>();
			
			for(int i = 0; i<deno.length; i++){
				
				PINList = PIN_Generator.getGeneratedPINlist();
				PINList.sortPINlist();
				
				while(!PINList.isEmpty()){
					PINinfo.add(PINList.remove().getPIN() + "," + deno[i]);
				}
				
			}
			
			PINinfo.sortStringlist();
			FileIO.updateFile(PINinfo, assignFile(fileCtr));*
		}
		
		System.out.print("SUCCESS");*/
	}

	private static String assignFile(int fCtr) 
	{
		String filePath = "";
		
		switch(fCtr){
		case 1: filePath = "GLOBE_PINList"; break;
		case 2: filePath = "SMART_PINList"; break;
		case 3: filePath = "SUN_PINList"; break;
		default: System.out.println("INVALID FILE DETECTED");
		}
		
		return filePath;
	}
	
}
