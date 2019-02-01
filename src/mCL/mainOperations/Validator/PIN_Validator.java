package mCL.mainOperations.Validator;

import java.io.IOException;

import mCL.IO_generate.FileIO;
import mCL.IO_generate.PIN_Generator;
import mCL.IO_generate.TransactionRecord;
import mCL._Domains.LoadBalance;
import mCL._Domains.PINnumber;
import mCL._Domains.PhoneNumber;
import mCL.dataStructs.LinkedListString;
import mCL.mainOperations.MatchedItems;
import mCL.mainOperations.SInfoProcessor;

public class PIN_Validator {
		
	private static LoadBalance loadBal;
	private static LoadBalance pinLoad = new LoadBalance(), 
													 precededLoad = new LoadBalance();
	private static String transactRec;
	
	public static boolean loadPINvalidate(PINnumber PINno, PhoneNumber sNo) throws Exception
	{
		loadBal = new LoadBalance(0);
		
		if(PINno.getPIN().equals("null"))
			return false;
				
		String loadStr = getLoadDenomination(PINno, sNo);
			
		if(loadStr.equals(""))
			return false;
		
		updateSInfo(sNo, loadStr, PINno.getPIN());
		
		return true;
	}


	private static LinkedListString<String> TelcoPINList;
	private static String[] _PINData;
	private static String getLoadDenomination(PINnumber PINno, PhoneNumber sNo) throws Exception 
	{
		String file_PINList = "";
		
		switch(sNo.getTelecom().getName())
		{
		case "Globe": file_PINList = "GLOBE_PINList"; break;
		case "Smart": file_PINList = "SMART_PINList"; break;
		case "Sun": file_PINList = "SUN_PINList"; break;
		default: System.out.println("INVALID TELECOM DETECTED"); System.exit(0);
		}
		
		TelcoPINList = FileIO.extractedDB(file_PINList);
		
		if(TelcoPINList.isEmpty()){
			System.out.println("-->" + file_PINList + " has empty data");
			return "";
		}
		
		_PINData = MatchedItems.findIn(PINno.getPIN(), TelcoPINList, 0);
		
		if(_PINData.length == 0)
			return "";
		
		PIN_Generator.updatePINList(TelcoPINList, _PINData, file_PINList);
		
		return _PINData[1];
	}

	private static String filePath = "sInfo DB";
	private static String[] sInfoData = new String[3]; 
	private static void updateSInfo(PhoneNumber pNo, String lBal, String pinStr) throws IOException 
	{
		LinkedListString<String> sInfoList = FileIO.extractedDB(filePath);
		
		String[] sNoData = MatchedItems.findIn(pNo.getNum(), sInfoList, 0);
				
		double latestLoadBal = 0, pinLoadAmount = 0, currentLoadAmount = 0;
		try{
			pinLoadAmount = Double.parseDouble(lBal);
			
			if(sNoData.length != 0){ 
				sInfoList.remove(MatchedItems.getMidIndex());	//sNoData[0] + "," + sNoData[1] + "," + sNoData[2]
				currentLoadAmount = Double.parseDouble(sNoData[2]);
				
				latestLoadBal =  pinLoadAmount + currentLoadAmount;
			} else 
				latestLoadBal = pinLoadAmount;
			
			loadBal.setAmount(latestLoadBal);
		} catch(Exception e){
			System.out.println("INVALID LOAD BAL FORMAT DETECTED"); System.exit(0);
		}
		
		setPINload(pinLoadAmount);
		setPrecededload(currentLoadAmount);
		
		sInfoData[0] = pNo.getNum();
		sInfoData[1] = pNo.getTelecom()+"";
		sInfoData[2] = loadBal.getAmount()+"";
		
		SInfoProcessor.finalizeSInfoUpdate(sInfoList, sInfoData);
		
		String[] trData = {pNo.getNum(), pinStr, getPrecededLoad().toString(), getPINload().toString(), getLoadBal().toString()};
		
		transactRec = TransactionRecord.generateTransactions("PIN Load", trData, pNo);
		
		System.gc();
	}

	private static void setPINload(double pinLoadAmount) 
	{
		pinLoad.setAmount(pinLoadAmount);
	}
	
	private static void setPrecededload(double currentLoadAmount) 
	{
		precededLoad.setAmount(currentLoadAmount);;
	}
	
	public static LoadBalance getLoadBal()
	{
		return loadBal;
	}
	
	public static LoadBalance getPINload()
	{
		return pinLoad;
	}
	
	public static LoadBalance getPrecededLoad()
	{
		return precededLoad;
	}

	public static String getTransactionRecord()
	{
		return transactRec;
	}
}
