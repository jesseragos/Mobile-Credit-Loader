package mCL.ExceptionsDomain;

public class InvalidPhoneNumException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidPhoneNumException(){}
	
	public InvalidPhoneNumException(int cmd){ 
		super("INVALID PHONE NUMBER\n "
					+ "POSSIBLE PROBLEMS: syntax, prefix, format, unsupported carrier, expired number");}
	
}
