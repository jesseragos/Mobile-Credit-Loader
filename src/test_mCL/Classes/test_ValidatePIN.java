package test_mCL.Classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.LinkedList;

import mCL.IO_generate.PIN_Generator;

public class test_ValidatePIN {

	static Scanner in, readFile;
	static PrintWriter outFile;
	static int amount;
	static LinkedList<String> PINlist;
	
	public static void main(String[] args) throws Exception
	{
		in = new Scanner(System.in);
		extractFile();

		System.out.print("Enter PIN: ");
		String PINno = in.next();
		
		int found = searchAndRemovePIN(PINno);
		
		if(found == -1)
			System.out.print("PIN input invalid");
		else{
			PINlist.remove(found);
			String newPin = PIN_Generator.getGeneratedPIN().getPIN();
			updateIOFile(newPin);
			System.out.print("You received P" + amount);
		}
	}

	private static void extractFile() throws FileNotFoundException 
	{
		String PIN;
		PINlist = new LinkedList<>();
		readFile = new Scanner(new FileReader("../CASE STUDY_1BSCS(2nd Sem)/src/mCL/IO_txtFiles/deno_200"));
		
		try{
		amount = readFile.nextInt();
		} catch(Exception e) {
			System.out.println("~ Cannot retrieve amount denomination from deno_200.txt ~");
			System.out.println("Please validate the format of this I/O file first\nTerminating...");
			System.exit(0);
		}
		
		while(readFile.hasNext())
		{
			PIN = readFile.next();
			PINlist.add(PIN);
		}
	}
	
	private static int searchAndRemovePIN(String PINno) 
	{
		//		BINARY SEARCH		
		int first = 0, last = PINlist.size(), mid; 
		
		while(first<=last){
			mid = (first+last)/2;
			
			if(PINlist.get(mid).equals(PINno)){
				return mid;
			}
			else if(PINlist.get(mid).compareTo(PINno) > 0){
				last = mid - 1;
			}
			else
				first = mid + 1;
		}
		
		return -1;		
	}	
	
	private static void updateIOFile(String nPin) throws FileNotFoundException 
	{
		PINlist.add(nPin);
		PINlist.sort(null);
		
		outFile = new PrintWriter("../CASE STUDY_1BSCS(2nd Sem)/src/mCL/IO_txtFiles/deno_200");
		
		if(amount != 0)
			outFile.println(amount);
		
		for(int i = 0; i<PINlist.size(); i++){
			outFile.println(PINlist.get(i));
		}
				
		outFile.close();
	}
	
}
