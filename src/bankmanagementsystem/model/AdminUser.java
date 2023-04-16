package bankmanagementsystem.model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminUser extends User {

	private static int index;
	private Scanner sc = new Scanner(System.in);

	AdminUser() {

	}

	public AdminUser(String id, String username, String password) {
		super(id, username, password);
	}

	public long addCustomer(ArrayList<User> users, String[] fields) {
		try {
			User temp = new User();
			long cardNum = temp.addUser(fields);
			users.add(temp);
			return cardNum;
		} catch (InputMismatchException ime) {
			return -1;
		} catch (Exception e) {
			return -1;
		}
		
	}

	public boolean deleteCustomer(ArrayList<User> users, User user) {
		try {

			if (viewCustomers(users) == false)
				return false;

			System.out.print("Enter the customer's ID: ");
			String userInput = sc.next();

			user.deleteUser(users, userInput);

			return true;

		} catch (InputMismatchException ime) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public void editCustomer(ArrayList<User> users, User user) {
		try {

			System.out.print("Enter the customer's ID: ");
			String userInput = sc.next();

			user.editUser(users, userInput, sc);

		} catch (InputMismatchException ime) {

		} catch (Exception e) {

		}
	}

	public void searchCustomer(ArrayList<User> users, User user) {
		try {

			System.out.print("Enter the customer's ID: ");
			String userInput = sc.next();

			user.searchUser(users, userInput);

		} catch (InputMismatchException ime) {

		} catch (Exception e) {

		}
	}

	public static boolean viewCustomers(ArrayList<User> list) {

		if (list.isEmpty()) {
			System.out.println("\nNo customers yet\n");
			return false;
		}
		index = 1;

		System.out.println("\n\n\t\t\t\t\tCustomers");
		System.out.println("<***************************************************************************************>");

		System.out.printf("%9s%7s%15s%17s%17s%17s", "No.", "ID", "Name", "email", "Phone Number", "Address");

		list.forEach((u) -> {
			System.out.printf("\n%8d%8s%15s%17s%17s%17s", index, u.getId(), u.getFullName(), u.getEmail(), u.getPhone(),
					u.getAddress());
			index++;
		});

		System.out.println(
				"\n<***************************************************************************************>\n\n");

		return true;

	}

}
