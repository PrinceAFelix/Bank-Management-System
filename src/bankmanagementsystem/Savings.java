package bankmanagementsystem;

import java.util.Scanner;

import bankmanagementsystem.model.User;
import bankmanagementsystem.model.UserAccount;

public class Savings extends UserAccount {

	public String account_title;

	public Savings() {

	}

	Savings(String acNumber, float acBal, String title) {
		super(acNumber, acBal, title);
		this.account_title = title;
	}

	public void view_Account(User user) {

		if (verifyAccount(user) == false) {
			System.out.println("\nYou don't have a savings account\n");
			return;
		}

		System.out.printf("\n----------------%s----------------", user.getUserAccount().get(1).accountTitle);
		System.out.printf("\nAccount Number: %s\n" + "Accouunt Balance: %.2f\n",
				user.getUserAccount().get(1).accountNumber, user.getUserAccount().get(1).accountBalance);
		System.out.println("---------------------------------\n");
	}

	@Override
	public boolean addAccount(Scanner sc, User user) {
		if (user.getUserAccount().size() == 2 && user.getUserAccount().get(1).accountTitle.equals("Savings")) {
			System.out.println("\nAlready have a Savings Account\n");
			return false;
		}

		System.out.print("\nEnter the amount you'd like to deposit into your new Account: ");
		float val = sc.nextFloat();

		UserAccount temp = new Savings(String.format("%04d", User.getAccountNumberCounter()), val, "Savings");

		user.getUserAccount().add(temp);

		return true;

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
	public boolean deleteAccount(Scanner sc, User user) {
		if (user.getUserAccount().size() == 2 && user.getUserAccount().get(1).accountTitle.equals("Savings")) {
			user.getUserAccount().remove(1);
			System.out.println("\nSuccessfully delete Svaings Account");
			return true;
		}

		return false;

	}

	private boolean verifyAccount(User user) {

		return user.getUserAccount().size() <= 1 ? false : true;

	}

}