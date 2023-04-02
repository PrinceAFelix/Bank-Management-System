package bankmanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class User{

	protected int id;
	public String user_FullName;
	public String email;
	public String userPhone;
	public String password;
	public String username;
	public String userAddress;
	public long user_CardNumber;
	public ArrayList<UserAccount> userAccount;
	
	private static long idCounter = 0;
	
	User() {}
	
	User(int id, String fname, String e, String phone, String pwd, String usrn, String adr, long cnum){
		userAccount = new ArrayList<UserAccount>();
		UserAccount temp = new Chequing(0002, 200, "Chequing");

		this.userAccount.add(temp);

		this.id = id;
		this.user_FullName = fname;
		this.password = pwd;
		this.username = usrn;
		this.userAddress = adr;
		this.user_CardNumber = cnum;
	} 
	

	public boolean addCustomer(Scanner sc, int an, float ib) {
		
	    /* return a random long of 16 length */
	    long smallest = 1000_0000_0000_0000L;
	    long biggest =  9999_9999_9999_9999L;
		
		try {
			
			
			id = (int)idCounter++;
			System.out.print("Enter user First Name: ");
			String fname = sc.next();
			System.out.print("Enter user Last Name: ");
			String lname = sc.next();
			user_FullName = getFullName(fname, lname);
			
			System.out.print("Enter user email: ");
			email = sc.next();
			
			System.out.print("Enter user phone number: ");
			userPhone = sc.next();
			
			System.out.print("Enter user password: ");
			password = sc.next();
			
			System.out.print("Enter user desired username: ");
			username = sc.next();
			
			System.out.print("Enter user address: ");
			userAddress = sc.next();
			
			
			user_CardNumber = ThreadLocalRandom.current().nextLong(smallest, biggest+1);
			
			Chequing temp = new Chequing(an, ib, "Chequing");
			
			this.userAccount.add(temp);
			
			
			
			System.out.println("\nYour Card Number is: " + user_CardNumber +
					"\nPlease save this number for you'll be using this to log in along with your password");
		}catch(Exception e) {
			return false;
		}
		
		return true;
		
	}
	
	
	public boolean deleteCustomer(ArrayList<User> user, int userId) {
		
		//Learn Binary Search to search specific user
		
		try {
			for(int i = 0; i < user.size() - 1; i++) {
				if(user.get(i).id == userId)
					user.remove(i);
			}
		}catch(Exception e) {
			return false;
		}
		
		
		return true;
	}
	
	public boolean editCustomer(ArrayList<User> user, int userId) {
		
		return false;
	}
	
	
	public void searchCustomer() {
		
	}
	
	
	private String getFullName(String fname, String lname) {
		return fname + " " + lname;
	}

	
}
