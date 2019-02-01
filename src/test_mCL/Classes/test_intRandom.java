package test_mCL.Classes;

import java.util.Random;
import java.util.Stack;

public class test_intRandom {
	
	public static void main(String[] args)
	{
		Stack<String> s = new Stack<>();
		
		Random n = new Random();
		
		int num = 0;;
		
		String str = "";
		for(int codes = 0; codes<10; codes++){
			str = "";
			
			for(int chars = 0; chars<10; chars++){
				num = n.nextInt(10);
				str += num;
			}
			
			s.push(str);
		}
		
		s.sort(null);
		System.out.println(s);
	}

}
