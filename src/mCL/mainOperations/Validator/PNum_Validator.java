package mCL.mainOperations.Validator;

import mCL.IO_generate.FileIO;
import mCL._Domains.Telecom;
import mCL.dataStructs.LinkedListString;
import mCL.mainOperations.MatchedItems;
import mCL.mainOperations.Telecoms_Philippines;

public class PNum_Validator {

	private static final String sInfo_filePath = "sInfo DB";
	private static Telecom prefTelco;
	
	public static boolean validatePNo(String pNoStr, String telco) throws Exception
	{				
		LinkedListString<String> sInfoList =  FileIO.extractedDB(sInfo_filePath);
		String[] sData = MatchedItems.findIn(pNoStr, sInfoList, 0);
		if(sData.length != 0){
			prefTelco = Telecoms_Philippines.verifiedTelecom(telco);
			return true;
		}
				
		//		Continue if not/to register no
		
		updateSInfoFile(sInfoList, pNoStr, telco);
		
		return true;
	}
	
	//		-------------	SubOperations		-------------

	private static String getFormalSInfo(String pNum, Telecom telco) 
	{
		return pNum + "," + telco + "," + 0.0;
	}
	
	private static void updateSInfoFile(LinkedListString<String> sInfoList, String pNumStr, String telco) throws Exception 
	{
		prefTelco = Telecoms_Philippines.verifiedTelecom(telco);
		String sDataStr = getFormalSInfo(pNumStr, prefTelco);
		
		sInfoList.add(sDataStr);
		sInfoList.sortStringlist();
		FileIO.updateFile(sInfoList, sInfo_filePath);
	}
	
	public static Telecom getTelcoPref() 
	{
		return prefTelco;
	}
	
}