package bankmanagementsystem;

import java.util.Scanner;

public class Chequing extends UserAccount {
	
	public String account_title;
	

	
	Chequing(){
		
	}

	Chequing(String acNumber, float acBal, String title) {
		super(acNumber, acBal, title);
		this.account_title = title;
	}
	
	public void view_Account(User user) {
		System.out.printf("----------------%s----------------", user.userAccount.get(0).accountTitle);
		System.out.printf("\nAccount Number: %s\n"
				+ "Accouunt Balance: %.2f\n", user.userAccount.get(0).accountNumber, user.userAccount.get(0).accountBalance);
		System.out.println("---------------------------------");
	}

	@Override
	public boolean addAccount(Scanner sc, User user) {
		
		if(user.userAccount.size() <= 2 && user.userAccount.get(0).accountTitle.equals("Chequing")) {
			System.out.println("\nAlready have a Chequing Account\n");
			return false;
		}
		
		System.out.print("Enter the amount you'd like to deposit into your new Account: ");
		float val = sc.nextFloat();
		
		Chequing temp = new Chequing(String.format("%04d", User.accountNumberCounter--), val, "Chequing");
		
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
