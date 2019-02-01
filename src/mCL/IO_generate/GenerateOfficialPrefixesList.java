package mCL.IO_generate;

import java.io.IOException;

import mCL.dataStructs.LinkedListString;

public class GenerateOfficialPrefixesList {
	
	private static void main(String[] args) throws IOException
	{
		/*LinkedListString<String> prefixes = new LinkedListString<>(); 
		
		String[] telcoData;
		for(int ctr = 3; 0<ctr; ctr--){
			telcoData =  getFilePathData(ctr);
			
			LinkedListString<String> fileData = FileIO.extractedDB(telcoData[0]);
			
			while(!fileData.isEmpty())
				prefixes.add(fileData.remove() + "," + telcoData[1]);
		}
		
		String chosenFile = "prefixes DB";
		prefixes.sortStringlist();
		FileIO.updateFile(prefixes, chosenFile);
		System.out.print("SUCCESS!!");*/
	}

	private static String[] getFilePathData(int ctr) 
	{
		String filePath = "", telco = "";
		
		switch(ctr){
			case 1: filePath = "Globe_Prefixes"; telco = "G"; break;
			case 2: filePath = "Smart_Prefixes"; telco = "T"; break;
			case 3: filePath = "Sun_Prefixes"; telco = "S"; break;
			default: filePath = "INVALID NUM"; System.exit(0);
		}
		
		String[] arr = {filePath, telco};
		return arr;
	}

}
