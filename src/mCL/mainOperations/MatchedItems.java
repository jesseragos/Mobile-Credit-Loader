package mCL.mainOperations;

import java.util.LinkedList;

import mCL.IO_generate.TransactionRecord;

public class MatchedItems {

	private static String ll = TransactionRecord.ll, v;
	private static IndexOutOfBoundsException inEx = new IndexOutOfBoundsException();
	private static int first,last,mid;
	
	public static String[] findIn(String item, LinkedList<String> itemList, int indx) 
	{
		first = 0;
		last = itemList.size()-1;
		
		String[] rowData;
		
		try{ if(itemList.get(first).indexOf(ll) != -1) v = ll;
				else throw inEx;
		} catch(IndexOutOfBoundsException inEx){ v = ","; }
		
		while(first <= last){
			mid = (first+last)/2;
			
			rowData = itemList.get(mid).split(v);
			
			if(rowData[indx].equals(item))
				return rowData;
			
			else if(rowData[indx].compareTo(item) > 0)
				last = mid - 1;
			else
				first = mid + 1;
		}
		
		return new String[0];
	}
	
	public static int getMidIndex()
	{
		return mid;
	}

}
