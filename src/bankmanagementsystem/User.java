package bankmanagementsystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class User{

	private String id;
	private String fullName;
	private String email;
	private String phone;
	private String password;
	private String username;
	private String address;
	private long cardNumber;
	
	
	private ArrayList<UserAccount> userAccount;
	private ArrayList<AtmTransaction> transactions;
	
	
	private static long idCounter = 0;
	private static long accountNumberCounter;
	
	int index;
	
	
	User() {
		userAccount = new ArrayList<UserAccount>();
		transactions = new ArrayList<AtmTransaction>();
	}
	
	
	/**
	 * Admin
	 * 
	 * @param id
	 * @param username
	 * @param password
	 */
	User(String id, String username, String password){
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Normal User
	 * 
	 * @param id
	 * @param fname
	 * @param e
	 * @param phone
	 * @param pwd
	 * @param usrn
	 * @param adr
	 * @param cnum
	 */
	User(String id, String fname, String e, String phone, String pwd, String usrn, String adr, long cnum){
		setAccountNumberCounter(9999);
		userAccount = new ArrayList<UserAccount>();
		UserAccount temp = new Chequing("5000", 200, "Chequing");
		this.userAccount.add(temp);
		transactions = new ArrayList<AtmTransaction>();
		
		
		this.id = id;
		this.fullName = fname;
		this.email = e;
		this.phone = phone;
		this.password = pwd;
		this.username = usrn;
		this.address = adr;
		this.cardNumber = cnum;

	} 
	
	public ArrayList<UserAccount> getUserAccount(){
		return userAccount;
	}
	
	public ArrayList<AtmTransaction> getTransactions(){
		return transactions;
	}
	

	public boolean addUser(Scanner sc, float ib) {
		
	    /* return a random long of 16 length */
	    long smallest = 1000_0000_0000_0000L;
	    long biggest =  9999_9999_9999_9999L;
		
		try {
			
			
			id = String.format("%04d", idCounter++);
			
			System.out.print("Enter user First Name: ");
			String fname = sc.next();
			System.out.print("Enter user Last Name: ");
			String lname = sc.next();
			
			setFullName(getFullName(fname, lname));
			
			System.out.print("Enter user email: ");
			setEmail(sc.next());
			
			System.out.print("Enter user phone number: ");
			setPhone(sc.next());
			
			System.out.print("Enter user password: ");
			setPassword(sc.next());
			
			System.out.print("Enter user desired username: ");
			setUsername(sc.next());
			
			System.out.print("Enter user address: ");
			sc.nextLine();
			setAddress(sc.nextLine());
			
			
			
			setCardNumber(ThreadLocalRandom.current().nextLong(smallest, biggest+1));
			
			Chequing temp = new Chequing(String.format("%04d", getAccountNumberCounter()), ib, "Chequing");
			
			this.userAccount.add(temp);
			
			
			
			System.out.println("\nYour Card Number is: " + getCardNumber() +
					"\nPlease save this number for you'll be using this to log in along with your password");
			
		}catch(Exception e) {
			return false;
		}
		
		return true;
		
	}
	
	
	public boolean deleteUser(ArrayList<User> user, String userId) {
		
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
	
	
	public boolean editUser(ArrayList<User> user, String userId,Scanner sc) {
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
									user.get(i).phone = confirmInput;
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
									user.get(i).address = confirmInput;
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
							
							
							
							AdminUser.viewCustomers(updatedUser);
							
							 
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
	
	
	public boolean searchUser(ArrayList<User> user, String userId) {
		ArrayList<User> result = new ArrayList<User>();
		try {
			for(int i = 0; i < user.size(); i++) {
				if((user.get(i).id).equals(userId)) {
					result.add(user.get(i));
					AdminUser.viewCustomers(result);
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


	
	
	public boolean verifyPassword(String password, String inputpassword) {
		if((password).equals(inputpassword)) {
			System.out.println("\nSuccess Sign in\n");
			return true;
		}else {
			System.out.println("Wrong password");
			return false;
		}
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}


	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return the cardNumber
	 */
	public long getCardNumber() {
		return cardNumber;
	}


	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}


	/**
	 * @return the accountNumberCounter
	 */
	public static long getAccountNumberCounter() {
		return accountNumberCounter--;
	}


	/**
	 * @param accountNumberCounter the accountNumberCounter to set
	 */
	public static void setAccountNumberCounter(long accountNumberCounter) {
		User.accountNumberCounter = accountNumberCounter;
	}

	
}
