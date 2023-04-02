package bankmanagementsystem;

import java.util.ArrayList;
import java.util.Date;

public class AtmTransaction {

	protected int id;
	public String transaction_Date;
	public String transaction_Type;
	public float transaction_Amount;
	public float post_balance;
	
	
	private int index;
	
	AtmTransaction(){
		
	}
	AtmTransaction(int id, String td, String tt, float ta, float pb){
		this.id = id;
		this.transaction_Date = td;
		this.transaction_Type = tt;
		this.transaction_Amount = ta;
		this.post_balance = pb;
		
	}
	
	public void transactions(ArrayList<AtmTransaction> list) {
		
		if(list.isEmpty()) {
			System.out.println("\nNo transaction yet\n");
			return;
		}
		index = 1;
		System.out.println ("\n\n\t\t\t\tTransactions");
	    System.out.println("<***************************************************************************************>");
	    
	    System.out.printf ("%10s%15s%22s%17s%17s", "No.", "Date", "Account","Amount", "Remaining");
	    
	    list.forEach((at) -> {
	    	System.out.printf ("\n%8d%20s%19s%17.2f%15.2f", index, at.transaction_Date, at.transaction_Type, at.transaction_Amount, at.post_balance);
	    	index++;
	    });

	    System.out.println("\n<***************************************************************************************>\n\n");
	}
	
}
