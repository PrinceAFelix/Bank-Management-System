package bankmanagementsystem;

import java.util.Scanner;

public class Chequing extends UserAccount {
	
	public String account_title;
	
	Chequing(){
		
	}

	Chequing(long acNumber, float acBal, String title) {
		super(acNumber, acBal, title);
		this.account_title = title;
	}
	
	public void view_Account() {
		
	}

	@Override
	public boolean addAccount(Scanner sc, User user) {
		
		if(user.userAccount.size() <= 2 && user.userAccount.get(0).accountTitle.equals("Chequing")) {
			System.out.println("\nAlready have a Chequing Account\n");
			return false;
		}
		
		System.out.print("Enter the amount you'd like to deposit into your new Account: ");
		float val = sc.nextFloat();
		
		Chequing temp = new Chequing(super.accountNumberGenerator(), val, "Chequing");
		
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
		// TODO Auto-generated method stub
		return true;
	}
	


}
