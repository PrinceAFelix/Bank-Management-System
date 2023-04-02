package bankmanagementsystem;

import java.util.Date;

public class AtmTransaction {

	protected int id;
	public Date transaction_Date;
	public String transaction_Type;
	public String transaction_Amount;
	public float post_balance;
	
	AtmTransaction(int id, Date td, String tt, String ta, float pb){
		this.id = id;
		this.transaction_Date = td;
		this.transaction_Type = tt;
		this.transaction_Amount = ta;
		this.post_balance = pb;
		
	}
	
	public void transactions() {
		
	}
	
}
