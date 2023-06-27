package bankmanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;

import bankmanagementsystem.model.User;
import bankmanagementsystem.model.UserAccount;

public class Chequing extends UserAccount {

	public String account_title;
	private ArrayList<AtmTransaction> transactions;

	public Chequing() {

	}

	public Chequing(String acNumber, float acBal, String title) {
		super(acNumber, acBal, title);
		this.account_title = title;
	}

	public void view_Account(ArrayList<UserAccount> accounts) {
		System.out.printf("\n----------------%s----------------", accounts.get(0).accountTitle);
		System.out.printf("\nAccount Number: %s\n" + "Accouunt Balance: %.2f\n",
				accounts.get(0).accountNumber, accounts.get(0).accountBalance);
		System.out.println("---------------------------------\n");
	}

	@Override
	public UserAccount addAccount(ArrayList<UserAccount> accounts, float val) {

		if (accounts.size() <= 2 && accounts.get(0).accountTitle.equals("Chequing")) {
			System.out.println("\nAlready have a Chequing Account\n");
			return null;
		}


		return new Chequing(String.format("%04d", User.getAccountNumberCounter()), val, "Chequing");

		//Perform Account insert

	
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
		// TODO Auto-generated method stub
		return true;
	}

}
