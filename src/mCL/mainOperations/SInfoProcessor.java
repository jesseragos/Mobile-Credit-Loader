package mCL.mainOperations;

import java.io.IOException;

import mCL.IO_generate.FileIO;
import mCL.dataStructs.LinkedListString;

public class SInfoProcessor {
	
	public static void finalizeSInfoUpdate(LinkedListString<String> sInfoList, String sInfoData[]) throws IOException
	{
		sInfoList.add(sInfoData[0] + "," + sInfoData[1] + "," + sInfoData[2]);
		sInfoList.sortStringlist();
		
		FileIO.updateFile(sInfoList, "sInfo DB");
	}

}
