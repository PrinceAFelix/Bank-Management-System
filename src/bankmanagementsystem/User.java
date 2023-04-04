package bankmanagementsystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class User{

	protected String id;
	public String user_FullName;
	public String email;
	public String userPhone;
	public String password;
	public String username;
	public String userAddress;
	public long user_CardNumber;
	public ArrayList<UserAccount> userAccount;
	public ArrayList<AtmTransaction> transactions;
	
	private static long idCounter = 0;
	public static long accountNumberCounter = 9999;
	
	int index;
	
	User() {
		userAccount = new ArrayList<UserAccount>();
		transactions = new ArrayList<AtmTransaction>();
	}
	
	User(String id, String fname, String e, String phone, String pwd, String usrn, String adr, long cnum){
		userAccount = new ArrayList<UserAccount>();
		UserAccount temp = new Chequing("5000", 200, "Chequing");
		this.userAccount.add(temp);
		transactions = new ArrayList<AtmTransaction>();
		
		
		this.id = id;
		this.user_FullName = fname;
		this.email = e;
		this.userPhone = phone;
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
			
			
			id = String.format("%04d", idCounter++);
			
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
			sc.nextLine();
			userAddress = sc.nextLine();
			
			
			user_CardNumber = ThreadLocalRandom.current().nextLong(smallest, biggest+1);
			
			Chequing temp = new Chequing(String.format("%04d", accountNumberCounter--), ib, "Chequing");
			
			this.userAccount.add(temp);
			
			
			
			System.out.println("\nYour Card Number is: " + user_CardNumber +
					"\nPlease save this number for you'll be using this to log in along with your password");
			
		}catch(Exception e) {
			return false;
		}
		
		return true;
		
	}
	
	
	public boolean deleteCustomer(ArrayList<User> user, String userId) {
		
		//For faster search -> Learn Binary Search to search specific user
		
		try {
			for(int i = 0; i < user.size(); i++) {
				if((user.get(i).id).equals(userId)) {
					user.remove(i);
					System.out.printf("\nSuccessfully dereister a customer with ID number: %s\n", userId);
					return true;
				}
			}
		}catch(Exception e) {
			return false;
		}
		
		System.out.printf("\nNo customer match with ID number: %s\n", userId);
		
		return false;
	}
	
	
	public boolean editCustomer(ArrayList<User> user, String userId,Scanner sc) {
		ArrayList<User> updatedUser = new ArrayList<User>();
		try {
			for(int i = 0; i < user.size(); i++) {
				if((user.get(i).id).equals(userId)) {
					
					String input = "";
					String confirmInput = "";
					
					do {
						
						
						try {
							
							System.out.print("Please select what you'd like to update\n\n"
									+ "1: Email\n"
									+ "2: Phone Number\n"
									+ "3: Password\n"
									+ "4: Username\n"
									+ "5: Address\n"
									+ "6: Exit\n"
									+ "> ");
							
							int userInput = sc.nextInt();
							
							switch(userInput) {
							case 1:
								System.out.print("Enter the new email: ");
								input = sc.next();
								System.out.print("Confirm the new email: ");
								confirmInput = sc.next();
								if(confirmInput.equals(input)) {
									user.get(i).email = confirmInput;
								}else {
									System.out.println("\n\nEmail you entered didn't match\n");
								}
								
								break;
							case 2:
								System.out.print("Enter the new phone number: ");
								input = sc.next();
								System.out.print("Confirm the new phone number: ");
								confirmInput = sc.next();
								if(confirmInput.equals(input)) {
									user.get(i).userPhone = confirmInput;
								}else {
									System.out.println("\n\nPhone Number you entered didn't match\n");
								}
								
								break;
							case 3:
								System.out.print("Enter the new password: ");
								input = sc.next();
								System.out.print("Confirm the new password: ");
								confirmInput = sc.next();
								if(confirmInput.equals(input)) {
									user.get(i).password = confirmInput;
								}else {
									System.out.println("\n\nPassword you entered didn't match\n");
								}
								
								break;
							case 4:
								System.out.print("Enter the new username: ");
								input = sc.next();
								System.out.print("Confirm the new username: ");
								confirmInput = sc.next();
								if(confirmInput.equals(input)) {
									user.get(i).username = confirmInput;
								}else {
									System.out.println("\n\nUsername you entered didn't match\n");
								}
								
								break;
							case 5:
								System.out.print("Enter the new address: ");
								sc.nextLine();
								input = sc.nextLine();
								System.out.print("Confirm the new address: ");
								confirmInput = sc.nextLine();
								if(confirmInput.equals(input)) {
									user.get(i).userAddress = confirmInput;
								}else {
									System.out.println("\n\nAddress you entered didn't match\n");
								}
								
								break;
							case 6:
								return true;
							default:
								break;
							
							}
							if(!updatedUser.contains(user.get(i))) {
								updatedUser.add(user.get(i));
							}
							
							
							
							displayUsers(updatedUser);
							
							 
						}catch(InputMismatchException ime) {
							
						}catch(Exception e) {
							
						}
						
						
						
					}while(true);
					
					
					
				}
			}
			
			
		}catch(Exception e) {
			return false;
		}
		
		System.out.printf("\nNo customer match with ID number: %s\n", userId);
		
		return false;
	}
	
	
	public boolean searchCustomer(ArrayList<User> user, String userId) {
		ArrayList<User> result = new ArrayList<User>();
		try {
			for(int i = 0; i < user.size(); i++) {
				if((user.get(i).id).equals(userId)) {
					result.add(user.get(i));
					displayUsers(result);
					return true;
				}
			}
			
			
		}catch(Exception e) {
			return false;
		}
		
		System.out.printf("\nNo customer match with ID number: %s\n", userId);
		
		return false;
		
	}
	
	
	private String getFullName(String fname, String lname) {
		return fname + " " + lname;
	}

	
	public boolean displayUsers(ArrayList<User> list) {
		
		if(list.isEmpty()) {
			System.out.println("\nNo customers yet\n");
			return false;
		}
		index = 1;


		System.out.println ("\n\n\t\t\t\t\tCustomers");
	    System.out.println("<***************************************************************************************>");
	    
	    System.out.printf ("%9s%7s%15s%17s%17s%17s", "No.", "ID", "Name","email", "Phone Number", "Address");
	    
	    list.forEach((u) -> {
	    	System.out.printf ("\n%8d%8s%15s%17s%17s%17s", index, u.id, u.user_FullName, u.email, u.userPhone, u.userAddress);
	    	index++;
	    });

	    System.out.println("\n<***************************************************************************************>\n\n");
	
	    return true;
	
	}

	
}
