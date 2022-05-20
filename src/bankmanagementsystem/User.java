package bankmanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class User implements Comparable<User>{

	protected int id;
	public String user_FullName;
	public String email;
	public String userPhone;
	public String password;
	public String username;
	public String userAddress;
	public String user_CardNumber;
	
	private static long idCounter = 0;
	
	User(int id, String fname, String e, String phone, String pwd, String usrn, String adr, String cnum){
		this.id = id;
		this.user_FullName = fname;
		this.password = pwd;
		this.username = usrn;
		this.userAddress = adr;
		this.user_CardNumber = cnum;
	} 
	

	public void addCustomer(Scanner sc) {
		
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
		
		System.out.print("Enter user card number: ");
		user_CardNumber = sc.next();
		
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
	
	public void verifyCustomer() {
		
	}
	
	public String getFullName(String fname, String lname) {
		return fname + " " + lname;
	}


	@Override
	public int compareTo(User u) {
		if(this.id > u.id)
			return 1;
		else if(this.id < u.id)
			return -1;
		else return 0;
	}
	
	
	
	
}
