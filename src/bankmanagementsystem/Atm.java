package bankmanagementsystem;

import bankmanagementsystem.model.User;

public class Atm {

	public String atmLocation;

	public Atm() {
	}

	public Atm(String adr) {
		this.atmLocation = adr;
	}

	public boolean deposit(User user, float amount, int depositForm) {
		
		if(!validateActiveAccount(user, depositForm)) return false;
		
		user.getUserAccount().get(depositForm).accountBalance += amount;
		System.out.printf("\nSuccessfully deposit %.2f into your account\n\n", amount);
		
		return true;
	}

	public boolean withdraw(User user, float amount, int withdrawFrom) {
		if(!validateActiveAccount(user, withdrawFrom)) return false;
		
		user.getUserAccount().get(withdrawFrom).accountBalance -= amount;
		System.out.printf("\nSuccessfully withdraw %.2f from your account\n\n", amount);
		
		return true;
	}

	public void checkBalance(User user, int thisAccount) {

		System.out.printf("\nYour balance is: %.2f\n\n", user.getUserAccount().get(thisAccount).accountBalance);
	}

	public boolean validateActiveAccount(User user, int account) {

		if (account == 1 && user.getUserAccount().size() < 2) {
			System.out.println("\nYou Do not have a Savings Account\n");
			return false;
		}

		return true;
	}
}
