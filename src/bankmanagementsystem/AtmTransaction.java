package bankmanagementsystem;

public class AtmTransaction {

	protected int id;
	public String transaction_Date;
	public String transaction_Type;
	public String transaction_Amount;
	public String post_balance;
	
	AtmTransaction(int id, String td, String tt, String ta, String pb){
		this.id = id;
		this.transaction_Date = td;
		this.transaction_Type = tt;
		this.transaction_Amount = ta;
		this.post_balance = pb;
		
	}
	
	public void transaction() {
		
	}
	
}
