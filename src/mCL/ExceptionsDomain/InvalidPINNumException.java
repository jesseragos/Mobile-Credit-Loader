package mCL.ExceptionsDomain;

public class InvalidPINNumException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidPINNumException(){}
	
	public InvalidPINNumException(int cmd){ 
		super("INVALID PIN NUMBER\n "
					+ "POSSIBLE PROBLEMS: syntax, format, PIN already used");}
	
}
