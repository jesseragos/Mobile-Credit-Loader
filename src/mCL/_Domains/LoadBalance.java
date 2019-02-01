package mCL._Domains;

import java.text.DecimalFormat;

public class LoadBalance {
		
	private DecimalFormat currency = new DecimalFormat("P###,###,##0.00");
	private DecimalFormat constant = new DecimalFormat("0.00");
	private double amount;
	
	public LoadBalance(){
		setAmount(0.00);
	}
	
	public LoadBalance(double amount){
		setAmount(amount);
	}
	
	public void setAmount(double amount)
	{		
		this.amount = get2DP(amount);
	}

	public double getAmount()
	{
		return get2DP(amount);
	}
	
	public String getAmountStr()
	{
		return constant.format(amount);
	}
	
	public String toString()
	{
		return currency.format(amount);
	}
		
	private double get2DP(double amount) 
	{		
		return Double.parseDouble(constant.format(amount));
	}

}
