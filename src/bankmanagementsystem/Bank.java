package bankmanagementsystem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Bank {

	public String atmNumber;
	public String code;
	public String address;
	public String name;
	
	private ArrayList<User> users = new ArrayList<User>(20);
	
	User user = new User();
	
	Atm atm;

	
	UserAccount useraccount;
	
	String pattern = "MM-dd-yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


	
	
	
	User initialUser = new User("0001", "John Doe", "doe@test.com", "123456789", "12345", "doe", "doe St", 987625481918L);
	

	
	
	Bank(){
		
	}
	
	Bank(String atmNum, String c, String adr, String nm){
		users.add(initialUser);
		this.atmNumber = atmNum;
		this.code = c;
		this.address = adr;
		this.name = nm;
	}
	
	
	
	
	
	protected void getAccount(Scanner sc) {
		
		String admin = "admin";
		String password = "admin";
		Random random = new Random();
		boolean isAdmin = false;
		
		do {
			
			if(isAdmin == false) {
				
				do {
					System.out.print("Username: ");
					String userInput = sc.next();
					
					if(userInput.equals(admin)) {
						do {
							System.out.print("Password: ");
							userInput = sc.next();
							
							if(userInput.equals(password)) {
								break;
							}else {
								System.out.println("Invalid password");
							}
							
						}while(true);
						isAdmin = !isAdmin;
						break;
					}else {
						System.out.println("Invalid username");
					}
				}	while(isAdmin == false);
			}
			
			System.out.print("\nPlease select one of the following:\n");
			System.out.print("1: Register a customer\n2: Unregister a customer\n3: Display customers\n4: Exit \n\n> ");
			
			int userChoice = sc.nextInt();
			
			
			switch(userChoice) {
			case 1:
				try {
					User temp = new User();
					temp.addCustomer(sc, random.nextInt(10000), 500);
					users.add(temp);
				}catch(InputMismatchException ime) {
					
				}catch(Exception e) {
					
				}
				break;
			case 2:
				try {
					
					if(user.displayUsers(users) == false) break;
					
					System.out.print("Enter the customer's ID: ");
					String userInput = sc.next();
					
					user.deleteCustomer(users, userInput);
					 
				}catch(InputMismatchException ime) {
					
				}catch(Exception e) {
					
				}
				break;
			case 3:
				user.displayUsers(users);
				break;
			case 4:
				isAdmin = !isAdmin;
				return;
			default:
				System.out.println("\nInvalid Choice.. Please Try Again\n");
				break;
				
			}
			
			
		}while(true);
		
	}
	
	protected void signIn(Scanner sc) {
		
		String userInput = "";
		String passwordInput = "";
		int userIndex = 0;
		
		boolean isValid = false;
		
		
		//Validating user
		do {
			
			if(users.isEmpty()) {
				System.out.println("No accounts in the data\nPlease contact the admin to register\n");
				break;
			}
			
			System.out.print("Please enter your card number: ");
			userInput = sc.next();
			//Validate if card number is present
			
			for(int i = 0; i < users.size(); i++) {
				isValid = (Long.toString(users.get(i).user_CardNumber).equals(userInput));
				if(isValid) userIndex = getUser(i);
			}
			
			//If Card Number is Present -> Validate for password
			if(isValid) {
				System.out.print("Please enter your password: ");
				passwordInput = sc.next();
				
				//Validate password
				if((users.get(userIndex).password).equals(passwordInput)) {
					System.out.println("\nSuccess Sign in\n");
					manageAccount(sc, users.get(userIndex));
					break;
				}else {
					System.out.println("Wrong password");
				}
			}else {
				System.out.println("Invalid card number");
			}
			
		}while(true);
		
		
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
		
//		protected int id;
//		public Date transaction_Date;
//		public String transaction_Type;
//		public String transaction_Amount;
//		public float post_balance;

		
		do {
			
			System.out.print("Please select one of the following:\n");
			System.out.print("\n1: Deposit\n2: Withdraw\n3: Check balance\n4: Open another account\n5: Delete an account\n6: View transaction\n7: Sign out\n\n> ");
			int userChoice = sc.nextInt();
			
			switch(userChoice) {
			case 1:

				do {
					System.out.print("Please select one of the following:\n");
					System.out.print("1: Chequing\n2: Savings\n3: Cancel\n\n> ");
					userChoice = sc.nextInt();
					
					switch(userChoice) {
					case 1:
						isChequing = 0;
						break;
					case 2:
						isChequing = 1;
						break;
					case 3:
						return;
					default:
						break;
					}
					
					break;
					
				} while(true);
				if(!atm.validateActiveAccount(activeUser, isChequing)) break;
				System.out.print("Enter amount to deposit: ");
				value = sc.nextFloat();
				atm.deposit(activeUser, value, isChequing);
				transactionTemp = new AtmTransaction(random.nextInt(10000), date, "deposit", value, activeUser.userAccount.get(isChequing).accountBalance);
				activeUser.transactions.add(transactionTemp);
				break;
			case 2:
				do {
					System.out.print("Please select one of the following:\n");
					System.out.print("1: Chequing\n2: Savings\n3: Cancel\n\n> ");
					userChoice = sc.nextInt();
					
					switch(userChoice) {
					case 1:
						isChequing = 0;
						break;
					case 2:
						isChequing = 1;
						break;
					case 3:
						return;
					default:
						break;
					}
					
					break;
					
				} while(true);
				
				if(!atm.validateActiveAccount(activeUser, isChequing)) break;
				System.out.print("Enter amount to withdraw: ");
				value = sc.nextFloat();
				atm.withdraw(activeUser, value, isChequing);;
				transactionTemp = new AtmTransaction(random.nextInt(10000), date, "withdraw", value, activeUser.userAccount.get(isChequing).accountBalance);
				activeUser.transactions.add(transactionTemp);
				break;
			case 3:
				do {
					System.out.print("Please select one of the following:\n");
					System.out.print("1: Chequing\n2: Savings\n3: Cancel\n\n> ");
					userChoice = sc.nextInt();
					
					switch(userChoice) {
					case 1:
						isChequing = 0;
						break;
					case 2:
						isChequing = 1;
						break;
					case 3:
						return;
					default:
						break;
					}
					
					break;
					
				} while(true);
				if(!atm.validateActiveAccount(activeUser, isChequing)) break;
				atm.checkBalance(activeUser, isChequing);
				break;
			case 4:
				do {
					System.out.print("Please select an account you'd like to open:\n");
					System.out.print("1: Chequing\n2: Savings\n3: Cancel\n\n> ");
					userChoice = sc.nextInt();
					
					switch(userChoice) {
					case 1:
						tempAccount = new Chequing();
						break;
					case 2:
						tempAccount = new Savings();
						break;
					case 3:
						return;
					default:
						break;
					}
					
					tempAccount.addAccount(sc, activeUser);

					break;
				}while(true);
				
				break;
			case 5:
				
				do {
					System.out.print("Please select an account you'd like to open:\n");
					System.out.print("1: Chequing\n2: Savings\n3: Cancel\n\n> ");
					userChoice = sc.nextInt();
					
					switch(userChoice) {
					case 1:
						tempAccount = new Chequing();
						break;
					case 2:
						tempAccount = new Savings();
						break;
					case 3:
						return;
					default:
						break;
					}
					
					tempAccount.deleteAccount(sc, activeUser);

					break;
				}while(true);
				
				break;
			case 6:
				atmtransaction.transactions(activeUser.transactions);
				break;
			case 7:
				return;
			default:
				System.out.println("\nInvalid Choice.. Please Try Again\n");
				break;
				
			}
			
		}while(true);
	}
	
	
	private int getUser(int i) {
		return i;
	}
	
	
}
