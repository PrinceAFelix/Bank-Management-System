package bankmanagementsystem;

import java.util.InputMismatchException;
import java.util.Scanner;

import bankmanagementsystem.controller.Bank;

public class BankManagementSystemTest {

	public static void main(String[] args) {

		// String atmNum, String c, String adr, String nm
		Bank b = new Bank("0001", "01", "123 Street", "Bank of PA");

		Scanner sc = new Scanner(System.in);
		int userChoice = 0;

		do {
			try {
				System.out.print("Welcome to Banko De Oro\n\n");

				// Sign in or create an account
				System.out.print("Please select one of the following:\n");
				System.out.print("\nSign in as\n1: Customer\n2: Admin\n3: exit\n\n> ");
				userChoice = sc.nextInt();

				switch (userChoice) {
				case 1:
					b.signIn(sc);
					break;
				case 2:
					b.getAccount(sc);
					break;
				case 3:
					return;
				default:
					System.out.println("\nInvalid Choice.. Please Try Again\n");
					break;

				}
			} catch (InputMismatchException ime) {
				System.err.flush();
				System.out.println("\nInput Mismatch Exception occured while selecting choicess\n");
				System.err.flush();
				sc.next();
			} catch (Exception ime) {
				System.err.flush();
				System.out.println("\nAn exception occured while selecting choices\n");
				System.err.flush();
				sc.next();
			}

		} while (true);

	}
}