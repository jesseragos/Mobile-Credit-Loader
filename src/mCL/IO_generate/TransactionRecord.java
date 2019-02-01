package mCL.IO_generate;

import java.io.IOException;

import mCL._Domains.PhoneNumber;
import mCL.dataStructs.LinkedListString;
import mCL.mainOperations.TimeEvaluate;

public class TransactionRecord {
	
	private static final int alphaSyntax = 2, numericSyntax = 4;
	private static String[] promoLabels = new String[2];
	private static String transactCode = "", finalTRreceipt = "";
	private static PhoneNumber pNum;
	
	private static final String pnl = "PNL",
												sla = "SLA",
												cps = "CPS",
												tps = "TPS",
												snp = "SNP",
												sap = "SAP";
	
	public static String generateTransactions(String transactionType, String[] dataTR, PhoneNumber pNo) throws IOException
	{
		pNum = pNo;
		
		switch(transactionType){
		case "PIN Load": transactCode = pnl; finalTRreceipt =  getPINLoadTR("PIN Load", dataTR); break;
		case "Share Load": transactCode = sla; finalTRreceipt =  getShareLoadTR("Share Load", dataTR); break;
		case "Call": transactCode = cps; finalTRreceipt =  getPromoTR("Call", dataTR, 0); break;
		case "Text": transactCode = tps; finalTRreceipt =  getPromoTR("Text", dataTR, 1); break;
		case "Internet Surf": transactCode = snp; finalTRreceipt =  getPromoTR("Internet Surf", dataTR, 2); break;
		case "Apps/Sites Surf": transactCode = sap; finalTRreceipt =  getPromoTR("App Surf", dataTR, 3);
		}

		FileIO.appendToFile("transactionRecords DB", finalTRreceipt);
		
		return finalTRreceipt;
	}

	private static String getPINLoadTR(String trType, String[] dataTR) 
	{
		return "Transaction Type: " + trType + "\t\tPhone number: " + dataTR[0] + "\nPIN input: " + dataTR[1] +
						"\t\tPreceded load: " + dataTR[2] + "\nPIN Load Denomination: " + dataTR[3] + "\tTotal/Latest Load Balance: " +
						dataTR[4] + "\nReceipt Code: " + getGeneratedCode() + "\t\tTransaction Time: " + TimeEvaluate.getCurrentFixedTimeFormat() +
						"\n" + getStarBanner() + "\n";
	}

	private static String getShareLoadTR(String trType, String[] dataTR) 
	{
		return "Transaction Type: " + trType + "\t\tPhone number: " + dataTR[0] +
						"\nPreceded load: " + dataTR[1] + "\t\tLoad sent: " + dataTR[2] + "\nCharge: " + dataTR[3] + "\t\t\tTotal/Latest Load Balance: " +
						dataTR[4] + "\nReceipt Code: " + getGeneratedCode() + "\t\tTransaction Time: " + TimeEvaluate.getCurrentFixedTimeFormat() +
						"\n" + getStarBanner() + "\n";
	}
	
	private static String transactTime;
	private static String getPromoTR(String trType, String[] dataTR, int promo) throws IOException 
	{
		transactTime = TimeEvaluate.getCurrentFixedTimeFormat();
		generatePromoLabels(promo);
		processPSubscriptionRecord(trType, dataTR, transactTime);
		//		Process and update sInfo
		return "Transaction Type: " + trType + " Promo Subscription\tPhone number: " + dataTR[0] + "\nPreceded load: " + dataTR[1] +  
						"\t\t" + promoLabels[0] + dataTR[2] + "\n" + promoLabels[1] + dataTR[3] + "\t\tPromo Cost: " + dataTR[4] + "\nTotal/Latest Load Balance: " +
						dataTR[5] + "\nReceipt Code: " + getGeneratedCode() + "\t\tTransaction Time: " + transactTime +
						"\n" + getStarBanner() + "\n";
	}

	public static String networkScope = "Network scope: ", dataAllocation =  "Data allocation: "; 
	public static String[] generatePromoLabels(int p)
	{
		switch(p){
		case 0: promoLabels[0] = networkScope; promoLabels[1] = "Call duration: "; break;
		case 1: promoLabels[0] = networkScope; promoLabels[1] = "Text limits: "; break;
		case 2: promoLabels[0] = "Surf duration: "; promoLabels[1] = dataAllocation; break;
		case 3: promoLabels[0] = "App/site choice(s): "; promoLabels[1] = dataAllocation; break;
		}
		
		return promoLabels;
	}

	private static LinkedListString<String> pSubList;
	private static String fileName = "promoSubscriptions DB";
	public static final String ll = "__"; 
	private static void processPSubscriptionRecord(String trType, String[] dataTR, String startTime) throws IOException 
	{
		pSubList = FileIO.extractedDB(fileName);
		pSubList.add(pNum.getNum() + ll + transactCode + ll + dataTR[2] + ll + dataTR[3] + ll + startTime);
		pSubList.sortStringlist();
		FileIO.updateFile(pSubList, fileName);
	}
	
	public static String getGeneratedCode()
	{
		String code = "", letter;
		int n;
		
		for(int i = 0; i<alphaSyntax; i++){
			n = getRandomNum();
			
			switch(n){
			case 0: letter = "A"; break;
			case 1: letter = "B"; break;
			case 2: letter = "C"; break;
			case 3: letter = "D"; break;
			case 4: letter = "E"; break;
			case 5: letter = "F"; break;
			case 6: letter = "G"; break;
			case 7: letter = "H"; break;
			case 8: letter = "I"; break;
			case 9: letter = "J"; break;
			default: letter = "J"; break;
			}
			
			code += letter;
		}
		for(int i = 0; i<numericSyntax; i++){
			code += getRandomNum();
		}
		
		return code + transactCode;
	}

	private static int getRandomNum() 
	{
		return (int) (Math.random() * 10);
	}
	
	private static String getStarBanner()
	{
		String banner = "";
		
		for(int i = 105; 0<i; i--){
			banner += "*";
		}
		
		return banner;
	}
	
	private static String[] transactCodeList = new String[6];
	public static String[] getTransactCodes()
	{
		//transactCodeList[0] = pnl;
		//transactCodeList[1] = sla;
		transactCodeList[0] = cps;
		transactCodeList[1] = tps;
		transactCodeList[2] = snp;
		transactCodeList[3] = sap;
		
		return transactCodeList;
	}

}
