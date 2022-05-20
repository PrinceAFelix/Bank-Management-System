package bankmanagementsystem;

import java.util.ArrayList;

public class Bank {

	public String atmNumber;
	public String code;
	public String address;
	public String name;
	
	Bank(){}
	
	Bank(String atmNum, String c, String adr, String nm){
		this.atmNumber = atmNum;
		this.code = c;
		this.address = adr;
		this.name = nm;
	}
	
	private ArrayList<User> users = new ArrayList<User>();
	
	
	
	protected void getAccount() {
		System.out.println("Enter the details of the user");
		
	}
	
}
