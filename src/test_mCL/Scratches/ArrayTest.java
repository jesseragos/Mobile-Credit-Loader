package test_mCL.Scratches;

public class ArrayTest {
	
	public static void main(String[ ] args)
	{
		Integer[] list = new Integer[5];
		
		int ct = 5;
		for(int i = 0; i<list.length; i++)
			list[i] = ct--;
		
		for(int i = 0; i<list.length; i++)
			System.out.println(list[i]);
	}

}
