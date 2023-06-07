package bankmanagementsystem.model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public abstract class UserAccount {

	public String accountNumber;
	public float accountBalance;
	public String accountTitle;

	public UserAccount() {
	}

	public UserAccount(String acNumber, float acBal, String at) {
		this.accountNumber = acNumber;
		this.accountBalance = acBal;
		this.accountTitle = at;
	}

	public abstract boolean addAccount(ArrayList<UserAccount> accounts, float val);

//	public abstract boolean updateAccount(Scanner sc, User user);

//	public abstract boolean edit_account(Scanner sc, User user);

	public abstract boolean deleteAccount(User user);

	protected long accountNumberGenerator() {
		long smallest = 1000_0000_0000_0000L;
		long biggest = 9999_9999_9999_9999L;

		return ThreadLocalRandom.current().nextLong(smallest, biggest + 1);
	}

}
