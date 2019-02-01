package test_mCL.Scratches;

public class RemoveSpaces {
	
	public static void main(String[] args)
	{
		String g = "0927 679 54 87";
		
		g = g.replaceAll("\\s+", "");
		
		System.out.print(g);
		
		if(g.equals("")) System.out.print("g = \"\"");
	}

}
