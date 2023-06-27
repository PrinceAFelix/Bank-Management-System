package bankmanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;

import bankmanagementsystem.model.User;
import bankmanagementsystem.model.UserAccount;

public class Savings extends UserAccount {

	public String account_title;
	private ArrayList<AtmTransaction> transactions;

	public Savings() {

	}

	public Savings(String acNumber, float acBal, String title) {
		super(acNumber, acBal, title);
		this.account_title = title;
	}

	public void view_Account(ArrayList<UserAccount> accounts) {

	

		System.out.printf("\n----------------%s----------------",accounts.get(1).accountTitle);
		System.out.printf("\nAccount Number: %s\n" + "Accouunt Balance: %.2f\n",
				accounts.get(1).accountNumber, accounts.get(1).accountBalance);
		System.out.println("---------------------------------\n");
	}

	@Override
	public UserAccount addAccount(ArrayList<UserAccount> accounts, float val) {
		if (accounts.size() == 2 && accounts.get(1).accountTitle.equals("Savings")) {
			System.out.println("\nAlready have a Savings Account\n");
			return null;
		}

		return new Savings(String.format("%04d", User.getAccountNumberCounter()), val, "Savings");

		

	

	}

//	@Override
//	public boolean updateAccount(Scanner sc, User user) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public boolean edit_account(Scanner sc, User user) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public boolean deleteAccount(User user) {
		
		if (user.getUserAccount().size() != 2) {
			System.out.println("\nYou do not have a Savings Account\n");
			return false;
		}
		
		user.getUserAccount().remove(1);
		System.out.println("\nSuccessfully delete Svaings Account");
		return true;

	}

	private boolean verifyAccount(User user) {

		return user.getUserAccount().size() <= 1 ? false : true;

	}

}