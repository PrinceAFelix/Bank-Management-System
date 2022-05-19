package bankmanagementsystem;

public class UserAccount extends Bank{
	
	public int accountNumber;
	public double accountBalance;
	
	UserAccount(){}

	UserAccount(String atmNum, String c, String adr, String nm) {
		super(atmNum, c, adr, nm);
	}
	
	UserAccount(int acNumber, double acBal){
		this.accountNumber = acNumber;
		this.accountBalance = acBal;
	}
	
	
	public void updateAccount() {
		
	}
	
	public void addAccount() {
		
	}
	
	public void deleteAccount() {
		
	}
	
	public void searchAccount() {
		
	}
	
	

	
}
