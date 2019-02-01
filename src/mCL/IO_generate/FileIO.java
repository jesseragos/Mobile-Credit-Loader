package mCL.IO_generate;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import mCL.dataStructs.LinkedListString;

public class FileIO {
	
	private static Scanner inFile;
	private static PrintWriter outFile;
	private static FileWriter toFile;
	
	//									----------------- FILE PATHS -----------------
	private static final String sInfoDB_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/IO_txtFiles/DataBase/sInfo DB";
	private static final String prefixDB_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/IO_txtFiles/DataBase/prefixes DB";
	private static final String transactionRecordsDB_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/IO_txtFiles/DataBase/transactionRecords DB";
	private static final String feedbackFormDB_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/IO_txtFiles/DataBase/feedbackForms DB";
	private static final String GlobePINList_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/IO_txtFiles/TelecomPINList/GLOBE_PINList",
						  						 SmartPINList_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/IO_txtFiles/TelecomPINList/SMART_PINList",
						  						 SunPINList_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/IO_txtFiles/TelecomPINList/SUN_PINList";
	private static final String GlobePrefixes_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/IO_txtFiles/TelecomPrefixList/Globe_Prefixes",
												 SmartPrefixes_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/IO_txtFiles/TelecomPrefixList/Smart_Prefixes",
												 SunPrefixes_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/IO_txtFiles/TelecomPrefixList/Sun_Prefixes";
	
	private static final String promoSubscriptions_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/IO_txtFiles/DataBase/promoSubscriptions DB";
	
	private static final String CallPromoIcon_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/_Images/MetroUI-Other-Phone-icon.png",
												 TextPromoIcon_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/_Images/MetroUI-Apps-Live-Messenger-Alt-2-icon.png",
												 SurfPromoIcon_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/_Images/MetroUI-Apps-Komposer-icon.png";
	
	private static final String ProgramIcon_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/_Images/MCLicon new.png",
												CreditIcon_path = "../CASE STUDY_1BSCS(2nd Sem)/src/mCL/_Images/mclicon_icon.png";
	//									----------------- FILE PATHS -----------------
	
	public static String getChosenFile(String chosenFile) 
	{
		String filePath = "";		
		
		switch(chosenFile){
		case "sInfo DB": filePath = sInfoDB_path; break;
		case "prefixes DB": filePath = prefixDB_path; break;
		case "transactionRecords DB": filePath = transactionRecordsDB_path; break;
		case "feedbackForms DB": filePath = feedbackFormDB_path; break;
		case "GLOBE_PINList": filePath = GlobePINList_path; break;
		case "SMART_PINList": filePath = SmartPINList_path; break;
		case "SUN_PINList": filePath = SunPINList_path; break;
		case "Globe_Prefixes": filePath = GlobePrefixes_path; break;
		case "Smart_Prefixes": filePath = SmartPrefixes_path; break;
		case "Sun_Prefixes": filePath = SunPrefixes_path; break;
		case "promoSubscriptions DB": filePath = promoSubscriptions_path; break;
		case "CallPromoIcon": filePath = CallPromoIcon_path; break;
		case "TextPromoIcon": filePath = TextPromoIcon_path; break;
		case "SurfPromoIcon": filePath = SurfPromoIcon_path; break;
		case "iconWindow": filePath = ProgramIcon_path; break;
		case "iconCredits": filePath = CreditIcon_path; break;
		default: System.out.print("INVALID FILE CHOICE"); System.exit(0); 
		}
		
		return filePath;
	}
	
	public static LinkedListString<String> extractedDB(String chosenFile) throws IOException 
	{						
		String rowData = "";
		LinkedListString<String> list = new LinkedListString<>();
		inFile = new Scanner(new FileReader(getChosenFile(chosenFile)));
		
		while(inFile.hasNext()){
			rowData = inFile.nextLine();
			list.add(rowData);
		}
		
		inFile.close();
		
		return list;
	}
	
	public static void updateFile(LinkedListString<String> dataList, String chosenFile) throws IOException
	{
		outFile = new PrintWriter(getChosenFile(chosenFile));
		
		while(!dataList.isEmpty()){
			outFile.println(dataList.remove());
		}
		
		outFile.close();
	}
	
	public static void appendToFile(String chosenFile, String dataStr) throws IOException
	{
		toFile = new FileWriter(getChosenFile(chosenFile), true);
		
		toFile.write(dataStr + "\n");
		
		toFile.close();
	}

}