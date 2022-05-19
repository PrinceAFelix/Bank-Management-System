package bankmanagementsystem;

public class Savings extends UserAccount {

	
	public String account_title;
	
	Savings(String title){
		this.account_title = title;
	}

	Savings(int acNumber, double acBal) {
		super(acNumber, acBal);
	}
	
	public void view_Account() {
		
	}
	
}
