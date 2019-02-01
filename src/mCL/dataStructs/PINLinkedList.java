package mCL.dataStructs;

import java.util.LinkedList;

import mCL._Domains.PINnumber;

@SuppressWarnings("hiding")
public class PINLinkedList<PINnumber> extends LinkedList<PINnumber>{
	//		||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	private static final long serialVersionUID = 1L;
	
	public void sortPINlist(){

		mCL._Domains.PINnumber tempPIN = new mCL._Domains.PINnumber();
		String PINn1, PINn2;
		
		for(int i = 0; i<size()-1; i++){
			for(int j = i+1; j<size(); j++){
				
				PINn1 = ((mCL._Domains.PINnumber) get(i)).getPIN();
				PINn2 = ((mCL._Domains.PINnumber) get(j)).getPIN();
				
				if(PINn1.compareTo(PINn2) > 0){					
					tempPIN.copyPINnum((mCL._Domains.PINnumber) get(i));
					((mCL._Domains.PINnumber) get(i)).copyPINnum((mCL._Domains.PINnumber) get(j));
					((mCL._Domains.PINnumber) get(j)).copyPINnum(tempPIN);
				}
				
			}
		}
	
	}
	
		//		location = indx; temp = tempPIN; list = linked; assignment = copy method
	/*public void sortPINlist()
	{
		mCL.numsAndCodes.PINnumber tempPIN = new mCL.numsAndCodes.PINnumber();
		int location;
		
		for(int n = 1; n<size(); n++){					//		Access all index to determine each element's position 
			location = n;
			
			tempPIN = (mCL.numsAndCodes.PINnumber) get(n);
			String PINn1 = ((mCL.numsAndCodes.PINnumber) get(location-1)).getPIN();
			String PINn2 = tempPIN.getPIN();
			
			while(location > 0 && PINn1.compareTo(PINn2) > 0){			//		Shift elements to right position until its bigger than previous element
				((mCL.numsAndCodes.PINnumber) get(location)).copyPINnum((mCL.numsAndCodes.PINnumber) get(location-1));
				location--;
			}
			
			((mCL.numsAndCodes.PINnumber) get(location)).copyPINnum(tempPIN);
		}
	}*/


}
