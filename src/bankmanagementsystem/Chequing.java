package bankmanagementsystem;

public class Chequing extends UserAccount {
	
	public String account_title;
	
	Chequing(String title){
		this.account_title = title;
	}

	Chequing(int acNumber, double acBal) {
		super(acNumber, acBal);
	}
	
	public void view_Account() {
		
	}

}
