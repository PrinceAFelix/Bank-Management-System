package bankmanagementsystem;

import java.util.ArrayList;
import java.util.Date;

public class AtmTransaction {

	private int id;
	private String account;
	private String transaction_Date;
	private String transaction_Type;
	private float transaction_Amount;
	private float post_balance;

	private int index;

	public AtmTransaction() {

	}

	public AtmTransaction(int id, String account, String td, String tt, float ta, float pb) {
		this.id = id;
		this.account = account;
		this.transaction_Date = td;
		this.transaction_Type = tt;
		this.transaction_Amount = ta;
		this.post_balance = pb;

	}

	public void transactions(ArrayList<AtmTransaction> list) {

		if (list.isEmpty()) {
			System.out.println("\nNo transaction yet\n");
			return;
		}
		index = 1;
		System.out.println("\n\n\t\t\t\t\t\tTransactions");
		System.out.println(
				"<******************************************************************************************************>");

		System.out.printf("%10s%15s%22s%15s%17s%17s", "No.", "Date", "Account", "Type", "Amount", "Remaining");

		list.forEach((at) -> {
			System.out.printf("\n%8d%20s%19s%15s%17.2f%15.2f", index, at.transaction_Date, at.account,
					at.transaction_Type, at.transaction_Amount, at.post_balance);
			index++;
		});

		System.out.println(
				"\n<******************************************************************************************************>\n\n");
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the transaction_Date
	 */
	public String getTransaction_Date() {
		return transaction_Date;
	}

	/**
	 * @param transaction_Date the transaction_Date to set
	 */
	public void setTransaction_Date(String transaction_Date) {
		this.transaction_Date = transaction_Date;
	}

	/**
	 * @return the transaction_Type
	 */
	public String getTransaction_Type() {
		return transaction_Type;
	}

	/**
	 * @param transaction_Type the transaction_Type to set
	 */
	public void setTransaction_Type(String transaction_Type) {
		this.transaction_Type = transaction_Type;
	}

	/**
	 * @return the transaction_Amount
	 */
	public float getTransaction_Amount() {
		return transaction_Amount;
	}

	/**
	 * @param transaction_Amount the transaction_Amount to set
	 */
	public void setTransaction_Amount(float transaction_Amount) {
		this.transaction_Amount = transaction_Amount;
	}

	/**
	 * @return the post_balance
	 */
	public float getPost_balance() {
		return post_balance;
	}

	/**
	 * @param post_balance the post_balance to set
	 */
	public void setPost_balance(float post_balance) {
		this.post_balance = post_balance;
	}

}
