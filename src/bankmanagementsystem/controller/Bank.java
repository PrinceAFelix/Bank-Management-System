package bankmanagementsystem.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import bankmanagementsystem.Atm;
import bankmanagementsystem.AtmTransaction;
import bankmanagementsystem.Chequing;
import bankmanagementsystem.Savings;
import bankmanagementsystem.model.AdminUser;
import bankmanagementsystem.model.User;
import bankmanagementsystem.model.UserAccount;

public class Bank {

	public String atmNumber;
	public String code;
	public String address;
	public String name;

	private ArrayList<User> users = new ArrayList<User>(20);
	private AdminUser admin;

	User user = new User();

	Atm atm;

	UserAccount useraccount;

	String pattern = "MM-dd-yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	User initialUser = new User("0001", "John Doe", "doe@test.com", "123456789", "12345", "doe", "doe St", 123);

	Bank() {
		admin = new AdminUser("adm001", "admin", "admin");
	}

	public Bank(String atmNum, String c, String adr, String nm) {

		admin = new AdminUser("adm001", "admin", "admin");

		users.add(initialUser);
		this.atmNumber = atmNum;
		this.code = c;
		this.address = adr;
		this.name = nm;
	}

	public void getAccount(Scanner sc) {

		
		boolean isAdmin = false;

		do {

			if (isAdmin == false) {

				do {
					System.out.print("\nUsername: ");
					String userInput = sc.next();

					if (userInput.equals(admin.getUsername())) {
						do {
							System.out.print("Password: ");
							userInput = sc.next();

							if (userInput.equals(admin.getPassword())) {
								break;
							} else {
								System.out.println("Invalid password");
							}

						} while (true);
						isAdmin = !isAdmin;
						break;
					} else {
						System.out.println("Invalid username");
					}
				} while (isAdmin == false);
			}

			System.out.print("\nPlease select one of the following:\n");
			System.out.print(
					"1: Register a customer\n2: Unregister a customer\n3: Display customers\n4: Search customer\n5: Modify customer\n6: Exit\n\n> ");

			int userChoice = sc.nextInt();

			switch (userChoice) {
			case 1:
//				admin.addCustomer(users);
				break;
			case 2:
				admin.deleteCustomer(users, user, "0000");
				break;
			case 3:
				AdminUser.viewCustomers(users);
				break;
			case 4:
//				admin.searchCustomer(users, user);
				break;
			case 5:
//				admin.editCustomer(users, user);
				break;
			case 6:
				isAdmin = !isAdmin;
				return;
			default:
				System.out.println("\nInvalid Choice.. Please Try Again\n");
				break;

			}

		} while (true);

	}

	public void signIn(Scanner sc) {

		String userInput = "";
		String passwordInput = "";
		int userIndex = 0;

		boolean isValid = false;

		// Validating user
		do {

			if (users.isEmpty()) {
				System.out.println("No accounts in the data\nPlease contact the admin to register\n");
				break;
			}

			System.out.print("\nPlease enter your card number: ");
			userInput = sc.next();
			// Validate if card number is present

			for (int i = 0; i < users.size(); i++) {
				isValid = (Long.toString(users.get(i).getCardNumber()).equals(userInput));
				if (isValid)
					userIndex = getUser(i);
			}

			// If Card Number is Present -> Validate for password
			if (isValid) {
				System.out.print("Please enter your password: ");
				passwordInput = sc.next();

				// Validate password
				if (users.get(userIndex).verifyPassword(users.get(userIndex).getPassword(), passwordInput)) {
					manageAccount(sc, users.get(userIndex));
					return;
				}
			} else {
				System.out.println("Invalid card number");
			}

		} while (true);

	}

