package bankmanagementsystem;

public class User {

	protected int id;
	public String user_FullName;
	public String email;
	public String userPhone;
	public String password;
	public String username;
	public String userAddress;
	public String user_CardNumber;
	
	User(int id, String fname, String e, String phone, String pwd, String usrn, String adr, String cnum){
		this.id = id;
		this.user_FullName = fname;
		this.password = pwd;
		this.username = usrn;
		this.userAddress = adr;
		this.user_CardNumber = cnum;
	} 
	
	
	public void addCustomer() {
		
	}
	
	
	public void deleteCustomer() {
		
	}
	
	public void editCustomer() {
		
	}
	
	
	public void searchCustomer() {
		
	}
	
	public void verifyCustomer() {
		
	}
	
	
	
	
}
