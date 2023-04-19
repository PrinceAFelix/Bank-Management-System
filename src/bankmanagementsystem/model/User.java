package bankmanagementsystem.model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import bankmanagementsystem.AtmTransaction;
import bankmanagementsystem.Chequing;
import bankmanagementsystem.Savings;
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

	private static long idCounter = 0;
	private static long accountNumberCounter;
	
	Random random = new Random();
	
	Components comp = new Components();

	int index;

	public User() {
		userAccount = new ArrayList<UserAccount>();
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
		
		
		setAccountNumberCounter(9999);
		this.userAccount = new ArrayList<UserAccount>();
		UserAccount temp = new Chequing("19836482", 200, "Chequing");
		UserAccount temp1 = new Savings("19836362", 200, "Savings");
		this.userAccount.add(temp);
		this.userAccount.add(temp1);
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

	public ArrayList<AtmTransaction> getTransactions(int account) {
		return transactions.get(account);
	}

	public long addUser(String[] fields) {

		/* return a random long of 16 length */
		long smallest = 1000_0000_0000_0000L;
		long biggest = 9999_9999_9999_9999L;

		try {

			
			setId(String.format("%04d", idCounter++));


			setFullName(getFullName(fields[0], fields[1]));

			setUsername(fields[2]);
			
			setEmail(fields[3]);

			setPhone(fields[4]);
			
			setAddress(fields[5]);
		
			if(fields[6].equals(fields[7])) {
				setPassword(fields[7]);
			}else {
				throw new Exception("Password Not Match");
			}

			setCardNumber(ThreadLocalRandom.current().nextLong(smallest, biggest + 1));

			Chequing temp = new Chequing(String.format("%04d", getAccountNumberCounter()), 500, "Chequing");
			
			this.userAccount.add(temp);
			

		} catch (Exception e) {
			return -1;
		}

		return getCardNumber();

	}

	public String[] deleteUser(ArrayList<User> user, String userId) {
		String[] deletedUser = new String[2];
		// For faster search -> Learn Binary Search to search specific user

		try {
			for (int i = 0; i < user.size(); i++) {
				if ((user.get(i).id).equals(userId)) {
					deletedUser[0] = user.get(i).getId();
					deletedUser[1] = user.get(i).getFullName();
					user.remove(i);
					System.out.printf("\nSuccessfully deregister a customer with ID number: %s\n", userId);
					return deletedUser;
				}
			}
		} catch (Exception e) {
			return deletedUser;
		}

		System.out.printf("\nNo customer match with ID number: %s\n", userId);

		return deletedUser;
	}

	public boolean editUser(ArrayList<User> user, String userId, Scanner sc) {
		ArrayList<User> updatedUser = new ArrayList<User>();
		try {
			for (int i = 0; i < user.size(); i++) {
				if ((user.get(i).id).equals(userId)) {

					String input = "";
					String confirmInput = "";

					do {

						try {

							System.out.print(
									"Please select what you'd like to update\n\n" + "1: Email\n" + "2: Phone Number\n"
											+ "3: Password\n" + "4: Username\n" + "5: Address\n" + "6: Exit\n" + "> ");

							int userInput = sc.nextInt();

							switch (userInput) {
							case 1:
								System.out.print("Enter the new email: ");
								input = sc.next();
								System.out.print("Confirm the new email: ");
								confirmInput = sc.next();
								if (confirmInput.equals(input)) {
									user.get(i).email = confirmInput;
								} else {
									System.out.println("\n\nEmail you entered didn't match\n");
								}

								break;
							case 2:
								System.out.print("Enter the new phone number: ");
								input = sc.next();
								System.out.print("Confirm the new phone number: ");
								confirmInput = sc.next();
								if (confirmInput.equals(input)) {
									user.get(i).phone = confirmInput;
								} else {
									System.out.println("\n\nPhone Number you entered didn't match\n");
								}

								break;
							case 3:
								System.out.print("Enter the new password: ");
								input = sc.next();
								System.out.print("Confirm the new password: ");
								confirmInput = sc.next();
								if (confirmInput.equals(input)) {
									user.get(i).password = confirmInput;
								} else {
									System.out.println("\n\nPassword you entered didn't match\n");
								}

								break;
							case 4:
								System.out.print("Enter the new username: ");
								input = sc.next();
								System.out.print("Confirm the new username: ");
								confirmInput = sc.next();
								if (confirmInput.equals(input)) {
									user.get(i).username = confirmInput;
								} else {
									System.out.println("\n\nUsername you entered didn't match\n");
								}

								break;
							case 5:
								System.out.print("Enter the new address: ");
								sc.nextLine();
								input = sc.nextLine();
								System.out.print("Confirm the new address: ");
								confirmInput = sc.nextLine();
								if (confirmInput.equals(input)) {
									user.get(i).address = confirmInput;
								} else {
									System.out.println("\n\nAddress you entered didn't match\n");
								}

								break;
							case 6:
								return true;
							default:
								break;

							}
							if (!updatedUser.contains(user.get(i))) {
								updatedUser.add(user.get(i));
							}

							AdminUser.viewCustomers(updatedUser);

						} catch (InputMismatchException ime) {

						} catch (Exception e) {

						}

					} while (true);

				}
			}

		} catch (Exception e) {
			return false;
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
