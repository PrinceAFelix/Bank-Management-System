package bankmanagementsystem;

import java.util.ArrayList;

import bankmanagementsystem.model.User;
import bankmanagementsystem.model.UserAccount;

public class Atm {

	public String atmLocation;

	public Atm() {
	}

	public Atm(String adr) {
		this.atmLocation = adr;
	}

	public boolean deposit(ArrayList<UserAccount> accounts, float amount, int depositForm) {
		
		if(!validateActiveAccount(accounts, depositForm)) return false;
		
		accounts.get(depositForm).accountBalance += amount;
		System.out.printf("\nSuccessfully deposit %.2f into your account\n\n", amount);
		
		return true;
	}

	public boolean withdraw(ArrayList<UserAccount> accounts, float amount, int withdrawFrom) {
		if(!validateActiveAccount(accounts, withdrawFrom)) return false;
		
		accounts.get(withdrawFrom).accountBalance -= amount;
		System.out.printf("\nSuccessfully withdraw %.2f from your account\n\n", amount);
		
		return true;
	}

	public void checkBalance(ArrayList<UserAccount> accounts, int thisAccount) {

		System.out.printf("\nYour balance is: %.2f\n\n", accounts.get(thisAccount).accountBalance);
	}

	public boolean validateActiveAccount(ArrayList<UserAccount> accounts, int account) {

		if (account == 1 && accounts.size() < 2) {
			System.out.println("\nYou Do not have a Savings Account\n");
			return false;
		}

		return true;
	}
}
