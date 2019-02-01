package mCL._Domains;

public class PINnumber {
	//		*NEW UPDATED STATEMENTS
	//		||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	private String num;
	private boolean isPINValid = true;
	
	public PINnumber(){
		num = "";
	}
	
	public PINnumber(String num) throws Exception{
		setPIN(num);
	}
	
	public void setPIN(String num) throws Exception
	{
		isPINValid = false;
		
		try{	
			if(num.length() != 14)
				throw new NumberFormatException();
			
			for(char c:num.toCharArray())
				Integer.parseInt(c+"");
			
				isPINValid = true;
				
		} catch(NumberFormatException nfe) {
			this.num = "null";
		}
		
		if(isPINValid)
			this.num = num;
	}
	
	public String getPIN()
	{
		return num;
	}

	public void copyPINnum(PINnumber otherPIN)
	{
		num = otherPIN.num;
	}
	
	public String toString()
	{
		String result = num;
		if(isPINValid == false)
			result += " <- INVALID PIN";
		return result;
	}

}
