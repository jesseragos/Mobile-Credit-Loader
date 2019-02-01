package mCL._Domains;

import java.io.IOException;
import java.util.LinkedList;

import mCL.IO_generate.FileIO;
import mCL.mainOperations.MatchedItems;
import mCL.mainOperations.Validator.PNum_Validator;

public class PhoneNumber {
	
	private String pNum;
	private Telecom pTelco;
	private LoadBalance sLoad = new LoadBalance(0);
	private String nullStr = "null";
	
	public PhoneNumber(){
		pNum = nullStr;		
	}
	
	public PhoneNumber(String num) throws Exception{
		setPNo(num);
	}
	
	public void setPNo(String num) throws Exception 
	{			
		this.pNum = validatedSNo(num);		
	}
	
	private String validatedSNo(String num) throws Exception 
	{		
		num = num.replaceAll("\\s+", "");
		
		if(num.equals(""))
			return nullStr;
		
		if(num.startsWith("0"))
			num = num.replaceFirst("0", "");
		
		if((num.startsWith("+63") || num.startsWith("63")) && (num.length() == 12 || num.length() == 13))
			return "IDF";
		
		if(num.length() != 10)
			return nullStr;
		
		String prefix = num.substring(0, 3);
		
		LinkedList<String> prefixesList = FileIO.extractedDB("prefixes DB");
		
		if(prefixesList.isEmpty()){
			System.out.println("--> prefixes DB has empty data");
			return nullStr;
		}
		
		String[] dataRow = MatchedItems.findIn(prefix, prefixesList, 0);
				
		if(dataRow.length == 0)
			return nullStr;
		
		String telco = dataRow[1];
				
		num = 0 + num;
		if(PNum_Validator.validatePNo(num, telco)){
			pTelco = PNum_Validator.getTelcoPref();
			return num;
			
		} else return nullStr;
		
	}

	public String getNum() 
	{
		return pNum;		
	}
	
	public Telecom getTelecom() 
	{
		return pTelco;		
	}
	
	public String[] getPNoAndTelco()
	{
		String[] data = {pNum, pTelco.getName()};
		return data;
	}
	
	public LoadBalance getLoadBalance() throws IOException 
	{
		LinkedList<String> sInfoList = FileIO.extractedDB("sInfo DB");
		String[] dataRow = MatchedItems.findIn(pNum, sInfoList, 0);

		if(dataRow.length == 0)
			return sLoad;
		
		try{
		sLoad.setAmount(Double.parseDouble(dataRow[2]));
		} catch(Exception e){
			System.out.println("INVALID LOAD BAL FORMAT IN ITS FILE");
		}
		
		return sLoad;
	}
	
	public String toString()
	{
		return pNum + " " + pTelco + " " + sLoad;
	}

}