	private void manageAccount(Scanner sc, User activeUser) {
		AtmTransaction atmtransaction = new AtmTransaction();
		Random random = new Random();
		atm = new Atm(this.atmNumber);
		AtmTransaction transactionTemp = null;
		String date = simpleDateFormat.format(new Date());
		float value = 0;
		UserAccount tempAccount = null;

		int isChequing = 0;

		try {

			do {

				System.out.print("\nPlease select one of the following:\n");
				System.out.print(
						"\n1: Deposit\n2: Withdraw\n3: Check balance\n4: Open another account\n5: Delete an account\n6: View transaction\n7: View Accounts\n8: Sign out\n\n> ");
				int userChoice = sc.nextInt();

				switch (userChoice) {
				case 1:

					try {
						do {
							System.out.print("\nPlease select one of the following:\n");
							System.out.print("1: Chequing\n2: Savings\n3: Cancel\n\n> ");
							userChoice = sc.nextInt();

							if (userChoice == 3)
								break;
							switch (userChoice) {
							case 1:
								isChequing = 0;
								break;
							case 2:
								isChequing = 1;
								break;
							default:
								break;
							}

							if (atm.validateActiveAccount(activeUser, isChequing)) {
								System.out.print("Enter amount to deposit: ");
								value = sc.nextFloat();
								atm.deposit(activeUser, value, isChequing);
								transactionTemp = new AtmTransaction(random.nextInt(10000),
										userChoice == 1 ? "Chequing" : "Savings", date, "deposit", value,
										activeUser.getUserAccount().get(isChequing).accountBalance);
//								activeUser.getTransactions().add(transactionTemp);
							}

							break;

						} while (true);

					} catch (InputMismatchException ime) {
						System.err.flush();
						System.out.println("\nInput Mismatch Exception occured while selecting choices\n");
						System.err.flush();
						sc.next();
					}

					break;
				case 2:
					try {
						do {
							System.out.print("\nPlease select one of the following:\n");
							System.out.print("1: Chequing\n2: Savings\n3: Cancel\n\n> ");
							userChoice = sc.nextInt();

							if (userChoice == 3)
								break;

							switch (userChoice) {
							case 1:
								isChequing = 0;
								break;
							case 2:
								isChequing = 1;
								break;
							default:
								break;
							}
							if (atm.validateActiveAccount(activeUser, isChequing)) {
								System.out.print("Enter amount to withdraw: ");
								value = sc.nextFloat();
								atm.withdraw(activeUser, value, isChequing);
								;
								transactionTemp = new AtmTransaction(random.nextInt(10000),
										userChoice == 1 ? "Chequing" : "Savings", date, "withdraw", value,
										activeUser.getUserAccount().get(isChequing).accountBalance);
//								activeUser.getTransactions().add(transactionTemp);
							}

							break;

						} while (true);
					} catch (InputMismatchException ime) {
						System.err.flush();
						System.out.println("\nInput Mismatch Exception occured while selecting choices\n");
						System.err.flush();
						sc.next();
					}

					break;
				case 3:
					try {
						do {
							System.out.print("\nPlease select one of the following:\n");
							System.out.print("1: Chequing\n2: Savings\n3: Cancel\n\n> ");
							userChoice = sc.nextInt();

							if (userChoice == 3)
								break;

							switch (userChoice) {
							case 1:
								isChequing = 0;
								break;
							case 2:
								isChequing = 1;
								break;
							default:
								break;
							}

							if (atm.validateActiveAccount(activeUser, isChequing))
								atm.checkBalance(activeUser, isChequing);

							break;

						} while (true);

					} catch (InputMismatchException ime) {
						System.err.flush();
						System.out.println("\nInput Mismatch Exception occured while selecting choices\n");
						System.err.flush();
						sc.next();
					}
					break;
				case 4:
					do {
						System.out.print("\nPlease select an account you'd like to open:\n");
						System.out.print("1: Chequing\n2: Savings\n3: Cancel\n\n> ");
						userChoice = sc.nextInt();

						if (userChoice == 3)
							break;

						switch (userChoice) {
						case 1:
							tempAccount = new Chequing();
							break;
						case 2:
							tempAccount = new Savings();
							break;
						default:
							break;
						}

						tempAccount.addAccount(sc, activeUser);

						break;
					} while (true);

					break;
				case 5:

					do {
						System.out.print("\nPlease select an account you'd like to delete:\n");
						System.out.print("1: Chequing\n2: Savings\n3: Cancel\n\n> ");
						userChoice = sc.nextInt();

						if (userChoice == 3)
							break;

						switch (userChoice) {
						case 1:
							tempAccount = new Chequing();
							break;
						case 2:
							tempAccount = new Savings();
							break;
						default:
							break;
						}

						tempAccount.deleteAccount(sc, activeUser);

						break;
					} while (true);

					break;
				case 6:
//					atmtransaction.transactions(activeUser.getTransactions());
					break;
				case 7:
					try {
						do {
							System.out.print("\nPlease select an account you'd like to view:\n");
							System.out.print("1: Chequing\n2: Savings\n3: Cancel\n\n> ");
							userChoice = sc.nextInt();

							if (userChoice == 3)
								break;

							switch (userChoice) {
							case 1:
								Chequing temp = new Chequing();
								temp.view_Account(activeUser);
								break;
							case 2:
								Savings temp1 = new Savings();
								temp1.view_Account(activeUser);
								break;
							default:
								break;
							}

							break;
						} while (true);

					} catch (InputMismatchException ime) {
						System.err.flush();
						System.out.println("\nInput Mismatch Exception occured while selecting choices\n");
						System.err.flush();
						sc.next();
					}

					break;
				case 8:
					return;
				default:
					System.out.println("\nInvalid Choice.. Please Try Again\n");
					break;

				}

			} while (true);

		} catch (InputMismatchException ime) {
			System.err.flush();
			System.out.println("\nInput Mismatch Exception occured while selecting choices\n");
			System.err.flush();

		} catch (Exception ime) {
			System.err.flush();
			System.out.println("\nAn exception occured while selecting choices\n");
			System.err.flush();

		}

		return;

	}

	private int getUser(int i) {
		return i;
	}

}
