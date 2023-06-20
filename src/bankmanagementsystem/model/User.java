package bankmanagementsystem.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import bankmanagementsystem.AtmTransaction;
import bankmanagementsystem.Chequing;
import bankmanagementsystem.Savings;
import bankmanagementsystem.service.JDBC;
import bankmanagementsystem.view.Components;

public class User {

	private String id;
	private String fullName;
	private String email;
	private String phone;
	private String password;
	private String username;
	private String address;
	private long cardNumber;
	private ArrayList<UserAccount> userAccount;
	private ArrayList<ArrayList<AtmTransaction>> transactions;

	private static long idCounter = 2;
	private static long accountNumberCounter = 9998;
	
	Random random = new Random();
	
	Components comp = new Components();

	int index;
	
	
	JDBC sqlConnect = new JDBC();


	public User() {
//		userAccount = new ArrayList<UserAccount>();
		transactions = new ArrayList<ArrayList<AtmTransaction>>();
	}

	/**
	 * Admin
	 * 
	 * @param id
	 * @param username
	 * @param password
	 */
	User(String id, String username, String password) {
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
	public User(String id, String fname, String e, String phone, String pwd, String usrn, String adr, long cnum) {
		
		
		setAccountNumberCounter(accountNumberCounter--);
//		this.userAccount = new ArrayList<UserAccount>();
//		UserAccount temp = new Chequing("19836482", 200, "Chequing");
//		UserAccount temp1 = new Savings("19836362", 200, "Savings");
//		this.userAccount.add(temp);
//		this.userAccount.add(temp1);
		this.transactions = new ArrayList<ArrayList<AtmTransaction>>();
		
		this.transactions.add(new ArrayList<AtmTransaction>());//Represents Chequing
		this.transactions.add(new ArrayList<AtmTransaction>());//Represents Savings


		this.id = id;
		this.fullName = fname;
		this.email = e;
		this.phone = phone;
		this.password = pwd;
		this.username = usrn;
		this.address = adr;
		this.cardNumber = cnum;

	}

	public ArrayList<UserAccount> getUserAccount() {
		return userAccount;
	}
	
	public ArrayList<UserAccount> setUserAccount( ArrayList<UserAccount> userAccount) {
		return this.userAccount = userAccount;
	}

	public ArrayList<AtmTransaction> getTransactions(int account) {
		
		
		 Collections.reverse(this.transactions.get(account));
		 
		 
		 return transactions.get(account);
	
	}

	public User addUser(String[] fields) {

		/* return a random long of 16 length */
		long smallest = 1000_0000_0000_0000L;
		long biggest = 9999_9999_9999_9999L;

		try {
			
			User user = new User();
			
			user.setId(String.format("%04d", idCounter++));


			user.setFullName(getFullName(fields[0], fields[1]));

			user.setUsername(fields[2]);
			
			user.setEmail(fields[3]);

			user.setPhone(fields[4]);
			
			user.setAddress(fields[5]);
		
			if(fields[6].equals(fields[7])) {
				user.setPassword(fields[7]);
			}else {
				throw new Exception("Password Not Match");
			}

			user.setCardNumber(ThreadLocalRandom.current().nextLong(smallest, biggest + 1));

			Chequing temp = new Chequing(String.format("%04d", getAccountNumberCounter()), 500, "Chequing");
			
//			user.userAccount.add(temp);
			
			System.out.println(user.getCardNumber());
	
			sqlConnect.insertUser(user.getId() ,user.getFullName(), user.getEmail(), user.getPhone(), user.getPassword(), user.getUsername(), user.getAddress(), String.valueOf(user.getCardNumber()), temp);
			
			return user;

		} catch (Exception e) {
			return null;
		}

		

	}

	public User deleteUser(String userId) {
		User deletedUser = new User();
		// For faster search -> Learn Binary Search to search specific user

		try {
			
			
			deletedUser = sqlConnect.getUser(userId);
			
			sqlConnect.deleteUser(userId);
		
			
			return deletedUser;
	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.printf("\nNo customer match with ID number: %s\n", userId);

		return deletedUser;
	}

	public boolean editUser(String[] fields, String userId) {
		
		
		try {
			sqlConnect.editCustomer(fields, userId);
			
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.printf("\nNo customer match with ID number: %s\n", userId);
		

		return false;
	}

	public int searchUser(ArrayList<User> user, String userId) {
		System.out.println(userId);
		try {
			for (int i = 0; i < user.size(); i++) {
				if ((user.get(i).id).equals(userId)) return i;
			}

		} catch (Exception e) {
			return -1;
		}

		System.out.printf("\nNo customer match with ID number: %s\n", userId);

		return -1;

	}

	private String getFullName(String fname, String lname) {
		return fname + " " + lname;
	}

	public boolean verifyPassword(String password, String inputpassword) {
		if ((password).equals(inputpassword)) {
			System.out.println("\nSuccess Sign in\n");
			return true;
		} else {
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
