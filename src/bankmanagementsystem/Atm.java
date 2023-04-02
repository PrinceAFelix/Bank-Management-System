package bankmanagementsystem;

public class Atm{
	
	public String atmLocation;

	Atm(){}
	
	Atm(String adr){
		this.atmLocation = adr;
	}
	
	public void deposit(User user, float amount, int depositForm) {

		user.userAccount.get(depositForm).accountBalance += amount;
		System.out.printf("\nSuccessfully deposit %.2f into your account\n\n", amount);
	}
	
	public void withdraw(User user, float amount, int withdrawFrom) {

		user.userAccount.get(withdrawFrom).accountBalance -= amount;
		System.out.printf("\nSuccessfully withdraw %.2f from your account\n\n", amount);
	}
	
	public void checkBalance(User user, int thisAccount) {

		System.out.printf("\nYour balance is: %.2f\n\n", user.userAccount.get(thisAccount).accountBalance);
	}
	
	public boolean validateActiveAccount(User user, int account) {
		if(account == 1 && user.userAccount.size() < 2) {
			System.out.println("\nYou Do not have a Savings Account\n");
			return false;
		}
		
		return true;
	}
}
