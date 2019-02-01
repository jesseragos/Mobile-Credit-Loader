package test_mCL.Scratches;

public class StringRemove {
	
	public static void main(String[] args)
	{
		String k = "092103325432 ";
		
		if(k.startsWith("0"))
			k = k.replaceFirst("0", "");
		
		k.trim();
		System.out.println(k);
		System.out.println(k.indexOf("6"));
	}

}
