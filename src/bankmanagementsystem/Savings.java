package bankmanagementsystem;

import java.util.Scanner;

public class Savings extends UserAccount {

	
	public String account_title;
	
	Savings(){
		
	}

	Savings(long acNumber, float acBal, String title) {
		super(acNumber, acBal, title);
		this.account_title = title;
	}
	
	public void view_Account() {
		
	}

	@Override
	public boolean addAccount(Scanner sc, User user) {
		if(user.userAccount.size() == 2 && user.userAccount.get(1).accountTitle.equals("Savings")) {
			System.out.println("\nAlready have a Savings Account\n");
			return false;
		}
		
		System.out.print("Enter the amount you'd like to deposit into your new Account: ");
		float val = sc.nextFloat();
		
		Savings temp = new Savings(super.accountNumberGenerator(), val, "Savings");
		
		user.userAccount.add(temp);
		
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
		if(user.userAccount.size() == 2 && user.userAccount.get(1).accountTitle.equals("Savings")) {
			user.userAccount.remove(1);
			System.out.println("\nSuccessfully delete Svaings Account");
			return true;
		}
		
		
		return false;
		
	}
	
	
	
}