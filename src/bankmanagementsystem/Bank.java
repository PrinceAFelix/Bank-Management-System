package bankmanagementsystem;

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
	
	protected void getAccount() {
		
	}
	
}
