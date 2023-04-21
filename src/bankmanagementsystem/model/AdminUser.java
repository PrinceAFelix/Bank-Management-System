package bankmanagementsystem.model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

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

	public String[] deleteCustomer(ArrayList<User> users, User user, String id) {
		try {

			
			 return user.deleteUser(users, id);
			 
			  

		} catch (InputMismatchException ime) {
			return  Stream.<String>empty().toArray(String[]::new);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return  Stream.<String>empty().toArray(String[]::new);
		}
	}

	public void editCustomer(ArrayList<User> users, int key, User value) {
		try {
			users.set(key, value);
		} catch (InputMismatchException ime) {

		} catch (Exception e) {

		}
	}

	public int searchCustomer(ArrayList<User> users, User user, String id) {
		try {

			return user.searchUser(users, id);
			

		}  catch (Exception e) {
			return -1;
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